package current.netflixlld;

import current.netflixlld.services.MovieService;

import java.util.LinkedHashSet;

public class DriverClass {
    public static void main(String[] args){

    }
    static void testMovie(){
        MovieService movieService = new MovieService();
        movieService.addMove("U1","HOT FUZZ","Comedy",4.0);
        movieService.playMovie("U1","M1");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
    }
}
