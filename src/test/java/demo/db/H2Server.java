package demo.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.h2.tools.Server;

public class H2Server extends Server {

	private static Server h2Server = null;

	private H2Server() {
	}

	public static void startH2Server() throws SQLException {
		if (null != h2Server && !h2Server.isRunning(true)) {
			h2Server = createTcpServer();
			h2Server.start();
		}
	}

	public static void stopH2Server() {
		if (null != h2Server && h2Server.isRunning(true))
			h2Server.stop();
	}

	public static Connection getSQLConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa",
				"");
		return conn;
	}

}
