package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StaticProperty {
	private static Properties properties;

	public StaticProperty() throws IOException{
		 properties = new Properties();
		 loadProp("db.properties");
		
	}
	public Properties getProperties() {
	        return properties;
	}
	public void loadProp(String path) throws IOException {
	        InputStream inputStream = getClass().getResourceAsStream(path);
	        properties.load(inputStream);
	        inputStream.close();
	}	
	public static String getuserclass_tb(){
		return properties.getProperty("db1");
	}
	public static String getuser_tb(){
		return properties.getProperty("db2");
	}
	public static String gettest_tb(){
		return properties.getProperty("db3");
	}
	public static String gettestdetail_tb(){
		return properties.getProperty("db4");
	}
	public static String getgrade_tb(){
		return properties.getProperty("db5");
	}
	public static String getdate_tb(){
		return properties.getProperty("db6");
	}
	public static String getnoticedetail_tb(){
		return properties.getProperty("db7");
	}
	
	
	
}



