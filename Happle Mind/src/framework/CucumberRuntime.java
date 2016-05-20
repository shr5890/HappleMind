package framework;

import com.google.common.base.Joiner;
import cucumber.runtime.io.*;
import cucumber.runtime.*;
import cucumber.runtime.Runtime;

public class CucumberRuntime {
	public void CucumberRunner() {
		try {
			ClassLoader classLoader=getClass().getClassLoader();
			ResourceLoader resourceLoader=new MultiLoader(classLoader);
			RuntimeOptionsFactory roFactory=new RuntimeOptionsFactory(getClass());			
			RuntimeOptions ro=roFactory.create();			
			ro.getGlue().clear();
			ro.getGlue().add("applications.StepDefinitions");		  
			ro.getFeaturePaths().clear();
			String feature = "src\\applications\\Features";		  
			ro.getFeaturePaths().add(feature);
			String tags = Joiner.on(",").join(XMLGenerator.Scenarios);			
			ro.getFilters().add(tags);			
//			ro.addPlugin("html:Reports\\");// under cinstruction
			ClassFinder classFinder=new ResourceLoaderClassFinder(resourceLoader,classLoader);
			Runtime runtime=new Runtime(resourceLoader,classFinder,classLoader,ro);			
			try {
				runtime.run();
			}
			catch (RuntimeException ex) {
				throw new RuntimeException(ex);
			}
			if (!runtime.getErrors().isEmpty()) {
				System.out.println("error");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}