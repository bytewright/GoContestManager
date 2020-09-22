package de.bytewright.contestManager.backend.util.json;

import java.io.IOException;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CurrencyUnitDeserializer extends StdDeserializer<CurrencyUnit> {
  public CurrencyUnitDeserializer() {
    super(CurrencyUnit.class);
  }

  @Override
  public CurrencyUnit deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    return Monetary.getCurrency(p.getValueAsString());
  }
}
