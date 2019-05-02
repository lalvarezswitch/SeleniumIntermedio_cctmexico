package org.testerselenium.intermedio;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

public class DataProviderClassByMethod {
	//esto es utilizando reflexion con DataProvider
	@DataProvider(name = "SearchProvider")
	public Object[][] getDataFromDataProvider(Method m){
		if (m.getName().equals("testMethodA")) {
			return new Object[][] {
				{"Fernando", "Google"},
				{"Luis", "Yahoo"},
				{"Sara", "Gmail"},
				{"Lorena", "Amazon"}
			};			
		} else {
			return new Object[][] {
				{"Mexico"},
				{"China"},
				{"Rusia"},
			};
		}		
		
	}

}
