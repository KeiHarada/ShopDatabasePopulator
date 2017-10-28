package shopdb;

import java.io.IOException;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import shopdb.httpTools.HTTPParser;
import shopdb.sql.OLSQLQueries;

/* Main class. Populates database with parking information. */
public class Main {

	public static void main(String[] args) throws JSONException, SQLException {
		HTTPParser parser = new HTTPParser();
		try {
			OLSQLQueries queryMachine = new OLSQLQueries("localhost", "SmartSantander", "root", "onizukalab");
			StringBuilder s = parser.getJSON();
			JSONObject n = new JSONObject(s.toString());
			JSONArray arr = n.getJSONArray("resources");
			for (int i = 0; i < arr.length(); i++) {
				queryMachine.insertShop(arr.getJSONObject(i));
			}
			s = parser.getOtherJSON();
			n = new JSONObject(s.toString());
			arr = n.getJSONArray("resources");
			for (int i = 0; i < arr.length(); i++) {
				queryMachine.insertShopLocation(arr.getJSONObject(i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
