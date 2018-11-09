package com.stepstone.training.arena.data;

import com.stepstone.training.arena.data.CreatureEntity;
import com.stepstone.training.arena.service.model.TournamentState;
import lombok.Data;

import javax.persistence.*;
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
