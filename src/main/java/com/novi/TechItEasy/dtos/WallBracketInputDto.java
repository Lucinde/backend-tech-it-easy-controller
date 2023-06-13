package com.novi.TechItEasy.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class WallBracketInputDto {
    public Long id;

    public String size;
    public Boolean adjustable;
    @NotBlank
    public String name;
    @Min(1)
    public double price;
}
