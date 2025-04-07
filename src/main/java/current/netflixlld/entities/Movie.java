package current.netflixlld.entities;

public class Movie {
    static int idCount=1;
    String id;
    String name;
    GENRE genre;
    Double rating;

    public Movie(String name, GENRE genre, Double rating) {
        this.id = "M"+idCount++;
        this.name = name;
        this.genre = genre;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", genre=" + genre.toString() +
                ", rating=" + rating +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GENRE getGenre() {
        return genre;
    }

    public void setGenre(GENRE genre) {
        this.genre = genre;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public enum GENRE{
        COMEDY,
        HORROR,
        SCI_FI,
        ROM_COM;
    }
}
