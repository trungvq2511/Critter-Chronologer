package com.udacity.jdnd.course3.critter.dto;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.entity.enumerate.EmployeeSkill;
import lombok.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Represents the form that employee request and response data takes. Does not map
 * to the database directly.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDTO {
    private long id;
    private String name;
    private Set<EmployeeSkill> skills;
    private Set<DayOfWeek> daysAvailable;

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.skills = employee.getSkills();
        this.daysAvailable = employee.getDaysAvailable();
    }

    public static List<EmployeeDTO> convertToDTOList(List<Employee> employeeList) {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        employeeList.stream().forEach((employee) -> {
            employeeDTOS.add(new EmployeeDTO(employee));
        });
        return employeeDTOS;
    }
}
