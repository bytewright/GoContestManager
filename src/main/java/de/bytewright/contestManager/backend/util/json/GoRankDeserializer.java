package de.bytewright.contestManager.backend.util.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import de.bytewright.contestManager.backend.persistence.dtos.Contest;
import de.bytewright.contestManager.backend.util.GoRank;

public class GoRankDeserializer extends StdDeserializer<GoRank> {

  public GoRankDeserializer() {
    super(Contest.class);
  }

  @Override
  public GoRank deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
    return GoRank.fromAbbrv(parser.getValueAsString());
  }
}
