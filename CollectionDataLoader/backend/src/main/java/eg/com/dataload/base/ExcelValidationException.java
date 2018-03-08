package eg.com.dataload.base;

public class ExcelValidationException extends Exception {

	private int colomnNo;
	private int rowNo;
	private String message;
	
	public ExcelValidationException(int colomnNo, int rowNo , String message) {
		super();
		this.colomnNo = colomnNo;
		this.rowNo = rowNo;
		this.message = message;
	}
	
	public ExcelValidationException(int colomnNo, int rowNo , String message , Throwable cause) {
		super(cause);
		this.colomnNo = colomnNo;
		this.rowNo = rowNo;
		this.message = message;
	}

	@Override
	public String getMessage() {		
		return "Error Parsing Col,Row ["+colomnNo+1 +","+rowNo+1 +"] :- "+message;
	}
	
	
	
	
	
}
