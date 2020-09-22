package de.bytewright.contestManager.frontend.components.manage.contests;

import org.apache.wicket.extensions.markup.html.form.datetime.ZonedDateTimeField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.bytewright.contestManager.backend.persistence.dtos.Contest;
import de.bytewright.contestManager.backend.persistence.dtos.ContestSettings;
import de.bytewright.contestManager.backend.services.ContestService;
import de.bytewright.contestManager.frontend.pages.ContestCreationPage;

public class ContestCreationForm extends Form<Contest> {
  private static final Logger LOGGER = LoggerFactory.getLogger(ContestCreationForm.class);
  @SpringBean
  private ContestService contestService;

  public ContestCreationForm(String contentId) {
    this(contentId, Model.of(new Contest()));
  }

  public ContestCreationForm(String id, IModel<Contest> model) {
    super(id, model);
    Contest contest = getModelObject();
    ContestSettings settings = contest.getContestSettings();
    add(new TextField<>("name", LambdaModel.of(settings::getName, settings::setName), String.class));
    add(new TextField<>("uId", LambdaModel.of(contest::getUniqueId, contest::setuId), String.class));
    add(new ZonedDateTimeField("startDate", LambdaModel.of(settings::getDateStart, settings::setDateStart)));
    add(new ZonedDateTimeField("endDate", LambdaModel.of(settings::getDateEnd, settings::setDateEnd)));
  }

  @Override
  protected void onSubmit() {
    Contest contest = getModelObject();
    LOGGER.info("contest creation form submitted, adding new contest: {}", contest);
    PageParameters parameters = new PageParameters();
    if (contestService.createContest(contest)) {
      parameters.add("success", Boolean.TRUE);
    } else {
      parameters.add("success", Boolean.FALSE);
    }
    setResponsePage(ContestCreationPage.class, parameters);
  }
}
