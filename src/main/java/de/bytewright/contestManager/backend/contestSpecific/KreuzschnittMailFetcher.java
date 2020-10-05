package de.bytewright.contestManager.backend.contestSpecific;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMultipart;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.bytewright.contestManager.backend.persistence.dtos.Contest;
import de.bytewright.contestManager.backend.persistence.dtos.Player;
import de.bytewright.contestManager.backend.persistence.entities.PlayerEntity;
import de.bytewright.contestManager.backend.persistence.repositories.PlayerRepository;
import de.bytewright.contestManager.backend.services.ContestService;
import de.bytewright.contestManager.backend.services.PlayerImporter;
import de.bytewright.contestManager.backend.util.mail.ImapMailFetcher;

/**
 * code parts from https://alvinalexander.com/blog/post/java/i-need-get-all-of-my-email-addresses-out-of-imap-mailbox/
 */
@Component
public class KreuzschnittMailFetcher {
  private static final Logger LOGGER = LoggerFactory.getLogger(KreuzschnittMailFetcher.class);
  @Autowired
  private PlayerImporter playerImporter;
  @Autowired
  private ModelMapper modelMapper;
  @Autowired
  private ImapMailFetcher imapMailFetcher;
  @Autowired
  private ContestService contestService;
  @Autowired
  private PlayerRepository playerRepository;

  public List<Player> processMails() {
    Contest kreuzschnitt2020 = contestService.getContest("Kreuzschnitt2020").orElseThrow();
    Map<String, String> extraData = kreuzschnitt2020.getExtraData();
    String host = extraData.get("host");
    String username = extraData.get("username");
    String pass = extraData.get("pass");
    String emailSubject = extraData.get("emailSubject");
    List<String> stringList = getAllMailContents(host, username, pass, emailSubject);
    List<Player> playerList = stringList.stream()
        .map(textToParse -> playerImporter.parse(textToParse))
        .collect(Collectors.toList());
    playerList.forEach(player -> player.setContestIdentifier(kreuzschnitt2020.getUniqueId()));
    LOGGER.info("Found playerList for kreuzschnitt: {}", playerList);
    for (Player player : playerList) {
      PlayerEntity map = modelMapper.map(player, PlayerEntity.class);
      playerRepository.save(map);
    }

    return playerList;
  }

  private List<String> getAllMailContents(String host, String username, String pass, String emailSubject) {
    List<String> messages = imapMailFetcher.getMessages(host, username, pass, emailSubject,
        message -> true, this::getMailContent);
    try {
      Files.writeString(Path.of("Mails.txt"), String.join("\n|||\n", messages));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return messages;
  }

  private Optional<String> getMailContent(Message message) {
    try {
      Object content = message.getContent();
      if (content instanceof MimeMultipart) {
        MimeMultipart multipart = (MimeMultipart) content;
        LOGGER.info("Found multipart Message with {} parts: {}", multipart.getCount(), multipart);
      } else if (content instanceof String) {
        return Optional.of((String) content);
      } else {
        LOGGER.info("Found mail content of type {}: {}", content.getClass(), content);
      }
    } catch (MessagingException | IOException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }
}
