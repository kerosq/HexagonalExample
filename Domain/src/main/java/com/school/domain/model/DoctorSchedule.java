package com.school.domain.model;

import java.time.LocalTime;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorSchedule {
  private Integer id;
  private Short dayOfWeek; // 1 (Lunes) a 7 (Domingo)
  private LocalTime startTime;
  private LocalTime endTime;
}
