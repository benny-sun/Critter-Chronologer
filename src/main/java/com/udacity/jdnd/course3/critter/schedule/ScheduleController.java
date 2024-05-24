package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.entities.Pet;
import com.udacity.jdnd.course3.critter.schedule.entities.Schedule;
import com.udacity.jdnd.course3.critter.schedule.services.ScheduleService;
import com.udacity.jdnd.course3.critter.user.entities.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = convertScheduleDtoToEntity(scheduleDTO);
        Schedule savedSchedule = scheduleService.save(schedule, scheduleDTO.getPetIds(), scheduleDTO.getEmployeeIds());
        return convertEntityToScheduleDto(savedSchedule);
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleService.getScheduleList();
        return schedules.stream().map(this::convertEntityToScheduleDto).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        List<Schedule> schedules = scheduleService.findScheduleForPet(petId);
        return schedules.stream().map(this::convertEntityToScheduleDto).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        List<Schedule> schedules = scheduleService.findScheduleForEmployee(employeeId);
        return schedules.stream().map(this::convertEntityToScheduleDto).collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        List<Schedule> schedules = scheduleService.findScheduleForCustomer(customerId);
        return schedules.stream().map(this::convertEntityToScheduleDto).collect(Collectors.toList());
    }

    private Schedule convertScheduleDtoToEntity(ScheduleDTO scheduleDto) {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDto, schedule);
        return schedule;
    }

    private ScheduleDTO convertEntityToScheduleDto(Schedule schedule) {
        ScheduleDTO scheduleDto = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDto);
        List<Long> petIds = schedule.getPets().stream()
                .map(Pet::getId)
                .collect(Collectors.toList());
        List<Long> employeeIds = schedule.getEmployees().stream()
                .map(Employee::getId)
                .collect(Collectors.toList());
        scheduleDto.setPetIds(petIds);
        scheduleDto.setEmployeeIds(employeeIds);
        return scheduleDto;
    }
}
