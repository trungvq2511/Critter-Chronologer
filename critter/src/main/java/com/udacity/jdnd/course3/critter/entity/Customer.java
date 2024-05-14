package com.udacity.jdnd.course3.critter.entity;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String phoneNumber;
    private String notes;
    @OneToMany(mappedBy = "customer")
    private List<Pet> pets = new ArrayList<>();

    public Customer(CustomerDTO customerDTO) {
        this.id = customerDTO.getId();
        this.name = customerDTO.getName();
        this.phoneNumber = customerDTO.getPhoneNumber();
        this.notes = customerDTO.getNotes();
    }
}
