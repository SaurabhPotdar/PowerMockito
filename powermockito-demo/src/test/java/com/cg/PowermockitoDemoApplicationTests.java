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
	
	private static final String METHOD = "iAmPrivate";

	@Test
	@DisplayName("Mocking static method")
	public void testMethodCallingStatic() throws Exception {
		PowerMockito.mockStatic(EmployeeService.class);
		PowerMockito.when(EmployeeService.get()).thenReturn("Hello");  //Mocking return from private method
		assertEquals("Hello", spy.callStatic());
	}
	
	@Test
	@DisplayName("Mocking private method")
	public void testMethodCallingPrivate() throws Exception {
		//EmployeeService spy = PowerMockito.spy(new EmployeeService());
	    PowerMockito.doReturn("Test").when(spy, METHOD);  //Mocking return from private method
	    assertEquals(spy.getDetails(), "Mock private method example: Test");
	    PowerMockito.verifyPrivate(spy, Mockito.times(1)).invoke(METHOD);
	}

}
