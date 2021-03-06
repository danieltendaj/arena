package com.stepstone.training.arena.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatureRepository extends CrudRepository<TournamentEntity, Integer> {
}
