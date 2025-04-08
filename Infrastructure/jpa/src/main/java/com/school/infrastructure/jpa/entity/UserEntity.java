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
  private Long id;

  @Column(name = "NAME", nullable = false)
  private String name;

  @Column(name = "ADDRESS", nullable = false)
  private String address;

  @Column(name = "AGE", nullable = false)
  private int age;

  @Column(name = "PHONE", nullable = false)
  private Integer phone;

  @Column(name = "EMAIL", nullable = false)
  private String email;

  @Column(name = "PASSWORD", nullable = false)
  private String password;

  @Column(name = "ROLE", nullable = false)
  private String role;

  @Column(name = "STATUS", nullable = false)
  private String status;
}
