package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
