

package com.example.android.mvvm.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.mvvm.R;
import com.example.android.mvvm.databinding.PeopleActivityBinding;
import com.example.android.mvvm.model.People;
import com.example.android.mvvm.viewmodel.PeopleViewModel;
import com.example.android.mvvm.viewmodel.PeopleViewModelContract;

import java.util.List;

public class PeopleActivity extends AppCompatActivity implements PeopleViewModelContract.MainView {

  private PeopleActivityBinding peopleActivityBinding;
  private PeopleViewModel peopleViewModel;
  private PeopleViewModelContract.MainView mainView = this;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    initDataBinding();
    setSupportActionBar(peopleActivityBinding.toolbar);
    setupListPeopleView(peopleActivityBinding.listPeople);
  }

  private void initDataBinding() {
    peopleActivityBinding = DataBindingUtil.setContentView(this, R.layout.people_activity);
    peopleViewModel = new PeopleViewModel(mainView, getContext());
    peopleActivityBinding.setMainViewModel(peopleViewModel);

  }

  private void setupListPeopleView(RecyclerView listPeople) {
    PeopleAdapter adapter = new PeopleAdapter();
    listPeople.setAdapter(adapter);
    listPeople.setLayoutManager(new LinearLayoutManager(this));
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    peopleViewModel.destroy();
  }

  @Override public Context getContext() {
    return PeopleActivity.this;
  }

  @Override public void loadData(List<People> peoples) {
    PeopleAdapter peopleAdapter = (PeopleAdapter) peopleActivityBinding.listPeople.getAdapter();
    peopleAdapter.setPeopleList(peoples);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.menu_github) {
      startActivityActionView();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void startActivityActionView() {
    startActivity(
        new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com")));
  }
}
