package com.thevitu.pyr.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thevitu.pyr.entities.UserHero;

@Repository
public interface UserHeroRepository extends JpaRepository<UserHero, Long> {

}
