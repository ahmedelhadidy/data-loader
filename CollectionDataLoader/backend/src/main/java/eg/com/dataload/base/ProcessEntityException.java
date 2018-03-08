package eg.com.dataload.base;

import eg.com.dataload.model.Entity;
import eg.com.dataload.model.Entity.Colomn;

public class ProcessEntityException extends Exception {
	
	private Entity entity;
	private Colomn colomn;
	private ProcessEntityErrorCodes code;
	
	public ProcessEntityException(Entity entity ,Colomn colomn, ProcessEntityErrorCodes code,String message, Throwable cause) {
		super(code.key +": " + code.message+", Entity ["+entity.getName() + "] , colomn ["+colomn.getName()+"] :-"+ message, cause);
		this.entity = entity;
		this.colomn = colomn;
		this.code = code;		
	}
	
	
	
	

}
