import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpURLConnecter {

	private final String USER_AGENT = "Mozilla/5.0";

	String lastSong = "";
	
	static Driver driver;
	
	public static void main(String[] args) throws Exception {

		HttpURLConnecter http = new HttpURLConnecter();

		driver = new Driver();
		
		System.out.println("Testing 1 - Send Http GET request");
		  try {
		        while (true) {
		    		http.sendGet();
		            Thread.sleep(1 * 60 * 1000);
		        }
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }

	}

	// HTTP GET request
	private void sendGet() throws Exception {
		String url = "https://cob.leanplayer.com/CINDFM/nowplaying";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		ObjectMapper mapper = new ObjectMapper();
//		System.out.println(response);
		System.out.println("Recieved response: " + response.toString());
		String input = response.toString().replaceAll("( \\(IND.+\")", "\"").replaceAll("'", "\\'");
		System.out.println("Input now: " + input);
		Song song = mapper.readValue(input, Song.class);

//		System.out.println(song.getTitle()); //John

		//print result
		
		if(lastSong.equals(song.getTitle())) {
			System.out.println("\nSONG HAS NOT CHANGED");
		} else {
			System.out.println("\nADDING SONG: " + song.getArtist() + " - " + song.getTitle());
			driver.addSong(song.getArtist(), song.getTitle());
			lastSong = song.getTitle();
		}
	}
	
}
