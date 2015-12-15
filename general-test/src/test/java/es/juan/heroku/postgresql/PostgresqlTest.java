package es.juan.heroku.postgresql;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class PostgresqlTest {

	@Test
	public void testConnection() throws Exception{
		
		try(Connection connection = getConnection()) {
		
		
			DatabaseMetaData meta = connection.getMetaData();
			
			try(ResultSet catalogs = meta.getCatalogs()) {
				catalogs.next();
				System.out.println("Catalog: " + catalogs.getString(1));
			}
			
			try(ResultSet columns = meta.getColumns(null, null, "properties", null)) {
				
				while(columns.next()) {
					System.out.println("column: " + columns.getString(4));
				}
			}
			
			executeStatment(connection, "GRANT ALL PRIVILEGES ON TABLE properties TO xnlaeoqaehskmt");
			
			executeStatment(connection, "grant all PRIVILEGES on all tables in schema public to xnlaeoqaehskmt");
			
			executeStatment(connection, "grant all on all sequences in schema public to xnlaeoqaehskmt");
			
			try(Statement statement = connection.createStatement()) {
				statement.executeQuery("SELECT * FROM properties");
				
				try(ResultSet properties = statement.getResultSet()) {
					while(properties.next()){
						System.out.println(String.format("%s  %s  %s  %s  %s", properties.getString(1), properties.getString(2), properties.getString(3), properties.getString(4), properties.getString(5)));
					}
				}
			}
			
			try(ResultSet schemas = meta.getSchemas()) {
				while(schemas.next()){
					System.out.println("Schema: " + schemas.getString(1));
				}
			}
		
			//executeStatment(connection, "INSERT INTO properties (prop_key, resource_id, user_id, text_value) VALUES ('sonar.core.id', NULL, NULL, '20151202112145')");
			//executeStatment(connection, "DELETE FROM properties WHERE prop_key='sonar.core.id'");
		}
	}

	private void executeStatment(Connection connection, String expression) throws SQLException {
		try(Statement statement = connection.createStatement()) {
			
			boolean isResultSet = statement.execute(expression);
			
			if(!isResultSet) {
				System.out.println(statement.getUpdateCount());
			}
		}
	}
	
	private Connection getConnection() throws URISyntaxException, SQLException {

		String username = "xnlaeoqaehskmt";
	    String password = "XcUia_M5QWypvN3tu6DRY07y-E";
	    String dbUrl = "jdbc:postgresql://ec2-107-21-221-107.compute-1.amazonaws.com:5432/d33rk12l6t4jbl?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

	    return DriverManager.getConnection(dbUrl, username, password);
	}
}
