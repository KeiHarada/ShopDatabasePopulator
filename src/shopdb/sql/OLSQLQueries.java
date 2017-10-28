package shopdb.sql;

import java.util.regex.Pattern;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONObject;

public class OLSQLQueries {

	private Connection con;

	/* Creates a new MySQL querying object. Specific for only this Project */
	public OLSQLQueries(String host, String database, String user, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = null;
			con = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database, user, password);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			System.out.println("MySQL driver Exception");
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public void insertShop(JSONObject obj) throws SQLException {
		PreparedStatement ps = con.prepareStatement("INSERT INTO shopInformation values(?,?,?,?)");
		ps.setInt(1, Integer.parseInt(obj.getString("ayto:IdComercio")));
		ps.setString(2, tuneString(obj.getString("ayto:nombreComercio")));
		checkAlphabet(tuneString(obj.getString("ayto:nombreComercio")));
		ps.setInt(3, Integer.parseInt(obj.getString("dc:identifier")));
		ps.setString(4, tuneString(obj.getString("dc:name")));
		checkAlphabet(tuneString(obj.getString("dc:name")));
		ps.execute();
	}
	
	private void checkAlphabet(String s){
		Pattern p = Pattern.compile("[^\\w^\\s^\\t^\\p{Punct}´]");
		if(p.matcher(s).find()){
			System.out.println("Un-Alphabets : "+s);
		}
	}

	private String tuneString(String s) {

		return s.replaceAll("á", "a").replaceAll("é", "e").replaceAll("ó", "o")
				.replaceAll("ñ", "n").replaceAll("í", "i").replaceAll("Ó", "O")
				.replaceAll("ú", "u").replaceAll("ü", "u").replaceAll("Ñ", "N")
				.replaceAll("Ü", "U").replaceAll("ª", "a").replaceAll("º", "o")
				.replaceAll("Ö", "O").replaceAll("È", "E").replaceAll("Ú", "U")
				.replaceAll("Í", "I");
	}

	public void insertShopLocation(JSONObject obj) throws SQLException {
		PreparedStatement ps = con.prepareStatement("INSERT INTO shopLocation VALUES(?,?,?)");
		String s = obj.getString("ayto:latitud");
		if (s.length() > 1) {
			ps.setInt(1, Integer.parseInt(obj.getString("dc:identifier")));
			ps.setDouble(2, Double.parseDouble(s));
			ps.setDouble(3, Double.parseDouble(obj.getString("ayto:longitud")));
			ps.execute();
		}

	}

	/* Closes the Connection. */
	public void close() throws SQLException {
		con.close();
	}

}