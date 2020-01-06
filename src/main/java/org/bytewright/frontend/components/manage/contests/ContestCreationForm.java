package org.bytewright.frontend.components.manage.contests;

import org.apache.wicket.extensions.markup.html.form.datetime.ZonedDateTimeField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.services.ContestService;
import org.bytewright.frontend.pages.ContestCreationPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContestCreationForm extends Form<Contest> {
  private static final Logger LOGGER = LoggerFactory.getLogger(ContestCreationForm.class);
  private final Contest contest;
  @SpringBean
  private ContestService contestService;

  public ContestCreationForm(String contentId) {
    this(contentId, Model.of(new Contest()));
  }

  public ContestCreationForm(String id, IModel<Contest> model) {
    super(id, model);
    contest = getModelObject();
    add(new TextField<>("name", LambdaModel.of(contest::getName, contest::setName), String.class));
    add(new TextField<>("uId", LambdaModel.of(contest::getUniqueId, contest::setuId), String.class));
    add(new ZonedDateTimeField("startDate", LambdaModel.of(contest::getDateStart, contest::setDateStart)));
    add(new ZonedDateTimeField("endDate", LambdaModel.of(contest::getDateEnd, contest::setDateEnd)));
  }

  @Override
  protected void onSubmit() {
    LOGGER.info("contest creation form submitted, adding new contest: {}", contest);
    PageParameters parameters = new PageParameters();
    if (contestService.createContest()) {
      parameters.add("success", Boolean.TRUE);
    } else {
      parameters.add("success", Boolean.FALSE);
    }
    setResponsePage(ContestCreationPage.class, parameters);
  }
}
