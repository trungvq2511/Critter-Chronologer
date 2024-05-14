package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.PetDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.exception.NotFoundException;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PetService {
    private PetRepository petRepository;
    private CustomerRepository customerRepository;

    public PetService(PetRepository petRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.customerRepository = customerRepository;
    }

    public Pet savePet(PetDTO petDTO) {
        Pet pet = new Pet(petDTO);
        Customer customer = customerRepository.getOne(petDTO.getOwnerId());

        //check not found
        if (customer == null) {
            throw new NotFoundException("Customer not found.");
        }

        //set pet for customer
        customer.getPets().add(pet);
        pet.setCustomer(customer);

        Pet save = petRepository.save(pet);
        return save;
    }

    public Pet getPet(long petId) {
        Pet pet = petRepository.getOne(petId);

        //check not found
        if (pet == null) {
            throw new NotFoundException("Pet not found.");
        }

        return pet;
    }

    public List<Pet> getPets() {
        List<Pet> petList = petRepository.findAll();

        return petList;
    }

    public List<Pet> getPetsByOwner(long ownerId) {
        List<Pet> petList = petRepository.findPetByCustomerId(ownerId);

        return petList;
    }


}
