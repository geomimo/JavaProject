package cinema;

import java.util.UUID;

public class Film {
	
	private UUID id;
	private String title;
	private int duration;
	private String description;
	private FilmCategory category[];
	
	public Film() {
		id = UUID.randomUUID();
	}
	
	public Film setTitle(String title) {
		this.title = title;
		return this;
	}

	public Film setDuration(int duration) {
		this.duration = duration;
		return this;
	}
	
	public Film setDescription(String description) {
		this.description = description;
		return this;
	}
	
	public Film setCategory(FilmCategory category[]) {
		this.category = category;
		return this;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public String getDescription() {
		return description;
	}
	
	public FilmCategory[] getCategory() {
		return category;
	}
	
	
	
	
	
	
	
}
