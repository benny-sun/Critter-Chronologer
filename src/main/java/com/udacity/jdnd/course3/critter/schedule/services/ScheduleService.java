package com.udacity.jdnd.course3.critter.schedule.services;

import com.udacity.jdnd.course3.critter.pet.entities.Pet;
import com.udacity.jdnd.course3.critter.pet.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.schedule.entities.Schedule;
import com.udacity.jdnd.course3.critter.schedule.repositories.ScheduleRepository;
import com.udacity.jdnd.course3.critter.user.entities.Customer;
import com.udacity.jdnd.course3.critter.user.entities.Employee;
import com.udacity.jdnd.course3.critter.user.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ScheduleService {

    private final PetRepository petRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(PetRepository petRepository, EmployeeRepository employeeRepository, CustomerRepository customerRepository, ScheduleRepository scheduleRepository) {
        this.petRepository = petRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
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

    public List<Schedule> findScheduleForPet(long petId) {
        Pet pet = petRepository.findById(petId).orElse(null);

        if (pet == null) return new ArrayList<>();

        return pet.getSchedules();
    }

    public List<Schedule> findScheduleForEmployee(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        if (employee == null) return new ArrayList<>();

        List<Schedule> schedules = employee.getSchedules();

        if (schedules == null) return new ArrayList<>();

        return schedules;
    }

    public List<Schedule> findScheduleForCustomer(long customerId) {
        Customer customer = customerRepository.findById(customerId).orElse(null);

        if (customer == null) return new ArrayList<>();

        List<Pet> pets = customer.getPets();

        if (pets == null) return new ArrayList<>();

        return pets.stream()
                .flatMap(pet -> pet.getSchedules().stream())
                .collect(Collectors.toList());
    }
}
