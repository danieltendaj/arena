package com.stepstone.training.arena.model;

import lombok.Data;
import lombok.Value;

@Data(staticConstructor="getInstance")
public class Tournament {

    private final String name;
    private int capacity;
    private int points;
    private TournamentState state;

}
