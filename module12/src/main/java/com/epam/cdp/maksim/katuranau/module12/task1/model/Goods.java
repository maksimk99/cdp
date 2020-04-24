package com.epam.cdp.maksim.katuranau.module12.task1.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class Goods {

    @Min(value = 0, message = "identifier should not be negative")
    private Long id;
    @NotBlank(message = "name of goods should not be empty")
    private String name;
    @NotBlank(message = "description of goods should not be empty")
    private String description;

    public Long getId() {
        return id;
    }

    public Goods setId(final Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Goods setName(final String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Goods setDescription(final String description) {
        this.description = description;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Objects.equals(id, goods.id) &&
                Objects.equals(name, goods.name) &&
                Objects.equals(description, goods.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
