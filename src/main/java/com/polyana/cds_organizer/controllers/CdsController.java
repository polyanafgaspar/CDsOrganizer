package com.polyana.cds_organizer.controllers;

import java.net.URI;
import java.util.List;

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

import com.polyana.cds_organizer.models.Cds;
import com.polyana.cds_organizer.services.CdsService;

@RestController
@RequestMapping("/cds")
@Validated
public class CdsController {
  
  @Autowired
  private CdsService cdsService;

  @GetMapping("/{id}")
  public ResponseEntity<Cds> findById(@PathVariable Long id) {
    Cds cd = this.cdsService.findById(id);
    return ResponseEntity.ok().body(cd);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Cds>> findAllByUserId(@PathVariable Long userId) {
    List<Cds> objs = this.cdsService.findAllByUserId(userId);
    return ResponseEntity.ok().body(objs);
  }

  @PostMapping
  public ResponseEntity<Void> create(@RequestBody Cds obj) {
    this.cdsService.create(obj);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(@RequestBody Cds obj, @PathVariable Long id) {
    obj.setId(id);
    this.cdsService.update(obj);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    this.cdsService.delete(id);
    return ResponseEntity.noContent().build();
  }

}
