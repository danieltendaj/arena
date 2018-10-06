package com.stepstone.training.arena.data;

import com.stepstone.training.arena.model.TournamentState;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(exclude={"capacity","points","state"})
@NoArgsConstructor(staticName="getInstance")
@Getter
@Setter
public class TournamentDto {

    private Integer id;
    private Integer capacity;
    private Integer points;
    private TournamentState state;

}
