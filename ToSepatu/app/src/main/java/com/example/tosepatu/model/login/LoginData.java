package com.example.tosepatu.model.login;

import com.google.gson.annotations.SerializedName;

public class LoginData{

	@SerializedName("password")
	private String password;

	@SerializedName("wilayah_id")
	private int wilayahId;

	@SerializedName("foto")
	private String foto;

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("id_users")
	private String idUsers;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("email_verified_at")
	private Object emailVerifiedAt;

	@SerializedName("id")
	private int id;

	@SerializedName("no_telp")
	private String noTelp;

	@SerializedName("remember_token")
	private Object rememberToken;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public String getPassword(){
		return password;
	}

	public int getWilayahId(){
		return wilayahId;
	}

	public String getFoto(){
		return foto;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public String getIdUsers(){
		return idUsers;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public Object getEmailVerifiedAt(){
		return emailVerifiedAt;
	}

	public int getId(){
		return id;
	}

	public String getNoTelp(){
		return noTelp;
	}

	public Object getRememberToken(){
		return rememberToken;
	}

	public String getEmail(){
		return email;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"LoginData{" + 
			"password = '" + password + '\'' + 
			",wilayah_id = '" + wilayahId + '\'' + 
			",foto = '" + foto + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",id_users = '" + idUsers + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",email_verified_at = '" + emailVerifiedAt + '\'' + 
			",id = '" + id + '\'' + 
			",no_telp = '" + noTelp + '\'' + 
			",remember_token = '" + rememberToken + '\'' + 
			",email = '" + email + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}