package org.bytewright.frontend.components;

import javax.money.MonetaryAmount;

import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LambdaModel;
import org.danekja.java.util.function.serializable.SerializableConsumer;
import org.danekja.java.util.function.serializable.SerializableSupplier;

public class MoneyField extends NumberTextField<Double> {
  public MoneyField(String contentId, IModel<Double> model) {
    super(contentId);
    setStep(0.01);
    setMinimum(0.0);
    setModel(model);
  }

  public MoneyField(String contentId, SerializableSupplier<MonetaryAmount> model, SerializableConsumer<Double> consumer) {
    this(contentId, LambdaModel.of(() -> model.get().getNumber().doubleValue(), consumer));
  }
}
