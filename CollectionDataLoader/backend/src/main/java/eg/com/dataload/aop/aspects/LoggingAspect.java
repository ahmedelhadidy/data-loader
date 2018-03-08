package eg.com.dataload.aop.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	static Logger logger=Logger.getLogger(LoggingAspect.class);
	
	@Around("@annotation(eg.com.dataload.aop.annotaion.Loggable)")
	public Object logMethodExecution(ProceedingJoinPoint joinPoint) throws Throwable{
		
		String methodName = joinPoint.getSignature().getName();
		logger.info("Start executing method ["+methodName+"] ");
	    
		try{
			Long startTime = System.currentTimeMillis();
			Object proceed = joinPoint.proceed();
			Long endTime = System.currentTimeMillis();
			logger.info("executing method ["+methodName+"] succeeded in "+ (endTime - startTime)/1000 +"  Seconds" );
			return proceed;
		}catch(Exception e){
			logger.error("executing method ["+methodName+"] failed with error :- "+e,e);
			throw e;
		}

	}

}
