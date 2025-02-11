package com.school.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String username;

  private String password;

  @Column(name = "is_enable")
  private boolean isEnabled; // Spring Security necesita que se llame isEnable

  @Column(name = "account_No_Expired")
  private boolean accountNoExpired; // Spring Security necesita que se llame asi

  @Column(name = "account_No_Locked")
  private boolean accountNoLocked; // Spring Security necesita que se llame asi

  @Column(name = "credentials_No_Expired")
  private boolean credentialNoExpired; // Spring Security necesita que se llame asi

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
      name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<RoleEntity> roles = new HashSet<>();
}
