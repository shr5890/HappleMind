package framework;

import java.util.Map;

public class BeanClass {

	private static boolean result = false;
	private static Map<String, String> hmMap = null;
	public boolean getResult() {
		return result;
	}

	public void setResult(boolean setResult) {
		result = setResult;
	}

	public Map<String, String> getGlobalDataStore() {
		return hmMap;
	}

	public void setGlobalDataStore(Map<String, String> StoreMap) {
		hmMap = StoreMap;
	}
}