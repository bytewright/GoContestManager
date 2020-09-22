package de.bytewright.contestManager.backend.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import de.bytewright.contestManager.backend.persistence.entities.AbstractPersonEntity;
import de.bytewright.contestManager.backend.persistence.entities.ContestEntity;
import de.bytewright.contestManager.backend.persistence.entities.HelperEntity;
import de.bytewright.contestManager.backend.persistence.entities.LocationEmbeddable;
import de.bytewright.contestManager.backend.persistence.entities.OrganizerEntity;
import de.bytewright.contestManager.backend.persistence.entities.PlayerEntity;

@Component
public class DataImporter {
  private static final Logger LOGGER = LoggerFactory.getLogger(DataImporter.class);

  private final Random rnd = new SecureRandom();

  public List<ContestEntity> getContestsFromFile(String fileName) {
    List<ContestEntity> entityList;
    Path path = Path.of(fileName);
    if (Files.exists(path)) {
      LOGGER.info("Importing contests from path: {}", path);
      entityList = importFile(path);
    } else {
      LOGGER.warn("Creating fake data for testing, no file found at {}", path);
      entityList = createContests();
    }
    LOGGER.debug("Importing {} contest...", entityList.size());
    return entityList;
  }

  private List<ContestEntity> importFile(Path path) {
    // todo import contests from json file
    return List.of();
  }

  private List<ContestEntity> createContests() {
    return IntStream.range(2020, 2035)
        .mapToObj(value -> "jcc" + value)
        .map(this::createContestEntity)
        .collect(Collectors.toList());
  }

  private ContestEntity createContestEntity(String contestId) {
    ContestEntity entity = new ContestEntity();
    entity.setShortIdentifier(contestId);
    entity.setContestName("contestname of " + contestId);
    entity.setCurrencyUnitCode("EUR");
    entity.setDiscountClubAmount(3.5d);
    entity.setDiscountGeneralAmount(2.8d);
    entity.setDiscountPreRegAmount(1.5d);
    ZonedDateTime start = ZonedDateTime.now().plus(rnd.nextInt(400), ChronoUnit.DAYS);
    ZonedDateTime end = start.plus(rnd.nextInt(20), ChronoUnit.DAYS);
    entity.setTimeZone(start.getZone().getId());
    entity.setStartUtcTime(start.toInstant()); // todo utc?
    entity.setEndUtcTime(end.toInstant()); // todo utc?
    entity.setRoundCount(5);
    entity.setFeeStartAmount(25d);
    entity.setFeeBreakfastAmount(5d);
    entity.setStartingFeeFreedRanks(Set.of(GoRank.KYU_30, GoRank.KYU_29));
    entity.setLocation(fakeLocation());
    entity.setPlayers(fakePlayers(entity, rnd.nextInt(200) + 10));
    entity.setOrganizers(fakeOrga(entity, 1));
    entity.setHelpers(fakeHelper(entity, rnd.nextInt(10) + 2));
    return entity;
  }

  private LocationEmbeddable fakeLocation() {
    LocationEmbeddable embeddable = new LocationEmbeddable();
    embeddable.setCity(generateRandomWord(2, 7) + "-City");
    embeddable.setName("some named place in city");
    embeddable.setStreet("streetName-" + generateRandomWord(0, 3));
    embeddable.setStreetNum("42" + generateRandomWord(0, 3));
    return embeddable;
  }

  private Set<HelperEntity> fakeHelper(ContestEntity entity, int count) {
    return IntStream.range(0, count)
        .mapToObj(value -> generateHelper(entity))
        .collect(Collectors.toSet());
  }

  private HelperEntity generateHelper(ContestEntity entity) {
    HelperEntity helperEntity = generatePerson(entity, new HelperEntity());
    helperEntity.setPhoneNumber(generateRandomWord(7, 10));
    helperEntity.setLocation(fakeLocation());
    return helperEntity;
  }

  private <T extends AbstractPersonEntity> T generatePerson(ContestEntity entity, T person) {
    person.setContestEntity(entity);
    person.setName(generateRandomWord(3, 12));
    person.setSurname(generateRandomWord(2, 8));
    person.setEmailAddr(generateRandomWord(2, 8) + "@" + generateRandomWord(2, 8) + ".com");
    return person;
  }

  private Set<OrganizerEntity> fakeOrga(ContestEntity entity, int count) {
    return IntStream.range(0, count)
        .mapToObj(value -> generateOrgan(entity))
        .collect(Collectors.toSet());
  }

  private OrganizerEntity generateOrgan(ContestEntity entity) {
    OrganizerEntity organizerEntity = generatePerson(entity, new OrganizerEntity());
    organizerEntity.setPhoneNumber(generateRandomWord(7, 10));
    organizerEntity.setLocation(fakeLocation());
    return organizerEntity;
  }

  private Set<PlayerEntity> fakePlayers(ContestEntity entity, int count) {
    return IntStream.range(0, count)
        .mapToObj(value -> generatePlayer(entity))
        .collect(Collectors.toSet());
  }

  private PlayerEntity generatePlayer(ContestEntity entity) {
    PlayerEntity playerEntity = generatePerson(entity, new PlayerEntity());
    playerEntity.setCity(fakeLocation().getCity());
    playerEntity.setGoClub(generateRandomWord(7, 10));
    playerEntity.setPaymentStatus(PaymentStatus.NOT_PAID);
    playerEntity.setCountry("DE");
    playerEntity.setAge(rnd.nextInt(50) + 8);
    playerEntity.setGoRank(
        Arrays.stream(GoRank.values()).skip(rnd.nextInt(GoRank.values().length - 1)).findFirst().orElse(GoRank.KYU_05));
    return playerEntity;
  }

  private String generateRandomWord(int minLength, int maxLength) {
    int wordLength = rnd.nextInt(maxLength - minLength) + minLength;
    StringBuilder sb = new StringBuilder(wordLength);
    for (int i = 0; i < wordLength; i++) { // For each letter in the word
      char tmp = (char) ('a' + rnd.nextInt('z' - 'a')); // Generate a letter between a and z
      sb.append(tmp); // Add it to the String
    }
    return sb.toString();
  }
}
