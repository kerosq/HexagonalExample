package com.school.infrastructure.jpa.enums.converters;

import com.school.infrastructure.jpa.enums.UserStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserStatusConverter implements AttributeConverter<UserStatus, String> {

  @Override
  public String convertToDatabaseColumn(UserStatus attribute) {
    if (attribute == null) {
      return null;
    }
    return attribute.getValue();
  }

  @Override
  public UserStatus convertToEntityAttribute(String dbData) {
    if (dbData == null) {
      return null;
    }
    for (UserStatus status : UserStatus.values()) {
      if (status.getValue().equalsIgnoreCase(dbData)) {
        return status;
      }
    }
    throw new IllegalArgumentException("Unknown value: " + dbData);
  }
}
