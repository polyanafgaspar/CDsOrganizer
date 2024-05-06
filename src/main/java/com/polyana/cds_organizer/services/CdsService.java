package com.polyana.cds_organizer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.polyana.cds_organizer.models.Cds;
import com.polyana.cds_organizer.models.User;
import com.polyana.cds_organizer.repositories.CdsRepository;

@Service
public class CdsService {
  
  @Autowired
  private CdsRepository cdsRepository;

  @Autowired
  private UserService userService;

  public Cds findById(Long id) {
    Optional<Cds> cd = this.cdsRepository.findById(id);
    return cd.orElseThrow(() -> new RuntimeException(
      "Cd não encontrado!"
    ));
  }

  public List<Cds> findAllByUserId(Long userId) {
    List<Cds> cds = this.cdsRepository.findByUser_Id(userId);
    return cds;
   }


  @Transactional
  public Cds create(Cds obj){
    User user = this.userService.findById(obj.getUser().getId());
    obj.setId(null);
    obj.setUser(user);
    obj = this.cdsRepository.save(obj);
    return obj;
  }

  @Transactional
  public Cds update(Cds obj) {
    Cds newObj = findById(obj.getId());
    newObj.setCdName(obj.getCdName());
    return this.cdsRepository.save(newObj);
  }

  public void delete(Long id){
    findById(id);
    try {
      this.cdsRepository.deleteById(id);
    } catch (Exception e) {
      throw new RuntimeException("Não foi possível deletar o CD!");
    }
  }


}
