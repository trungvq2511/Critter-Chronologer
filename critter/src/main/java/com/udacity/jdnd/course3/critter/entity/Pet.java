package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.entity.enumerate.PetType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private PetType type;
    private String name;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private LocalDate birthDate;
    private String notes;

    public Pet(PetDTO petDTO) {
        this.type = petDTO.getType();
        this.name = petDTO.getName();
        this.birthDate = petDTO.getBirthDate();
        this.notes = petDTO.getNotes();
    }
}
