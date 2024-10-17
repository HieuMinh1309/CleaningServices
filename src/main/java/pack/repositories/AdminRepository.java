package pack.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pack.models.Admin;
import pack.models.Blog;
import pack.models.Service;
import pack.modelviews.Admin_mapper;
import pack.modelviews.Blog_mapper;
import pack.utils.Views;

@Repository
public class AdminRepository {
	@Autowired
	JdbcTemplate db;

	public Admin findAdminbyUsername(String username) {
		try {
			String str_query = String.format("select * from %s where %s=?", Views.TBL_ADMIN, Views.COL_ADMIN_USERNAME);
			return db.queryForObject(str_query, new Admin_mapper(), new Object[] { username });
		} catch (Exception e) {
			return null;
		}
	}

	public Admin findAdminById(int id) {
		try {
			String str_query = String.format("select * from %s where %s=?", Views.TBL_ADMIN, Views.COL_ADMIN_ID);
			return db.queryForObject(str_query, new Admin_mapper(), new Object[] { id });
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
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return null;
	}

	public String editService(Service service) {
		try {
			String str_query = String.format("update %s set %s=?, %s=?, %s=?, %s=? where %s=?", Views.TBL_SERVICE,
					Views.COL_SERVICE_NAME, Views.COL_SERVICE_DESCRIPTION, Views.COL_SERVICE_BASE_PRICE,
					Views.COL_SERVICE_DURATION, Views.COL_SERVICE_ID);
			int rowaccept = db.update(str_query, new Object[] { service.getSerName(), service.getDescription(),
					service.getBasePrice(), service.getDuration(), service.getId() });
			if (rowaccept == 1) {
				return "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return null;
	}

	public String deleteService(int id) {
		try {
			int rowaccept = db.update("delete from services where id = ?", new Object[] { id });
			if (rowaccept == 1) {
				return "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return null;
	}

	/***
	 * get all data from table tbl_blog
	 * 
	 * @return list blog
	 */

	public List<Blog> getBlogs() {
		try {
			String str_query = String.format("select * from %s", Views.TBL_BLOG);
			return db.query(str_query, new Blog_mapper());
		} catch (Exception e) {
			return null;
		}
	}

	/***
	 * get data from table tbl_blog by id
	 * 
	 * @return a blog
	 */

	public Blog findBlogById(int id) {
		try {
			String str_query = String.format("select * from %s where %s=?", Views.TBL_BLOG, Views.COL_BLOG_ID);
			return db.queryForObject(str_query, new Blog_mapper(), new Object[] { id });
		} catch (Exception e) {
			return null;
		}
	}

	/***
	 * create new blog
	 * 
	 * @return new blog
	 */

	public String newBlog(Blog blog) {
		try {
			String str_query = String.format("insert into %s (title, content, images) values(?,?,?)", Views.TBL_BLOG);
			int rowaccept = db.update(str_query,
					new Object[] { blog.getTitle(), blog.getContent(), blog.getImages() });
			if (rowaccept == 1) {
				return "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return null;
	}

	/***
	 * edit a blog
	 * 
	 * @return edited blog
	 */

	public String editBlog(Blog blog) {
		try {
			String str_query = String.format("update %s set %s=?, %s=?, %s=?, %s=GETDATE() where id=?", Views.TBL_BLOG,
					Views.COL_BLOG_TITLE, Views.COL_BLOG_CONTENT, Views.COL_BLOG_IMAGES, Views.COL_BLOG_UPDATE_DATE);
			int rowaccept = db.update(str_query,
					new Object[] { blog.getTitle(), blog.getContent(), blog.getImages(), blog.getUpdateDate() });
			if (rowaccept == 1) {
				return "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "failed";
		}
		return null;
	}

	/***
	 * delete a blog
	 * 
	 * @return deleted blog
	 */

	public String deleteBlog(int id) {
		try {
			String str_query = String.format("delete from %s where %s=?", Views.TBL_BLOG, Views.COL_BLOG_ID);
			int rowaccept = db.update(str_query, new Object[] { id });
			if (rowaccept == 1) {
				return "success";
			}
			return "failed";
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}