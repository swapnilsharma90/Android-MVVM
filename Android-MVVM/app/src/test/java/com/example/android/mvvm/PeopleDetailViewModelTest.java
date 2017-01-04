
package com.example.android.mvvm;

import android.view.View;

import com.example.android.mvvm.model.People;
import com.example.android.mvvm.viewmodel.PeopleDetailViewModel;
import com.example.android.mvvm.data.FakeRandomUserGeneratorAPI;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

/**
 * Notes for Mac!!
 * <p/>
 * If you are on a Mac, you will probably need to configure the
 * default JUnit test runner configuration in order to work around a bug where IntelliJ / Android
 * Studio
 * does not set the working directory to the module being tested. This can be accomplished by
 * editing
 * the run configurations, Defaults -> JUnit and changing the working directory value to
 * $MODULE_DIR$
 * <p/>
 * You have to specify  sdk < 23 (Robolectric does not support API level 23.)
 * <p/>
 * https://github.com/robolectric/robolectric/issues/1648
 **/

@RunWith(RobolectricGradleTestRunner.class) @Config(constants = BuildConfig.class, sdk = 21)
public class PeopleDetailViewModelTest {

  private PeopleDetailViewModel peopleDetailViewModel;
  private People people;

  @Before public void setUpDetailViewModelTest() {
    people = FakeRandomUserGeneratorAPI.getPeople();
    peopleDetailViewModel = new PeopleDetailViewModel(people);
  }

  @Test public void shouldGetURLPictureProfile() throws Exception {
    assertEquals(people.picture.large, peopleDetailViewModel.getPictureProfile());
  }

  @Test public void shouldGetUserName() throws Exception {
    assertEquals(people.userName.userName, peopleDetailViewModel.getUserName());
  }

  @Test public void shouldGetCell() throws Exception {
    assertEquals(people.cell, peopleDetailViewModel.getCell());
  }

  @Test public void shouldGetMail() throws Exception {
    assertEquals(people.mail, peopleDetailViewModel.getEmail());
  }

  @Test public void shouldGetGender() throws Exception {
    assertEquals(people.gender, peopleDetailViewModel.getGender());
  }

  @Test public void shouldGetAddress() throws Exception {
    String fakeAddress =
        people.location.street + " " + people.location.city + " " + people.location.state;
    assertEquals(fakeAddress, peopleDetailViewModel.getAddress());
  }

  @Test public void givenTheEmailVisibilityVisible() throws Exception {
    assertEquals(View.VISIBLE, peopleDetailViewModel.getEmailVisibility());
  }

  @Test public void givenTheEmailVisibilityGone() throws Exception {
    people.mail = null;
    assertEquals(View.GONE, peopleDetailViewModel.getEmailVisibility());
  }
}
