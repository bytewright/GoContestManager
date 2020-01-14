package org.bytewright.backend.persistence;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@EnableJpaRepositories(basePackages = "org.bytewright.backend.persistence.repositories")
@EntityScan(basePackages = "org.bytewright.backend.persistence.entities")
@Configuration
public class PersistenceConfig {
  private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceConfig.class);

  @Bean
  public DataSource dataSource() {
    EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
        .setType(EmbeddedDatabaseType.H2)
        .setScriptEncoding("UTF-8")
        .build();
    LOGGER.info("Created DB: {}", db);
    return db;
  }
}