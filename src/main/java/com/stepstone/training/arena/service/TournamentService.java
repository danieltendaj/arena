package com.stepstone.training.arena.service;


import com.stepstone.training.arena.data.TournamentDto;
import com.stepstone.training.arena.data.TournamentRepository;
import com.stepstone.training.arena.model.TournamentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TournamentService {

    @Autowired
    TournamentRepository repository;

    @Autowired
    TournamentAssembler assembler;

    public TournamentDto createTournament(TournamentDto tournamentDto) {

        TournamentEntity tournamentEntity = assembler.toEntity(tournamentDto);
        TournamentEntity tournamentSaved = repository.save(tournamentEntity);
        return assembler.toDto(tournamentSaved);

    }




}
