package com.stepstone.training.arena.service.model;

import lombok.Data;
import lombok.Value;

@Value
public class NoSuchTournamentException extends RuntimeException {

    private int id;

}
