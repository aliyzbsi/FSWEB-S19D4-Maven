package com.workintech.s19d1.controller;

import com.workintech.s19d1.dto.ActorRequest;
import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping
    public List<Actor> findAll(){
        return actorService.findAll();
    }
    @GetMapping("/{id}")
    public Actor findById(@PathVariable("id") long id){
        return actorService.findById(id);
    }
    @PostMapping
    public Actor save(@RequestBody ActorRequest actorRequest) {
        Actor actor=new Actor(actorRequest.getActor().getId(),
                actorRequest.getActor().getFirstName(),
                actorRequest.getActor().getLastName(),
                actorRequest.getActor().getGender(), actorRequest.getActor().getBirthDate(), new ArrayList<>());
        return actorService.save(actor);
    }
    @PutMapping("/{id}")
    public Actor update(@PathVariable("id") long id,@RequestBody Actor actor){
        return actorService.update(id,actor);
    }
    @DeleteMapping("/{id}")
    public Actor delete(@PathVariable("id") long id){


        return actorService.delete(id);
    }
}
