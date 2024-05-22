package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.user.entities.Customer;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Customer saveCustomer(Customer customer) {
        return userRepository.save(customer);
    }

}
