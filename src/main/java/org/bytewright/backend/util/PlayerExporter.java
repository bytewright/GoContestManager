package org.bytewright.backend.util;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.bytewright.backend.persistence.dtos.Player;

/**
 * https://www.cgerlach.de/go/macmahon-documentation.html
 *
 * Importing participants from text files
 *
 * It is possible to import participants from text files that must be encoded as UTF-8. Each line of the file represents the data for one participant. Empty lines are permitted and lines starting with ";" are recognized as comments and are ignored.
 *
 * A line with data of a participant must meet the following format:
 *
 * surname|firstname|strength|country|club|rating|registration|playinginrounds
 *
 * The following rules apply to one line of data:
 *
 *     Only the surname is mandatory. It is possible to import participants without a level but the program will behave unpredictable untill all players have a level assigned.
 *     If you don't want to provide a value, just let it empty, e.g. "surname||3 Dan" is a valid import.
 *     The strength is a number followed by "d" (Dan) or "p" (Pro). Everything else will be interpreted as Kyu. Examples: "10 Kyu", "14", "1 Dan", "7d", "3p" are all valid strengths.
 *     The country must conform to internet codes. Country codes are not case sensitive.
 *     The club must either match a known club (case insensitive) by name or by egd name or will be added as a new club to the country (with the first four characters of the club name used as EGD-name). If there is no valid country, the club is ignored.
 *     The rating will only be stored with the participant if the option "Allow ratings for participants" is checked before the import is started. The rank will be determined by the rating instead of the rank in the import data if the option "Build rank by rating" is checked.
 *     Registration can be set to "p" or "P" for a preliminary registered participant or "f" or "F" for a final registered participant. If no value is given, the participant will have the default registration value as set in the tournament settings.
 *     The default country as set in the tournament settings will not be applied to participants without a country in the import data.
 *     The participant will be marked for having an "asian name" if this is set as default in the tournament settings before starting the import.
 *     If you import the data with the current round > 1, participants with a final registration will be imported as playing from the current round. (Attention: if you have explicit values in playinginrounds, these overwrite this behaviour.)
 *     playinginrounds allows to you to set which rounds your participant should play in the tournament. 0 = not playing, 1 = playing. Example: "11100" will have this participant playing rounds 1-3 only. This parameter will only be evaluated if the participant has a final registration (either explicitely or by default in tournament settings).
 */
public class PlayerExporter {
  private static final String MACMAHON_SEPARATOR = "|";
  private Collection<Player> players;

  public PlayerExporter(Collection<Player> players) {
    this.players = players;
  }

  public List<String> getLines() {
    return players.stream()
        .map(this::macmahonline)
        .collect(Collectors.toList());
  }

  private String macmahonline(Player player) {
    return Optional.ofNullable(player.getSurname()).map(StringUtils::strip).orElseThrow()
        + MACMAHON_SEPARATOR
        + prep(player.getName())
        + MACMAHON_SEPARATOR
        + prep(player.getGoRank().getAbbreviation())
        + MACMAHON_SEPARATOR
        + prep(player.getCountry())
        + MACMAHON_SEPARATOR
        + prep(player.getGoClub());
  }

  private String prep(String value) {
    return Optional.ofNullable(value).map(StringUtils::strip).orElse("");
  }
}
