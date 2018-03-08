package eg.com.dataload.base;


public enum DataBaseEnvironmentErrorCodes  {
	
	NoEnvExists("NoEnvExists","No Environment Exists with that Name"),
	NoDataBaseExists("NoDataBaseExists","No Database Exists with that Name ");
	
	
	
	private DataBaseEnvironmentErrorCodes(String code, String desc) {
		this.key = code;
		this.message = desc;
	}
	String key;
	String message;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}
