package com.example.watchlist;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoviesServices {
    
    //create
    @Autowired
    MoviesRepository movieRepo; 

    public void create(Movie movie){
        movieRepo.save(movie);
    }

    public List<Movie> getAllMovies(){
      return movieRepo.findAll();
    }

    public Movie getMovieByid(Integer id){
      return movieRepo.findById(id).get();
    }

    public void update(Movie movie, Integer id){
      Movie tobeUpdated = getMovieByid(id);
      tobeUpdated.setTitle(movie.getTitle());
      tobeUpdated.setRating(movie.getRating());
      tobeUpdated.setPriority(movie.getPriority());
      tobeUpdated.setComment(movie.getComment());
      movieRepo.save(tobeUpdated);
    }
    
}
