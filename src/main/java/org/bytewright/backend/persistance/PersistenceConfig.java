package org.bytewright.backend.persistance;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("org.bytewright.backend.persistance.repositories")
@EntityScan("org.bytewright.backend.persistance.entities")
@Configuration
public class PersistenceConfig {
  private static final Logger LOGGER = LoggerFactory.getLogger(PersistenceConfig.class);

  //  @Bean
  //  public DataSource dataSource() {
  //    EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
  //    EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2)
  //        .setName("GCM-DB")
  //        .addDefaultScripts()
  //        .setScriptEncoding("UTF-8")
  //        .build();
  //    LOGGER.info("Created DB: {}", db);
  //    return db;
  //  }
  @Bean
  public DataSource getDataSource() {
    DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.driverClassName("org.h2.Driver");
    dataSourceBuilder.url("jdbc:h2:file:./devdb/h2.db");
    dataSourceBuilder.username("sa");
    dataSourceBuilder.password("");
    DataSource db = dataSourceBuilder.build();
    LOGGER.info("Created DB: {}", db);
    return db;
  }
}
