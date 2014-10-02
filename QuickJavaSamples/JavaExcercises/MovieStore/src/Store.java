import java.util.ArrayList;
import java.util.List;


public class Store {
	private List<MovieProduct> movies;
	
	public Store(){
		this.movies = new ArrayList<MovieProduct>();
	}
	
	public ArrayList<MovieProduct> getAllMovies(){
		return (ArrayList<MovieProduct>) this.movies;
	}
	
	public void addMovie(MovieProduct movie){
		this.movies.add(movie);
	}
	
	public void removeMovie(MovieProduct movie){
		int indexOfProduct = movies.indexOf(movie);
		if (indexOfProduct != -1) {
			movies.remove(indexOfProduct);
		}
	}
	
	public ArrayList<MovieProduct> getFreeMovies(){
		ArrayList<MovieProduct> freeMovies = new ArrayList<MovieProduct>();
		
		for (MovieProduct movie : movies) {
			if (movie.isFree()) {
				freeMovies.add(movie);
			}
		}
		
		return freeMovies;
	}
}
