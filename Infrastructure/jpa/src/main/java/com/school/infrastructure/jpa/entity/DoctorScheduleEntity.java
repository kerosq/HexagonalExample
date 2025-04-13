package com.school.infrastructure.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalTime;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Doctor_Schedule_Entity")
@Table(name = "doctor_schedules", schema = "public")
public class DoctorScheduleEntity implements Serializable {
  private static final long serialVersionUID = 7326507286734985216L;
  private Integer id;

  private DoctorEntity doctor;

  private Short dayOfWeek;

  private LocalTime startTime;

  private LocalTime endTime;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_schedules_id_gen")
  @SequenceGenerator(
      name = "doctor_schedules_id_gen",
      sequenceName = "doctor_schedules_id_seq",
      allocationSize = 1)
  @Column(name = "id", nullable = false)
  public Integer getId() {
    return id;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "doctor_id")
  public DoctorEntity getDoctor() {
    return doctor;
  }

  @Column(name = "day_of_week")
  public Short getDayOfWeek() {
    return dayOfWeek;
  }

  @NotNull
  @Column(name = "start_time", nullable = false)
  public LocalTime getStartTime() {
    return startTime;
  }

  @NotNull
  @Column(name = "end_time", nullable = false)
  public LocalTime getEndTime() {
    return endTime;
  }
}
