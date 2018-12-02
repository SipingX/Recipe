package entity;

public class User {
	
	private String id;
	private String password;
	private String name;
	private String gender;
	private String age;
	private String portrait;
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return this.id;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return this.password;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGender() {
		return this.gender;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
	public String getAge() {
		return this.age;
	}
	
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
	public String getPortrait() {
		return this.portrait;
	}

}
