package de.bytewright.contestManager.backend.persistence.converter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.money.Monetary;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.bytewright.contestManager.backend.persistence.dtos.Contest;
import de.bytewright.contestManager.backend.persistence.dtos.ContestSettings;
import de.bytewright.contestManager.backend.persistence.dtos.Location;
import de.bytewright.contestManager.backend.persistence.dtos.Player;
import de.bytewright.contestManager.backend.persistence.entities.ContestEntity;
import de.bytewright.contestManager.backend.persistence.entities.LocationEmbeddable;

/**
 * http://modelmapper.org/user-manual/converters/
 */
@Component
public class ContestToDtoConverter implements Converter<ContestEntity, Contest> {
  public static final String EXTRA_DATA_KEY_VALUE_SEPARATOR = "'''";
  public static final String EXTRA_DATA_ENTRY_SEPARATOR = "''''''";
  private static final Logger LOGGER = LoggerFactory.getLogger(ContestToDtoConverter.class);
  @Autowired
  private ModelMapper modelMapper;

  @Override
  public Contest convert(MappingContext<ContestEntity, Contest> context) {
    ContestEntity source = context.getSource();
    if (source == null) {
      return null;
    }
    ContestSettings settings = new ContestSettings();
    settings.setName(source.getContestName());
    ZoneId zoneId = ZoneId.of(source.getTimeZone());
    settings.setDateStart(ZonedDateTime.ofInstant(source.getStartUtcTime(), zoneId));
    settings.setDateEnd(ZonedDateTime.ofInstant(source.getEndUtcTime(), zoneId));
    settings.setCurrencyUnit(Monetary.getCurrency(source.getCurrencyUnitCode()));
    settings.setFeeStart(source.getFeeStartAmount());
    settings.setFeeBreakfast(source.getFeeBreakfastAmount());
    settings.setDiscount(source.getDiscountGeneralAmount());
    settings.setDiscountClubMember(source.getDiscountClubAmount());
    settings.setDiscountPreRegistered(source.getDiscountPreRegAmount());
    settings.setRoundCount(source.getRoundCount());
    settings.setStartingFeeFreedRanks(source.getStartingFeeFreedRanks());

    LocationEmbeddable locationEntity = source.getLocation();
    Location location = new Location();
    if (locationEntity != null) {
      modelMapper.map(locationEntity, location);
    }
    settings.setLocation(location);

    Contest contest = context.getDestination();
    if (contest == null) {
      contest = new Contest();
    }
    contest.setUniqueID(source.getShortIdentifier());
    Set<Player> playerSet = source.getPlayers().stream()
        .map(entity -> modelMapper.map(entity, Player.class))
        .collect(Collectors.toSet());
    contest.setPlayers(playerSet);
    contest.setHelpers(new HashSet<>());
    contest.setOrganisers(new HashSet<>());
    contest.setContestSettings(settings);

    if (source.getExtraData() != null && !source.getExtraData().isEmpty()) {
      try {
        Map<String, String> stringMap = Arrays.stream(source.getExtraData().split(EXTRA_DATA_ENTRY_SEPARATOR))
            .map(s -> s.split(EXTRA_DATA_KEY_VALUE_SEPARATOR))
            .collect(Collectors.toMap(strings -> strings[0], strings -> strings[1]));
        contest.setExtraData(stringMap);
      } catch (Exception e) {
        LOGGER.error("Failed to parse extradata from entity id={}: {}", source.getId(), source.getExtraData());
      }
    }
    return contest;
  }
}
