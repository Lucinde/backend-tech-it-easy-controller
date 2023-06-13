package com.novi.TechItEasy.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="remote_controllers")
public class RemoteController {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String compatibleWith;
    private String batteryType;
    private String name;
    private String brand;
    private double price;
    private int originalStock;

    /* relations */
    @OneToOne(mappedBy = "remoteController")
    @JsonIgnore
    private Television television;

}
