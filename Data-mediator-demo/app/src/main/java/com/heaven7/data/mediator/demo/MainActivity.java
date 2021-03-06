package com.heaven7.data.mediator.demo;


import com.heaven7.data.mediator.demo.activity.TestAnalyseActivity;
import com.heaven7.data.mediator.demo.activity.TestChainCallActivity;
import com.heaven7.data.mediator.demo.activity.TestDoubleBindActivity;
import com.heaven7.data.mediator.demo.activity.TestParcelableDataActivity;
import com.heaven7.data.mediator.demo.activity.TestPropertyChangeActivity;
import com.heaven7.data.mediator.demo.activity.TestRecyclerListBind2Activity;
import com.heaven7.data.mediator.demo.activity.TestRecyclerListBindActivity;
import com.heaven7.data.mediator.demo.activity.TestSelfMethodWithImplInterface;
import com.heaven7.data.mediator.demo.activity.TestSparseArrayActivity;
import com.heaven7.data.mediator.demo.activity.TestTextViewBindActivity;
import com.heaven7.data.mediator.demo.activity.TestViewBindActivity;
import com.heaven7.java.data.mediator.DataMediatorFactory;
import com.heaven7.java.data.mediator.GlobalConfig;
import com.heaven7.java.data.mediator.GsonConfig;

import java.util.List;

import heaven7.test_compiler.TestItem;


/**
 * Created by heaven7 on 2017/7/12 0012.
 */

//全局设置
@GlobalConfig(
        gsonConfig = @GsonConfig(
                version = 2.0,
                forceDisable = false,
                generateJsonAdapter = true
        )
)
public class MainActivity extends AbsMainActivity {

    @Override
    protected void addDemos(List<ActivityInfo> list) {
        //just for test multi module
        DataMediatorFactory.createDataMediator(TestItem.class)
                .getDataProxy()
                .setTestItem_test("sdfsfsf");

        list.add(new ActivityInfo(TestPropertyChangeActivity.class, "Test Property Change"));
        list.add(new ActivityInfo(TestDoubleBindActivity.class, "test double bind"));
        list.add(new ActivityInfo(TestChainCallActivity.class, "test chain call"));
        list.add(new ActivityInfo(TestParcelableDataActivity.class, "Test Parcelable Data"));

        list.add(new ActivityInfo(TestViewBindActivity.class, "Test binder View property"));
        list.add(new ActivityInfo(TestTextViewBindActivity.class, "Test binder TextView property"));
        list.add(new ActivityInfo(TestRecyclerListBindActivity.class, "Test binder recycler list property"));
        list.add(new ActivityInfo(TestRecyclerListBind2Activity.class, "Test binder recycler list2"));
        list.add(new ActivityInfo(TestSparseArrayActivity.class, "Test sparse array callback"));
        list.add(new ActivityInfo(TestAnalyseActivity.class, "Test analyse"));
        list.add(new ActivityInfo(TestSelfMethodWithImplInterface.class, "Test self method/interface"));
    }
}
