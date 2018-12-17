package entity;

public class Include {

	private int recipe;
	private int material;
	private String matname;
	private String quantity;
	
	public void setRecipe(int recipe) {
		this.recipe=recipe;
	}
	public int getRecipe() {
		return this.recipe;
	}
	
	public void setMaterial(int material) {
		this.material=material;
	}
	public int getMaterial() {
		return this.material;
	}
	
	public void setMatName(String matname) {
		this.matname=matname;
	}
	public String getMatName() {
		return this.matname;
	}
	
	public void setQuantity(String quantity) {
		this.quantity=quantity;
	}
	public String getQuantity() {
		return this.quantity;
	}
	
}
