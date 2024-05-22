package com.udacity.jdnd.course3.critter.user.entities;

import com.udacity.jdnd.course3.critter.schedule.entities.Schedule;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Employee extends User {
    @ElementCollection
    private List<Skill> skills;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Schedule> schedules;

    public List<Skill> getSkills() {
        return skills;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSkills(List<Skill> employeeSkills) {
        this.skills = employeeSkills;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
