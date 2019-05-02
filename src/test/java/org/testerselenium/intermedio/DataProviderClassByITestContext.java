package org.testerselenium.intermedio;

//import java.lang.reflect.Method;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

public class DataProviderClassByITestContext {
	//esto es utilizando ITestContext con DataProvider
	@DataProvider(name = "SearchProvider")
	public Object[][] getDataFromDataProvider(ITestContext c){
		Object[][] groupArray = null;
		for (String group: c.getIncludedGroups()) {
			if (group.equals("A")) {
				groupArray = new Object[][] {
					{"Isabel", "Google"},
					{"Ernesto", "Yahoo"},
					{"Gabriela", "Amazon"},
					{"Pedro", "Gmail"}					
				};
				break;
			} else if (group.equals("B")) {
				groupArray = new Object[][] {
					{"Mexico"},
					{"China"},
					{"Rusia"},
					{"Japon"}
				};			
			}
			break;			
		}
		return groupArray;
	}

}
