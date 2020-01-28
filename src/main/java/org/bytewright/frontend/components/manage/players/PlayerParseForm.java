package org.bytewright.frontend.components.manage.players;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.bytewright.backend.persistence.dtos.Player;
import org.bytewright.backend.services.PersonService;
import org.bytewright.backend.util.PlayerImporter;
import org.bytewright.backend.util.exceptions.PlayerParseException;
import org.bytewright.frontend.pages.PlayerEditPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerParseForm extends Form<Player> {
  private static final Logger LOGGER = LoggerFactory.getLogger(PlayerParseForm.class);
  private final TextArea<String> textArea;
  @SpringBean
  private PersonService personService;

  public PlayerParseForm(String contentId, IModel<Player> model) {
    super(contentId, model);
    textArea = new TextArea<>("emailContent", Model.of(""));
    add(textArea);
  }

  @Override
  protected void onSubmit() {
    try {
      PlayerImporter importer = new PlayerImporter();
      Player player = importer.parse(textArea.getModelObject());
      Long id = personService.saveOrUpdatePlayer(player);
      PageParameters pageParameters = new PageParameters();
      pageParameters.add(PlayerEditPage.PLAYER_PARAM, id);
      setResponsePage(PlayerEditPage.class, pageParameters);
    } catch (PlayerParseException e) {
      LOGGER.error("Failed to parse valid player from parseInput!", e);
    }
  }
}
