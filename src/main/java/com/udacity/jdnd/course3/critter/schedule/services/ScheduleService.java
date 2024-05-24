package com.udacity.jdnd.course3.critter.schedule.services;

import com.udacity.jdnd.course3.critter.pet.entities.Pet;
import com.udacity.jdnd.course3.critter.pet.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.schedule.entities.Schedule;
import com.udacity.jdnd.course3.critter.schedule.repositories.ScheduleRepository;
import com.udacity.jdnd.course3.critter.user.entities.Employee;
import com.udacity.jdnd.course3.critter.user.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ScheduleService {

    private final PetRepository petRepository;
    private final EmployeeRepository employeeRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(PetRepository petRepository, EmployeeRepository employeeRepository, ScheduleRepository scheduleRepository, CustomerRepository customerRepository) {
        this.petRepository = petRepository;
        this.employeeRepository = employeeRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public Schedule save(Schedule schedule, List<Long> petIds, List<Long> employeeIds) {
        List<Pet> pets = petRepository.findByIdIn(petIds);
        List<Employee> employees = employeeRepository.findByIdIn(employeeIds);
        schedule.setPets(pets);
        schedule.setEmployees(employees);
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> getScheduleList() {
        return scheduleRepository.findAll();
    }
}
