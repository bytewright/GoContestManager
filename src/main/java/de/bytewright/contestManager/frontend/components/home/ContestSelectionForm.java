package de.bytewright.contestManager.frontend.components.home;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.bytewright.contestManager.backend.persistence.dtos.Contest;
import de.bytewright.contestManager.backend.security.GoContestManagerSession;
import de.bytewright.contestManager.backend.services.ContestService;
import de.bytewright.contestManager.frontend.pages.HomePage;

public class ContestSelectionForm extends Form<String> {
  private static final Logger LOGGER = LoggerFactory.getLogger(ContestSelectionForm.class);
  private final TextField<String> contestFormId;

  @SpringBean
  private ContestService contestService;

  public ContestSelectionForm(String contestSelection, IModel<String> identifier) {
    super(contestSelection);
    contestFormId = new HiddenField<>("contestSelectionField", identifier, String.class);
    add(contestFormId);
  }

  @Override
  protected void onSubmit() {
    String contestId = contestFormId.getModelObject();
    Contest contest = contestService.getContest(contestId)
        .orElseThrow(() -> new IllegalArgumentException("Contest with id " + contestId + " could not be found!"));
    LOGGER.info("Form submitted. contestId: {}; contest: {}", contestId, contest);
    GoContestManagerSession.get().setContest(contest);
    setResponsePage(HomePage.class);
  }
}
