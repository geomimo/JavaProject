package cinema;

import java.util.UUID;

public class Film {
	
	private UUID id;
	private String title;
	private int duration;
	private String description;
	private FilmCategory category[];
	
	public Film() {
		//give uuid
	}
	
	public Film setTitle(String title) {
		this.title = title;
		return this;
	}

	public Film setDuration(int duration) {
		this.duration = duration;
		return this;
	}
	
	public String getTitle() {
		return this.title;
	}
}
