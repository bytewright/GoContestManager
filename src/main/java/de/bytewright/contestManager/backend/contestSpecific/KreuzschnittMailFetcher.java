package de.bytewright.contestManager.backend.contestSpecific;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.bytewright.contestManager.backend.persistence.dtos.Player;
import de.bytewright.contestManager.backend.util.PlayerImporter;

/**
 * code parts from https://alvinalexander.com/blog/post/java/i-need-get-all-of-my-email-addresses-out-of-imap-mailbox/
 */
@Component
public class KreuzschnittMailFetcher {
  private static final Logger LOGGER = LoggerFactory.getLogger(KreuzschnittMailFetcher.class);
  @Autowired
  private PlayerImporter playerImporter;

  public List<Player> processMails() {
    List<String> stringList = getAllMailContents();
    List<Player> playerList = stringList.stream()
        .map(playerImporter::parse)
        .collect(Collectors.toList());
    LOGGER.info("Found playerList for kreuzschnitt: {}", playerList);
    // TODO convert to players for contest
    return List.of();
  }

  private List<String> getAllMailContents() {
    List<String> mailList = List.of();
    Properties props = new Properties();

    String host = "";
    String username = "uname";
    String password = "secret";
    String provider = "imap";
    LOGGER.info("Connecting to ({}) {} using username {}", provider, host, username);
    try {
      Session session = Session.getDefaultInstance(props, null);
      try (Store store = session.getStore(provider)) {
        store.connect(host, username, password);
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        mailList = Arrays.stream(inbox.getMessages())
            .filter(this::isRegistrationMail)
            .map(this::getMailContent)
            .flatMap(Optional::stream)
            .collect(Collectors.toList());

        LOGGER.info("Got {} mails from server: {}", mailList.size(), mailList);

        //close the inbox folder but do not
        //remove the messages from the server
        inbox.close(false);
      }
    } catch (NoSuchProviderException nspe) {
      LOGGER.error("invalid provider name", nspe);
    } catch (MessagingException me) {
      LOGGER.error("messaging exception", me);
    }
    return mailList;
  }

  private Optional<String> getMailContent(Message message) {
    try {
      return Optional.ofNullable((String) message.getContent());
    } catch (MessagingException | IOException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  private boolean isRegistrationMail(Message message) {
    try {
      String subject = message.getSubject();
      return subject.equals("Anmeldung");
    } catch (MessagingException e) {
      e.printStackTrace();
    }
    return false;
  }
}
