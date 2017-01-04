

package com.example.android.mvvm.data;

import com.example.android.mvvm.model.Location;
import com.example.android.mvvm.model.Login;
import com.example.android.mvvm.model.People;
import com.example.android.mvvm.model.Picture;

import java.util.ArrayList;
import java.util.List;

public class FakeRandomUserGeneratorAPI {

  private static final String PEOPLE_CELL_TEST = "0177-6155420";
  private static final String PEOPLE_MAIL_TEST = "theodor.kaufmann@example.com";
  private static final String PEOPLE_PICTURE_TEST =
      "http://api.randomuser.me/portraits/women/39.jpg";
  private static final String PEOPLE_TITLE_TEST = "ms";
  private static final String PEOPLE_FIRST_TEST = "constance";
  private static final String PEOPLE_LAST_TEST = "fowler";
  private static final String PEOPLE_STREET_TEST = "9193 brock rd";
  private static final String PEOPLE_CITY_TEST = "flatrock";
  private static final String PEOPLE_STATE_TEST = "prince edward island";
  private static final String PEOPLE_USER_NAME_TEST = "organicgoose874";

  public static List<People> getPeopleList() {
    List<People> peoples = new ArrayList<>();
    for (int i = 0; i < 10; i++)
      peoples.add(getPeople());

    return peoples;
  }

  public static People getPeople() {
    People people = new People();
    people.picture = new Picture();
    people.location = new Location();
    people.userName = new Login();
    people.userName.userName = PEOPLE_USER_NAME_TEST;
    people.fullName = PEOPLE_TITLE_TEST + "." + PEOPLE_FIRST_TEST + " " + PEOPLE_LAST_TEST;
    people.cell = PEOPLE_CELL_TEST;
    people.mail = PEOPLE_MAIL_TEST;
    people.picture.large = PEOPLE_PICTURE_TEST;
    people.location.street = PEOPLE_STREET_TEST;
    people.location.city = PEOPLE_CITY_TEST;
    people.location.state = PEOPLE_STATE_TEST;
    return people;
  }
}
