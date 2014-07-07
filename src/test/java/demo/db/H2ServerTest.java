package demo.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class H2ServerTest {
	Connection connection;

	@Before
	public void setUp() throws Exception {
		H2Server.startH2Server();
		connection = H2Server.getSQLConnection();
		connection.createStatement().execute(
				"create table nameTable(name varchar(22))");
		connection.createStatement().execute(
				"insert into nameTable values('Salil')");
	}

	@Test
	public void testH2DatabaseSetup() throws SQLException {
		ResultSet resultSet = connection.createStatement().executeQuery(
				"select name from nameTable");
		resultSet.next();
		Assert.assertEquals("Salil", resultSet.getString("name"));
	}

	@After
	public void tearDown() throws Exception {
		if (connection != null) {
			connection.createStatement().execute("drop table nameTable");
			connection.close();
		}
		H2Server.stopH2Server();
	}
}
