package com.udacity.jdnd.course3.critter.user.services;

import com.udacity.jdnd.course3.critter.pet.repositories.PetRepository;
import com.udacity.jdnd.course3.critter.pet.entities.Pet;
import com.udacity.jdnd.course3.critter.pet.services.PetNotFoundException;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.entities.Customer;
import com.udacity.jdnd.course3.critter.user.entities.Employee;
import com.udacity.jdnd.course3.critter.user.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserService {

    private final CustomerRepository customerRepository;
    private final PetRepository petRepository;
    private final EmployeeRepository employeeRepository;

    public UserService(CustomerRepository customerRepository, PetRepository petRepository, EmployeeRepository employeeRepository) {
        this.customerRepository = customerRepository;
        this.petRepository = petRepository;
        this.employeeRepository = employeeRepository;
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer findCustomer(long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new UserNotFoundException(customerId));
    }

    public Customer findCustomerByPetId(long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(() -> new PetNotFoundException(petId));
        return pet.getOwner();
    }

    public List<Customer> getCustomerList() {
        return customerRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findEmployee(long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new UserNotFoundException(employeeId));
    }

    public List<Employee> finEmployeesForService(DayOfWeek dayOfWeek, Set<EmployeeSkill> skills) {
        List<Employee> employees = employeeRepository.findEmployeesByDayOfWeek(dayOfWeek);
        return employees.stream()
                .filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
    }
}
