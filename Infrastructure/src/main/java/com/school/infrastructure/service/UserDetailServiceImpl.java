package com.school.infrastructure.service;

import com.school.infrastructure.persistence.entity.UserEntity;
import com.school.infrastructure.persistence.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity userEntity =
        this.userRepository
            .findUserEntityByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

    userEntity
        .getRoles()
        .forEach(
            role ->
                authorityList.add(
                    new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

    userEntity.getRoles().stream()
        .flatMap(role -> role.getPermissionList().stream())
        .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

    return new User(
        userEntity.getUsername(),
        userEntity.getPassword(),
        userEntity.isEnabled(),
        userEntity.isAccountNoExpired(),
        userEntity.isCredentialNoExpired(),
        userEntity.isAccountNoLocked(),
        authorityList);
  }
}
