package org.bytewright.backend.persistence.converter;

import javax.persistence.AttributeConverter;

import org.apache.wicket.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringToClass implements AttributeConverter<Class<? extends Page>, String> {
  private static final Logger LOGGER = LoggerFactory.getLogger(StringToClass.class);

  @Override
  public String convertToDatabaseColumn(Class<? extends Page> attribute) {
    if (attribute != null) {
      return attribute.getCanonicalName();
    }
    return null;
  }

  @Override
  public Class<? extends Page> convertToEntityAttribute(String dbData) {
    try {
      return (Class<? extends Page>) Class.forName(dbData);
    } catch (ClassNotFoundException e) {
      LOGGER.error("Failed to get class {}", dbData);
    }
    return null;
  }
}
