package org.bytewright.backend.util;

import org.bytewright.backend.dto.*;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PersonUtil {
  private static Random rnd = new Random();

  public static Helper rndHelper() {
    Helper helper = rndPerson(new Helper());
    helper.setPhoneNumber(rnd.ints(10).mapToObj(Integer::toString).collect(Collectors.joining()));
    return helper;
  }

  public static Organiser rndOrga() {
    Organiser organiser = rndPerson(new Organiser());
    organiser.setPhoneNumber(rnd.ints(10).mapToObj(Integer::toString).collect(Collectors.joining()));
    return organiser;
  }

  public static Player rndPlayer() {
    Player player = rndPerson(new Player());
    player.setGoClub(generateRandomWord(8));
    GoRank[] values = GoRank.values();
    GoRank rank = Arrays.stream(values)
      .skip(rnd.nextInt(values.length - 1))
      .findFirst().orElse(GoRank.KYU_30);
    player.setGoRank(rank);
    player.setSenior(rnd.nextBoolean());
    player.setStudent(!player.isSenior() && rnd.nextBoolean());
    return player;
  }

  static String generateRandomWord(int wordLength) {
    Random r = new Random(); // Intialize a Random Number Generator with SysTime as the seed
    StringBuilder sb = new StringBuilder(wordLength);
    for (int i = 0; i < wordLength; i++) { // For each letter in the word
      char tmp = (char) ('a' + r.nextInt('z' - 'a')); // Generate a letter between a and z
      sb.append(tmp); // Add it to the String
    }
    return sb.toString();
  }

  private static <T extends Person> T rndPerson(T person) {
    // TODO
    person.setName("rndString");
    Location rndAdress = new Location();
    rndAdress.setStreet("rndStreet");
    rndAdress.setStreetNum("42");
    rndAdress.setCity("rndCity");
    person.setAdress(rndAdress);
    return person;
  }

  public static Set<Player> rndPlayers(int num) {
    return IntStream.range(0, num)
      .mapToObj(value -> rndPlayer())
      .collect(Collectors.toSet());
  }
}
