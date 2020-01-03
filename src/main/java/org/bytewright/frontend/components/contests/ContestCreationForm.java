package org.bytewright.frontend.components.contests;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.services.ContestService;
import org.bytewright.frontend.pages.ContestCreationPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContestCreationForm extends Form<String> {
  private static final Logger LOGGER = LoggerFactory.getLogger(ContestCreationForm.class);
  private final TextField<String> name;
  private final TextField<String> uId;
  private final TextField<String> startDate;
  private final TextField<String> endDate;
  @SpringBean
  private ContestService contestService;

  public ContestCreationForm(String contentId) {
    super(contentId);
    name = new TextField<>("name", Model.of(""), String.class);
    uId = new TextField<>("uId", Model.of(""), String.class);
    startDate = new TextField<>("startDate", Model.of(""), String.class);
    endDate = new TextField<>("endDate", Model.of(""), String.class);
    add(name);
    add(uId);
    add(startDate);
    add(endDate);
  }

  @Override
  protected void onSubmit() {
    LOGGER.info("contest creation form submitted, adding new contest...");
    PageParameters parameters = new PageParameters();
    if (contestService.createContest()) {
      parameters.add("success", Boolean.TRUE);
    } else {
      parameters.add("success", Boolean.FALSE);
    }
    setResponsePage(ContestCreationPage.class, parameters);
  }
}
