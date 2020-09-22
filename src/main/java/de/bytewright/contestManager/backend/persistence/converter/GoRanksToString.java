package de.bytewright.contestManager.backend.persistence.converter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.apache.commons.lang3.StringUtils;

import de.bytewright.contestManager.backend.util.GoRank;

@Converter
public class GoRanksToString implements AttributeConverter<Set<GoRank>, String> {
  @Override
  public String convertToDatabaseColumn(Set<GoRank> attribute) {
    return attribute.stream()
        .sorted()
        .map(GoRank::getAbbreviation)
        .collect(Collectors.joining(";"));
  }

  @Override
  public Set<GoRank> convertToEntityAttribute(String dbData) {
    return Arrays.stream(dbData.split(";"))
        .filter(StringUtils::isNotBlank)
        .map(GoRank::fromAbbrv)
        .collect(Collectors.toSet());
  }
}
