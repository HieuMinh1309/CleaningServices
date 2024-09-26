package pack.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pack.models.User;
import pack.modelviews.User_mapper;
import pack.utils.SecurityUtility;
import pack.utils.Views;

@Repository
public class UserRepository {
	@Autowired
	JdbcTemplate db;
	
	public User findUserbyUsername(String username) {
		try {
			String str_query = String.format("select * from %s where %s = ?", Views.TBL_USER, Views.COL_USER_USERNAME);
			return db.queryForObject(str_query, new User_mapper(), new Object[] {username});
		} catch (Exception e) {
			return null;
		}
	}
	
	public String newUser(User user) {
		try {
			String str_query = String.format("insert into %s (username, password, email, phone) values(?,?,?,?)", Views.TBL_USER);
			String hashPassword = SecurityUtility.encryptBcrypt(user.getPassword());
			int rowaccept = db.update(str_query, new Object[] {user.getUsername(), hashPassword, user.getEmail(), user.getPhone()});
			if(rowaccept == 1) {
				return "success";
			}
			return "failed";
		}catch (DuplicateKeyException e) {
	        throw new IllegalArgumentException("Some information(username, email, phone) may already exists."); 
		}catch (Exception e) {
			return "error";
		}
	}
}
