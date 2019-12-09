package org.bytewright;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringBootApp {

  public static void main(String[] args) throws Exception {
    new SpringApplicationBuilder()
        .sources(SpringBootApp.class)
        .run(args);
  }
}
