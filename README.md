# GoContestManager

Manager for Go board game tournaments.

Lists available tournaments [here](http://localhost:8080/home).

Allows player management for tournaments as well es financial and helper management.

***I could really use some help from a designer :D***

## Architecture
See dep.tree file for detailed dependency graph.

Uses Wicket Framework as frontend and spring boot as backend.

Security is implemented using apache shiro.

App entry point is org.bytewright.SpringBootApp,
Wicket is implemented as a request filter and configured in class:
org.bytewright.frontend.WicketApplicationConfiguration

Wicket uses backend services for data retrieval which are wired by spring
using annotation `@SpringBean`:
```
  @SpringBean
  private ContestService contestService;
```

### Data storage
Uses an `H2` embedded db for easy local development, see:
org.bytewright.backend.persistence.PersistenceConfig

## Usage
Currently, everything is initialized during startup
with random data for testing.

There is a lot of logging active, see src/main/resources/logback.xml for settings

