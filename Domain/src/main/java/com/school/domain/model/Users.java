package com.school.domain.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {

  private Long Id;
  private String Name;
  private String Address;
  private int Age;
  private Integer Phone;
  private String Email;
  private String Password;
  private String Role;
  private String Status;
}
