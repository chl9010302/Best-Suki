package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StaticProperty {
	private static Properties properties;

	public StaticProperty() throws IOException{
		 properties = new Properties();
		 loadProp("db.properties");
		 loadProp("nav.properties");
		 loadProp("alert.properties");
		
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
	public static String getvideodetail_tb(){
		return properties.getProperty("db8");
	}
	public static String getnavloginview(){
		return properties.getProperty("nav1");
	}
	public static String getnavtestview(){
		return properties.getProperty("nav2");
	}
	public static String getnavtestboardview(){
		return properties.getProperty("nav3");
	}
	public static String getnavstasticsview(){
		return properties.getProperty("nav4");
	}
	public static String getnavmypageview(){
		return properties.getProperty("nav5");
	}
	public static String getnavmypageeditview(){
		return properties.getProperty("nav6");
	}
	public static String getnavsignupview(){
		return properties.getProperty("nav7");
	}
	public static String getnavaddtestboardview(){
		return properties.getProperty("nav8");
	}
	public static String getnavmypageeditpasswordview(){
		return properties.getProperty("nav9");
	}
	public static String getnavtestboarddetailview(){
		return properties.getProperty("nav10");
	}
	public static String getnavaddmainview(){
		return properties.getProperty("nav11");
	}
	public static String getnavmainview(){
		return properties.getProperty("nav12");
	}
	public static String getnavmaindetailview(){
		return properties.getProperty("nav13");
	}
	public static String getnavmaindetaileditview(){
		return properties.getProperty("nav14");
	}
	public static String getnavvideoview(){
		return properties.getProperty("nav15");
	}
	public static String getnavvideodetailview(){
		return properties.getProperty("nav16");
	}
	public static String getnavvideodetaileditview(){
		return properties.getProperty("nav17");
	}
	public static String getnavaddvideoview(){
		return properties.getProperty("nav18");
	}
	public static String getnavtestboarddetaileditview(){
		return properties.getProperty("nav19");
	}
	public static String getnavapplication(){
		return properties.getProperty("nav20");
	}
	public static String getnavfullscreenvideo(){
		return properties.getProperty("nav21");
	}
	public static String getnavtestdetailview(){
		return properties.getProperty("nav22");
	}
	public static String getnavtestdetaileditview(){
		return properties.getProperty("nav23");
	}
	public static String getnavaddtestview(){
		return properties.getProperty("nav24");
	}
	public static String alertlogout(){
		return properties.getProperty("alert1");
	}
	public static String alertcancel(){
		return properties.getProperty("alert2");
	}
	public static String alertfailedtologin(){
		return properties.getProperty("alert3");
	}
	public static String alertedit(){
		return properties.getProperty("alert4");
	}
	public static String alertcompletetoedit(){
		return properties.getProperty("alert5");
	}
	public static String alertcompletetosignup(){
		return properties.getProperty("alert6");
	}
	public static String alertfailedtosignup(){
		return properties.getProperty("alert7");
	}
	public static String alertgoback(){
		return properties.getProperty("alert8");
	}
	public static String alertpasswordnotchanged(){
		return properties.getProperty("alert9");
	}
	public static String alerttitlemessage(){
		return properties.getProperty("alerttitle1");
	}
	public static String alerttitlecancel(){
		return properties.getProperty("alerttitle2");
	}
	public static String alerttitlelogout(){
		return properties.getProperty("alerttitle3");
	}
	public static String alertbtnyes(){
		return properties.getProperty("alertbtn1");
	}
	public static String alertbtnno(){
		return properties.getProperty("alertbtn2");
	}
	public static String alertbtndone(){
		return properties.getProperty("alertbtn3");
	}
}



