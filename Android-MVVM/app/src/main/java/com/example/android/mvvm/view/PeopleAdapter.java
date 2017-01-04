
package com.example.android.mvvm.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.android.mvvm.R;
import com.example.android.mvvm.model.People;
import com.example.android.mvvm.viewmodel.ItemPeopleViewModel;
import com.example.android.mvvm.databinding.ItemPeopleBinding;

import java.util.Collections;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleAdapterViewHolder> {

  private List<People> peopleList;

  public PeopleAdapter() {
    this.peopleList = Collections.emptyList();
  }

  @Override public PeopleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ItemPeopleBinding itemPeopleBinding =
        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_people,
            parent, false);
    return new PeopleAdapterViewHolder(itemPeopleBinding);
  }

  @Override public void onBindViewHolder(PeopleAdapterViewHolder holder, int position) {
    holder.bindPeople(peopleList.get(position));
  }

  @Override public int getItemCount() {
    return peopleList.size();
  }

  public void setPeopleList(List<People> peopleList) {
    this.peopleList = peopleList;
    notifyDataSetChanged();
  }

  public static class PeopleAdapterViewHolder extends RecyclerView.ViewHolder {
    ItemPeopleBinding mItemPeopleBinding;

    public PeopleAdapterViewHolder(ItemPeopleBinding itemPeopleBinding) {
      super(itemPeopleBinding.itemPeople);
      this.mItemPeopleBinding = itemPeopleBinding;
    }

    void bindPeople(People people) {
      if (mItemPeopleBinding.getPeopleViewModel() == null) {
        mItemPeopleBinding.setPeopleViewModel(
            new ItemPeopleViewModel(people, itemView.getContext()));
      } else {
        mItemPeopleBinding.getPeopleViewModel().setPeople(people);
      }
    }
  }
}
