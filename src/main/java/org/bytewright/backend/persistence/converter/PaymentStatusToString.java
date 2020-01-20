package org.bytewright.backend.persistence.converter;

import javax.persistence.AttributeConverter;

import org.bytewright.backend.util.PaymentStatus;

public class PaymentStatusToString implements AttributeConverter<PaymentStatus, String> {
  @Override
  public String convertToDatabaseColumn(PaymentStatus attribute) {
    return attribute.name();
  }

  @Override
  public PaymentStatus convertToEntityAttribute(String dbData) {
    return PaymentStatus.valueOf(dbData);
  }
}
