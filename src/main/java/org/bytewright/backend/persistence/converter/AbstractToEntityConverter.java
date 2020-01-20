package org.bytewright.backend.persistence.converter;

import java.util.Optional;

import javax.annotation.Nonnull;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public abstract class AbstractToEntityConverter<FROM, TO> implements Converter<FROM, TO> {
  @Override
  public TO convert(MappingContext<FROM, TO> context) {
    FROM source = context.getSource();
    if (source == null) {
      return null;
    }
    Optional<TO> entityOptional = findOperation(source);
    return entityOptional
        .map(entity -> updateValuesFromDto(entity, source))
        .orElseGet(() -> createNewEntity(source));
  }

  protected abstract TO createNewEntity(@Nonnull FROM source);

  protected abstract TO updateValuesFromDto(@Nonnull TO entity, @Nonnull FROM source);

  protected abstract Optional<TO> findOperation(@Nonnull FROM source);
}
