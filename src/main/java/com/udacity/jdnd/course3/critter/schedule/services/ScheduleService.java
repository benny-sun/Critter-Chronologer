package com.udacity.jdnd.course3.critter.schedule.services;

import com.udacity.jdnd.course3.critter.pet.entities.Pet;
import com.udacity.jdnd.course3.critter.pet.services.PetService;
import com.udacity.jdnd.course3.critter.schedule.entities.Schedule;
import com.udacity.jdnd.course3.critter.schedule.repositories.ScheduleRepository;
import com.udacity.jdnd.course3.critter.user.entities.Customer;
import com.udacity.jdnd.course3.critter.user.entities.Employee;
import com.udacity.jdnd.course3.critter.user.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ScheduleService {

    private final PetService petService;
    private final UserService userService;
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(PetService petService, UserService userService, ScheduleRepository scheduleRepository) {
        this.petService = petService;
        this.userService = userService;
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule save(Schedule schedule, List<Long> petIds, List<Long> employeeIds) {
        List<Pet> pets = petService.findByIdIn(petIds);
        List<Employee> employees = userService.findByEmployeeIdIn(employeeIds);
        schedule.setPets(pets);
        schedule.setEmployees(employees);
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getScheduleList() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> findScheduleForPet(long petId) {
        Pet pet = petService.findByPetId(petId);
        return pet.getSchedules();
    }

    public List<Schedule> findScheduleForEmployee(long employeeId) {
        Employee employee = userService.findEmployee(employeeId);
        List<Schedule> schedules = employee.getSchedules();

        if (schedules == null) return new ArrayList<>();

        return schedules;
    }

    public List<Schedule> findScheduleForCustomer(long customerId) {
        Customer customer = userService.findCustomer(customerId);
        List<Pet> pets = customer.getPets();

        if (pets == null) return new ArrayList<>();

        return pets.stream()
                .flatMap(pet -> pet.getSchedules().stream())
                .collect(Collectors.toList());
    }
}
