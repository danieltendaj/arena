package com.stepstone.training.arena.data;

import com.stepstone.training.arena.model.TournamentState;
import lombok.Data;

@Data (staticConstructor="getInstance")
public class TournamentDto {

    private Integer id;
    private Integer capacity;
    private Integer points;
    private TournamentState state;

}
