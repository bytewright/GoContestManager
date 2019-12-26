package org.bytewright;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.bytewright.frontend.pages.HomePage;
import org.bytewright.testing.BaseWicketPageTest;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */

public class TestHomePage extends BaseWicketPageTest {
  @Test
  public void homepageRendersSuccessfully() {
    //start and render the test page
    tester.startPage(HomePage.class);

    //assert rendered page class
    tester.assertRenderedPage(HomePage.class);
  }
}
