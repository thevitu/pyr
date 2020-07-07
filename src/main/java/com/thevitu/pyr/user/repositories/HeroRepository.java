package com.thevitu.pyr.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thevitu.pyr.entities.Hero;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {

}
