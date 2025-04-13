package com.school.infrastructure.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Prescription_Entity")
@Table(
    name = "prescriptions",
    schema = "public",
    indexes = {@Index(name = "idx_prescriptions_medical_record", columnList = "medical_record_id")})
public class PrescriptionEntity implements Serializable {
  private static final long serialVersionUID = 7834317560889305388L;
  private Integer id;

  private MedicalRecordEntity medicalRecord;

  private String medicationName;

  private String dosage;

  private String frequency;

  private String duration;

  private String notes;

  private DoctorEntity prescribedBy;

  private Instant prescribedAt;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prescriptions_id_gen")
  @SequenceGenerator(
      name = "prescriptions_id_gen",
      sequenceName = "prescriptions_id_seq",
      allocationSize = 1)
  @Column(name = "id", nullable = false)
  public Integer getId() {
    return id;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "medical_record_id")
  public MedicalRecordEntity getMedicalRecord() {
    return medicalRecord;
  }

  @Size(max = 100)
  @NotNull
  @Column(name = "medication_name", nullable = false, length = 100)
  public String getMedicationName() {
    return medicationName;
  }

  @Size(max = 50)
  @NotNull
  @Column(name = "dosage", nullable = false, length = 50)
  public String getDosage() {
    return dosage;
  }

  @Size(max = 50)
  @NotNull
  @Column(name = "frequency", nullable = false, length = 50)
  public String getFrequency() {
    return frequency;
  }

  @Size(max = 50)
  @Column(name = "duration", length = 50)
  public String getDuration() {
    return duration;
  }

  @Column(name = "notes", length = Integer.MAX_VALUE)
  public String getNotes() {
    return notes;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "prescribed_by")
  public DoctorEntity getPrescribedBy() {
    return prescribedBy;
  }

  @ColumnDefault("CURRENT_TIMESTAMP")
  @Column(name = "prescribed_at")
  public Instant getPrescribedAt() {
    return prescribedAt;
  }
}
