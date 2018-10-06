package com.stepstone.training.arena.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stepstone.training.arena.data.TournamentDto;
import com.stepstone.training.arena.model.*;
import com.stepstone.training.arena.model.creature.Creature;
import com.stepstone.training.arena.model.creature.CreatureType;
import com.stepstone.training.arena.service.CreaturesFactory;
import com.stepstone.training.arena.service.FightService;
import com.stepstone.training.arena.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@RestController
public class FightController {

    @Autowired
    CreaturesFactory creaturesFactory;

    @Autowired
    FightService fightService;

    @Autowired
    TournamentService tournamentService;

    private List<TournamentDto> tournaments = new ArrayList<>();

    private Map<Integer, List<Creature>> fighters = new HashMap<>();

    @PostMapping("/tournament/{id}/fighter")
    public String addFighter(@PathVariable Integer id, String type, String name, Integer strength, Integer dexterity, Integer initiative, Integer endurance, Integer lifepoints, String protection) {

        TournamentDto tournament = null;
        try {
            tournament = tournaments.get(id);
        } catch (Exception e) {
            throw new NoSuchTournamentException(id);
        }

        if (!verifyCapacity(id)){
            return "Too many fighters. Maximum capacity is " + tournament.getCapacity();
        }

        if (!verifyPoints(id, strength, dexterity, initiative, endurance, lifepoints)){
            return "Too many points. Maximum allowed is " + tournament.getPoints();
        }

        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.valueOf(protection), 1);

        Creature creature = creaturesFactory.generate(CreatureType.valueOf(type), name, strength, dexterity, initiative, endurance, lifepoints, map);

        if (fighters.get(id).contains(creature)){
            return name + " - there is already such creature";
        }
        else {
            fighters.get(id).add(creature);
            return name + " succesfully created";
        }

    }

    @DeleteMapping("/tournament/{id}/fighter/{name}")
    public String removeFighter(@PathVariable Integer id, @PathVariable String name) {

        TournamentDto tournament = null;
        try {
            tournament = tournaments.get(id);
        } catch (Exception e) {
            throw new NoSuchTournamentException(id);
        }

        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.valueOf("HELMET"), 1);

        Creature creature = creaturesFactory.generate(CreatureType.valueOf("ELF"), name, 1, 1, 1, 1, 1, map);

        if (fighters.get(id).contains(creature)){
            if (fighters.get(id).remove(creature)) {
                return name + " is no longer in the tournament";
            }
            else {
                return "Removing " + name + " from the tournament failed";
            }
        }
        else {
            return name + " was not found";
        }

    }

    @GetMapping("/tournament/{id}/fighter/{name}")
    public String getFighter(@PathVariable Integer id, @PathVariable String name) {

        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.valueOf("HELMET"), 1);

        Creature creature = creaturesFactory.generate(CreatureType.valueOf("ELF"), name, 1, 1, 1, 1, 1, map);

        if (fighters.get(id).contains(creature)){
            Gson gson = new GsonBuilder().disableHtmlEscaping()
                    .enableComplexMapKeySerialization()
                    .setPrettyPrinting()
                    .create();
            return gson.toJson(fighters.get(id).get(fighters.get(id).indexOf(creature)));
        }
        else {
            return name + " was not found";
        }

    }

    @PutMapping("/tournament/{id}/fighter/{name}")
    public String amendFighter(@PathVariable Integer id, @PathVariable String name, String type, Integer strength, Integer dexterity, Integer initiative, Integer endurance, Integer lifepoints, String protection) {

        Map<ProtectionItem, Integer> map = new HashMap<>();
        map.put(ProtectionItem.valueOf(protection), 1);

        Creature creature = creaturesFactory.generate(CreatureType.valueOf(type), name, strength, dexterity, initiative, endurance, lifepoints, map);

        if (fighters.get(id).contains(creature)){
            fighters.get(id).set(fighters.get(id).indexOf(creature), creature);
            return name + " parameters were changed";
        }
        else {
            return name + " was not found";
        }
    }

    @PostMapping("/tournament")
    public String createTournament(Integer capacity, Integer points){
        TournamentDto tournament = TournamentDto.getInstance();
        tournament.setCapacity(capacity);
        tournament.setPoints(points);
        tournament.setState(TournamentState.CREATED);
        TournamentDto tournamentDto = tournamentService.createTournament(tournament);
        tournaments.add(tournamentDto);
        fighters.put(tournamentDto.getId(), null);
        return "Tournament created: " + tournamentDto.getId();
    }

    @GetMapping("/tournament/{id}")
    public String getTournament(@PathVariable Integer id){

        TournamentDto tournament;
        TournamentDto tournamentDto = TournamentDto.getInstance();
        tournamentDto.setId(id);
        try {
            tournament = tournaments.get(tournaments.indexOf(tournamentDto));
        } catch (Exception e) {
            throw new NoSuchTournamentException(id);
        }
        Gson gson = new GsonBuilder().disableHtmlEscaping()
                .enableComplexMapKeySerialization()
                .setPrettyPrinting()
                .create();
        return gson.toJson(tournament);

    }

    @GetMapping("/tournaments")
    public String getTournaments(){

        Gson gson = new Gson();
        return gson.toJson(tournaments);

    }

    @GetMapping("/results")
    public String getResults(){
        return fightService.results();
    }

    @PostMapping("/tournament/{id}/start")
    public String runTournament(@PathVariable Integer id){

        TournamentDto tournament = null;
        try {
            tournament = tournaments.get(id);
        } catch (Exception e) {
            throw new NoSuchTournamentException(id);
        }

        tournament.setState(TournamentState.RUNNING);
        String returnString = fightService.tournament(fighters.get(id));
        tournament.setState(TournamentState.COMPLETED);
        return returnString;
    }

    @CrossOrigin
    @GetMapping("/tournament/{id}/fighters")
    public String getFighters(@PathVariable Integer id){

        TournamentDto tournament = null;
        try {
            tournament = tournaments.get(id);
        } catch (Exception e) {
            throw new NoSuchTournamentException(id);
        }

        Gson gson = new Gson();
        return gson.toJson(fighters.get(id).stream().map(Creature::getName).collect(toList()));

    }

    @CrossOrigin
    @GetMapping("/species")
    public String getCreatureTypes(){

        Gson gson = new Gson();
        return gson.toJson(CreatureType.values());

    }

    @CrossOrigin
    @GetMapping("/protections")
    public String getProtectionItems(){

        Gson gson = new Gson();
        return gson.toJson(ProtectionItem.values());

    }

    private boolean verifyPoints(Integer id, Integer strength, Integer dexterity, Integer initiative, Integer endurance, Integer lifepoints){

        if (strength + dexterity + initiative + endurance + lifepoints > tournaments.get(id).getPoints())
            return false;
        else
            return true;
    }

    private boolean verifyCapacity(Integer id){

        if (fighters.get(id).size() >= tournaments.get(id).getCapacity() - 1)
            return false;
        else
            return true;
    }

}
