package com.example.demo.address.repository.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "state")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class State {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "state_id")
    private Long stateId;
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @OneToMany(mappedBy = "state", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<City> city = new HashSet<>();

    public State(Long stateId, String name, Country country) {
        this.stateId = stateId;
        this.name = name;
        this.country = country;
    }
}
