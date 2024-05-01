package com.polyana.cds_organizer.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polyana.cds_organizer.models.User;
import com.polyana.cds_organizer.repositories.CdsRepository;
import com.polyana.cds_organizer.repositories.UserRepository;

@Service

public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User findById(Long id) {
    Optional<User> user = this.userRepository.findById(id);
    return user.orElseThrow(() -> new RuntimeException(
      "Usuário não encontrado!"
    ));
  }

  @Transactional
  public User create(User obj) {
    obj.setId(null);
    obj = this.userRepository.save(obj);
    return obj;
  }

  @Transactional
  public User update(User obj) {
    User newObj = findById(obj.getId());
    newObj.setPassword(obj.getPassword());
    return this.userRepository.save(newObj);
  }

  public void delete(Long id) {
    findById(id);
    try {
      this.userRepository.deleteById(id);
    } catch (Exception e) {
      throw new RuntimeException("Não é possível excluir o usuário!");
    }
  }

}
