package com.workintech.s19d1.validations;

import com.workintech.s19d1.entity.Movie;
import com.workintech.s19d1.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class MovieValidations {

    public static void movieNullCheck(Movie movie) {
        if (movie==null){
            throw new ApiException("Movie boş olamaz!", HttpStatus.BAD_REQUEST);
        }
    }
}
