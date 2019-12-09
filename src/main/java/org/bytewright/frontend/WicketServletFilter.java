package org.bytewright.frontend;

import org.apache.wicket.protocol.http.WicketFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WicketServletFilter extends WicketFilter {
  @Autowired
  public WicketServletFilter(WicketApplicationConfiguration application) {
    super(application);
    setFilterPath("/*");
  }
}
