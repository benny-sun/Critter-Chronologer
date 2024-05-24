package com.udacity.jdnd.course3.critter.schedule.entities;

import com.udacity.jdnd.course3.critter.pet.entities.Pet;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;
import com.udacity.jdnd.course3.critter.user.entities.Employee;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {
    @Id
    private Long id;

    private LocalDate date;

    @ManyToMany
    private List<Pet> pets;

    @ManyToMany
    private List<Employee> employees;

    @ElementCollection
    @Enumerated
    private Set<EmployeeSkill> activities;

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

    public Set<EmployeeSkill> getActivities() {
        return activities;
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

    public void setActivities(Set<EmployeeSkill> activities) {
        this.activities = activities;
    }
}
