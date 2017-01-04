

package com.example.android.mvvm.data;


import retrofit2.http.GET;
import retrofit2.http.Url;
import rx.Observable;

public interface PeopleService {
  @GET Observable<PeopleResponse> fetchPeople(@Url String url);
}
