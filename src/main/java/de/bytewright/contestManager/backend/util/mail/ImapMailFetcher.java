package de.bytewright.contestManager.backend.util.mail;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ImapMailFetcher {
  private static final Logger LOGGER = LoggerFactory.getLogger(ImapMailFetcher.class);

  public List<String> getMessages(
      String host,
      String username,
      String password,
      String subjectSearchPattern,
      Predicate<? super Message> filterPredicate,
      Function<Message, Optional<String>> messageFunction) {
    Properties props = new Properties();
    props.setProperty("mail.store.protocol", "imap");
    props.setProperty("mail.transport.protocol", "imap");
    props.setProperty("mail.host", host);
    props.setProperty("mail.user", username);
    props.setProperty("mail.imap.starttls.enable", "true");
    props.setProperty("ssl.SocketFactory.provider", ExchangeSSLSocketFactory.class.getName());
    props.setProperty("mail.imap.socketFactory.class", ExchangeSSLSocketFactory.class.getName());

    //props.setProperty("mail.smtp.auth", "true"); //Enabling SMTP Authentication
    LOGGER.info("Connecting to mail using props: {}", props);
    try {
      Session session = Session.getDefaultInstance(props);
      session.setDebug(false);
      try (Store store = session.getStore("imap")) {
        store.connect(host, username, password);
        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);

        SearchTerm searchTerm = new SubjectTerm(subjectSearchPattern);
        Message[] messages = inbox.search(searchTerm);
        LOGGER.info("Got {} mails from server matching subject pattern: {}", messages.length, subjectSearchPattern);

        List<String> stringList = Arrays.stream(messages)
            .filter(filterPredicate)
            .map(messageFunction)
            .flatMap(Optional::stream)
            .collect(Collectors.toList());
        //close the inbox folder but do not
        //remove the messages from the server
        inbox.close(false);
        return stringList;
      }
    } catch (NoSuchProviderException nspe) {
      LOGGER.error("invalid provider name", nspe);
    } catch (MessagingException me) {
      LOGGER.error("messaging exception", me);
    } catch (Exception e) {
      LOGGER.error("Unknown Exception while fetching mails", e);
    }
    return List.of();
  }
}
