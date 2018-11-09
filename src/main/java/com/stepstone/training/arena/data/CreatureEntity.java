package com.stepstone.training.arena.data;

import com.stepstone.training.arena.service.model.*;
import com.stepstone.training.arena.service.model.creature.CreatureType;
import lombok.Data;

import javax.persistence.*;

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
    private ProtectionItem protectionItem;
    //private Map<ProtectionItem, Integer> mapProtection;

    @ManyToOne
    @JoinColumn(name="tournament_entity_id")
    private TournamentEntity tournamentEntity;

}
