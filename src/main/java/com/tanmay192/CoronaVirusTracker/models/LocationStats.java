package com.tanmay192.CoronaVirusTracker.models;

import lombok.*;
import javax.persistence.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class LocationStats {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String state;
    private String country;
    private Long newNumberOfCases;
    private Long changeInNumberOfCases;
}
