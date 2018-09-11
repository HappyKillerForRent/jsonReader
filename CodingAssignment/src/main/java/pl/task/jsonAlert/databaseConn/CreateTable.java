package pl.task.jsonAlert.databaseConn;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTable {

	public static void createTable() {

		Connection con = null;
		Statement stmt = null;
		@SuppressWarnings("unused")
		int result = 0;

		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/testdb", "SA", "");
			stmt = con.createStatement();

			result = stmt.executeUpdate(
					"CREATE TABLE json_alerts " + "(id VARCHAR(50) NOT NULL," + " duration VARCHAR(50) NOT NULL,"
							+ "type VARCHAR(50) NOT NULL, " + "alert boolean not null," + "PRIMARY KEY (id));");

			con.commit();

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		System.out.println("Table created successfully");
	}
}