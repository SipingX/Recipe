package entity;

public class Material {
	
	private int id;
	private String name;
	private String category;
	private String picture;
	private String description;
	
	public void setId(int id) {
		this.id=id;
	}
	public int getId() {
		return this.id;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return this.name;
	}
	
	public void setCategory(String category) {
		this.category=category;
	}
	public String getCategory() {
		return this.category;
	}

	public void setPicture(String picture) {
		this.picture=picture;
	}
	public String getPicture() {
		return this.picture;
	}

	public void setDescription(String descriptio) {
		this.description=descriptio;
	}
	public String getDescriptio() {
		return this.description;
	}


}
