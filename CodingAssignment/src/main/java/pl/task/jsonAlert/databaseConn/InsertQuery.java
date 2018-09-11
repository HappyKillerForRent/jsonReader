package pl.task.jsonAlert.databaseConn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertQuery {
	public static void insert(String id, long diff, String type, Boolean alert) {
		Connection con = null;
		Statement stmt = null;
		int result = 0;
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
			stmt = con.createStatement();
			result = stmt.executeUpdate(
					"INSERT INTO json_alerts  VALUES ('" + id + "','" + diff + "', '" + type + "', " + alert + ")");
			con.commit();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		System.out.println(result + " rows effected");
		System.out.println("Rows inserted successfully");
	}
}