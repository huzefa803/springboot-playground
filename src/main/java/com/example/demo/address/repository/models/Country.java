package com.example.demo.address.repository.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Country {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "country_id")
    private Long countryId;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "country_iso2", nullable = false, unique = true)
    private String countryISO2;
    @Column(name = "country_iso3", nullable = false, unique = true)
    private String countryISO3;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<City> city = new HashSet<>();

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<State> state = new HashSet<>();

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Address> address = new HashSet<>();

    public Country(Long countryId, String name, String countryISO2, String countryISO3) {
        this.countryId = countryId;
        this.name = name;
        this.countryISO2 = countryISO2;
        this.countryISO3 = countryISO3;
    }

    public Country(String name, String countryISO2, String countryISO3){
        this.name = name;
        this.countryISO2 = countryISO2;
        this.countryISO3 = countryISO3;
    }

}
