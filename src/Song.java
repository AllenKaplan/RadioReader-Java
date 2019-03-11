public class Song {

	private String title;
	private String artist;
	private String albumart;
	
	public Song(String title, String artist, String albumart){
		this.title = title;
		this.artist = artist;
		this.albumart = albumart;
	}


	public Song() {
		System.out.println("JSON PARSED FROM NO-ARGS CONS");
	}
	
	@Override
	public String toString() {
		return "Title: " + title + "\nArtist: " + artist;
	}
	
	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public String getAlbumart() {
		return albumart;
	}

}
