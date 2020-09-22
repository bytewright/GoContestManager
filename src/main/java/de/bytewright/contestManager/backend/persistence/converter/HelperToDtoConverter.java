package de.bytewright.contestManager.backend.persistence.converter;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.bytewright.contestManager.backend.persistence.dtos.Helper;
import de.bytewright.contestManager.backend.persistence.dtos.Location;
import de.bytewright.contestManager.backend.persistence.entities.HelperEntity;
import de.bytewright.contestManager.backend.persistence.entities.LocationEmbeddable;

@Component
public class HelperToDtoConverter implements Converter<HelperEntity, Helper> {

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public Helper convert(MappingContext<HelperEntity, Helper> context) {
    HelperEntity source = context.getSource();
    if (source == null) {
      return null;
    }
    Helper dto = new Helper();
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
