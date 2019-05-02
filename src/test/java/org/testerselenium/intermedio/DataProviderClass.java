package org.testerselenium.intermedio;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	@DataProvider(name = "SearchProvider")
	public Object[][] getDataFromDataProvider(){
		return new Object[][] {
			{"Fernando", "Google"},
			{"Luis", "Yahoo"},
			{"Sara", "Gmail"},
			{"Lorena", "Amazon"}
		};
	}

}
