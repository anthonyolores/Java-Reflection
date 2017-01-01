Java Reflection
===================

In java the normal way to use of a class is to instantiate a new object from that class during compile-time. Reflection is a way of java to access or instantiate class and it's methods during run-time state.

In this example I will use Reflection to access private methods from a class to conduct Unit Testing. Apparently you can't call a private method from other class if it has a private access modifier during compile-time.

This scenario is not usual in an actual production code. Basically you need to expose methods that are need to be unit tested. But this is just to show how Reflection can help if there's an instance that you need to access a private methods or attributes without changing the visibility of class' access modifiers.


Unit Test Private Methods
===================
This procedure will show how to unit test private methods outside the class.

1. Class with Private Methods
-----------

This class will contain all the methods that have private access modifier that will be unit tested. This time I will use Calculator.

    public class Calculator {
	
		private int getSum(int a, int b)
		{
			return a+b;
		}
		private int getProduct(int a, int b)
		{
			return a*b;
		}
		private int getQuotient(int a, int b)
		{
			return a/b;
		}
		private int getDifference(int a, int b)
		{
			return a-b;
		}
	}
	

2. Class with Unit Test Methods
-----------
This class will contain the unit test methods and on how to access the Calculator's private methods during run-time using Java Reflection.

> **Note:**  To execute unit testing in java you have to download the Unit Test API. This time JUnit has been used. It can be downloaded here http://junit.org/junit4/

        
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

And Run as JUnit Test!
