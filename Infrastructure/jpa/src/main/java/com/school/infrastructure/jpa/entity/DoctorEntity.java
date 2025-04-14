package com.school.infrastructure.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;
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
@Entity(name = "Doctor_Entity")
@Table(
    name = "doctors",
    schema = "public",
    indexes = {
      @Index(name = "idx_doctors_user_id", columnList = "user_id"),
      @Index(name = "idx_doctors_specialty", columnList = "specialty_id")
    },
    uniqueConstraints = {
      @UniqueConstraint(
          name = "doctors_license_number_key",
          columnNames = {"license_number"}),
      @UniqueConstraint(
          name = "doctors_email_key",
          columnNames = {"email"})
    })
public class DoctorEntity implements Serializable {
  private static final long serialVersionUID = -1717351768008006104L;
  private Integer id;

  private UserEntity user;

  private String firstName;

  private String lastName;

  private SpecialtyEntity specialty;

  private String licenseNumber;

  private String phone;

  private String email;

  private LocalDate hireDate;

  private Boolean active;

  private OffsetDateTime createdAt;

  private OffsetDateTime updatedAt;

  private Set<AppointmentEntity> appointments = new LinkedHashSet<>();

  private Set<DoctorScheduleEntity> doctorSchedules = new LinkedHashSet<>();

  private Set<MedicalRecordEntity> medicalRecords = new LinkedHashSet<>();

  private Set<PrescriptionEntity> prescriptions = new LinkedHashSet<>();

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctors_id_gen")
  @SequenceGenerator(name = "doctors_id_gen", sequenceName = "doctors_id_seq", allocationSize = 1)
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

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.RESTRICT)
  @JoinColumn(name = "specialty_id")
  public SpecialtyEntity getSpecialty() {
    return specialty;
  }

  @Size(max = 50)
  @NotNull
  @Column(name = "license_number", nullable = false, length = 50)
  public String getLicenseNumber() {
    return licenseNumber;
  }

  @Size(max = 20)
  @NotNull
  @Column(name = "phone", nullable = false, length = 20)
  public String getPhone() {
    return phone;
  }

  @Size(max = 100)
  @NotNull
  @Column(name = "email", nullable = false, length = 100)
  public String getEmail() {
    return email;
  }

  @Column(name = "hire_date")
  public LocalDate getHireDate() {
    return hireDate;
  }

  @ColumnDefault("true")
  @Column(name = "active")
  public Boolean getActive() {
    return active;
  }

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "created_at")
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "updated_at")
  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  @OneToMany(mappedBy = "doctor")
  public Set<AppointmentEntity> getAppointments() {
    return appointments;
  }

  @OneToMany(mappedBy = "doctor")
  public Set<DoctorScheduleEntity> getDoctorSchedules() {
    return doctorSchedules;
  }

  @OneToMany(mappedBy = "createdBy")
  public Set<MedicalRecordEntity> getMedicalRecords() {
    return medicalRecords;
  }

  @OneToMany(mappedBy = "prescribedBy")
  public Set<PrescriptionEntity> getPrescriptions() {
    return prescriptions;
  }
}
