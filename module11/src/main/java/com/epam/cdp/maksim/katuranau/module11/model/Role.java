package com.epam.cdp.maksim.katuranau.module11.model;

public class Role {

    private Integer id;
    private String name;

    public Role(final int role_id, final String name) {
        this.id = role_id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
