package com.stepstone.training.arena.data;

import com.stepstone.training.arena.model.ProtectionItem;
import com.stepstone.training.arena.model.TournamentState;
import com.stepstone.training.arena.model.creature.CreatureType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Map;

@EqualsAndHashCode(exclude={"strength","dexterity","initiative","velocity","endurance","lifePoints","type","protectionItem"})
@NoArgsConstructor(staticName="getInstance")
@Getter
@Setter
public class CreatureDto {

    @NotNull private String name;
    @NotNull private Integer strength;
    @NotNull private Integer dexterity;
    @NotNull private Integer initiative;
    @NotNull private Integer velocity;
    @NotNull private Integer endurance;
    @NotNull private Integer lifePoints;
    @NotNull private CreatureType type;
    @NotNull private ProtectionItem protectionItem;

}
