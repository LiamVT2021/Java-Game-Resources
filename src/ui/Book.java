package ui;

/**
 * objects that can have their information displayed in pages.
 * 
 * @author Liam
 * @version 6/20/2021
 */
public interface Book {

	//optional
	public String title();

	public int pageCount();

	public String page(int pageNum, boolean full);
}
