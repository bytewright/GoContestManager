package org.bytewright.frontend.components.manage.contests;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.dto.ContestSettings;
import org.bytewright.backend.services.ContestService;
import org.bytewright.backend.util.GoRank;
import org.bytewright.frontend.components.MoneyField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContestSettingsEditForm extends Form<ContestSettings> {
  private static final Logger LOGGER = LoggerFactory.getLogger(ContestSettingsEditForm.class);
  @SpringBean
  private ContestService contestService;

  public ContestSettingsEditForm(String contentId, Model<ContestSettings> model) {
    super(contentId, model);
    ContestSettings settings = model.getObject();
    IModel<CurrencyUnit> currencyUnitModel = LambdaModel.of(settings::getCurrencyUnit, settings::setCurrencyUnit);
    add(new DropDownChoice<>("roundCount", LambdaModel.of(settings::getRoundCount, settings::setRoundCount), intList()));
    add(new MoneyField("feeStart", settings::getFeeStart, settings::setFeeStart));
    add(new DropDownChoice<>("currencyUnit", currencyUnitModel, new ArrayList<>(Monetary.getCurrencies())));
    add(new MoneyField("discount", settings::getDiscount, settings::setDiscount));
    add(new MoneyField("discountClub", settings::getDiscountClubMember, settings::setDiscountClubMember));
    add(new MoneyField("discountPreRegistered", settings::getDiscountPreRegistered, settings::setDiscountPreRegistered));
    add(new MoneyField("feeBreakfast", settings::getFeeBreakfast, settings::setFeeBreakfast));
    List<GoRank> ranksList = List.of(GoRank.values());
    add(new ListMultipleChoice<>("startFeeFreedRanks",
        LambdaModel.of(settings::getStartingFeeFreedRanks, settings::setStartingFeeFreedRanks), ranksList));
  }

  private List<Integer> intList() {
    return IntStream.range(1, 21)
        .boxed()
        .collect(Collectors.toList());
  }

  @Override
  protected void onSubmit() {
    LOGGER.info("Contest settings where changed: {}", getModelObject());
    contestService.saveOrUpdateContestSettings(getModelObject());
  }

}
