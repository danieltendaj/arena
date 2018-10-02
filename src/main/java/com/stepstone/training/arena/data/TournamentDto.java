package com.stepstone.training.arena.data;

import com.stepstone.training.arena.model.TournamentState;
import lombok.Data;

import javax.persistence.*;

@Data(staticConstructor="getInstance")
public class TournamentDto {

    private Integer id;
    private int capacity;
    private int points;
    private TournamentState state;

}
