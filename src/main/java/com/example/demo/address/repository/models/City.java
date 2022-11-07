package com.example.demo.address.repository.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "city")
@Getter
@Setter
@NoArgsConstructor
public class City {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "city_id")
    private Long cityId;
    @Column(name = "city_name", nullable = false)
    private String cityName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = true)
    private State state;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Address> address = new HashSet<>();

    public City(Long cityId, String cityName, Country country, State state) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.country = country;
        this.state = state;
    }

    public City(String name, Country country){
        this.cityName = name;
        this.country = country;
    }

    public City(Long cityId, String cityName, Country country) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.country = country;
    }
}
