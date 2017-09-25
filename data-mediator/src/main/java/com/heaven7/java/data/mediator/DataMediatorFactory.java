package com.heaven7.java.data.mediator;

import com.heaven7.java.data.mediator.util.PlatformDependent;

import java.lang.reflect.Constructor;

/**
 * Created by heaven7 on 2017/9/14 0014.
 * @since 1.0.4
 */
public final class DataMediatorFactory {

    private static final String  SUFFIX_INTERFACE = "Module";
    private static final String  SUFFIX_IMPL   = "_Impl";
    private static final String  SUFFIX_PROXY  = "_Proxy";

    /**
     * create module data for target interface class.
     * @param clazz the interface class.
     * @param <T> the module type. must be interface.
     * @return the module data.
     */
    public static <T> T createData(Class<T> clazz){
        String name = clazz.getName();
        if(name.endsWith(SUFFIX_INTERFACE)){
            name = name + SUFFIX_IMPL;
        }
        try {
            return (T) Class.forName(name).newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException("can't create module for class("+ clazz.getName()
                    + ")! have you make project or rebuild ? ", e);
        }
    }

    /**
     * create data mediator for target interface class.
     * @param clazz the interface class
     * @param <T> the module type
     * @return the proxy helper for target module type.
     */
    public static <T> DataMediator<T> createDataMediator(Class<T> clazz){
        String name = clazz.getName();
        if(name.endsWith(SUFFIX_INTERFACE)){
            name = name + SUFFIX_PROXY;
        }
        T t = createData(clazz);
        try {
            Class<?> proxyClazz = Class.forName(name);
            return new DataMediator<T>((BaseMediator<T>) proxyClazz.getConstructor(clazz).newInstance(t));
        } catch (Exception e) {
            throw new IllegalArgumentException("can't create module proxy for class("+ clazz.getName()
                    + ")! have you make project or rebuild ? " ,e);
        }
    }

    /**
     * wrap the module data to data mediator.
     * @param t  the module data.
     * @param <T> the module data type
     * @return the data mediator .
     * @see DataMediator
     */
    public static <T> DataMediator<T> wrapToDataMediator(T t){
        if(t instanceof DataMediator){
            return (DataMediator<T>) t;
        }
        if(t instanceof BaseMediator){
            return new DataMediator<T>((BaseMediator<T>) t);
        }
        String ImplName = t.getClass().getName();
        if(!ImplName.endsWith(SUFFIX_IMPL)){
            throw new IllegalArgumentException("target object(" + ImplName + ") can't wrap to DataMediator.");
        }
        final String interfaceName = ImplName.substring(0, ImplName.lastIndexOf(SUFFIX_IMPL));
        if(!interfaceName.endsWith(SUFFIX_INTERFACE)){
            throw new IllegalArgumentException("the argument isn't support.");
        }
        try {
            Class<?> proxyClazz = Class.forName(interfaceName + SUFFIX_PROXY);
            Constructor<?> constructor = proxyClazz.getConstructor(Class.forName(interfaceName));
            return new DataMediator<T>((BaseMediator<T>) constructor.newInstance(t));
        } catch (Exception e) {
            throw new IllegalArgumentException("can't create module proxy for class("+ ImplName
                    + ")! have you make project or rebuild ? " ,e);
        }
    }

    /**
     * create binder for target data mediator.
     * @param mediator the data mediator.
     * @param <T> the module data type
     * @return the binder.
     * @since 1.0.8
     */
    public static <T> Binder<T> createBinder(DataMediator<T> mediator) {
        if (PlatformDependent.isAndroid()) {
            try {
                final Class<?> clazz = Class.forName("com.heaven7.android.data.mediator.BinderSupplierImpl");
                IBinderSupplier<T> supplier = (IBinderSupplier<T>) clazz.newInstance();
                return supplier.create(mediator);
            } catch (Exception e) {
                throw new RuntimeException("create binder failed.", e);
            }
        }
        throw new UnsupportedOperationException("caused by currently only support android platform.");
    }
    /**
     * create binder for target module class..
     * @param moduleClass the module data class.
     * @param <T> the module data type
     * @return the binder.
     * @since 1.0.8
     */
    public static <T> Binder<T> createBinder(Class<T> moduleClass){
        return createBinder(createDataMediator(moduleClass));
    }


}
