package ui;

public class SimpleBook implements Book {

	private String title;
	private Page[] pages;

	public SimpleBook(Page[] pageArr) {
		this(null, pageArr);
	}

	public SimpleBook(String title, Page[] pageArr) {
		this.title = title;
		pages = pageArr;
	}

	@Override
	public String title() {
		return title;
	}

	@Override
	public int pageCount() {
		return pages.length;
	}

	@Override
	public String page(int pageNum, boolean full) {
		return pages[pageNum].page(full);
	}

}
