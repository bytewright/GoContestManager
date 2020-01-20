package org.bytewright.backend.persistence.converter;

import javax.persistence.AttributeConverter;

import org.bytewright.backend.util.GoRank;

public class GoRankToString implements AttributeConverter<GoRank, String> {
  @Override
  public String convertToDatabaseColumn(GoRank attribute) {
    return attribute.getAbbreviation();
  }

  @Override
  public GoRank convertToEntityAttribute(String dbData) {
    return GoRank.fromAbbrv(dbData);
  }
}
