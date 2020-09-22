package de.bytewright.contestManager.backend.persistence.converter;

import java.util.Optional;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.bytewright.contestManager.backend.persistence.dtos.Player;
import de.bytewright.contestManager.backend.persistence.entities.ContestEntity;
import de.bytewright.contestManager.backend.persistence.entities.PlayerEntity;
import de.bytewright.contestManager.backend.persistence.repositories.ContestRepository;
import de.bytewright.contestManager.backend.persistence.repositories.PlayerRepository;

@Component
public class PlayerToEntityConverter extends AbstractToEntityConverter<Player, PlayerEntity> {
  @Autowired
  private PlayerRepository playerRepository;
  @Autowired
  private ContestRepository contestRepository;

  @Override
  protected PlayerEntity createNewEntity(@Nonnull Player source) {
    PlayerEntity entity = new PlayerEntity();
    return updateValuesFromDto(entity, source);
  }

  @Override
  protected PlayerEntity updateValuesFromDto(@Nonnull PlayerEntity entity, @Nonnull Player source) {
    ContestEntity contestEntity = contestRepository.findByShortIdentifier(source.getContestIdentifier())
        .orElseThrow(() -> new IllegalArgumentException("Can't find contest with id: " + source.getContestIdentifier()));
    entity.setId(source.getUniqueId());
    entity.setEmailAddr(source.getEmailAddr());
    entity.setName(source.getName());
    entity.setSurname(source.getSurname());
    entity.setAge(source.getAge());
    entity.setCity(source.getCity());
    entity.setCountry(source.getCountry());
    entity.setGoClub(source.getGoClub());
    entity.setGoRank(source.getGoRank());
    entity.setPaymentStatus(source.getPaymentStatus());
    entity.setRegistrationFormMessage(source.getRegistrationFormMessage());
    entity.setContestEntity(contestEntity);

    entity.setNeedsSleepOver(source.isNeedsSleepOver());
    entity.setFirstContest(source.isFirstContest());
    entity.setDiscounted(source.isDiscounted());
    entity.setFemale(source.isFemale());
    entity.setAttendsBreakfast(source.isAttendsBreakfast());
    entity.setGoClubMember(source.isGoClubMember());
    entity.setSenior(source.isSenior());
    entity.setStudent(source.isStudent());
    entity.setU10(source.isU10());
    entity.setSeminarMember(source.isSeminarMember());
    return entity;
  }

  @Override
  protected Optional<PlayerEntity> findOperation(Player source) {
    Long uniqueId = source.getUniqueId();
    if (uniqueId != null) {
      return playerRepository.findById(uniqueId);
    }
    return Optional.empty();
  }
}
