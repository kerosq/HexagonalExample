package com.school.infrastructure.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "APP_USER")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", nullable = false)
  private Long Id;

  @Column(name = "NAME", nullable = false)
  private String Name;

  @Column(name = "ADDRESS", nullable = false)
  private String Address;

  @Column(name = "AGE", nullable = false)
  private int Age;

  @Column(name = "PHONE", nullable = false)
  private Integer Phone;

  @Column(name = "EMAIL", nullable = false)
  private String Email;

  @Column(name = "PASSWORD", nullable = false)
  private String Password;

  @Column(name = "ROLE", nullable = false)
  private String Role;

  @Column(name = "STATUS", nullable = false)
  private String Status;
}
