package de.bytewright.contestManager.backend.persistence;

import javax.sql.DataSource;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import de.bytewright.contestManager.backend.persistence.entities.EntitiesMarker;
import de.bytewright.contestManager.backend.persistence.repositories.ReposMarker;

@EnableJpaRepositories(basePackageClasses = ReposMarker.class)
@EntityScan(basePackageClasses = EntitiesMarker.class)
@Configuration
public class PersistenceConfig {
  private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceConfig.class);

  @Bean
  public DataSource dataSource() {
    EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
        .setType(EmbeddedDatabaseType.H2)
        .setScriptEncoding("UTF-8")
        .addDefaultScripts()
        .setName("gcmDB")
        .build();
    LOGGER.info("Created DB: {}", db);
    return db;
  }

  @Bean
  public ModelMapper modelMapper() {
    /* This converts db entites to dtos used by frontend */
    /* https://www.baeldung.com/entity-to-and-from-dto-for-a-java-spring-application */
    return new ModelMapper();
  }
}
