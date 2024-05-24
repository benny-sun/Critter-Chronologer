package com.udacity.jdnd.course3.critter.user.entities;

import com.udacity.jdnd.course3.critter.schedule.entities.Schedule;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Entity
public class Employee extends User {
    @ElementCollection
    @Enumerated
    private Set<EmployeeSkill> skills;

    @ElementCollection
    @Enumerated
    private Set<DayOfWeek> daysAvailable;

    @ManyToMany(mappedBy = "employees", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Schedule> schedules;

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setSkills(Set<EmployeeSkill> employeeSkills) {
        this.skills = employeeSkills;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public void setDaysAvailable(Set<DayOfWeek> availableDate) {
        this.daysAvailable = availableDate;
    }
}
