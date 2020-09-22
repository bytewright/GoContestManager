package de.bytewright;

import org.junit.Test;

import de.bytewright.contestManager.frontend.pages.HomePage;
import de.bytewright.testing.BaseWicketPageTest;

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
