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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Appointment_Entity")
@Table(
    name = "appointments",
    schema = "public",
    indexes = {
      @Index(name = "idx_appointments_patient_id", columnList = "patient_id"),
      @Index(name = "idx_appointments_doctor_id", columnList = "doctor_id"),
      @Index(name = "idx_appointments_date", columnList = "appointment_date")
    })
public class AppointmentEntity implements Serializable {
  private static final long serialVersionUID = -5956600486663762870L;
  private Integer id;

  private PatientEntity patient;

  private DoctorEntity doctor;

  private Instant appointmentDate;

  private String reason;

  private String status;

  private String notes;

  private Instant createdAt;

  private Instant updatedAt;

  private Set<BillingEntity> billings = new LinkedHashSet<>();

  private Set<MedicalRecordEntity> medicalRecords = new LinkedHashSet<>();

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointments_id_gen")
  @SequenceGenerator(
      name = "appointments_id_gen",
      sequenceName = "appointments_id_seq",
      allocationSize = 1)
  @Column(name = "id", nullable = false)
  public Integer getId() {
    return id;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "patient_id")
  public PatientEntity getPatient() {
    return patient;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.RESTRICT)
  @JoinColumn(name = "doctor_id")
  public DoctorEntity getDoctor() {
    return doctor;
  }

  @NotNull
  @Column(name = "appointment_date", nullable = false)
  public Instant getAppointmentDate() {
    return appointmentDate;
  }

  @NotNull
  @Column(name = "reason", nullable = false, length = Integer.MAX_VALUE)
  public String getReason() {
    return reason;
  }

  @Size(max = 20)
  @Column(name = "status", length = 20)
  public String getStatus() {
    return status;
  }

  @Column(name = "notes", length = Integer.MAX_VALUE)
  public String getNotes() {
    return notes;
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

  @OneToMany(mappedBy = "appointment")
  public Set<BillingEntity> getBillings() {
    return billings;
  }

  @OneToMany(mappedBy = "appointment")
  public Set<MedicalRecordEntity> getMedicalRecords() {
    return medicalRecords;
  }
}
