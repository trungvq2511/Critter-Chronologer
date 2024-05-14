package com.udacity.jdnd.course3.critter.dto;

import com.udacity.jdnd.course3.critter.entity.Customer;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the form that customer request and response data takes. Does not map
 * to the database directly.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerDTO {
    private long id;
    private String name;
    private String phoneNumber;
    private String notes;
    private List<Long> petIds;

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.phoneNumber = customer.getPhoneNumber();
        this.notes = customer.getNotes();

        List<Long> petIds = new ArrayList<>();
        if (customer.getPets() != null) {
            customer.getPets().stream().forEach((pet -> {
                petIds.add(pet.getId());
            }));
        }
        this.petIds = petIds;
    }

    public static List<CustomerDTO> convertToDTOList(List<Customer> customerList) {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        customerList.stream().forEach((customer) -> {
            customerDTOS.add(new CustomerDTO(customer));
        });
        return customerDTOS;
    }
}
