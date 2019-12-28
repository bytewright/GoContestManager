package org.bytewright.backend.util;

import java.io.Serializable;

public enum PaymentStatus implements Serializable {
  NO_PAYMENT,
  NOT_PAID,
  FULLY_PAID
}
