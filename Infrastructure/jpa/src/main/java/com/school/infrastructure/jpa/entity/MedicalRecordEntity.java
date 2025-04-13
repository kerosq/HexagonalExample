package com.school.infrastructure.jpa.entity;

import jakarta.persistence.*;
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
@Entity(name = "Medical_Record_Entity")
@Table(
    name = "medical_records",
    schema = "public",
    indexes = {@Index(name = "idx_medical_records_patient_id", columnList = "patient_id")})
public class MedicalRecordEntity implements Serializable {
  private static final long serialVersionUID = 6244557166218837342L;
  private Integer id;

  private PatientEntity patient;

  private AppointmentEntity appointment;

  private String diagnosis;

  private String treatment;

  private String notes;

  private DoctorEntity createdBy;

  private Instant createdAt;

  private Instant updatedAt;

  private Set<PrescriptionEntity> prescriptions = new LinkedHashSet<>();

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medical_records_id_gen")
  @SequenceGenerator(
      name = "medical_records_id_gen",
      sequenceName = "medical_records_id_seq",
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
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "appointment_id")
  public AppointmentEntity getAppointment() {
    return appointment;
  }

  @Column(name = "diagnosis", length = Integer.MAX_VALUE)
  public String getDiagnosis() {
    return diagnosis;
  }

  @Column(name = "treatment", length = Integer.MAX_VALUE)
  public String getTreatment() {
    return treatment;
  }

  @Column(name = "notes", length = Integer.MAX_VALUE)
  public String getNotes() {
    return notes;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "created_by")
  public DoctorEntity getCreatedBy() {
    return createdBy;
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

  @OneToMany(mappedBy = "medicalRecord")
  public Set<PrescriptionEntity> getPrescriptions() {
    return prescriptions;
  }
}
