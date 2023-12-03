package com.demo.su.commodity.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Commodity {
    @Id
    private Long id;


}
