package com.school.infrastructure.jpa.enums.converters;

import com.school.infrastructure.jpa.enums.UserRoles;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserRolesConverter implements AttributeConverter<UserRoles, String> {

  @Override
  public String convertToDatabaseColumn(UserRoles role) {
    if (role == null) {
      return null;
    }
    return role.getValue();
  }

  @Override
  public UserRoles convertToEntityAttribute(String dbData) {
    if (dbData == null) {
      return null;
    }
    for (UserRoles role : UserRoles.values()) {
      if (role.getValue().equalsIgnoreCase(dbData)) {
        return role;
      }
    }
    throw new IllegalArgumentException("Unknown value: " + dbData);
  }
}
