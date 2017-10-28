package shopdb.httpTools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPParser {

	/* Plain constructor, no special features. */
	public HTTPParser() {
	}

	/**
	 * Performs the query and returns a <i>StringBuilder</i> containing a JSON
	 * Array with parking spots history entries.
	 * 
	 * @param from
	 *            The earlier <i>Timestamp</i> object used.
	 * @param to
	 *            The later <i>Timestamp</i> object used.
	 * @return <i>StringBuilder</i> containing the JSON Array.
	 * @throws IOException 
	 */
	public StringBuilder getJSON() throws IOException {
		URL url = new URL("http://datos.santander.es/api/rest/datasets/comercios_actividades.json?items=9000");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("Sending request");
		System.out.println("Response code: " + responseCode);
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String s = null;
		StringBuilder sb = new StringBuilder();
		while((s = br.readLine()) != null){
			sb.append(s);
		}
		br.close();
		return sb;
	}
	
	public StringBuilder getOtherJSON() throws IOException{
		URL url = new URL("http://datos.santander.es/api/rest/datasets/comercios_comercios.json?items=9000");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("Sending request");
		System.out.println("Response code: " + responseCode);
		BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		String s = null;
		StringBuilder sb = new StringBuilder();
		while((s = br.readLine()) != null){
			sb.append(s);
		}
		br.close();
		return sb;
		
		//TODO and run boy
	}

}
