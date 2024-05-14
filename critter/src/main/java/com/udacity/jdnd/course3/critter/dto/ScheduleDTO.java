package com.udacity.jdnd.course3.critter.dto;

import com.udacity.jdnd.course3.critter.entity.Schedule;
import com.udacity.jdnd.course3.critter.entity.enumerate.EmployeeSkill;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Represents the form that schedule request and response data takes. Does not map
 * to the database directly.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ScheduleDTO {
    private long id;
    private List<Long> employeeIds;
    private List<Long> petIds;
    private LocalDate date;
    private Set<EmployeeSkill> activities;

    public ScheduleDTO(Schedule schedule) {
        this.id = schedule.getId();
        List<Long> employeeIds = new ArrayList<>();
        schedule.getEmployees().stream().forEach((employee -> {
            employeeIds.add(employee.getId());
        }));
        this.employeeIds = employeeIds;
        List<Long> petIds = new ArrayList<>();
        schedule.getPets().stream().forEach((pet -> {
            petIds.add(pet.getId());
        }));
        this.petIds = petIds;
        this.date = schedule.getDate();
        this.activities = schedule.getActivities();
    }

    public static List<ScheduleDTO> convertToDTOList(List<Schedule> scheduleList) {
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        scheduleList.stream().forEach((schedule) -> {
            scheduleDTOS.add(new ScheduleDTO(schedule));
        });

        return scheduleDTOS;
    }
}
