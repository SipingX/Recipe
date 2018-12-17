package entity;

public class Step {
	
	private int recipe;
	private int sequence;
	private String description;
	private String picture;
	
	public void setRecipe(int recipe) {
		this.recipe=recipe;
	}
	public int getRecipe() {
		return this.recipe;
	}
	
	public void setSequence(int sequence) {
		this.sequence=sequence;
	}
	public int getSequence() {
		return this.sequence;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}
	public String getDescription() {
		return this.description;
	}
	
	public void setPicture(String picture) {
		this.picture=picture;
	}
	public String getPicture() {
		return this.picture;
	}
	
}
