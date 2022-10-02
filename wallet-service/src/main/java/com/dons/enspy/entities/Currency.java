package com.dons.enspy.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@ToString
@Builder
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String code ;
    private String name;
    private String symbol;
    private Double salePrice;
    private Double pouchasePrice;
}
