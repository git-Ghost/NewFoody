package com.foody.pojo;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "timeZone" })
public class Configuration {

	@JsonProperty("timeZone")
	private String timeZone;

	@JsonProperty("timeZone")
	public String getTimeZone() {
		return timeZone;
	}

	@JsonProperty("timeZone")
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	
	/**
	 * Method is to Get Json Object of the given String 
	 * @param jsonInput = JsonString
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static Configuration getObject(String jsonInput) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Configuration readValue = mapper.readValue(jsonInput, Configuration.class);
		return readValue;
	}
}
