package com.novi.TechItEasy.dtos;

import com.novi.TechItEasy.models.Television;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class CiModuleInputDto {
    public Long id;
    @NotBlank
    public String name;
    public String type;
    @Min(1)
    public double price;
    public Television television;
}
