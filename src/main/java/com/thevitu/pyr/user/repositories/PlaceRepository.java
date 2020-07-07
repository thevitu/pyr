package com.thevitu.pyr.user.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thevitu.pyr.entities.Place;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

}
