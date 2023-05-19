package com.novi.TechItEasy.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class RemoteControllerInputDto {
    public Long id;
    public String compatibleWith;
    public String batteryType;
    @NotBlank
    public String name;
    public String brand;
    @Min(1)
    public double price;
    public int originalStock;
}
