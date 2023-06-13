package com.novi.TechItEasy.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

// voor POST en PUT
public class TelevisionInputDto {
    public Long id;

    public String type;
    public String brand;
    @NotBlank
    public String name;
    @Min(1)
    public double price;
    public double availableSize;
    public double refreshRate;
    public String screenType;
    public String screenQuality;
    public Boolean smartTv;
    public Boolean wifi;
    public Boolean voiceControl;
    public Boolean hdr;
    public Boolean bluetooth;
    public Boolean ambilight;
    public int originalStock;
    public int sold;
}
