package com.thevitu.pyr.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thevitu.pyr.entities.BaseHero;

@Repository
public interface BaseHeroRepository extends JpaRepository<BaseHero, Long> {

	BaseHero getByLvl(int lvl);
	
}
