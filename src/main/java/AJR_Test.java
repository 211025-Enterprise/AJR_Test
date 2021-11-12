import annotations.Test;
import org.apache.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AJR_Test {
	/*	 TODONE
		 [X] Locate Tests
		 [X] Execute Tests
		 [X] 3 Test Methods
		 [X] output/logger
		 [X] package
	 */
	public static boolean assertTest(Object obj,String testName){
		Logger logger = Logger.getLogger(AJR_Test.class);
		try {
			Method method = null;
			for (Method m:obj.getClass().getDeclaredMethods()) {
				for (Annotation a: m.getDeclaredAnnotations()) {
					if (a.annotationType().equals(Test.class) && ((Test) a).testname().equals(testName)) {
						method = m;
						break;
					}
				}
			}
			assert method != null;
			method.setAccessible(true);
			method.invoke(obj);
			return true;
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.fatal(e);
			e.printStackTrace();
			return false;
		}
	}
	public static boolean assertEquals(Object value,Object value2){
		Logger logger = Logger.getLogger(AJR_Test.class);
		if ( value.equals(value2)){
			logger.debug("Passed");
			return true;
		}else {
			logger.fatal("Failed");
			return false;
		}

	}
}
