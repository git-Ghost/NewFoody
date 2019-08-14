package com.aws.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.util.ResourceUtils;

import com.foody.pojo.Configuration;

public class FoodyProp {

	static final String classPathForConf = "classpath:conf.json";
	
	private static Configuration conf = null;
	static Logger log = Logger.getLogger(FoodyProp.class);
	
	/**
	 * No Objects Can be created. So, made this constructor as private
	 */
	private FoodyProp() {}
	
	/**
	 * This method will return configuration object for the application
	 * @return Configuration Object
	 * @throws IOException
	 */
	private static Configuration getInstance() throws IOException {
		if (conf == null) {
			log.info("|| Fetching the conf.json File ||");
			File file = ResourceUtils.getFile(classPathForConf);
			String content = new String(Files.readAllBytes(file.toPath()));
			conf = Configuration.getObject(content);
			log.info("TimeZone Found || "+conf.getTimeZone());
			log.info("Configuration Properties has been initialised");
		}
		return conf;
	}
	
	/**
	 * This method will return the sql Date as per configured timeZone
	 * @return java.sql.Date
	 * @throws IOException
	 */
	public static java.sql.Date getConfiguredDate() throws IOException {
		getInstance();
		DateTime dt = new DateTime(new java.sql.Date(System.currentTimeMillis()));
		DateTimeZone dtZone = DateTimeZone.forID(conf.getTimeZone());
		DateTime dtus = dt.withZone(dtZone);
		Date date = dtus.toLocalDateTime().toDate();
		return (new java.sql.Date(date.getTime()));
	}
	
	/**
	 * This method will return the sql Time as per configured timeZone
	 * @return java.sql.Time
	 * @throws IOException
	 */
	public static java.sql.Time getConfiguredTime() throws IOException {
		return(new java.sql.Time(getConfiguredDate().getTime()));
	}
}
