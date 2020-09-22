package de.bytewright.contestManager.backend.init;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.bytewright.contestManager.backend.persistence.dtos.Contest;
import de.bytewright.contestManager.backend.persistence.entities.ContestEntity;
import de.bytewright.contestManager.backend.util.json.JsonReader;

/**
 * mostly for testing...
 */
@Component
public class DataImporterForFile implements DataImporter {
  private static final Logger LOGGER = LoggerFactory.getLogger(DataImporterForFile.class);

  @Autowired
  private JsonReader jsonReader;
  @Autowired
  private ModelMapper modelMapper;

  @Override
  public List<ContestEntity> importContests() {
    String dirName = "contestImports";
    List<ContestEntity> entityList = Collections.emptyList();
    Path path = Path.of(dirName);
    if (Files.exists(path)) {
      LOGGER.info("Importing contests from path: {}", path);
      entityList = importDir(path);
    }
    return entityList;
  }

  private List<ContestEntity> importDir(Path path) {
    List<ContestEntity> contestEntities = new LinkedList<>();
    try {
      ObjectMapper deserializer = jsonReader.getDeserializer();
      List<File> fileList = Files.list(path)
          .map(Path::toFile)
          .collect(Collectors.toList());
      LOGGER.info("Found {} readable contest jsons, converting to entites", fileList.size());
      for (File file : fileList) {
        Contest contest = deserializer.readValue(file, Contest.class);
        contest.getOrganisers().forEach(organiser -> organiser.setContestIdentifier(contest.getUniqueId()));
        ContestEntity map = modelMapper.map(contest, ContestEntity.class);
        contestEntities.add(map);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return contestEntities;
  }

  private Stream<String> readToString(File file) {
    try {
      return Stream.of(FileUtils.readFileToString(file, StandardCharsets.UTF_8));
    } catch (IOException e) {
      LOGGER.error("Failed to read file {}", file, e);
    }
    return Stream.empty();
  }
}
