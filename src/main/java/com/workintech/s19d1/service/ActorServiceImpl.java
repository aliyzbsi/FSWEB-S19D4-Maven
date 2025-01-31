package com.workintech.s19d1.service;

import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.exceptions.ActorException;
import com.workintech.s19d1.exceptions.ApiException;
import com.workintech.s19d1.repository.ActorRepository;
import com.workintech.s19d1.validations.ActorValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService{

    private final ActorRepository actorRepository;
    @Autowired
    public ActorServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }


    @Override
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    @Override
    public Actor findById(Long id) {
       Optional<Actor> actor= actorRepository.findById(id);
       if (actor.isPresent()){
           return actor.get();
       }else {
           throw new ApiException("actor is not found with id: " + id, HttpStatus.NOT_FOUND);
       }
    }

    @Override
    @Transactional
    public Actor save(Actor actor) {
        try {

            return actorRepository.save(actor);
        } catch (Exception exception) {
            throw new ApiException("Actor kaydedilirken hata oluştu: " +
                    exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Actor update(Long id, Actor actor) {
        try {
            Actor actorToUpdate=findById(id);
            actorToUpdate.setFirstName(actor.getFirstName());
            actorToUpdate.setLastName(actor.getLastName());
            actorToUpdate.setGender(actor.getGender());
            actorToUpdate.setBirthDate(actor.getBirthDate());
            actor.setMovies(actor.getMovies());

            return actorRepository.save(actorToUpdate);
        }catch (ApiException e){
            throw new ApiException(actor.getId()+": ID'li Actor güncellenirken hata: "+e.getMessage(),e.getHttpStatus());
        }

    }

    @Override
    @Transactional
    public Actor delete(Long id) {
        Actor actor=findById(id);
        actor.getMovies().forEach(movie -> {
            movie.getActors().remove(actor);
        });
        actor.getMovies().clear();

        actorRepository.delete(actor);
        return actor;
    }
}
