
package com.example.android.mvvm;

import android.view.View;

import com.example.android.mvvm.data.PeopleService;
import com.example.android.mvvm.model.People;
import com.example.android.mvvm.viewmodel.PeopleViewModel;
import com.example.android.mvvm.viewmodel.PeopleViewModelContract;
import com.example.android.mvvm.data.FakeRandomUserGeneratorAPI;
import com.example.android.mvvm.databinding.PeopleActivityBinding;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.List;

import rx.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

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

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, manifest = Config.NONE)
public class PeopleViewModelTest {

  private static final String URL_TEST = "http://api.randomuser.me/?results=10&nat=en";

  @Mock private PeopleService peopleService;

  @Mock private PeopleViewModelContract.MainView mainView;

  private PeopleViewModel peopleViewModel;

  @Mock private PeopleActivityBinding peopleActivityBinding;

  @Before public void setUpMainViewModelTest() {
    // inject the mocks
    MockitoAnnotations.initMocks(this);

    // Mock the PeopleService so we don't call the Random User Generator API (we are simulating only a call to the api)
    // and all observables will now run on the same thread
    PeopleApplication peopleApplication = (PeopleApplication) RuntimeEnvironment.application;
    peopleApplication.setPeopleService(peopleService);
    peopleApplication.setScheduler(Schedulers.immediate());

    peopleViewModel = new PeopleViewModel(mainView, peopleApplication);
  }

  @Test public void simulateGivenTheUserCallListFromApi() throws Exception {
    List<People> peoples = FakeRandomUserGeneratorAPI.getPeopleList();
    doReturn(rx.Observable.just(peoples)).when(peopleService).fetchPeople(URL_TEST);
  }

  @Test public void ensureTheViewsAreInitializedCorrectly() throws Exception {
    peopleViewModel.initializeViews();
    assertEquals(View.GONE, peopleViewModel.peopleLabel.get());
    assertEquals(View.GONE, peopleViewModel.peopleList.get());
    assertEquals(View.VISIBLE, peopleViewModel.peopleProgress.get());
  }
}
