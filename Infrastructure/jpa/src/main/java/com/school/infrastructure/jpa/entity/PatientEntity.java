package com.school.infrastructure.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Patient_Entity")
@Table(
    name = "patients",
    schema = "public",
    indexes = {@Index(name = "idx_patients_user_id", columnList = "user_id")})
public class PatientEntity implements Serializable {
  private static final long serialVersionUID = -1366698111351199916L;
  private Integer id;

  private UserEntity user;

  private String firstName;

  private String lastName;

  private LocalDate birthDate;

  private String gender;

  private String address;

  private String phone;

  private String email;

  private String insuranceNumber;

  private Instant createdAt;

  private Instant updatedAt;

  private Set<AppointmentEntity> appointments = new LinkedHashSet<>();

  private Set<BillingEntity> billings = new LinkedHashSet<>();

  private Set<MedicalRecordEntity> medicalRecords = new LinkedHashSet<>();

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patients_id_gen")
  @SequenceGenerator(name = "patients_id_gen", sequenceName = "patients_id_seq", allocationSize = 1)
  @Column(name = "id", nullable = false)
  public Integer getId() {
    return id;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "user_id")
  public UserEntity getUser() {
    return user;
  }

  @Size(max = 50)
  @NotNull
  @Column(name = "first_name", nullable = false, length = 50)
  public String getFirstName() {
    return firstName;
  }

  @Size(max = 50)
  @NotNull
  @Column(name = "last_name", nullable = false, length = 50)
  public String getLastName() {
    return lastName;
  }

  @NotNull
  @Column(name = "birth_date", nullable = false)
  public LocalDate getBirthDate() {
    return birthDate;
  }

  @Column(name = "gender", length = Integer.MAX_VALUE)
  public String getGender() {
    return gender;
  }

  @Column(name = "address", length = Integer.MAX_VALUE)
  public String getAddress() {
    return address;
  }

  @Size(max = 20)
  @NotNull
  @Column(name = "phone", nullable = false, length = 20)
  public String getPhone() {
    return phone;
  }

  @Size(max = 100)
  @Column(name = "email", length = 100)
  public String getEmail() {
    return email;
  }

  @Size(max = 50)
  @Column(name = "insurance_number", length = 50)
  public String getInsuranceNumber() {
    return insuranceNumber;
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

  @OneToMany(mappedBy = "patient")
  public Set<AppointmentEntity> getAppointments() {
    return appointments;
  }

  @OneToMany(mappedBy = "patient")
  public Set<BillingEntity> getBillings() {
    return billings;
  }

  @OneToMany(mappedBy = "patient")
  public Set<MedicalRecordEntity> getMedicalRecords() {
    return medicalRecords;
  }
}
