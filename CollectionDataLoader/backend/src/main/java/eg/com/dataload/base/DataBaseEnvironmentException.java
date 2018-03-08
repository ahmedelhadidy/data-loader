package eg.com.dataload.base;

public class DataBaseEnvironmentException extends Exception{

	private DataBaseEnvironmentErrorCodes code;

	public DataBaseEnvironmentException(DataBaseEnvironmentErrorCodes code,String message, Throwable cause) {
		super(code.getKey()+" :-"+code.getMessage() +" , "+message, cause);
		this.code = code;
	}

	public DataBaseEnvironmentErrorCodes getCode() {
		return code;
	}

	public void setCode(DataBaseEnvironmentErrorCodes code) {
		this.code = code;
	}
	
	
	
}
