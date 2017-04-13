package applications.MethodsLibrary.CommonLibrary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cucumber.api.DataTable;

public class CommonLibrary {

	public static void getStepName() {
		// TODO Auto-generated method stub

	}	

	public List<HashMap<String,String>> convertFeatureData(DataTable DT){
		List<HashMap<String,String>> FeatureData = new ArrayList<HashMap<String,String>>();  
		HashMap<String,String> ColumnData = null;
		List<String> ColumnNames = DT.raw().get(0);
		int rows = DT.getGherkinRows().size();
		for(int i = 0; i < rows-1; i++){
			ColumnData = new HashMap<String,String>();
			for(int j = 0; j < ColumnNames.size(); j++){
				List<String> ColumnValues = DT.raw().get(i+1);
				ColumnData.put(ColumnNames.get(j), ColumnValues.get(j));
			}
			FeatureData.add(ColumnData);
		}
		return FeatureData;
	}
}