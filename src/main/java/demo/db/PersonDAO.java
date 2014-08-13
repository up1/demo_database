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
		Person person = null;
		try {
			Connection connection = this.dataSource.getConnection();
			String sql = "select * from person where id=?";
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setInt(1, id);
			ResultSet resultSet = prep.executeQuery();

			if (resultSet.next()) {
				person = mappingPerson(resultSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return person;
	}

	public List<Person> getAll() {
		List<Person> allPerson = new ArrayList<Person>();
		try {
			Connection connection = this.dataSource.getConnection();
			String sql = "select * from person";
			PreparedStatement prep = connection.prepareStatement(sql);
			ResultSet resultSet = prep.executeQuery();

			while (resultSet.next()) {
				allPerson.add(mappingPerson(resultSet));
			}
		} catch (Exception e) {
			throw new RuntimeException("Cat not get all person");
		}
		return allPerson;
	}
	
	private Person mappingPerson(ResultSet resultSet) throws Exception{
		Person person = new Person();
		person.setID(resultSet.getInt("ID"));
		person.setFirstName(resultSet.getString("FIRSTNAME"));
		return person;
	}

}
