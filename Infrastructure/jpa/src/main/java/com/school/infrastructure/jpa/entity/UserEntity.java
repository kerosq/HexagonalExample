package com.school.infrastructure.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "User_Entity")
@Table(
    name = "users",
    schema = "public",
    indexes = {
      @Index(name = "idx_users_email", columnList = "email"),
      @Index(name = "idx_users_role", columnList = "role")
    },
    uniqueConstraints = {
      @UniqueConstraint(
          name = "users_email_key",
          columnNames = {"email"})
    })
public class UserEntity implements Serializable {
  private static final long serialVersionUID = 4205115831018755988L;
  private Integer id;

  private String name;

  private String email;

  private String password;

  private Integer age;

  private String phone;

  private String address;

  private String role;

  private String status;

  private Instant createdAt;

  private Instant updatedAt;

  private Set<DoctorEntity> doctors = new LinkedHashSet<>();

  private Set<PatientEntity> patients = new LinkedHashSet<>();

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_id_gen")
  @SequenceGenerator(name = "users_id_gen", sequenceName = "users_id_seq", allocationSize = 1)
  @Column(name = "id", nullable = false)
  public Integer getId() {
    return id;
  }

  @Size(max = 100)
  @NotNull
  @Column(name = "name", nullable = false, length = 100)
  public String getName() {
    return name;
  }

  @Size(max = 100)
  @NotNull
  @Column(name = "email", nullable = false, length = 100)
  public String getEmail() {
    return email;
  }

  @Size(max = 255)
  @NotNull
  @Column(name = "password", nullable = false)
  public String getPassword() {
    return password;
  }

  @Column(name = "age")
  public Integer getAge() {
    return age;
  }

  @Size(max = 20)
  @Column(name = "phone", length = 20)
  public String getPhone() {
    return phone;
  }

  @Column(name = "address", length = Integer.MAX_VALUE)
  public String getAddress() {
    return address;
  }

  @Size(max = 20)
  @Column(name = "role", length = 20)
  public String getRole() {
    return role;
  }

  @Size(max = 20)
  @Column(name = "status", length = 20)
  public String getStatus() {
    return status;
  }

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "created_at")
  public Instant getCreatedAt() {
    return createdAt;
  }

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "updated_at")
  public Instant getUpdatedAt() {
    return updatedAt;
  }

  @OneToMany(mappedBy = "user")
  public Set<DoctorEntity> getDoctors() {
    return doctors;
  }

  @OneToMany(mappedBy = "user")
  public Set<PatientEntity> getPatients() {
    return patients;
  }
}
