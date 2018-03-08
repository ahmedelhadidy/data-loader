package eg.com.dataload.util; 

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

import eg.com.dataload.base.ExcelValidationException;
import eg.com.dataload.model.DataType;
import eg.com.dataload.model.Entity;
import eg.com.dataload.model.Entity.Colomn;
import eg.com.dataload.model.Entity.Colomn.Mapping;
import eg.com.dataload.model.Entity.Parameters.Param;

public class ExcelRowParser {
	
	public static void parseEntity(XSSFRow row, Entity entity ) throws ExcelValidationException {
		int col = 1;
		String entityName = getCell(row, col++);
		if(CommonUtil.isEmptyString(entityName)){
			throw new ExcelValidationException(col-1, row.getRowNum(), "Table name is expected");
		}
		col++; // escape Target Title

		String targetStr = getCell(row, col++);
		if(CommonUtil.isEmptyString(targetStr)){
			throw new ExcelValidationException(col-1, row.getRowNum(), "Entity Target Database should be defined");
		}
	
	
		entity.setName(entityName);
		entity.setTarget(targetStr);
	}
	
	public static void parseParameter(XSSFRow row ,Param param) throws ExcelValidationException{
		int col = 0;
		String paramName =  getCell(row, col++);
		if(CommonUtil.isEmptyString(paramName)){
			throw new ExcelValidationException(col-1, row.getRowNum(), "Parameter name is expected");
		}
		String value = getCell(row, col++);
		if(CommonUtil.isEmptyString(value)){
			throw new ExcelValidationException(col-1, row.getRowNum(), "Parameter value is expected");
		}
		String dataTypeStr = getCell(row, col++);
		if(CommonUtil.isEmptyString(dataTypeStr)){
			throw new ExcelValidationException(col-1, row.getRowNum(), "Parameter value data type is expected");
		}
		DataType dataType =null;
		try{
			dataType = DataType.valueOff(dataTypeStr) ;			
		}catch(IllegalArgumentException e){
			throw new ExcelValidationException(col-1, row.getRowNum(), "Parameter Data Type should be one of "+DataType.values(),e);			
		}
		String dateFormate =  getCell(row, col++);
		if(dataType == DataType.DATE && CommonUtil.isEmptyString(dateFormate)){
			throw new ExcelValidationException(col-1, row.getRowNum(), "Date Formate is mandatory as you specified Date as parameter Date Type ");	
		}
		
		param.setName("$"+paramName.toUpperCase());
		param.setValue(value);
		param.setDataType(dataType);
		param.setDateFormate(dateFormate);
	
	}
	
	
	public static void parseColomn(XSSFRow row ,Colomn colomn) throws ExcelValidationException{
	   
		int col=0;
		boolean isKey = false;
		
		String colomnName = getCell(row, col++);
		if(CommonUtil.isEmptyString(colomnName)){
			throw new ExcelValidationException(col-1, row.getRowNum(), "Colomn name is expected");
		}
		
		String isKeyStr = getCell(row, col++);
		if(!CommonUtil.isEmptyString(isKeyStr) && isKeyStr.equalsIgnoreCase("true")){
			isKey=true;
		}
		String value = getCell(row, col++);
		DataType dataType =null;
		String dataTypeStr = getCell(row, col++);
		try{
			dataType = DataType.valueOff(dataTypeStr) ;			
		}catch(IllegalArgumentException e){
			throw new ExcelValidationException(col-1, row.getRowNum(), "colomn Data Type should be one of "+DataType.values(),e);			
		}
		String dateFormate =  getCell(row, col++);
		if(dataType == DataType.DATE && CommonUtil.isEmptyString(dateFormate)){
			throw new ExcelValidationException(col-1, row.getRowNum(), "Date Formate is mandatory as you specified Date as colomn Date Type ");	
		}
		String sourceStr = getCell(row, col++);
		
		if(CommonUtil.isEmptyString(value) && CommonUtil.isEmptyString(sourceStr) && dataType!=DataType.NULL){
			throw new ExcelValidationException(col-1, row.getRowNum(), "Source Database is mandatory in case no value has been specified or data type is not NULL for colomn");
		}
		
		String query = getCell(row, col++);
		
		if(CommonUtil.isEmptyString(query) && CommonUtil.isEmptyString(value) && dataType!=DataType.NULL){
			throw new ExcelValidationException(col-1, row.getRowNum(), "Colomn value or query are expected");	
		}
		
		colomn.setKey(isKey);
		colomn.setDataType(dataType);
		colomn.setDateFormate(dateFormate);
		colomn.setName(colomnName);
		colomn.setQuery(query);
		colomn.setSource(sourceStr);
		colomn.setValue(value);
	}
	
	public static void parseColomnMapping(XSSFRow row,Colomn colomn) throws ExcelValidationException{
		int col=7;
		String key = getCell(row, col++);
		/*if(CommonUtil.isEmptyString(key)){
			throw new ExcelValidationException(col-1, row.getRowNum(), "colomn Mapping key is required ");	
		}*/
		
		String value = getCell(row, col++);
		if(CommonUtil.isEmptyString(value)){
			throw new ExcelValidationException(col-1, row.getRowNum(), "colomn Mapping value is required ");	
		}
		Mapping mapping = new Mapping();
		mapping.setKey(key);
		mapping.setValue(value);
		
		colomn.getMapping().add(mapping);

	}
	
	public static String getCell(XSSFRow row,int currentCursor){
		XSSFCell cell =  row.getCell(currentCursor, XSSFRow.RETURN_BLANK_AS_NULL);
		if(cell == null ){
			return null;
		}else{
			String value;
			if(cell.getCellType() != XSSFCell.CELL_TYPE_STRING){
				value = cell.toString();
				if(value.endsWith(".0")){
					value = value.substring(0, value.lastIndexOf(".0"));
				}
			}else{
				value = cell.toString();
			}
			
			return value;
		}
	}
	
}
