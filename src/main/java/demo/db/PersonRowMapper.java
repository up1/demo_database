package demo.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonRowMapper extends RowMapper<Person> {

	@Override
	public Person mapRow(ResultSet resultSet) throws SQLException {
		Person person = new Person();
		person.setID(resultSet.getInt("ID"));
		person.setFirstName(resultSet.getString("FIRSTNAME"));
		return person;
	}

}
