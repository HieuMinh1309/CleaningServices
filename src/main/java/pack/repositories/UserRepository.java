package pack.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pack.models.Order;
import pack.models.User;
import pack.modelviews.Order_mapper;
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
			return db.queryForObject(str_query, new User_mapper(), new Object[] { username });
		} catch (Exception e) {
			return null;
		}
	}

	public User findUserbyId(int id) {
		try {
			String str_query = String.format("select * from %s where %s = ?", Views.TBL_USER, Views.COL_USER_ID);
			return db.queryForObject(str_query, new User_mapper(), new Object[] { id });
		} catch (Exception e) {
			return null;
		}
	}

	public String newUser(User user) {
		try {
			String str_query = String.format(
					"insert into %s (username, password, email, phone, fullname) values(?,?,?,?,?)", Views.TBL_USER);
			String hashPassword = SecurityUtility.encryptBcrypt(user.getPassword());
			int rowaccept = db.update(str_query, new Object[] { user.getUsername(), hashPassword, user.getEmail(),
					user.getPhone(), user.getFullname() });
			if (rowaccept == 1) {
				return "success";
			}
			return "failed";
		} catch (DuplicateKeyException e) {
			throw new IllegalArgumentException("Some information(username, email, phone) may already exists.");
		} catch (Exception e) {
			return "error";
		}
	}

	public User checkPhoneNumberExists(String phoneNumber) {
		try {
			String str_query = String.format("select * from %s Where %s = ?", Views.TBL_USER, Views.COL_USER_PHONE);
			return db.queryForObject(str_query, new User_mapper(), new Object[] { phoneNumber });
		} catch (Exception e) {
			return null;
		}
	}

	public String changePass(String phoneNumber, String password) {
		try {
			String str_query = String.format("update %s set %s = ? where %s = ?", Views.TBL_USER,
					Views.COL_USER_PASSWORD, Views.COL_USER_PHONE);
			String hashpassword = SecurityUtility.encryptBcrypt(password);
			int rowaccept = db.update(str_query, new Object[] { hashpassword, phoneNumber });
			if (rowaccept == 1) {
				return "success";
			}
			return "failed";
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public String editProfile(User user) {
		try {
			StringBuilder queryBuilder = new StringBuilder("UPDATE " + Views.TBL_USER + " SET ");
			List<Object> params = new ArrayList<>();

			if (user.getFullname() != null && !user.getFullname().isEmpty()) {
				queryBuilder.append("fullname = ?, ");
				params.add(user.getFullname());
			}
			if (user.getPassword() != null && !user.getPassword().isEmpty()) {
				queryBuilder.append("password = ?, ");
				String hashPassword = SecurityUtility.encryptBcrypt(user.getPassword());
				params.add(hashPassword);
			}
			if (user.getEmail() != null && !user.getEmail().isEmpty()) {
				queryBuilder.append("email = ?, ");
				params.add(user.getEmail());
			}
			if (user.getPhone() != null && !user.getPhone().isEmpty()) {
				queryBuilder.append("phone = ?, ");
				params.add(user.getPhone());
			}
			if (user.getAddress() != null && !user.getAddress().isEmpty()) {
				queryBuilder.append("address = ?, ");
				params.add(user.getAddress());
			}
			if (user.getImage() != null && !user.getImage().isEmpty()) {
				queryBuilder.append("images = ?, ");
				params.add(user.getImage());
			}

			if (params.isEmpty()) {
				return "No fields to update";
			}

			queryBuilder.setLength(queryBuilder.length() - 2);
			queryBuilder.append(" WHERE " + Views.COL_USER_ID + " = ?");
			params.add(user.getId());

			int rowsAffected = db.update(queryBuilder.toString(), params.toArray());
			return rowsAffected == 1 ? "success" : "failed";
		} catch (DuplicateKeyException e) {
			throw new IllegalArgumentException("Some information(username, email, phone) may already exists.");
		} catch (Exception e) {
			return "Error: " + e.getMessage();
		}
	}

	// Orders
	public List<Order> OrderList(int id) {
		try {
			String str_query = String.format("select * from %s where %s=?", Views.TBL_ORDER,
					Views.COL_ORDERS_USER_ID);
			return db.query(str_query, new Order_mapper(), new Object[] { id });
		} catch (Exception e) {
			return null;
		}
	}
}