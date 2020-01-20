package org.bytewright.backend.persistence.converter;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.ContestSettings;
import org.bytewright.backend.dto.Location;
import org.bytewright.backend.persistence.entities.ContestEntity;
import org.bytewright.backend.persistence.entities.LocationEmbeddable;
import org.bytewright.backend.persistence.entities.PlayerEntity;
import org.bytewright.backend.persistence.repositories.ContestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContestToEntityConverter extends AbstractToEntityConverter<Contest, ContestEntity> {
  @Autowired
  private ContestRepository contestRepository;
  @Autowired
  private ModelMapper modelMapper;

  @Override
  public ContestEntity createNewEntity(@Nonnull Contest source) {
    ContestEntity entity = new ContestEntity();
    return updateValuesFromDto(entity, source);
  }

  @Override
  public ContestEntity updateValuesFromDto(@Nonnull ContestEntity entity, @Nonnull Contest source) {
    ContestSettings contestSettings = source.getContestSettings();
    entity.setShortIdentifier(source.getUniqueId());
    entity.setContestName(contestSettings.getName());
    entity.setCurrencyUnitCode(contestSettings.getCurrencyUnit().getCurrencyCode());
    entity.setDiscountClubAmount(contestSettings.getDiscountClubMember().getNumber().doubleValue());
    entity.setDiscountGeneralAmount(contestSettings.getDiscount().getNumber().doubleValue());
    entity.setDiscountPreRegAmount(contestSettings.getDiscountPreRegistered().getNumber().doubleValue());
    entity.setTimeZone(contestSettings.getDateStart().getZone().getId());
    entity.setStartUtcTime(contestSettings.getDateStart().toInstant()); // todo utc?
    entity.setEndUtcTime(contestSettings.getDateEnd().toInstant()); // todo utc?
    entity.setRoundCount(contestSettings.getRoundCount());
    entity.setFeeStartAmount(contestSettings.getFeeStart().getNumber().doubleValue());
    entity.setFeeBreakfastAmount(contestSettings.getFeeBreakfast().getNumber().doubleValue());
    entity.setStartingFeeFreedRanks(contestSettings.getStartingFeeFreedRanks());
    Location location = contestSettings.getLocation();
    LocationEmbeddable embeddable = new LocationEmbeddable();
    if (location != null) {
      modelMapper.map(location, LocationEmbeddable.class);
    }
    entity.setLocation(embeddable);
    entity.setPlayers(source.getPlayers().stream()
        .map(player -> modelMapper.map(player, PlayerEntity.class))
        .collect(Collectors.toSet()));
    //todo player usw...
    return entity;
  }

  @Override
  protected Optional<ContestEntity> findOperation(@Nonnull Contest source) {
    return contestRepository.findByShortIdentifier(source.getUniqueId());
  }
}
