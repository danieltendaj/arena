package com.stepstone.training.arena.model.creature;

import com.stepstone.training.arena.model.AttackResult;
import com.stepstone.training.arena.model.BodyPart;
import com.stepstone.training.arena.model.Fightable;
import com.stepstone.training.arena.model.ProtectionItem;
import com.stepstone.training.arena.util.CreaturesRandomizer;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Data
public class CreatureParams {

    private String name;
    private Integer strength;
    private Integer dexterity;
    private Integer initiative;
    private Integer velocity;
    private Integer endurance;
    private Integer lifePoints;
    private Integer initialLifePoints;
    private CreatureType type;
    private Map<ProtectionItem, Integer> mapProtection;

}
