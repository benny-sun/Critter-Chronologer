package com.udacity.jdnd.course3.critter.user.entities;

import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Skill {
    @Enumerated(EnumType.ORDINAL)
    private EmployeeSkill skill;

    public EmployeeSkill getSkill() {
        return skill;
    }

    public void setSkill(EmployeeSkill skill) {
        this.skill = skill;
    }
}
