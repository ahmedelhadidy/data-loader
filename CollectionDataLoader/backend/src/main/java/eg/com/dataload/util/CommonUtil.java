package eg.com.dataload.util;

import java.util.Collection;
import java.util.Map;

public class CommonUtil {

	
	public static boolean isEmptyString(String string){
		return string == null || string.length()<=0;
	}
	
	public static boolean isEmptyCollection(Collection collection){
		return collection == null || collection.size()<=0;
	}
	
	public static boolean isEmptyArray(Object [] array){
		return array == null || array.length<=0;
	}
	
	public static boolean isEmptyMap(Map [] map){
		return map == null || map.length <=0;
	}
	
	
}
