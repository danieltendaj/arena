package com.stepstone.training.arena.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data(staticConstructor="getInstance")
@Table(name = "tournament")
public class TournamentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer capacity;
    private Integer points;
    private TournamentState state;

}
