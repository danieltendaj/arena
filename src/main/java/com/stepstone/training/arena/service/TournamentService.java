package com.stepstone.training.arena.service;


import com.stepstone.training.arena.data.TournamentDto;
import com.stepstone.training.arena.data.TournamentRepository;
import com.stepstone.training.arena.model.TournamentEntity;
import com.stepstone.training.arena.model.TournamentState;
import com.stepstone.training.arena.model.creature.Creature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TournamentService {

    @Autowired
    TournamentRepository repository;

    @Autowired
    TournamentAssembler assembler;

    public TournamentDto createTournament(TournamentDto tournamentDto) {

        tournamentDto.setState(TournamentState.CREATED);
        TournamentEntity tournamentEntity = assembler.toEntity(tournamentDto);
        TournamentEntity tournamentSaved = repository.save(tournamentEntity);
        return assembler.toDto(tournamentSaved);

    }

    public void addFighterToTournament(TournamentDto tournamentDto, Creature creature){

    }


}
