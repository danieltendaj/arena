package com.stepstone.training.arena.model;

import com.stepstone.training.arena.model.creature.CreatureEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

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

    @OneToMany (mappedBy = "tournamentEntity")
    private List<CreatureEntity> creatureEntityList;

}
