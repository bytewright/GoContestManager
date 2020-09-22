package de.bytewright.contestManager.backend.persistence.converter;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.bytewright.contestManager.backend.persistence.dtos.Location;
import de.bytewright.contestManager.backend.persistence.dtos.Organiser;
import de.bytewright.contestManager.backend.persistence.entities.LocationEmbeddable;
import de.bytewright.contestManager.backend.persistence.entities.OrganizerEntity;

@Component
public class OrganizerToDtoConverter implements Converter<OrganizerEntity, Organiser> {

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public Organiser convert(MappingContext<OrganizerEntity, Organiser> context) {
    OrganizerEntity source = context.getSource();
    if (source == null) {
      return null;
    }
    Organiser dto = new Organiser();
    dto.setUniqueId(source.getId());
    dto.setContestIdentifier(source.getContestEntity().getShortIdentifier());
    dto.setEmailAddr(source.getEmailAddr());
    dto.setName(source.getName());
    dto.setSurname(source.getSurname());
    LocationEmbeddable locationEntity = source.getLocation();
    Location location = new Location();
    if (locationEntity != null) {
      modelMapper.map(locationEntity, location);
    }
    dto.setAddress(location);
    dto.setPhoneNumber(source.getPhoneNumber());

    return dto;
  }
}
