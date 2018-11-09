package com.stepstone.training.arena.service;

import com.stepstone.training.arena.controller.TournamentDto;
import com.stepstone.training.arena.data.TournamentEntity;
import org.springframework.stereotype.Component;

@Component
public class TournamentAssembler {

    public TournamentDto toDto(TournamentEntity tournamentEntity){
        TournamentDto tournamentDto = TournamentDto.getInstance();
        tournamentDto.setId(tournamentEntity.getId());
        tournamentDto.setCapacity(tournamentEntity.getCapacity());
        tournamentDto.setPoints(tournamentEntity.getPoints());
        tournamentDto.setState(tournamentEntity.getState());
        return tournamentDto;
    }

    public TournamentEntity toEntity(TournamentDto tournamentDto){
        TournamentEntity tournamentEntity = TournamentEntity.getInstance();
        tournamentEntity.setCapacity(tournamentDto.getCapacity());
        tournamentEntity.setPoints(tournamentDto.getPoints());
        tournamentEntity.setState(tournamentDto.getState());
        return tournamentEntity;
    }

}
