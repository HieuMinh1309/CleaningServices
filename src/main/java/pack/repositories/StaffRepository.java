package pack.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pack.models.Staff;
import pack.modelviews.Staff_mapper;
import pack.utils.Views;

@Repository
public class StaffRepository {
	@Autowired
	JdbcTemplate db;
	
	public boolean ExistsStaffCheck(String username, String password) {
		String str_query = String.format("select * from where %s = ? and %s = ?", Views.TBL_STAFFS, Views.COL_STAFFS_USERNAME, Views.COL_STAFFS_PASSWORD);
		List<Staff> staff = db.query(str_query, new Staff_mapper(), new Object[] {username, password});
		
		if(!staff.isEmpty()) {
			return true;
		}
		return false;
	}
}
