package eg.com.dataload.service;



import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Service;

import eg.com.dataload.aop.annotaion.Loggable;
import eg.com.dataload.base.EntityValidationErrorCodes;
import eg.com.dataload.base.EntityValidationException;
import eg.com.dataload.base.ProcessEntityErrorCodes;
import eg.com.dataload.base.ProcessEntityException;
import eg.com.dataload.model.DataType;
import eg.com.dataload.model.Entity;
import eg.com.dataload.model.Entity.Colomn;
import eg.com.dataload.model.Entity.Colomn.Mapping;
import eg.com.dataload.model.Entity.Parameters.Param;
import eg.com.dataload.util.CommonUtil;

@Service
public class EntityService {
	
	private static Logger logger = Logger.getLogger(EntityService.class);

	static String PARAM_REG_X ="(\\$(\\w*\\d*)+)(?=\\W*)";
	static Pattern PARAM_PATT = Pattern.compile(PARAM_REG_X);
	
	@Loggable
	public void validateEntity(Entity entity) throws EntityValidationException{
		if(CommonUtil.isEmptyString(entity.getName())){
			throw new EntityValidationException(EntityValidationErrorCodes.NoEntityName);
		}
		if(CommonUtil.isEmptyString(entity.getTarget() )){
			throw new EntityValidationException(EntityValidationErrorCodes.NoEntityTarget);
		}
		if(entity.getParameters() !=null && !CommonUtil.isEmptyCollection(entity.getParameters().getParam())){
			for(Param p:entity.getParameters().getParam()){
				if(CommonUtil.isEmptyString(p.getName())){
					throw new EntityValidationException(EntityValidationErrorCodes.NoParameterName);
				}
				if(CommonUtil.isEmptyString(p.getValue())){
					throw new EntityValidationException(EntityValidationErrorCodes.NoParameterValue);
				}
				if(p.getDataType() == null){
					throw new EntityValidationException(EntityValidationErrorCodes.NoParameterDataType);
				}
				if( p.getDataType() == DataType.DATE && CommonUtil.isEmptyString(p.getDateFormate())){
					throw new EntityValidationException(EntityValidationErrorCodes.NoParameterDateFormat);
				}
				
			}
		}
		
		if(!CommonUtil.isEmptyCollection(entity.getColomn())){
			int countOfKeys = 0;
			for(Colomn c:entity.getColomn()){
				if(c.isKey()){
					countOfKeys++;
				}
				if(CommonUtil.isEmptyString(c.getName())){
					throw new EntityValidationException(EntityValidationErrorCodes.NoColomnName);
				}
				if(c.getDataType() == null){
					throw new EntityValidationException(EntityValidationErrorCodes.NoColomnDataType);
				}
				if(c.getDataType() == DataType.DATE && CommonUtil.isEmptyString(c.getDateFormate())){
					throw new EntityValidationException(EntityValidationErrorCodes.NoColomnDateFormat);
				}
				if(CommonUtil.isEmptyString(c.getValue()) && CommonUtil.isEmptyString( c.getSource())  && c.getDataType()!=DataType.NULL){
					throw new EntityValidationException(EntityValidationErrorCodes.NoColomnSource);
				}
				if(CommonUtil.isEmptyString(c.getValue()) && CommonUtil.isEmptyString(c.getQuery()) && c.getDataType()!=DataType.NULL){
					throw new EntityValidationException(EntityValidationErrorCodes.NoColomnQuery);
				}
				Map<String, String> mappingMap=new HashMap<String, String>();
				if(!CommonUtil.isEmptyCollection(c.getMapping())){
					for(Mapping m:c.getMapping()){
						
						if(mappingMap.containsKey(m.getKey())){
							throw new EntityValidationException(EntityValidationErrorCodes.DoubColMappingKey);							
						}
						if(CommonUtil.isEmptyString(m.getValue())){
							throw new EntityValidationException(EntityValidationErrorCodes.NoColMappingVal);
						}
						
						mappingMap.put(m.getKey(), m.getValue());
						
					}
				}
			}
			if(countOfKeys==0){
				throw new EntityValidationException(EntityValidationErrorCodes.ColomnNoKey);
			}else if(countOfKeys>1){
				throw new EntityValidationException(EntityValidationErrorCodes.ColomnKeyOver);
			}
		}
		
	}
	
	public String  getEntityLoaderXml(Entity entity) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Entity.class);
		Marshaller m = context.createMarshaller();
		
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.setProperty(com.sun.xml.bind.marshaller.CharacterEscapeHandler.class.getName() ,
				new com.sun.xml.bind.marshaller.CharacterEscapeHandler() {					
					@Override
					public void escape(char[] ch, int start, int length,
							boolean isAttVal, Writer writer)
							throws IOException {
						writer.write(ch, start, length);
					}
				});
		
		
		StringWriter writer = new StringWriter();
		
		m.marshal(entity, writer);
		String result = writer.toString();
              
		return result;
		
		
	}

	@Loggable
	public void processEntity(Entity entity , Map<String, DataSource> dataSources) throws Exception{
		
		 Object [][] colomnsValues = processEntityColumns(entity, dataSources);
		 if(colomnsValues!=null ){
			 
			 List<Colomn> colomns = entity.getColomn();
			 Collections.sort(colomns);
			List<Map<String, Object>> batch = new ArrayList<Map<String,Object>>();
			 
			 for(int i=0;i<colomnsValues.length;i++){
				 Map<String, Object> rowValues = new HashMap<String, Object>();
				 for(int j=0;j<colomnsValues[i].length;j++){
					 rowValues.put(colomns.get(j).getName(), colomnsValues[i][j]);
				 }
				 batch.add(rowValues);
			 }			 
			 NamedParameterJdbcTemplate  template = new NamedParameterJdbcTemplate(dataSources.get(entity.getTarget()));
			 SqlParameterSource [] batchArr = SqlParameterSourceUtils.createBatch(batch.toArray(new Map[1]));
			 
			 template.batchUpdate(entity.getInsertStatement(), batchArr);			 
		 }		
	}
	

    private Object [][] processEntityColumns(Entity entity , Map<String, DataSource> dataSources) throws Exception{
    	logger.info("Start executing processEntityColumns mathod");
    	Map<String , Object> parameters = new HashMap<String, Object>();
    	List<Object []> retrived = new ArrayList<Object[]>();
    	Colomn keyColomn = entity.getKeyColomn();
    	if(entity.getParameters()!=null && !CommonUtil.isEmptyCollection(entity.getParameters().getParam())){
    		for(Param p: entity.getParameters().getParam()){
    			if(p.getValue()!=null){
    				parameters.put(p.getName(), getValueWithCorrectType(p.getValue(), p.getDataType(), p.getDateFormate()));
    			}
    		}    		
    	}
    	
    	Object keyColomnValue = resolvColomnValue(entity,keyColomn, parameters, dataSources.get(keyColomn.getSource()));
    	List<Colomn> allColomns = entity.getColomn();
    	Collections.sort(allColomns);
    	
    	if(keyColomnValue instanceof List){
    		List<Object> keyColmnValueList = List.class.cast(keyColomnValue);
    		logger.debug("["+keyColmnValueList.size()+"] records returned for entity ["+entity.getName()+"]");
    		for(Object onekeyValue : keyColmnValueList){
    			parameters.put("$KEY", onekeyValue);
    			Object [] row = new Object[allColomns.size()];
        		Map<String, Object> colomnAsParameter = new HashMap<String, Object>();
    			for(int i=0;i<allColomns.size();i++){
        			Colomn  c = allColomns.get(i);
        			if(c.isKey()){
        				row[i] = onekeyValue;
        			}else{        				
        				row[i] = resolvColomnValue(entity,c, parameters, dataSources.get(c.getSource()));
        			}
        			
        			mergeParameters(parameters , "$"+c.getName().toUpperCase() , row[i] ,(oldVal , newVal) ->{
        				return newVal;
        			});
        		}
        		retrived.add(row);
    		}
    	}else{
    		logger.debug("one record returned for entity ["+entity.getName()+"]");
    		parameters.put("$KEY", keyColomnValue);
    		Object [] row = new Object[allColomns.size()];
    		for(int i=0;i<allColomns.size();i++){
    			Colomn  c = allColomns.get(i);
    			if(c.isKey()){
    				row[i] = keyColomnValue;
    			}else{
    				row[i] = resolvColomnValue(entity,c, parameters, dataSources.get(c.getSource()));
    			}
    			mergeParameters(parameters , "$"+c.getName().toUpperCase() , row[i] ,(oldVal , newVal) ->{
    				return newVal;
    			});
    		}
    		retrived.add(row);
    	}
    	
    	Object [][] _return = new Object [retrived.size()][retrived.get(0).length];
    	if(!CommonUtil.isEmptyCollection(retrived)){
    		int rowId = 0;
    		for(Object[] row : retrived){
    			_return[rowId++] =  row;
    		}
    	}
    	
    	return _return;
    }
    
    
    private void mergeParameters(Map<String, Object> parameters, String key, Object value, BiFunction<Object, Object, Object> remappingFunction) {
    	if(value!=null){
    		parameters.merge(key, value, remappingFunction);    		
    	}
	}

    @Loggable
	private Object resolvColomnValue (Entity entity , Colomn colomn , Map<String, Object> parameters , DataSource dataSource ) throws ProcessEntityException {
    	try{
    		Object value=null;
        	if(colomn.getDataType() == DataType.NULL){
        		return null;
        	}
    		else if(!CommonUtil.isEmptyString(colomn.getValue())){
        		if(colomn.getValue().trim().matches(PARAM_REG_X)){
        			value = parameters.get(colomn.getValue().toUpperCase());
        		}else{
        			value = getValueWithCorrectType(colomn.getValue() , colomn.getDataType(),colomn.getDateFormate());        			
        		}
        	}else{
        		String query =  getReplaceParamName(colomn.getQuery()); 
        		
        		if(!CommonUtil.isEmptyCollection(colomn.getMapping())){
        			Object queryResult = executeQuery(query , DataType.STRING ,parameters ,dataSource);
        			Mapping defaultMapping = null;
        			for(Mapping m:colomn.getMapping()){
        				if(CommonUtil.isEmptyString(m.getKey())){
        					defaultMapping = m;
        					continue;
        				}
        				if(queryResult !=null && m.getKey().equalsIgnoreCase(queryResult.toString())){
        					value = getValueWithCorrectType(m.getValue(),colomn.getDataType(),colomn.getDateFormate());
        					break;
        				}
        			}
        			if(value==null && defaultMapping!=null){
        				value = getValueWithCorrectType(defaultMapping.getValue(),colomn.getDataType(),colomn.getDateFormate());
        			}
        		}else{
        			value = executeQuery(query , colomn.getDataType() ,parameters ,dataSource);
        		}
        	}
        	    	
        	return value;
    	}catch(Exception e){
    		throw new ProcessEntityException(entity, colomn, ProcessEntityErrorCodes.FailedProcessColomn, "", e);
    	}
    	
    	
    	
    }

	private Object executeQuery(String query, DataType dataType, Map<String, Object> parameters, DataSource dataSource) {
		
		NamedParameterJdbcTemplate  template = new NamedParameterJdbcTemplate(dataSource); 
		Object _return = null;
		List returnedList = null;
		switch (dataType){
		case STRING:
			returnedList  = template.queryForList(query, parameters, String.class);
			break;
		case NUMBER:
			returnedList  = template.queryForList(query, parameters, Long.class);
			break;
		case DOUBLE:
			returnedList  =template.queryForList(query, parameters, Double.class);
			break;
		case DATE:
			returnedList  = template.queryForList(query, parameters, Date.class);
			break;
		}
		
		if(returnedList == null ){
			_return= null;
		}else if(returnedList.size()==0){
			_return= null;
		}
		else if(returnedList.size() == 1){			
			_return =returnedList.get(0);		
		}else{
			_return =returnedList;
		}
		
	
		return _return;
	}

	private String getReplaceParamName(String query) {
		
		
		Matcher matcher = PARAM_PATT.matcher(query);
		StringBuffer sb = new StringBuffer();
		List<String> paramNames = new ArrayList<String>();
		while(matcher.find()){
			String p = matcher.group();
			matcher.appendReplacement(sb, ":\\"+p.toUpperCase());
			paramNames.add(p);
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	private Object getValueWithCorrectType(String value, DataType dataType,String dateFormate) throws ParseException {
		switch(dataType){
		case STRING:
			return value;
		case NUMBER:
			return Integer.parseInt(value);
		case DOUBLE:
			return Double.parseDouble(value);
		case DATE:{
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormate);
			simpleDateFormat.setLenient(false);
			return simpleDateFormat.parse(value);
		}
		
	    default:
	    	throw new IllegalArgumentException("data type value ["+dataType+"] is not mapped");			
		}		
	}

}
