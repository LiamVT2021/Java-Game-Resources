package ui;

/**
 * objects that can have there information displayed in pages.
 * 
 * @author Liam
 * @version 6/20/2021
 */
public interface Readable<Back> {

	public int pageCount();

	public String page(int pageNum, Back back);
}
