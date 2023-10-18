package com.example.watchlist;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class MoviesController {
    @Autowired
    MoviesServices movieService;

    // fetch form  and update accounding to id value
    @GetMapping("/form")
    public ModelAndView getform(@RequestParam(required=false)Integer id){
        String viewName = "form";
         Map<String, Object> model = new HashMap<>();
        if(id == null){
            model.put("form", new Movie());
        }
        else{
           model.put("form",movieService.getMovieByid(id));
        }
        return new ModelAndView(viewName, model);
    }
    
    // fetching watchlist page 
     @GetMapping("/watchlist")
    public ModelAndView getewatchlist(){
      
       String viewName ="watchlist";
       Map<String,Object> model = new HashMap<>();
       model.put("watchlistrows",movieService.getAllMovies());

       //  model.put("no of movies",Movie);
       return new ModelAndView(viewName, model);
    }

    //post data in from
    @PostMapping("/form")
    public ModelAndView submitform(@Valid Movie movie, BindingResult bindingResult){
       if(bindingResult.hasErrors()){
        return new  ModelAndView("form");
       }
       
        //if id = null post new mivie otherwise update 
       
        movieService.create(movie);

    // redirecting to another page
        RedirectView rd = new RedirectView();
        rd.setUrl("/watchlist");
        return new ModelAndView(rd);
    }
    
   
}
