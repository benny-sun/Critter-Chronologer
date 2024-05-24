package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.entities.Pet;
import com.udacity.jdnd.course3.critter.user.entities.Customer;
import com.udacity.jdnd.course3.critter.user.entities.Employee;
import com.udacity.jdnd.course3.critter.user.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer savedCustomer = userService.saveCustomer(convertCustomerDtoToEntity(customerDTO));
        return convertEntityToCustomerDto(savedCustomer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = userService.getCustomerList();
        return customers.stream()
                .map(this::convertEntityToCustomerDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer owner = userService.findCustomerByPetId(petId);
        return convertEntityToCustomerDto(owner);
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee savedEmployee = userService.saveEmployee(convertEmployeeDtoToEntity(employeeDTO));
        return convertEntityToEmployeeDto(savedEmployee);
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = userService.findEmployee(employeeId);
        return convertEntityToEmployeeDto(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        Employee employee = userService.findEmployee(employeeId);
        employee.setDaysAvailable(daysAvailable);
        userService.saveEmployee(employee);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        DayOfWeek dayOfWeek = employeeDTO.getDate().getDayOfWeek();
        List<Employee> employees = userService.finEmployeesForService(dayOfWeek, employeeDTO.getSkills());
        return employees.stream().map(this::convertEntityToEmployeeDto).collect(Collectors.toList());
    }

    private Customer convertCustomerDtoToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    private CustomerDTO convertEntityToCustomerDto(Customer customer) {
        CustomerDTO customerDto = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDto);
        List<Pet> pets = customer.getPets();
        if (pets != null) {
            List<Long> petIds = pets.stream().map(Pet::getId).collect(Collectors.toList());
            customerDto.setPetIds(petIds);
        }
        return customerDto;
    }

    private Employee convertEmployeeDtoToEntity(EmployeeDTO employeeDto) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        return employee;
    }

    private EmployeeDTO convertEntityToEmployeeDto(Employee employee) {
        EmployeeDTO employeeDto = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDto);
        return employeeDto;
    }
}
