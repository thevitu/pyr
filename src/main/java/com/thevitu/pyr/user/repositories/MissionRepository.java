package com.thevitu.pyr.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thevitu.pyr.entities.Mission;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

}
