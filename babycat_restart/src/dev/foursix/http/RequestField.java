package dev.foursix.http;

import java.util.ArrayList;
import java.util.List;

public class RequestField {
	
	private String	fieldName;
	private List<String> fieldValues;
	
	public RequestField() {
		// TODO Auto-generated constructor stub
		fieldValues = new ArrayList<String>();
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldValue(int index) {
		return fieldValues.get(index);
	}
	public void addFieldValue(String fieldValue) {
		fieldValues.add(fieldValue);
	}
}
