package eg.com.dataload.base;

public class EntityValidationException extends Exception {

	private EntityValidationErrorCodes  code;
	
	public EntityValidationException(EntityValidationErrorCodes code , Throwable cause){
		super(code.getKey()+" :-"+code.getMessage(),cause);
		this.code = code;
	}
	
	public EntityValidationException(EntityValidationErrorCodes code){
		super(code.getKey()+" :-"+code.getMessage());
		this.code = code;
	}
	
	public EntityValidationErrorCodes getCode(){
		return this.code;
	}
	
	
}
