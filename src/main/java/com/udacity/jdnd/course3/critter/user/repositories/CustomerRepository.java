package com.udacity.jdnd.course3.critter.user.repositories;

import com.udacity.jdnd.course3.critter.user.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c JOIN c.pets p WHERE p.id = :petId")
    Customer findCustomerByPetId(@Param("petId") long petID);

}
