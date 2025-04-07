package current.netflixlld.services;

import current.netflixlld.entities.Consumer;
import current.netflixlld.entities.Movie;
import current.netflixlld.entities.User;

import java.util.HashMap;
import java.util.Map;

public class MovieService {
    Map<String,Movie> movieMap;
    Map<String, User> userMap;
// make singleton
    public MovieService() {
        this.movieMap = new HashMap<>();
    }
    public void addUser(String name, String email,String role){
        if(role.equalsIgnoreCase("Admin")){

        }else{
        }

    }
    public void playMovie(String userId,String movieId){
        //VALIDATION
        System.out.println("Movie " + movieId +" playing for user:" +userId);
    }
    public Movie addMove(String userId, String name, String genre, Double rating){
        Movie movie= new Movie(name, Movie.GENRE.valueOf(genre.toUpperCase()), rating);
        movieMap.put(movie.getId(),movie);
        System.out.println("Movie created :" +movie);
        return movie;
    }

}
