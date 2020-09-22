package de.bytewright.contestManager.backend.persistence.converter;

import java.util.Optional;

import javax.annotation.Nonnull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.bytewright.contestManager.backend.persistence.dtos.Location;
import de.bytewright.contestManager.backend.persistence.dtos.Organiser;
import de.bytewright.contestManager.backend.persistence.entities.ContestEntity;
import de.bytewright.contestManager.backend.persistence.entities.LocationEmbeddable;
import de.bytewright.contestManager.backend.persistence.entities.OrganizerEntity;
import de.bytewright.contestManager.backend.persistence.repositories.ContestRepository;
import de.bytewright.contestManager.backend.persistence.repositories.OrganizerRepository;

@Component
public class OrganiserToEntityConverter extends AbstractToEntityConverter<Organiser, OrganizerEntity> {
  @Autowired
  private OrganizerRepository organizerRepository;
  @Autowired
  private ContestRepository contestRepository;
  @Autowired
  private ModelMapper modelMapper;

  @Override
  protected OrganizerEntity createNewEntity(@Nonnull Organiser source) {
    OrganizerEntity entity = new OrganizerEntity();
    return updateValuesFromDto(entity, source);
  }

  @Override
  protected OrganizerEntity updateValuesFromDto(@Nonnull OrganizerEntity entity, @Nonnull Organiser source) {
    ContestEntity contestEntity = contestRepository.findByShortIdentifier(source.getContestIdentifier())
        .orElseThrow(() -> new IllegalArgumentException("Can't find contest with id: " + source.getContestIdentifier()));
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
  protected Optional<OrganizerEntity> findOperation(Organiser source) {
    Long uniqueId = source.getUniqueId();
    if (uniqueId != null) {
      return organizerRepository.findById(uniqueId);
    }
    return Optional.empty();
  }
}
