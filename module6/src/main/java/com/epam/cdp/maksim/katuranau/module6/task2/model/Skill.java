package com.epam.cdp.maksim.katuranau.module6.task2.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Skill {
    @NotBlank
    private String skillName;
    @Min(0)
    @Max(5)
    private int priority;

    public Skill(String skillName, int priority) {
        this.skillName = skillName;
        this.priority = priority;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
