package demo.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class JdbcTemplate<T> {

	private DataSource dataSource;
	private PreparedStatement preparedStatement;
	
	public JdbcTemplate(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<T> executeQuery(String sql, Object[] params, RowMapper<T> rowMapper) throws SQLException{
		List<T> results = new ArrayList<T>();
		this.preparedStatement = this.dataSource.getConnection().prepareStatement(sql);
		setupParams(params);
		ResultSet resultSet = this.preparedStatement.executeQuery();
		while(resultSet.next()) {
			results.add(rowMapper.mapRow(resultSet));
		}
		return results;
	}

	public T executeQueryObject(String sql, Object[] params, RowMapper<T> rowMapper) throws SQLException{
		this.preparedStatement = this.dataSource.getConnection().prepareStatement(sql);
		setupParams(params);
		ResultSet resultSet = this.preparedStatement.executeQuery();
		if(resultSet.next()) {
			return rowMapper.mapRow(resultSet);
		}
		throw new SQLException("Data not found");
	}
	
	private void setupParams(Object[] params) throws SQLException {
		if(params != null) {
			int index = 1;
			for (Object param : params) {
				if(param instanceof Integer) {
					this.preparedStatement.setInt(index++, (Integer) param);
				}
			}
		}
	}

	public int queryForInt(String sql, Object[] params) throws SQLException {
		this.preparedStatement = this.dataSource.getConnection().prepareStatement(sql);
		setupParams(params);
		ResultSet resultSet = this.preparedStatement.executeQuery();
		if(resultSet.next()) {
			return resultSet.getInt(1);
		}
		throw new SQLException("Data not found");
	}

}
