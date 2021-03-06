/**
 * Copyright 2017
 group of data-mediator
 member: heaven7(donshine723@gmail.com)

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */
package com.heaven7.java.data.mediator;

import com.heaven7.java.base.util.Throwables;
import com.heaven7.java.data.mediator.util.DefaultEqualsComparator;
import com.heaven7.java.data.mediator.util.EqualsComparator;

import java.util.ArrayList;

/**
 * the base module mediator service. note the property of sub class.
 * all proxy class should extend this class.
 * Created by heaven7 on 2017/9/11 0011.
 */
public class BaseMediator<T> {

    private final ArrayList<DataMediatorCallback<? super T>> _mCallbacks;
    private final T _mTarget;
    private EqualsComparator _mEqualsComparator = DefaultEqualsComparator.getInstance();
    private DataConsumer<? super T> _mConsumer;
    private PropertyInterceptor _mInterceptor = PropertyInterceptor.NULL;
    //temps
    private SparseArrayDispatcher _mSparseArrayDispatcher;

    /**
     * create a base mediator by target object. called often by framework.
     *
     * @param target the target object which is ready to been act as agent by this.
     */
    public BaseMediator(T target) {
        if (target == null) {
            throw new NullPointerException();
        }
        this._mTarget = target;
        this._mCallbacks = new ArrayList<>();
    }

    /**
     * get the target object which is acted as agent by this object.
     *
     * @return the target object
     */
    protected T _getTarget() { //make _ to avoid property conflict
        return _mTarget;
    }

    /**
     * get the data consumer.
     * @return the data consumer
     * @since 1.1.2
     */
    protected DataConsumer<? super T> _getDataConsumer() {
        return _mConsumer;
    }

    /**
     * set the data consumer
     * @param mConsumer the data consumer
     * @since 1.1.2
     */
    protected void _setDataConsumer(DataConsumer<? super T> mConsumer) {
        this._mConsumer = mConsumer;
    }

    /**
     * get the equals comparator.
     *
     * @return the equals comparator.
     * @since 1.0.2
     */
    protected EqualsComparator _getEqualsComparator() {
        return _mEqualsComparator;
    }

    /**
     * set the equals comparator
     *
     * @param comparator the equals comparator
     * @since 1.0.2
     */
    protected void _setEqualsComparator(EqualsComparator comparator) {
        Throwables.checkNull(comparator);
        this._mEqualsComparator = comparator;
    }

    /**
     * get the property interceptor , default is {@linkplain PropertyInterceptor#NULL}
     * @return the property interceptor.
     * @since 1.1.3
     */
    protected PropertyInterceptor _getPropertyInterceptor() {
        return _mInterceptor;
    }

    /**
     * set the property interceptor . default is {@linkplain PropertyInterceptor#NULL}
     * @param interceptor the target property interceptor.
     * @since 1.1.3
     */
    protected void _setPropertyInterceptor(PropertyInterceptor interceptor) {
        Throwables.checkNull(interceptor);
        this._mInterceptor = interceptor;
    }

    /**
     * add  a data mediator callback
     *
     * @param o the data mediator callback
     */
    public synchronized void addCallback(DataMediatorCallback<? super T> o) {
        if (o == null)
            throw new NullPointerException();
        if (!_mCallbacks.contains(o)) {
            _mCallbacks.add(o);
        }
    }

    /**
     * remove the data mediator callback
     *
     * @param o the data mediator callback
     */
    public synchronized void removeCallback(DataMediatorCallback<? super T> o) {
        _mCallbacks.remove(o);
    }

    /**
     * remove all  data mediator callbacks
     */
    public synchronized void removeCallbacks() {
        _mCallbacks.clear();
    }

    /**
     * count the data mediator callback.
     *
     * @return the count of the data mediator callbacks.
     */
    public synchronized int countCallbacks() {
        return _mCallbacks.size();
    }

    /**
     * apply all current properties with current property interceptor.
     * And the default interceptor is {@linkplain PropertyInterceptor#NULL}
     *
     * @since 1.0.8
     */
    public final void applyProperties() {
        applyProperties(_mInterceptor);
    }

    /**
     * apply the all properties with target interceptor.
     *
     * @param interceptor the interceptor
     * @since 1.0.8
     */
    public void applyProperties(PropertyInterceptor interceptor) {
        Throwables.checkNull(interceptor);
        throw new UnsupportedOperationException("you must override this method.");
    }

    /**
     * apply the data to target consumer.
     * @param consumer the data consumer
     * @since 1.1.2
     */
    public void applyTo(DataConsumer<? super T> consumer){
        Throwables.checkNull(consumer);
        consumer.accept(_getTarget());
    }
    /**
     * apply the data to current consumer. so you should calla {@linkplain #_setDataConsumer(DataConsumer)} first.
     * @since 1.1.2
     */
    public void applyTo(){
        applyTo(_mConsumer);
    }

//=========================================================================

    /**
     * dispatch the change event to the callbacks.
     * <p>use {@linkplain #dispatchValueChanged(Property, Object, Object)} instead</p>
     *
     * @param prop     the property which is changed.
     * @param oldValue the old value of property
     * @param newValue the new value of property
     */
    @Deprecated
    public void dispatchCallbacks(Property prop, Object oldValue, Object newValue) {
        dispatchValueChanged(prop, oldValue, newValue);
    }

    /**
     * dispatch the change event of property.
     *
     * @param prop     the property which is changed.
     * @param oldValue the old value of property
     * @param newValue the new value of property
     * @since 1.0.8
     */
    public void dispatchValueChanged(Property prop, Object oldValue, Object newValue) {
        final DataMediatorCallback[] arrLocal = _getCallbacks();
        for (int i = arrLocal.length - 1; i >= 0; i--) {
            arrLocal[i].onPropertyValueChanged(_mTarget, prop, oldValue, newValue);
        }
    }

    /**
     * dispatch the apply event for target property.
     *
     * @param prop  the property
     * @param value the value of property.
     * @since 1.0.8
     */
    public void dispatchValueApplied(Property prop, Object value) {
        final DataMediatorCallback[] arrLocal = _getCallbacks();
        for (int i = arrLocal.length - 1; i >= 0; i--) {
            arrLocal[i].onPropertyApplied(_mTarget, prop, value);
        }
    }

    /**
     * dispatch the add event of property.
     *
     * @param prop       the property which is changed.
     * @param newValue   the newest value of property
     * @param addedValue the values which is added.
     * @since 1.0.8
     */
    public void dispatchAddValues(Property prop, Object newValue, Object addedValue) {
        final DataMediatorCallback[] arrLocal = _getCallbacks();
        for (int i = arrLocal.length - 1; i >= 0; i--) {
            arrLocal[i].onAddPropertyValues(_mTarget, prop, newValue, addedValue);
        }
    }

    /**
     * dispatch the add event with index of property.
     *
     * @param prop     the property which is changed.
     * @param newValue the newest value of property
     * @param addValue the values which is added.
     * @param index    the index to add.
     * @since 1.0.8
     */
    public void dispatchAddValuesWithIndex(Property prop, Object newValue, Object addValue, int index) {
        final DataMediatorCallback[] arrLocal = _getCallbacks();
        for (int i = arrLocal.length - 1; i >= 0; i--) {
            arrLocal[i].onAddPropertyValuesWithIndex(_mTarget, prop,
                    newValue, addValue, index);
        }
    }

    /**
     * dispatch the remove event of property.
     *
     * @param prop         the property which is changed.
     * @param newValue     the newest value of property
     * @param removeValues the values which is removed..
     * @since 1.0.8
     */
    public void dispatchRemoveValues(Property prop, Object newValue, Object removeValues) {
        final DataMediatorCallback[] arrLocal = _getCallbacks();
        for (int i = arrLocal.length - 1; i >= 0; i--) {
            arrLocal[i].onRemovePropertyValues(_mTarget, prop, newValue, removeValues);
        }
    }

    /**
     * dispatch item changed which is changed between old item and new item on target index.
     * @param prop the property of list items
     * @param oldItem the old item
     * @param newItem the new item
     * @param index the index.
     * @since 1.1.2
     */
    public void dispatchItemChanged(Property prop, Object oldItem, Object newItem , int index) {
        final DataMediatorCallback[] arrLocal = _getCallbacks();
        for (int i = arrLocal.length - 1; i >= 0; i--) {
            arrLocal[i].onPropertyItemChanged(_mTarget, prop, oldItem, newItem, index);
        }
    }

    /**
     * get callbacks/
     *
     * @return the callbacks.
     */
    private DataMediatorCallback[] _getCallbacks() {
        /*
         * a temporary array buffer, used as a snapshot of the state of
         * current Observers.
         */
        DataMediatorCallback[] arrLocal;

        synchronized (this) {
            arrLocal = _mCallbacks.toArray(new DataMediatorCallback[_mCallbacks.size()]);
        }
        return arrLocal;
    }
    /**
     * start batch applier.
     * @param interceptor the property  interceptor
     * @return the batch applier.
     * @since 1.0.8
     */
    protected BatchApplier<T> startBatchApply(PropertyInterceptor interceptor) {
        return new BatchApplier<>(this, interceptor);
    }

    /**
     * get the callback dispatcher of SparseArray
     * @return the callback dispatcher of SparseArray
     * @see com.heaven7.java.base.util.SparseArray
     * @since 1.1.3
     */
    /*protected*/ SparseArrayDispatcher _getSparseArrayDispatcher(){
        if(_mSparseArrayDispatcher == null){
            _mSparseArrayDispatcher = new SparseArrayDispatcher();
        }
        return _mSparseArrayDispatcher;
    }

    /**
     * dispatch callback of SparseArray.
     * @since 1.1.3
     * @see com.heaven7.java.base.util.SparseArray
     */
    public class SparseArrayDispatcher{

        private SparseArrayDispatcher(){}
        /**
         * dispatch add entry event.
         * @param prop the property
         * @param key the key of entry
         * @param value the value of entry
         */
        public void dispatchAddEntry(Property prop, int key, Object value){
            final DataMediatorCallback[] callbacks = _getCallbacks();
            final T target = _getTarget();
            for (int i = callbacks.length - 1; i >= 0; i--) {
                final SparseArrayPropertyCallback callback = callbacks[i].getSparseArrayPropertyCallback();
                if(callback != null) {
                    callback.onAddEntry(target, prop, key, value);
                }
            }
        }
        /**
         * dispatch change entry value.
         * @param prop the property
         * @param key the key of entry
         * @param oldValue the old value of entry
         * @param newValue the new value of entry
         */
        public void dispatchChangeEntryValue(Property prop, int key, Object oldValue, Object newValue){
            final DataMediatorCallback[] callbacks = _getCallbacks();
            final T target = _getTarget();
            for (int i = callbacks.length - 1; i >= 0; i--) {
                final SparseArrayPropertyCallback callback = callbacks[i].getSparseArrayPropertyCallback();
                if(callback != null) {
                    callback.onEntryValueChanged(target, prop, key, oldValue, newValue);
                }
            }
        }
        /**
         * dispatch remove entry event.
         * @param prop the property
         * @param key the key of entry
         * @param value the value of entry
         */
        public void dispatchRemoveEntry(Property prop, int key, Object value){
            final DataMediatorCallback[] callbacks = _getCallbacks();
            final T target = _getTarget();
            for (int i = callbacks.length - 1; i >= 0; i--) {
                final SparseArrayPropertyCallback callback = callbacks[i].getSparseArrayPropertyCallback();
                if(callback != null) {
                    callback.onRemoveEntry(target, prop, key, value);
                }
            }
        }
        /**
         * dispatch clear all entries.
         * @param prop the property
         * @param entries the all entries which were  removed.
         *                type is {@linkplain com.heaven7.java.base.util.SparseArray}.
         */
        public void dispatchClearEntries(Property prop, Object entries){
            final DataMediatorCallback[] callbacks = _getCallbacks();
            final T target = _getTarget();
            for (int i = callbacks.length - 1; i >= 0; i--) {
                final SparseArrayPropertyCallback callback = callbacks[i].getSparseArrayPropertyCallback();
                if(callback != null) {
                    callback.onClearEntries(target, prop, entries);
                }
            }
        }
    }
    /**
     * the batch applier.
     * @param <T> the module data type
     * @since 1.0.8
     */
    public static class BatchApplier<T> {
        private final ArrayList<Property> mProps;
        private final ArrayList<Object> mValue;
        private final PropertyInterceptor mInterceptor;
        private final BaseMediator<T> mMediator;

        /*public*/ BatchApplier(BaseMediator<T> mediator, PropertyInterceptor interceptor) {
            this.mMediator = mediator;
            this.mInterceptor = interceptor;
            mProps = new ArrayList<>();
            mValue = new ArrayList<>();
        }

        /**
         * add property with it's value.
         * @param prop the property
         * @param value the value of property
         * @return this.
         */
        public BatchApplier addProperty(Property prop, Object value) {
            Throwables.checkNull(prop);
            if(!mInterceptor.shouldIntercept(mMediator._getTarget(), prop, value)) {
                this.mProps.add(prop);
                this.mValue.add(value);
            }
            return this;
        }

        /**
         * batch apply the properties with theirs' value.
         */
        public void apply() {
            final T data = mMediator._getTarget();
            final DataMediatorCallback[] arrLocal = mMediator._getCallbacks();
            final int size = mProps.size();

            DataMediatorCallback callback;
            for (int i = arrLocal.length - 1; i >= 0; i--) {
                callback = arrLocal[i];
                for (int j = 0; j < size; j++) {
                    callback.onPropertyApplied(data, mProps.get(j), mValue.get(j));
                }
            }
        }
    }
}
