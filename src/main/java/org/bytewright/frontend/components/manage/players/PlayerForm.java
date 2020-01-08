package org.bytewright.frontend.components.manage.players;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.INullAcceptingValidator;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.ValidationError;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.Player;
import org.bytewright.backend.services.PersonService;
import org.bytewright.backend.util.GoRank;
import org.bytewright.backend.util.GoRankName;
import org.bytewright.frontend.pages.PlayerManagementPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerForm extends Form<Player> { // todo FormComponentPanel
  private static final Logger LOGGER = LoggerFactory.getLogger(PlayerForm.class);
  private final Contest contest;
  private final Player player;
  private final GoRankModel goRankModel;

  @SpringBean
  private PersonService personService;

  public PlayerForm(String contentId, IModel<Player> model, Contest contest) {
    super(contentId, model);
    this.contest = contest;
    this.player = model.getObject();
    if (player.getGoRank() == null)
      this.goRankModel = new GoRankModel();
    else
      this.goRankModel = new GoRankModel(player.getGoRank());
    add(new TextField<>("name", LambdaModel.of(player::getName, player::setName), String.class));
    add(new TextField<>("surname", LambdaModel.of(player::getSurname, player::setSurname), String.class));
    add(new EmailTextField("emailAddr", LambdaModel.of(player::getEmailAddr, player::setEmailAddr)).add(
        EmailAddressValidator.getInstance()));
    add(new TextField<>("club", LambdaModel.of(player::getGoClub, player::setGoClub), String.class));
    add(new NumberTextField<>("age", LambdaModel.of(player::getAge, player::setAge), Integer.class));
    addGoRankFields("rankNum", "rank");

    add(new CheckBox("isFemale", LambdaModel.of(player::isFemale, player::setFemale)));
    add(new CheckBox("isStudent", LambdaModel.of(player::isStudent, player::setStudent)));
    add(new CheckBox("isSenior", LambdaModel.of(player::isSenior, player::setSenior)));
    add(new CheckBox("isU10", LambdaModel.of(player::isU10, player::setU10)));
    add(new CheckBox("isFirstContest", LambdaModel.of(player::isFirstContest, player::setFirstContest)));
    add(new CheckBox("isSeminarMember", LambdaModel.of(player::isSeminarMember, player::setSeminarMember)));

    add(new PlayerValidator());
    add(new FeedbackPanel("feedback").setMaxMessages(3));
  }

  private void addGoRankFields(String rankNumId, String rankNameId) {
    IModel<Integer> rankNumIModel = LambdaModel.of(goRankModel::getRank, goRankModel::setRank);
    IModel<GoRankName> rankNameIModel = LambdaModel.of(goRankModel::getGoRankName, goRankModel::setGoRankName);
    var rankNumField = new NumberTextField<>(rankNumId, rankNumIModel, Integer.class);
    List<GoRankName> values = List.of(GoRankName.values());
    var rankNameField = new DropDownChoice<>(rankNameId, rankNameIModel, values);
    add(rankNumField);
    add(rankNameField);
    add(new GoRankValidator(rankNumField, rankNameField));
  }

  @Override
  protected void onSubmit() {
    GoRank newRank = Arrays.stream(GoRank.values())
        .filter(goRank -> goRank.getGoRankName().equals(goRankModel.getGoRankName()))
        .filter(goRank -> goRank.getRank() == goRankModel.getRank())
        .findFirst().orElseThrow();
    player.setGoRank(newRank);
    LOGGER.info("submitted! {}", player);
    personService.saveOrUpdatePlayer(contest, player);
    setResponsePage(PlayerManagementPage.class);
  }

  private static class GoRankModel implements Serializable {

    private int rank;
    private GoRankName goRankName;

    public GoRankModel() {
      rank = 1;
      goRankName = GoRankName.KYU;
    }

    public GoRankModel(GoRank goRank) {
      rank = goRank.getRank();
      goRankName = goRank.getGoRankName();
    }

    public int getRank() {
      return rank;
    }

    public void setRank(int rank) {
      this.rank = rank;
    }

    public GoRankName getGoRankName() {
      return goRankName;
    }

    public void setGoRankName(GoRankName goRankName) {
      this.goRankName = goRankName;
    }
  }

  private static class PlayerValidator extends Behavior implements INullAcceptingValidator<Player> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerValidator.class);

    @Override
    public void validate(IValidatable<Player> validatable) {
      LOGGER.info("validating {}", validatable.getValue());
    }
  }

  private static class GoRankValidator extends AbstractFormValidator {
    private final NumberTextField<Integer> rankNumField;
    private final DropDownChoice<GoRankName> rankNameField;

    public GoRankValidator(NumberTextField<Integer> rankNumField, DropDownChoice<GoRankName> rankNameField) {
      this.rankNumField = rankNumField;
      this.rankNameField = rankNameField;
    }

    @Override
    public FormComponent<?>[] getDependentFormComponents() {
      return new FormComponent[] { rankNumField, rankNameField };
    }

    @Override
    public void validate(Form<?> form) {
      GoRankName goRankName = rankNameField.getConvertedInput();
      Integer rankNum = rankNumField.getConvertedInput();

      Optional<GoRank> newRank = GoRank.from(rankNum, goRankName);

      if (newRank.isEmpty()) {
        String msg = "Go Rank does not exist: " + rankNum + " " + goRankName.getName() +
            " (" + rankNum + goRankName.getLetter() + ")";
        ValidationError error = new ValidationError(msg);
        rankNumField.error(error);
        LOGGER.error(msg);
      }
    }
  }
}
