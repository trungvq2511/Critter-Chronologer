package com.udacity.jdnd.course3.critter.dto;

import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.enumerate.PetType;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the form that pet request and response data takes. Does not map
 * to the database directly.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PetDTO {
    private long id;
    private PetType type;
    private String name;
    private long ownerId;
    private LocalDate birthDate;
    private String notes;

    public PetDTO(Pet pet) {
        this.id = pet.getId();
        this.type = pet.getType();
        this.name = pet.getName();
        this.ownerId = pet.getCustomer().getId();
        this.birthDate = pet.getBirthDate();
        this.notes = pet.getNotes();
    }

   public static List<PetDTO> convertToDTOList(List<Pet> petList) {
        List<PetDTO> petDTOS = new ArrayList<>();
        petList.stream().forEach((pet) -> {
            petDTOS.add(new PetDTO(pet));
        });
        return petDTOS;
    }
}
