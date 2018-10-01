package com.stepstone.training.arena.data;

import com.stepstone.training.arena.model.TournamentState;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data(staticConstructor="getInstance")
@Table(name = "tournament")
public class TournamentDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private int capacity;
    private int points;
    private TournamentState state;

}
