package com.stepstone.training.arena.model;

import lombok.Data;
import lombok.Value;

@Value
public class NoSuchTournamentException extends RuntimeException {

    private int id;

}
