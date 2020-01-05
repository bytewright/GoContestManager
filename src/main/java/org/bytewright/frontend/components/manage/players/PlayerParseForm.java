package org.bytewright.frontend.components.manage.players;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.bytewright.backend.dto.Contest;
import org.bytewright.backend.dto.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayerParseForm extends Form<Player> {
  private static final Logger LOGGER = LoggerFactory.getLogger(PlayerParseForm.class);

  private final TextArea<String> textArea;
  private final Contest contest;

  public PlayerParseForm(String contentId, IModel<Player> model, Contest contest) {
    super(contentId, model);
    this.contest = contest;
    textArea = new TextArea<>("emailContent", Model.of(""));
    add(textArea);
  }

  @Override
  protected void onSubmit() {
    LOGGER.info("{} submitted! {}", this.getClass(), textArea.getModelObject());
  }
}
