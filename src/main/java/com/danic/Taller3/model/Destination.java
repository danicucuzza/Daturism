package com.danic.Taller3.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Destination")
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_destination")
    private Long id;

    @Column
    private String name;

    @Column
    private String location;

    @Column
    private String description;

    @Column
    private BigDecimal price;

    public Destination(Long id, String name, String location, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
