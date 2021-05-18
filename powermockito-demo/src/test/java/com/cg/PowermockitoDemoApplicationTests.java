package com.cg;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.service.EmployeeService;

@SpringBootTest
@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringRunner.class)
@PrepareForTest({EmployeeService.class})
@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "javax.xml.transform.*", "org.xml.*", "javax.management.*", "javax.net.ssl.*", "com.sun.org.apache.xalan.internal.xsltc.trax.*"})
public class PowermockitoDemoApplicationTests {
	
	@InjectMocks
	@Spy
	private EmployeeService spy;
	
	private static final String PRIVATE_METHOD = "privateMethod";
	
	private static final String FINAL_METHOD = "finalMethod";

	@Test
	@DisplayName("Mocking static method")
	public void testMethodCallingStatic() throws Exception {
		PowerMockito.mockStatic(EmployeeService.class);
		PowerMockito.when(EmployeeService.staticMethod()).thenReturn("Hello");  //Mocking return from private method
		assertEquals("Hello", spy.callStatic());
	}
	
	@Test
	@DisplayName("Mocking private method")
	public void testMethodCallingPrivate() throws Exception {
		//EmployeeService spy = PowerMockito.spy(new EmployeeService());
	    PowerMockito.doReturn("Test").when(spy, PRIVATE_METHOD);  //Mocking return from private method
	    assertEquals(spy.callPrivate(), "Mock private method example: Test");
	    PowerMockito.verifyPrivate(spy, Mockito.times(1)).invoke(PRIVATE_METHOD);
	}
	
	@Test
	@DisplayName("Mocking final method")
	public void testMethodCallingFinal() throws Exception {
	    PowerMockito.doReturn("Test").when(spy, FINAL_METHOD);  //Mocking return from private method
	    assertEquals(spy.callFinal(), "Mock final method example: Test");
	    PowerMockito.verifyPrivate(spy, Mockito.times(1)).invoke(FINAL_METHOD);
	}

}
