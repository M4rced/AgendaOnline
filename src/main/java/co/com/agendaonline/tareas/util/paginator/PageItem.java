package co.com.agendaonline.tareas.util.paginator;

public class PageItem {

	private int pageNumber;
	private boolean actual;
	
	public PageItem(int pageNumber, boolean actual) {
		this.pageNumber = pageNumber;
		this.actual = actual;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public boolean isActual() {
		return actual;
	}
	
}



/*** creado por M4rced and G3ors ***/


