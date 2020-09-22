package de.bytewright.contestManager.backend.persistence.converter;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.bytewright.contestManager.backend.persistence.dtos.Contest;
import de.bytewright.contestManager.backend.persistence.dtos.ContestSettings;
import de.bytewright.contestManager.backend.persistence.dtos.Location;
import de.bytewright.contestManager.backend.persistence.entities.ContestEntity;
import de.bytewright.contestManager.backend.persistence.entities.HelperEntity;
import de.bytewright.contestManager.backend.persistence.entities.LocationEmbeddable;
import de.bytewright.contestManager.backend.persistence.entities.OrganizerEntity;
import de.bytewright.contestManager.backend.persistence.entities.PlayerEntity;
import de.bytewright.contestManager.backend.persistence.repositories.ContestRepository;

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
    Set<PlayerEntity> playerEntities = source.getPlayers().stream()
        .map(player -> modelMapper.map(player, PlayerEntity.class))
        .collect(Collectors.toSet());
    entity.setPlayers(playerEntities);

    Set<OrganizerEntity> organizerEntities = source.getOrganisers().stream()
        .map(organiser -> modelMapper.map(organiser, OrganizerEntity.class))
        .collect(Collectors.toSet());
    entity.setOrganizers(organizerEntities);
    Set<HelperEntity> helperEntities = source.getHelpers().stream()
        .map(helper -> modelMapper.map(helper, HelperEntity.class))
        .collect(Collectors.toSet());
    entity.setHelpers(helperEntities);
    return entity;
  }

  @Override
  protected Optional<ContestEntity> findOperation(@Nonnull Contest source) {
    return contestRepository.findByShortIdentifier(source.getUniqueId());
  }
}
