package org.bytewright.testing;

import org.apache.wicket.util.tester.WicketTester;
import org.bytewright.frontend.WicketApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * https://ci.apache.org/projects/wicket/guide/6.x/guide/testingspring.html
 *
 */
@ContextConfiguration(classes = SpringTestContext.class)
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class BaseWicketPageTest {

  @Autowired
  protected WicketApplication wicketApplication;

  protected WicketTester tester;

  @Before
  public void setUp() {
    tester = new WicketTester(wicketApplication);
  }
}
