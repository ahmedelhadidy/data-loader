package eg.com.dataload.base;

public enum EntityValidationErrorCodes {
	
	NoKey("NoKey","Entity Hasn't defined Key Colomn"),
	NoEntityName("NoEntityName","No Entity Name Defined"),
	NoEntityTarget("NoEntityTarget","No Entity Target Database  Defined"),
	
	NoParameterName("NoParameterName","loader has parameter with no name defined"),
	NoParameterValue("NoParameterValue","loader has parameter with no value defined"),
	NoParameterDataType("NoParameterDataType","loader has parameter with no Data Type defined"),
	NoParameterDateFormat("NoParameterDateFormat","loader has Date parameter with no Date Format defined"),
	
	NoColomnName("NoColomnName","Colomn hasn't name defined"),
	NoColomnDataType("NoColomnDataType","Colomn hasn't Data Type Defimed"),
	NoColomnDateFormat("NoColomnDateFormat","Colomn wuth Date Date Type hasn't Date Format Defined"),
	NoColomnSource("NoColomnSource","Colomn with no value defined , has no source defined"),
	NoColomnQuery("NoColomnQuery","Colomn with no value defined , has no query defined"),
	ColomnNoKey("NoColomnNoKey","Entity hasn't key defined as key"),
	ColomnKeyOver("NoColomnKeyOver","Entity has more than one colomn defined as key"),
	DoubColMappingKey("DoubColMappingKey","Doublicate Mapping Key"),
	NoColMappingVal("NoColMappingVal","Colomn Mapping has has no mapping value Defined"),
	
	
	
	;
	
	private String key;
	private String message;
	
	private EntityValidationErrorCodes(String key, String message) {
		this.key = key;
		this.message = message;
	}

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
