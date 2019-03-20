package entity;

public class User {
	
	private String id;
	private String password;
	private String role;
	private String name;
	private String gender;
	private int age;
	private String portrait;
	private String address;
	
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
	
	public void setRole(String role) {
		this.role = role;
	}
	public String getRole() {
		return this.role;
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
	
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return this.age;
	}
	
	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}
	public String getPortrait() {
		return this.portrait;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress() {
		return this.address;
	}
	
}