package org.bytewright.backend.persistence.converter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.money.Monetary;

import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.ContestSettings;
import org.bytewright.backend.dto.Location;
import org.bytewright.backend.dto.Player;
import org.bytewright.backend.persistence.entities.ContestEntity;
import org.bytewright.backend.persistence.entities.LocationEmbeddable;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * http://modelmapper.org/user-manual/converters/
 */
@Component
public class ContestToDtoConverter implements Converter<ContestEntity, Contest> {
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
    contest.setuId(source.getShortIdentifier());
    Set<Player> playerSet = source.getPlayers().stream()
        .map(entity -> modelMapper.map(entity, Player.class))
        .collect(Collectors.toSet());
    contest.setPlayers(playerSet);
    contest.setHelpers(new HashSet<>());
    contest.setOrganisers(new HashSet<>());
    contest.setContestSettings(settings);
    return contest;
  }
}
