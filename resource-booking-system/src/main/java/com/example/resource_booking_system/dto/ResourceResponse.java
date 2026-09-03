package com.example.resource_booking_system.dto;

import java.math.BigDecimal;

public class ResourceResponse {

    private Long id;
    private String name;
    private String type;
    private String description;
    private boolean available;
    private BigDecimal price;

    public ResourceResponse() {
    }

    public ResourceResponse(
            Long id,
            String name,
            String type,
            String description,
            boolean available,
            BigDecimal price) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        this.available = available;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAvailable() {
        return available;
    }

    public BigDecimal getPrice() {
        return price;
    }
}