
package com.example.android.mvvm.viewmodel;

import android.content.Context;

import com.example.android.mvvm.model.People;

import java.util.List;

public interface PeopleViewModelContract {

  interface MainView {

    Context getContext();

    void loadData(List<People> peoples);
  }

  interface ViewModel {

    void destroy();
  }
}
