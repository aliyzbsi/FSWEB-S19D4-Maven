package com.workintech.s19d1.validations;

import com.workintech.s19d1.entity.Actor;
import com.workintech.s19d1.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class ActorValidations {
    public static void actorNullCheck(Actor actor) {
        if (actor==null){
            throw new ApiException("Actor boş olamaz!", HttpStatus.BAD_REQUEST);
        }
    }


    public static void actorNameEqualsNewNameCheck(Actor actorToUpdate, Actor actor) {
        if (actorToUpdate.getFirstName().equalsIgnoreCase(actor.getFirstName())){
            throw new ApiException(actorToUpdate.getId() +": ID'li kullanıcının adı güncellenmesini istediğiniz isim ile aynı olamaz!",HttpStatus.BAD_REQUEST);

        }
    }

    public static void actorNameEqualsNewLastnameCheck(Actor actorToUpdate, Actor actor) {
        if (actorToUpdate.getLastName().equalsIgnoreCase(actor.getLastName())){
            throw new ApiException(actorToUpdate.getId() +": ID'li kullanıcının soyadı güncellenmesini istediğiniz soyad ile aynı olamaz!",HttpStatus.BAD_REQUEST);

        }
    }
}
