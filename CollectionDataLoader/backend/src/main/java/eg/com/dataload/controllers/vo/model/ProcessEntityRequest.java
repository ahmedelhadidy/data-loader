package eg.com.dataload.controllers.vo.model;

import eg.com.dataload.model.Entity;
import eg.com.dataload.model.env.Env;


public class ProcessEntityRequest {

	private Entity entity;
	private Env env;
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public Env getEnv() {
		return env;
	}
	public void setEnv(Env env) {
		this.env = env;
	}
	
	
	
}
