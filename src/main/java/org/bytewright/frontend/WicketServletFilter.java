package org.bytewright.frontend;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import org.apache.wicket.protocol.http.WicketFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WicketServletFilter extends WicketFilter {
  private static final Logger LOGGER = LoggerFactory.getLogger(WicketServletFilter.class);

  @Autowired
  public WicketServletFilter(WicketApplicationConfiguration application) {
    super(application);
    setFilterPath("/*");
  }

  @Override
  public void init(boolean isServlet, FilterConfig filterConfig) throws ServletException {
    LOGGER.info("Initialization of {} finished", this.getClass().getName());
  }
}
