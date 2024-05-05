package com.polyana.cds_organizer.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = User.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
  public static final String TABLE_NAME = "user";

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotBlank
    @Size(min = 2, max = 100)
    private String username;

    @Column(name = "password", length = 60, nullable = false)
    @JsonProperty(access = Access.WRITE_ONLY)
    @NotBlank
    @Size(min = 4, max = 60)
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Cds> cds = new ArrayList<Cds>();
}
