package org.bytewright.frontend.components.manage.contests;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.apache.wicket.model.Model;
import org.bytewright.backend.dto.ContestSettings;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ContestSettingsEditForm extends Form<ContestSettings> {
  public ContestSettingsEditForm(String contentId, Model<ContestSettings> model) {
    super(contentId, model);
    ContestSettings settings = model.getObject();
    IModel<CurrencyUnit> currencyUnitModel = LambdaModel.of(settings::getCurrencyUnit, settings::setCurrencyUnit);
    add(new DropDownChoice<>("roundCount", LambdaModel.of(settings::getRoundCount, settings::setRoundCount), intList()));
    add(new NumberTextField<>("startFee", LambdaModel.of(settings::getStartingFeeAmount, settings::setStartingFeeAmount)));
    add(new DropDownChoice<>("currencyUnit", currencyUnitModel, new ArrayList<>(Monetary.getCurrencies())));
    add(new NumberTextField<>("discountStudent", LambdaModel.of(settings::getDiscountStudentAmount, settings::setDiscountStudentAmount)));
    add(new NumberTextField<>("discountSenior", LambdaModel.of(settings::getDiscountSeniorAmount, settings::setDiscountSeniorAmount)));
    add(new NumberTextField<>("discountClub", LambdaModel.of(settings::getDiscountClubMemberAmount, settings::setDiscountClubMemberAmount)));
  }

  private List<Integer> intList() {
    return IntStream.range(1, 10)
      .boxed()
      .collect(Collectors.toList());
  }
}
