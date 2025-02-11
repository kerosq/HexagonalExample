package com.school.infrastructure;

import com.school.infrastructure.persistence.entity.PermissionEntity;
import com.school.infrastructure.persistence.entity.RoleEntity;
import com.school.infrastructure.persistence.entity.RoleEnum;
import com.school.infrastructure.persistence.entity.UserEntity;
import com.school.infrastructure.persistence.repository.UserRepository;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InfrastructureApplication {

  public static void main(String[] args) {
    SpringApplication.run(InfrastructureApplication.class, args);
  }

  @Bean
  CommandLineRunner init(UserRepository userRepository) {
    return args -> {
      PermissionEntity createPermission = PermissionEntity.builder().name("create").build();
      PermissionEntity readPermission = PermissionEntity.builder().name("read").build();
      PermissionEntity updatePermission = PermissionEntity.builder().name("update").build();
      PermissionEntity deletePermission = PermissionEntity.builder().name("delete").build();
      PermissionEntity refactorPermission = PermissionEntity.builder().name("refactor").build();

      RoleEntity adminRole =
          RoleEntity.builder()
              .roleEnum(RoleEnum.ADMIN)
              .permissionList(
                  Set.of(createPermission, refactorPermission, updatePermission, deletePermission))
              .build();
      RoleEntity userRole =
          RoleEntity.builder()
              .roleEnum(RoleEnum.USER)
              .permissionList(Set.of(readPermission))
              .build();

      RoleEntity developerRole =
          RoleEntity.builder()
              .roleEnum(RoleEnum.DEVELOPER)
              .permissionList(Set.of(refactorPermission))
              .build();

      UserEntity userKevin =
          UserEntity.builder()
              .username("kevin")
              .password("$2a$10$7OKdXZ2RMqnRan.I2CzE6O8iOVU/L69boKmOgQivCLvRRD4TH4s1S")
              .isEnabled(true)
              .accountNoExpired(true)
              .accountNoLocked(true)
              .credentialNoExpired(true)
              .roles(Set.of(adminRole))
              .build();

      UserEntity userCarl =
          UserEntity.builder()
              .username("jhon")
              .password("$2a$10$7OKdXZ2RMqnRan.I2CzE6O8iOVU/L69boKmOgQivCLvRRD4TH4s1S")
              .isEnabled(true)
              .accountNoExpired(true)
              .accountNoLocked(true)
              .credentialNoExpired(true)
              .roles(Set.of(userRole))
              .build();

      userRepository.saveAll(Set.of(userKevin, userCarl));
    };
  }
}
