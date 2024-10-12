package pack.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pack.models.Admin;
import pack.models.Article;
import pack.models.Service;
import pack.modelviews.Admin_mapper;
import pack.utils.Views;

@Repository
public class AdminRepository {
	@Autowired
	JdbcTemplate db;

	public boolean ExistsAdminCheck(String username, String password) {
		String str_query = String.format("select * from %s where %s=? and %s=?", Views.TBL_ADMIN,
				Views.COL_ADMIN_USERNAME, Views.COL_ADMIN_PASSWORD);
		List<Admin> admin = db.query(str_query, new Admin_mapper(), new Object[] { username, password });
		if (!admin.isEmpty()) {
			return true;
		}
		return false;
	}

	public boolean createService(Service service) {
		String query = "INSERT INTO " + Views.TBL_SERVICE
				+ " (service_name, description, base_price, duration) VALUES (?, ?, ?, ?)";
		try {
			int rowsAffected = db.update(query, service.getSerName(), service.getDescription(), service.getBasePrice(),
					service.getDuration());
			return rowsAffected == 1;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateService(Service service) {
		String query = "UPDATE " + Views.TBL_SERVICE + " SET " + Views.COL_SERVICE_NAME + " = ?, "
				+ Views.COL_SERVICE_DESCRIPTION + " = ?, " + Views.COL_SERVICE_BASE_PRICE + " = ?, "
				+ Views.COL_SERVICE_DURATION + " = ? " + "WHERE " + Views.COL_SERVICE_ID + " = ?";
		try {
			int rowsAffected = db.update(query, service.getSerName(), service.getDescription(), service.getBasePrice(),
					service.getDuration(), service.getId());
			return rowsAffected == 1;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteService(int id) {
		String query = "DELETE FROM " + Views.TBL_SERVICE + " WHERE " + Views.COL_SERVICE_ID + " = ?";
		try {
			int rowsAffected = db.update(query, id);
			return rowsAffected == 1;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean createArticle(Article article) {
		String query = "INSERT INTO " + Views.TBL_ARTICLE
				+ " (title, content, images, created_at, updated_at) VALUES (?, ?, ?, GETDATE(), GETDATE())";
		try {
			int rowsAffected = db.update(query, article.getTitle(), article.getContent(), article.getImages());
			return rowsAffected == 1;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateArticle(Article article) {
		String query = "UPDATE " + Views.TBL_ARTICLE + " SET " + Views.COL_ARTICLE_TITLE + " = ?, "
				+ Views.COL_ARTICLE_CONTENT + " = ?, " + Views.COL_ARTICLE_IMAGES + " = ?, "
				+ Views.COL_ARTICLE_UPDATE_DATE + " = GETDATE() " + "WHERE " + Views.COL_ARTICLE_ID + " = ?";
		try {
			int rowsAffected = db.update(query, article.getTitle(), article.getContent(), article.getImages(),
					article.getId());
			return rowsAffected == 1;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteArticle(int id) {
		String query = "DELETE FROM " + Views.TBL_ARTICLE + " WHERE " + Views.COL_ARTICLE_ID + " = ?";
		try {
			int rowsAffected = db.update(query, id);
			return rowsAffected == 1;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
	}
}