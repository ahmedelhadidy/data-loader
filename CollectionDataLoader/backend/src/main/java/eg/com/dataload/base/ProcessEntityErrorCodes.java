package eg.com.dataload.base;

public enum ProcessEntityErrorCodes {

	FailedProcessColomn("FailedProcessColomn", "Error while executing colomn query");
	
	
	private ProcessEntityErrorCodes(String key, String message) {
		this.key = key;
		this.message = message;
	}
	String key;
	String message;
	
	
	
}
