package com.udacity.jdnd.course3.critter.schedule.entities;

import com.udacity.jdnd.course3.critter.pet.entities.Pet;
import com.udacity.jdnd.course3.critter.user.entities.Employee;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Schedule {
    @Id
    private Long id;

    private LocalDate date;

    @ManyToMany(mappedBy = "schedules")
    private List<Pet> pets;

    @ManyToMany(mappedBy = "schedules")
    private List<Employee> employees;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
