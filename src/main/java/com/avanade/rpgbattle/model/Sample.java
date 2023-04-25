package com.avanade.rpgbattle.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Sample implements Serializable {

    @Id
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;
}
