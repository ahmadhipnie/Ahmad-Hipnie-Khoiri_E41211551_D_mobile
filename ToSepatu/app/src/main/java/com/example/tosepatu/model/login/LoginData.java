package com.example.tosepatu.model.login;

import com.google.gson.annotations.SerializedName;

public class LoginData {

	@SerializedName("password")
	private String password;

	@SerializedName("wilayah_id")
	private String wilayahId;

	@SerializedName("foto")
	private String foto;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("id_users")
	private String idUsers;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("email_verified_at")
	private String emailVerifiedAt;

	@SerializedName("id")
	private String id;

	@SerializedName("no_telp")
	private String noTelp;

	@SerializedName("remember_token")
	private String rememberToken;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setWilayahId(String wilayahId){
		this.wilayahId = wilayahId;
	}

	public String getWilayahId(){
		return wilayahId;
	}

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setIdUsers(String idUsers){
		this.idUsers = idUsers;
	}

	public String getIdUsers(){
		return idUsers;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setEmailVerifiedAt(String emailVerifiedAt){
		this.emailVerifiedAt = emailVerifiedAt;
	}

	public String getEmailVerifiedAt(){
		return emailVerifiedAt;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setNoTelp(String noTelp){
		this.noTelp = noTelp;
	}

	public String getNoTelp(){
		return noTelp;
	}

	public void setRememberToken(String rememberToken){
		this.rememberToken = rememberToken;
	}

	public String getRememberToken(){
		return rememberToken;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}
}