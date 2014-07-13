package demo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

public class LogDAO {
	private DataSource dataSource;

	public LogDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Log getLog(int id) {
		Log log = null;
		try {
			Connection connection = this.dataSource.getConnection();
			PreparedStatement prep = connection
					.prepareStatement("select * from LOG_IMPORT where LOG_IMPORT_ID=? and TO_CHAR( TRUNC(sysdate), 'YYYYMM' ) = '201407'");
			prep.setInt(1, id);
			ResultSet resultSet = prep.executeQuery();

			if (resultSet.next()) {
				log = new Log();
				log.setId(resultSet.getInt("LOG_IMPORT_ID"));
				log.setDataType(resultSet.getString("IMPORT_DATATYPE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return log;
	}
}
