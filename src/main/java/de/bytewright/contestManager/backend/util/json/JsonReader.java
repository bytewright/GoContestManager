package de.bytewright.contestManager.backend.util.json;

import javax.money.CurrencyUnit;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import de.bytewright.contestManager.backend.util.GoRank;

@Component
public class JsonReader {
  public ObjectMapper getDeserializer() {
    return JsonMapper.builder()
        .addModule(new ParameterNamesModule())
        .addModule(new Jdk8Module())
        .addModule(new JavaTimeModule())
        .addModule(getProjectModule())
        .addModule(getMoneyModule())
        .build();
  }

  private Module getMoneyModule() {
    SimpleModule module = new SimpleModule("MoneyDeserializer",
        new Version(1, 0, 0, null, null, null));
    module.addDeserializer(CurrencyUnit.class, new CurrencyUnitDeserializer());
    return module;
  }

  private SimpleModule getProjectModule() {
    /* All other types are auto converted by jackson */
    SimpleModule module = new SimpleModule("ContestDeserializer",
        new Version(1, 0, 0, null, null, null));
    module.addDeserializer(GoRank.class, new GoRankDeserializer());
    return module;
  }
}
