package com.stepstone.training.arena.model.creature;

import com.stepstone.training.arena.model.*;
import com.stepstone.training.arena.util.CreaturesRandomizer;
import lombok.Data;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Entity
@Data
@Table (name="creature")
public class CreatureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private Integer strength;
    private Integer dexterity;
    private Integer initiative;
    private Integer velocity;
    private Integer endurance;
    private Integer numberOfAttacks;
    private Integer numberOfDodges;
    private Integer lifePoints;
    private Integer initialLifePoints;
    private CreatureType type;
    //private Map<ProtectionItem, Integer> mapProtection;

    @ManyToOne
    @JoinColumn(name="tournament_entity_id")
    private TournamentEntity tournamentEntity;

}
