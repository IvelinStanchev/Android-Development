import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Store store = new Store();
		MovieProduct firstMovie = new MovieProduct("test", 100);
		MovieProduct secondMovie = new MovieProduct("test1", 20);
		MovieProduct thirdtMovie = new MovieProduct("test2", 50);
		
		store.addMovie(firstMovie);
		store.addMovie(secondMovie);
		store.addMovie(thirdtMovie);
		
		ArrayList<MovieProduct> movies = store.getFreeMovies();
		
		for (MovieProduct movieProduct : movies) {
			System.out.println(movieProduct.getName() + " " + movieProduct.getPrice());
		}
		
		firstMovie.setAvailability(false);
		
		movies = store.getFreeMovies();
		
		System.out.println("----------");
		
		for (MovieProduct movieProduct : movies) {
			System.out.println(movieProduct.getName() + " " + movieProduct.getPrice());
		}
	}
}
