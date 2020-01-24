package org.bytewright.backend.persistence.converter;

import org.bytewright.backend.persistence.dtos.Player;
import org.bytewright.backend.persistence.entities.PlayerEntity;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class PlayerToDtoConverter implements Converter<PlayerEntity, Player> {

  @Override
  public Player convert(MappingContext<PlayerEntity, Player> context) {
    PlayerEntity source = context.getSource();
    if (source == null) {
      return null;
    }
    Player player = new Player();
    player.setUniqueId(source.getId());
    player.setContestIdentifier(source.getContestEntity().getShortIdentifier());
    player.setEmailAddr(source.getEmailAddr());
    player.setName(source.getName());
    player.setSurname(source.getSurname());
    player.setAge(source.getAge());
    player.setCity(source.getCity());
    player.setCountry(source.getCountry());
    player.setGoClub(source.getGoClub());
    player.setGoRank(source.getGoRank());
    player.setPaymentStatus(source.getPaymentStatus());
    player.setRegistrationFormMessage(source.getRegistrationFormMessage());

    player.setNeedsSleepOver(source.isNeedsSleepOver());
    player.setFirstContest(source.isFirstContest());
    player.setDiscounted(source.isDiscounted());
    player.setFemale(source.isFemale());
    player.setAttendsBreakfast(source.isAttendsBreakfast());
    player.setGoClubMember(source.isGoClubMember());
    player.setSenior(source.isSenior());
    player.setStudent(source.isStudent());
    player.setU10(source.isU10());
    player.setSeminarMember(source.isSeminarMember());
    return player;
  }
}
