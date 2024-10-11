package pack.repositories;

import org.springframework.beans.factory.annotation.Autowired;
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

	public Admin findAdminbyUsername(String username) {
		try {
			String str_query = String.format("select * from %s where %s = ?", Views.TBL_ADMIN, Views.COL_ADMIN_USERNAME);
			return db.queryForObject(str_query, new Admin_mapper(), new Object[] {username});
		} catch (Exception e) {
			return null;
		}
	}
	
	public Admin findAdminById(int id) {
		try {
			String str_query = String.format("select * from %s where %s = ?", Views.TBL_ADMIN, Views.COL_ADMIN_ID);
			return db.queryForObject(str_query, new Admin_mapper(), new Object[] {id});
		} catch (Exception e) {
			return null;
		}
	}

	public String newService(Service service) {
		try {
			String str_query = String.format("insert into %s values(?,?,?,?)", Views.TBL_SERVICE);
			int rowaccept = db.update(str_query, new Object[] { service.getSerName(), service.getDescription(),
					service.getBasePrice(), service.getDuration() });
			if (rowaccept == 1) {
				return "success";
			}
			return "failed";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}

	public String editService(Service service) {
		try {
			String str_query = String.format("update %s set %=?, %s = ?, %s = ?, %s = ? where %s = ?",
					Views.TBL_SERVICE, Views.COL_SERVICE_NAME, Views.COL_SERVICE_DESCRIPTION,
					Views.COL_SERVICE_BASE_PRICE, Views.COL_SERVICE_DURATION, Views.COL_SERVICE_ID);
			int rowaccept = db.update(str_query, new Object[] { service.getSerName(), service.getDescription(),
					service.getBasePrice(), service.getDuration(), service.getId() });
			if (rowaccept == 1) {
				return "success";
			}
			return "failed";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}

	public String deleteService(int id) {
		try {
			int rowaccept = db.update("delete from services where id = ?", new Object[] { id });
			if (rowaccept == 1) {
				return "success";
			}
			return "failed";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}

	public String newArticle(Article article) {
		try {
			String str_query = String.format("insert into %s values(?,?,?)", Views.TBL_ARTICLE);
			int rowaccept = db.update(str_query,
					new Object[] { article.getTitle(), article.getContent(), article.getImages() });
			if (rowaccept == 1) {
				return "success";
			}
			return "failed";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}

	public String editArticle(Article article) {
		try {
			String str_query = String.format("update %s set %s=?, %s=?, %s=?, %s=GETDATE() where id = ?",
					Views.TBL_ARTICLE, Views.COL_ARTICLE_TITLE, Views.COL_ARTICLE_CONTENT, Views.COL_ARTICLE_IMAGES,
					Views.COL_ARTICLE_UPDATE_DATE);
			int rowaccept = db.update(str_query,
					new Object[] { article.getTitle(), article.getContent(), article.getImages() });
			if (rowaccept == 1) {
				return "success";
			}
			return "failed";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}

	public String deleteArticle(int id) {
		try {
			int rowaccept = db.update("delete from %s where %s = ?", Views.TBL_ARTICLE, Views.COL_ARTICLE_ID,
					new Object[] { id });
			if (rowaccept == 1) {
				return "success";
			}
			return "failed";
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}
}
