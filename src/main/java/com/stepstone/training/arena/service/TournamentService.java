package com.stepstone.training.arena.service;


import com.stepstone.training.arena.data.CreatureRepository;
import com.stepstone.training.arena.controller.TournamentDto;
import com.stepstone.training.arena.data.TournamentRepository;
import com.stepstone.training.arena.data.TournamentEntity;
import com.stepstone.training.arena.service.model.TournamentState;
import com.stepstone.training.arena.service.model.creature.Creature;
import com.stepstone.training.arena.data.CreatureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TournamentService {

    @Autowired
    TournamentRepository tournamentRepository;

    @Autowired
    TournamentAssembler tournamentAssembler;

    @Autowired
    CreatureRepository creatureRepository;

    @Autowired
    CreatureAssembler creatureAssembler;


    public TournamentDto createTournament(TournamentDto tournamentDto) {

        tournamentDto.setState(TournamentState.CREATED);
        TournamentEntity tournamentEntity = tournamentAssembler.toEntity(tournamentDto);
        TournamentEntity tournamentSaved = tournamentRepository.save(tournamentEntity);
        return tournamentAssembler.toDto(tournamentSaved);

    }

    public void addFighterToTournament(TournamentDto tournamentDto, Creature creature){

        CreatureEntity creatureEntity = creatureAssembler.toEntity();
        CreatureEntity creatureSaved = creatureRepository.save(creatureEntity);
        return creatureAssembler.toDto(creatureSaved);

    }


}
