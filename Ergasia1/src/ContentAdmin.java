package user;

public class ContentAdmin extends User {

	public ContentAdmin() {
		
	}
	private Film insertFilm(String title) {
		 Film insertFilm = new Film().setFilm(title);
		 return insertFilm;
		}
	private Film deleteFilm(String title,UUID id) {
		Film deleteFilm = new Film().setFilm(title);
		return deleteFilm;	
		}
	private Film assignFilmToCinema(String title,UUID id) {
		Film assignFilmToCinema = new Film().setFilm(title);
		return assignFilmToCinema;
		}
}
