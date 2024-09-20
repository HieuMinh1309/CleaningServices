package pack.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pack.models.Admin;
import pack.modelviews.Admin_mapper;
import pack.utils.Views;

@Repository
public class AdminRepository {
	@Autowired
	JdbcTemplate db;
	
	public boolean ExistsAdminCheck(String username, String password) {
		String str_query = String.format("select * from %s where %s = ? and %s = ?", Views.TBL_ADMIN, Views.COL_ADMIN_USERNAME, Views.COL_ADMIN_PASSWORD);
		List<Admin> admin = db.query(str_query, new Admin_mapper(), new Object[] {username, password});
		if(!admin.isEmpty()) {
			return true;
		}
		return false;
	}
}
