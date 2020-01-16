package org.bytewright.backend.persistence.converter;

import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.persistence.entities.ContestEntity;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class ContestToEntityConverter implements Converter<Contest, ContestEntity> {
  @Override
  public ContestEntity convert(MappingContext<Contest, ContestEntity> context) {
    return null;
  }
}
