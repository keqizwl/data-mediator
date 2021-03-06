package com.heaven7.plugin.idea.data_mediator.test;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.heaven7.java.base.util.Objects;
import com.heaven7.java.base.util.SparseArray;
import com.heaven7.java.data.mediator.ListPropertyEditor;
import com.heaven7.java.data.mediator.SparseArrayPropertyEditor;
import com.heaven7.java.data.mediator.support.gson.TypeHandler;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>This class is generated by Data-mediator framework. DON'T EDIT IT!!!</p>
 *  @author heaven7 */
@JsonAdapter($TestItem$TypeAdapter.class)
public class TestItem_$Impl extends FlowItem_$Impl implements TestItem, FlowItem {
  static {
    TypeHandler.registerTypeAdapter(TestItem_$Impl.class, new $TestItem$TypeAdapter());
  }

  @Since(1.2)
  @Until(2.0)
  private Student testItem_1;

  private String testItem_2;

  private List<String> testItem_3;

  private boolean testItem_4;

  private int testItem_5;

  private TestParcelableData testItem_6;

  public TestItem_$Impl() {
  }

  @Override
  public void clearProperties() {
    super.clearProperties();
    this.testItem_4 = false;
    this.testItem_5 = 0;
    this.testItem_1 = null;
    this.testItem_2 = null;
    this.testItem_3 = null;
    this.testItem_6 = null;
  }

  @Override
  public String toString() {
    Objects.ToStringHelper helper = Objects.toStringHelper(this)
        .add("testItem_1", String.valueOf(this.testItem_1))
        .add("testItem_2", String.valueOf(this.testItem_2))
        .add("testItem_3", String.valueOf(this.testItem_3))
        .add("testItem_4", String.valueOf(this.testItem_4))
        .add("testItem_5", String.valueOf(this.testItem_5))
        .add("testItem_6", String.valueOf(this.testItem_6));
    return helper.toString() + "#_@super_#" + super.toString();
  }

  public Student getTestItem_1() {
    return testItem_1;
  }

  public TestItem setTestItem_1(Student testItem_11) {
    this.testItem_1 = testItem_11;
    return this;
  }

  public String getTestItem_2() {
    return testItem_2;
  }

  public TestItem setTestItem_2(String testItem_21) {
    this.testItem_2 = testItem_21;
    return this;
  }

  public List<String> getTestItem_3() {
    return testItem_3;
  }

  public TestItem setTestItem_3(List<String> testItem_31) {
    this.testItem_3 = testItem_31;
    return this;
  }

  public ListPropertyEditor<? extends TestItem, String> beginTestItem_3Editor() {
    if (testItem_3 == null) {
      testItem_3 = new ArrayList<>();
    }
    return new ListPropertyEditor<TestItem,String>(this, testItem_3, null, null);
  }

  public boolean isTestItem_4() {
    return testItem_4;
  }

  public TestItem setTestItem_4(boolean testItem_41) {
    this.testItem_4 = testItem_41;
    return this;
  }

  public int getTestItem_5() {
    return testItem_5;
  }

  public TestItem setTestItem_5(int testItem_51) {
    this.testItem_5 = testItem_51;
    return this;
  }

  public TestParcelableData getTestItem_6() {
    return testItem_6;
  }

  public TestItem setTestItem_6(TestParcelableData testItem_61) {
    this.testItem_6 = testItem_61;
    return this;
  }

  public TestItem setXxx1(int xxx11) {
    return (TestItem)super.setXxx1(xxx11);
  }

  public TestItem setDesc(List<String> desc1) {
    return (TestItem)super.setDesc(desc1);
  }

  public ListPropertyEditor<? extends TestItem, String> beginDescEditor() {
    return (ListPropertyEditor<? extends TestItem, String>) super.beginDescEditor();
  }

  public TestItem setXxx4(int[] xxx41) {
    return (TestItem)super.setXxx4(xxx41);
  }

  public TestItem setId(Student id1) {
    return (TestItem)super.setId(id1);
  }

  public TestItem setXxx5(Integer[] xxx51) {
    return (TestItem)super.setXxx5(xxx51);
  }

  public TestItem setXxx2(Integer xxx21) {
    return (TestItem)super.setXxx2(xxx21);
  }

  public TestItem setSelected(boolean selected1) {
    return (TestItem)super.setSelected(selected1);
  }

  public TestItem setName(String name1) {
    return (TestItem)super.setName(name1);
  }

  public TestItem setXxx3(SparseArray<Integer> xxx31) {
    return (TestItem)super.setXxx3(xxx31);
  }

  public SparseArrayPropertyEditor<? extends TestItem, Integer> beginXxx3Editor() {
    return (SparseArrayPropertyEditor<? extends TestItem, Integer>) super.beginXxx3Editor();
  }

  public void test() {
    Student.test( this );
  }

  public void _test2() {
    Student.test2( this );
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (getTestItem_1() != null ? getTestItem_1().hashCode() : 0);
    result = 31 * result + (getTestItem_2() != null ? getTestItem_2().hashCode() : 0);
    result = 31 * result + (getTestItem_3() != null ? getTestItem_3().hashCode() : 0);
    result = 31 * result + (isTestItem_4() ? 1 : 0);
    result = 31 * result + getTestItem_5();
    result = 31 * result + (getTestItem_6() != null ? getTestItem_6().hashCode() : 0);
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof TestItem_$Impl)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
     TestItem_$Impl that = (TestItem_$Impl) o;
    if (getTestItem_1() != null ? !getTestItem_1().equals(that.getTestItem_1()) : that.getTestItem_1() != null) {
      return false;
    }
    if (getTestItem_2() != null ? !getTestItem_2().equals(that.getTestItem_2()) : that.getTestItem_2() != null) {
      return false;
    }
    if (getTestItem_3() != null ? !getTestItem_3().equals(that.getTestItem_3()) : that.getTestItem_3() != null) {
      return false;
    }
    if (isTestItem_4() != that.isTestItem_4()) {
      return false;
    }
    if (getTestItem_5() != that.getTestItem_5()) {
      return false;
    }
    if (getTestItem_6() != null ? !getTestItem_6().equals(that.getTestItem_6()) : that.getTestItem_6() != null) {
      return false;
    }
    return true;
  }
}
