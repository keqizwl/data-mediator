package com.heaven7.data.mediator.demo.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;

import com.heaven7.android.util2.LauncherIntent;
import com.heaven7.data.mediator.demo.R;
import com.heaven7.data.mediator.demo.testpackage.ClassBind;
import com.heaven7.data.mediator.demo.testpackage.ResultData;
import com.heaven7.data.mediator.demo.testpackage.TestBind;
import com.heaven7.java.data.mediator.DataMediator;
import com.heaven7.java.data.mediator.DataMediatorFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 测试 parcelable 数据的传输
 * Created by heaven7 on 2017/9/19 0019.
 */

public class TestParcelableDataActivity extends BaseActivity {

    public static final String KEY_DATA = "data";

    @BindView(R.id.bt_set_text_on_TextView)
    Button mBt_changeProperty;
    @BindView(R.id.bt_set_text_on_mediator)
    Button mBt_temp;

    private DataMediator<ClassBind> mClassMediator;

    @Override
    protected int getLayoutId() {
        return R.layout.ac_test_double_bind;
    }

    @Override
    protected void onInit(Context context, Bundle savedInstanceState) {
        mBt_changeProperty.setText("click this to test parcelable");
        mBt_temp.setVisibility(View.GONE);

        mClassMediator = DataMediatorFactory.createDataMediator(ClassBind.class);
    }

    @OnClick(R.id.bt_set_text_on_TextView)
    public void onClickSetTextOnTextView(View v){
        List<TestBind> list = new ArrayList<>();
        list.add(DataMediatorFactory.createData(TestBind.class)
                .setName("heaven7")
                .setData(new ResultData())
        );

        TestBind[] array= new TestBind[]{
                DataMediatorFactory.createData(TestBind.class)
                        .setName("heaven7")
                        .setData(new ResultData())
        };

        //拿到真正的数据 （只有真正的数据才能传输，否则数据会丢失）
        ClassBind module = mClassMediator.getData()
                .setName(6)
                .setData(new ResultData()) //继承的链式调用问题。。idea-plugin插件Bug
                .setStudent(DataMediatorFactory.createData(TestBind.class)
                        //添加 sparseArray 数据
                        .beginCityDataEditor()
                        .put(1, new ResultData())
                        .end())
                .setStudent2(list)
                .setStudent3(array);

        new LauncherIntent.Builder()
                .setClass(this, LogForParcelableActivity.class)
                .putExtra(KEY_DATA, (Parcelable) module)
                .build()
                .startActivity();
    }
}
