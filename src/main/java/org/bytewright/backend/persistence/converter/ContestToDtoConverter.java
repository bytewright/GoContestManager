package org.bytewright.backend.persistence.converter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;

import javax.money.Monetary;

import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.ContestSettings;
import org.bytewright.backend.dto.Location;
import org.bytewright.backend.persistence.entities.ContestEntity;
import org.bytewright.backend.persistence.entities.LocationEmbeddable;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

/**
 * http://modelmapper.org/user-manual/converters/
 */
@Component
public class ContestToDtoConverter implements Converter<ContestEntity, Contest> {

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
      location.setCity(locationEntity.getCity());
      location.setStreet(locationEntity.getStreet());
      location.setStreetNum(locationEntity.getStreetNum());
      location.setName(locationEntity.getName());
    }
    settings.setLocation(location);

    Contest contest = context.getDestination();
    if (contest == null) {
      contest = new Contest();
    }
    contest.setuId(source.getShortIdentifier());
    contest.setHelpers(new HashSet<>());
    contest.setPlayers(new HashSet<>());
    contest.setOrganisers(new HashSet<>());
    contest.setContestSettings(settings);
    return contest;
  }
}
