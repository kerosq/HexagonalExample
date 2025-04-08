package com.school.domain.service;

import com.school.domain.model.User;
import java.util.List;

/** Si hubiera que usar un servicio externo de servicio implementar y llamar esta clase */
public interface UserService {

  List<User> getUsers();

  User getUserById(Long id);
}
