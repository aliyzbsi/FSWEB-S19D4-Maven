package com.workintech.s19d1.service;

import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ActorService {

    List<Actor> findAll();
    Actor findById(Long id);
    Actor save(Actor actor);
    Actor update(Long id,Actor actor);
    Actor delete(Long id);

}
