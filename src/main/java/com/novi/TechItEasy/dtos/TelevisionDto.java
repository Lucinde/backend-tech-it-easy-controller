package com.novi.TechItEasy.dtos;

import com.novi.TechItEasy.models.RemoteController;
import com.novi.TechItEasy.models.WallBracket;

import java.util.List;

// Voor GET en het returnen van televisies
public class TelevisionDto {
    public Long id;

    public String type;
    public String brand;
    public String name;
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

    public RemoteController remoteController;

}
