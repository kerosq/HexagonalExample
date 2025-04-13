package com.school.infrastructure.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Entity(name = "Billing_Entity")
@Table(name = "billing", schema = "public")
public class BillingEntity implements Serializable {
  private static final long serialVersionUID = 5837645598335049640L;
  private Integer id;

  private AppointmentEntity appointment;

  private PatientEntity patient;

  private BigDecimal amount;

  private String paymentStatus;

  private Instant paymentDate;

  private String paymentMethod;

  private BigDecimal insuranceCoverage;

  private Instant createdAt;

  private Instant updatedAt;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "billing_id_gen")
  @SequenceGenerator(name = "billing_id_gen", sequenceName = "billing_id_seq", allocationSize = 1)
  @Column(name = "id", nullable = false)
  public Integer getId() {
    return id;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  @JoinColumn(name = "appointment_id")
  public AppointmentEntity getAppointment() {
    return appointment;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.RESTRICT)
  @JoinColumn(name = "patient_id")
  public PatientEntity getPatient() {
    return patient;
  }

  @NotNull
  @Column(name = "amount", nullable = false, precision = 10, scale = 2)
  public BigDecimal getAmount() {
    return amount;
  }

  @Size(max = 20)
  @Column(name = "payment_status", length = 20)
  public String getPaymentStatus() {
    return paymentStatus;
  }

  @Column(name = "payment_date")
  public Instant getPaymentDate() {
    return paymentDate;
  }

  @Size(max = 50)
  @Column(name = "payment_method", length = 50)
  public String getPaymentMethod() {
    return paymentMethod;
  }

  @ColumnDefault("0")
  @Column(name = "insurance_coverage", precision = 10, scale = 2)
  public BigDecimal getInsuranceCoverage() {
    return insuranceCoverage;
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
}
