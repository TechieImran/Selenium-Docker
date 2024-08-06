package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class config {
	
	private static final Logger log = LoggerFactory.getLogger(config.class);
	private static final String DEFAULT_PROPERTIES ="config/default.properties";
	private static Properties properties;
	
	public static void initialize()
	{
		// load default properties
		   properties = loadProperties();
		
		// check for any override
		   for(String s:properties.stringPropertyNames())
		   {
			 if(System.getProperties().containsKey(s))   
				 properties.setProperty(s, System.getProperty(s));
		   }
		
		//print 
		   System.out.println("---------------");
		   for(String s:properties.stringPropertyNames())
		   {
			   log.info("{} : {}",s,properties.getProperty(s));
		   }
		   System.out.println("---------------");
	}
	
	public static String get(String s)
	{
		return properties.getProperty(s);
	}
	
	private static Properties loadProperties()
	{
		Properties properties = new Properties();
		try(InputStream stream = ResourceLoader.getResource(DEFAULT_PROPERTIES))
		{
			properties.load(stream);
		}
		catch(Exception e)
		{
			log.error("unable to read properties file {}",DEFAULT_PROPERTIES,e);
		}
		return properties;
	}

}
