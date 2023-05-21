package com.example.tosepatu.model.register;

import com.google.gson.annotations.SerializedName;

public class Register{

	@SerializedName("data")
	private RegisterData data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private Boolean status;

	public void setData(RegisterData data){
		this.data = data;
	}

	public RegisterData getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(Boolean status){
		this.status = status;
	}

	public Boolean getStatus(){
		return status;
	}
}