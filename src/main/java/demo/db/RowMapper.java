package demo.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class RowMapper<T> {
	
	public abstract T mapRow(ResultSet resultSet) throws SQLException;

}
