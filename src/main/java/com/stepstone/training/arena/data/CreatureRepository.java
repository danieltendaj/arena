package com.stepstone.training.arena.data;

import com.stepstone.training.arena.model.TournamentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatureRepository extends CrudRepository<TournamentEntity, Integer> {
}
