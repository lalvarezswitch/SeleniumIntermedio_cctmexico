package org.testerselenium.intermedio;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGAnnotations {
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("Este método se ejecuta antes de una suite de pruebas");
	}
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("Este método se ejecuta antes de las pruebas @Test");
	}
	
	@BeforeClass
	public void beforeClass() {
		System.out.println("Este método se ejecuta antes de la clase");
	}
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("Se ejecuta antes de cada método de prueba @Test");
	}
	
	@Test
	public void testCase1() {
		System.out.println("Caso de prueba 1");
	}
	
	@Test
	public void testCase2() {
		System.out.println("Caso de prueba 2");
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("Se ejecuta después de cada método de prueba");
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("Este método se ejecuta después de la clase");
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("Este método se ejecuta después de las pruebas @Test");
	}
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("Este método se ejecuta después de una suite de pruebas");
	}
}
