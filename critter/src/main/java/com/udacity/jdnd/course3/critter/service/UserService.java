package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.dto.CustomerDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeDTO;
import com.udacity.jdnd.course3.critter.dto.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.entity.Customer;
import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.exception.NotFoundException;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.*;

@Service
@Transactional
public class UserService {
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;

    public UserService(CustomerRepository customerRepository, EmployeeRepository employeeRepository) {
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
    }

    public Customer saveCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO);
        Customer save = customerRepository.save(customer);
        return save;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> all = customerRepository.findAll();

        return all;
    }

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee(employeeDTO);
        Employee save = employeeRepository.save(employee);
        return new EmployeeDTO(save);
    }

    public Employee getEmployee(long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);

        //check not found
        if (employee == null) {
            throw new NotFoundException("Employee not found.");
        }

        return employee;
    }

    public Employee setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = employeeRepository.getOne(employeeId);

        //check not found
        if (employee == null) {
            throw new NotFoundException("Employee not found.");
        }

        employee.setDaysAvailable(daysAvailable);
        Employee save = employeeRepository.save(employee);
        return save;
    }

    public List<Employee> findEmployeesForService(EmployeeRequestDTO employeeDTO) {
        List<Employee> employeeList = employeeRepository.findByDaysAvailableIn(
                new HashSet<>(Arrays.asList(employeeDTO.getDate().getDayOfWeek())));

        List<Employee> employeeExpectedList = new ArrayList<>();
        employeeList.stream().forEach((employee -> {
            if (employee.getSkills().containsAll(employeeDTO.getSkills())) {
                employeeExpectedList.add(employee);
            }
        }));
        return employeeExpectedList;
    }

    public Customer getOwnerByPet(long petId) {
        Customer customer = customerRepository.findByPetsId(petId);

        //check not found
        if (customer == null) {
            throw new NotFoundException("Customer not found.");
        }
        return customer;
    }
}
