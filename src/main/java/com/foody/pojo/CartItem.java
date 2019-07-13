package com.foody.pojo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "item_name", "item_price", "item_count" })
public class CartItem {

	@JsonProperty("item_name")
	private String itemName;
	@JsonProperty("item_price")
	private String itemPrice;
	@JsonProperty("item_count")
	private Integer itemCount;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("item_name")
	public String getItemName() {
		return itemName;
	}

	@JsonProperty("item_name")
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@JsonProperty("item_price")
	public String getItemPrice() {
		return itemPrice;
	}

	@JsonProperty("item_price")
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	@JsonProperty("item_count")
	public Integer getItemCount() {
		return itemCount;
	}

	@JsonProperty("item_count")
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
	
	/**
	 * Method is to Get Json Object of the given String 
	 * @param jsonInput = JsonString
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static CartItem getObject(String jsonInput) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		CartItem readValue = mapper.readValue(jsonInput, CartItem.class);
		return readValue;
	}
	
	/**
	 * Returns the Object Data
	 */
	@Override
	public String toString() {
		return ("Item_Name ==> "+itemName+" Item_Unit ==> "+itemCount+" Item_Price ==> "+itemPrice);
	}
}