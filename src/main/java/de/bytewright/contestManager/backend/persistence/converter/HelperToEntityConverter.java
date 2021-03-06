package de.bytewright.contestManager.backend.persistence.converter;

import java.util.Optional;

import javax.annotation.Nonnull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.bytewright.contestManager.backend.persistence.dtos.Helper;
import de.bytewright.contestManager.backend.persistence.dtos.Location;
import de.bytewright.contestManager.backend.persistence.entities.ContestEntity;
import de.bytewright.contestManager.backend.persistence.entities.HelperEntity;
import de.bytewright.contestManager.backend.persistence.entities.LocationEmbeddable;
import de.bytewright.contestManager.backend.persistence.repositories.ContestRepository;
import de.bytewright.contestManager.backend.persistence.repositories.HelperRepository;

@Component
public class HelperToEntityConverter extends AbstractToEntityConverter<Helper, HelperEntity> {
  @Autowired
  private HelperRepository helperRepository;
  @Autowired
  private ContestRepository contestRepository;
  @Autowired
  private ModelMapper modelMapper;

  @Override
  protected HelperEntity createNewEntity(@Nonnull Helper source) {
    HelperEntity entity = new HelperEntity();
    return updateValuesFromDto(entity, source);
  }

  @Override
  protected HelperEntity updateValuesFromDto(@Nonnull HelperEntity entity, @Nonnull Helper source) {
    ContestEntity contestEntity = contestRepository.findByShortIdentifier(source.getContestIdentifier())
        .orElseThrow(() -> new IllegalArgumentException("Can't find orga with id: " + source.getContestIdentifier()));
    entity.setId(source.getUniqueId());
    entity.setEmailAddr(source.getEmailAddr());
    entity.setName(source.getName());
    entity.setSurname(source.getSurname());
    entity.setContestEntity(contestEntity);
    entity.setEmailAddr(source.getEmailAddr());
    entity.setPhoneNumber(source.getPhoneNumber());
    Location location = source.getAddress();
    LocationEmbeddable embeddable = new LocationEmbeddable();
    if (location != null) {
      modelMapper.map(location, LocationEmbeddable.class);
    }
    entity.setLocation(embeddable);
    return entity;
  }

  @Override
  protected Optional<HelperEntity> findOperation(Helper source) {
    Long uniqueId = source.getUniqueId();
    if (uniqueId != null) {
      return helperRepository.findById(uniqueId);
    }
    return Optional.empty();
  }
}
