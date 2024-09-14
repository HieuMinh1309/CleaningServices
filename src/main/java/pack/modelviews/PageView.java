package pack.modelviews;

public class PageView {
	private int page_size = 5;
	private int page_current = 1;
	private int total_page = 1;

	public PageView() {
	}

	public PageView(int page_size, int page_current, int total_page) {
		super();
		this.page_size = page_size;
		this.page_current = page_current;
		this.total_page = total_page;
	}

	public int getPage_size() {
		return page_size;
	}

	public void setPage_size(int page_size) {
		this.page_size = page_size;
	}

	public int getPage_current() {
		return page_current;
	}

	public void setPage_current(int page_current) {
		this.page_current = page_current;
	}

	public int getTotal_page() {
		return total_page;
	}

	public void setTotal_page(int total_page) {
		this.total_page = total_page;
	}
}
