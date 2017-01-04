

package com.example.android.mvvm.data;

import com.example.android.mvvm.model.People;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PeopleResponse {

  @SerializedName("results") private List<People> peopleList;

  public List<People> getPeopleList() {
    return peopleList;
  }

  public void setPeopleList(List<People> mPeopleList) {
    this.peopleList = mPeopleList;
  }
}
