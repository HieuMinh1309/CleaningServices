package pack.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pack.models.User;
import pack.modelviews.User_mapper;
import pack.utils.Views;

@Repository
public class UserRepository {
	@Autowired
	JdbcTemplate db;
	
	public boolean ExistsUserCheck(String username, String password) {
		String str_query = String.format("select * from %s where %s = ? and %s = ?", Views.TBL_USER, Views.COL_USER_USERNAME, Views.COL_USER_PASSWORD);
		List<User> user = db.query(str_query, new User_mapper(), new Object[] {username, password});
		if(!user.isEmpty()) {
			return true;
		}
		return false;
	}
}
