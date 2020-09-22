package de.bytewright.contestManager.backend.persistence;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Needed to avoid circular references
 */
@Component
public class ModelMapperInitializer {
  private static final Logger LOGGER = LoggerFactory.getLogger(ModelMapperInitializer.class);

  @Autowired
  public void initModelMapper(ModelMapper modelMapper, List<Converter<?, ?>> converterList) {
    for (Converter<?, ?> converter : converterList) {
      LOGGER.info("Registering converter {} with model mapper", converter.getClass().getName());
      modelMapper.addConverter(converter);
    }
  }
}
