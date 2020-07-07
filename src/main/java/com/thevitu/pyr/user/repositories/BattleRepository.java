package com.thevitu.pyr.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thevitu.pyr.entities.Battle;

@Repository
public interface BattleRepository extends JpaRepository<Battle, Long> {

}
