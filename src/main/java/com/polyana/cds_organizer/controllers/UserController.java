package com.polyana.cds_organizer.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.polyana.cds_organizer.models.User;
import com.polyana.cds_organizer.services.UserService;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

  @Autowired
  private UserService userService;

  // localhost:8080/user/1
  @GetMapping("/{id}")
  public ResponseEntity<User> findById(@PathVariable Long id) {
    User user = this.userService.findById(id);
    return ResponseEntity.ok().body(user);
  }

  @PostMapping
  public ResponseEntity<Void> create(@RequestBody User obj) {
    this.userService.create(obj);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@RequestBody User obj, @PathVariable Long id) {
    obj.setId(id);
    this.userService.update(obj);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    this.userService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
