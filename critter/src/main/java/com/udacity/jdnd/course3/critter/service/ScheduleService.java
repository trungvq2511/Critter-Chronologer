package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Pet;
import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.exception.NotFoundException;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ScheduleService {
    private ScheduleRepository scheduleRepository;
    private PetRepository petRepository;
    private EmployeeRepository employeeRepository;
    private CustomerRepository customerRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, PetRepository petRepository, EmployeeRepository employeeRepository, CustomerRepository customerRepository) {
        this.scheduleRepository = scheduleRepository;
        this.petRepository = petRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
    }

    public Schedule createSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = new Schedule();
        schedule.setDate(scheduleDTO.getDate());
        schedule.setActivities(scheduleDTO.getActivities());
        schedule.setPets(petRepository.findAllById(scheduleDTO.getPetIds()));
        schedule.setEmployees(employeeRepository.findAllById(scheduleDTO.getEmployeeIds()));
        Schedule save = scheduleRepository.save(schedule);
        return save;
    }

    public List<Schedule> getAllSchedules() {
        List<Schedule> scheduleList = scheduleRepository.findAll();

        return scheduleList;
    }

    public List<Schedule> getScheduleForPet(long petId) {
        List<Schedule> scheduleList = scheduleRepository.findScheduleByPetsId(petId);

        return scheduleList;
    }

    public List<Schedule> getScheduleForEmployee(long employeeId) {
        List<Schedule> scheduleList = scheduleRepository.findScheduleByEmployeesId(employeeId);

        return scheduleList;
    }

    public List<ScheduleDTO> getScheduleForCustomer(long customerId) {
        Customer customer = customerRepository.getOne(customerId);

        //check not found
        if (customer == null) {
            throw new NotFoundException("Customer not found.");
        }

        List<Pet> pets = customer.getPets();

        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        pets.stream().forEach((pet) -> {
            List<Schedule> scheduleList = scheduleRepository.findScheduleByPetsId(pet.getId());
            scheduleList.stream().forEach((schedule) -> {
                scheduleDTOS.add(new ScheduleDTO(schedule));
            });
        });

        return scheduleDTOS;
    }

}
