
import static org.junit.Assert.*;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

public class MyClass {

	 private Calculator calc;
	 private  Method methods[];
	 private Class[] parameterTypes;
	 private Object[] parameters;
	 
	 /**
	  * Setup necessary data before executing unit test
	  * 
	  * @throws Exception
	  */
 	@Before
    public void setUp() throws Exception {
 		
 		//instantiate object
        calc = (Calculator)Class.forName("Calculator").newInstance();
        
        //parameter types
        parameterTypes = new Class[2];
        parameterTypes[0] = int.class;
        parameterTypes[1] = int.class;
        
        //parameter values
        parameters = new Object[2];
        parameters[0] = 5;
        parameters[1] = 5;	        
        
        //get methods
        methods = Calculator.class.getDeclaredMethods();
         for(int i = 0; i < methods.length; i++){
             methods[i].setAccessible(true);
          }

    }

    @Test
    public void testGetDifference() throws Exception {

        int result = (int) methods[0].invoke(calc, parameters); 
        assertEquals(0, result);
    }
    @Test
    public void testGetQuotient() throws Exception {

        int result = (int) methods[1].invoke(calc, parameters); 
        assertEquals(1, result);
    }	    
    @Test
    public void testGetProduct() throws Exception {

        int result = (int) methods[2].invoke(calc, parameters); 
        assertEquals(25, result);
    }
    @Test
    public void testGetSum() throws Exception {

        int result = (int) methods[3].invoke(calc, parameters); 
        assertEquals(10, result);
    }
}
