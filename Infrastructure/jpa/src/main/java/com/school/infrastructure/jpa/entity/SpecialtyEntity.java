package com.school.infrastructure.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "Specialty_Entity")
@Table(
    name = "specialties",
    schema = "public",
    uniqueConstraints = {
      @UniqueConstraint(
          name = "specialties_name_key",
          columnNames = {"name"})
    })
public class SpecialtyEntity implements Serializable {
  private static final long serialVersionUID = -885627306005056187L;
  private Integer id;

  private String name;

  private String description;

  private Set<DoctorEntity> doctors = new LinkedHashSet<>();

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "specialties_id_gen")
  @SequenceGenerator(
      name = "specialties_id_gen",
      sequenceName = "specialties_id_seq",
      allocationSize = 1)
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

  @Column(name = "description", length = Integer.MAX_VALUE)
  public String getDescription() {
    return description;
  }

  @OneToMany(mappedBy = "specialty")
  public Set<DoctorEntity> getDoctors() {
    return doctors;
  }
}
