package demo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class PersonDAO {

	private DataSource dataSource;

	public PersonDAO(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Person getPerson(int id) {
		try {
			String sql = "select * from person where id=?";
			JdbcTemplate<Person> jdbcTemplate = new JdbcTemplate<Person>(this.dataSource);
			return jdbcTemplate.executeQueryObject(sql, new Object[] {id}, new PersonRowMapper());
		} catch (Exception e) {
			throw new RuntimeException("Cat not get all person");
		}
	}

	public List<Person> getAll() {
		try {
			String sql = "select * from person";
			JdbcTemplate<Person> jdbcTemplate = new JdbcTemplate<Person>(this.dataSource);
			return jdbcTemplate.executeQuery(sql, null, new PersonRowMapper());
		} catch (Exception e) {
			throw new RuntimeException("Cat not get all person");
		}
	}

}
