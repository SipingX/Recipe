package entity;

public class Recipe {
	
	private int id;
	private String author;
	private String censor;
	private String name;
	private String rating;
	private int browse;
	private String complexity;
	private int minute;
	private String tasty;
	private String method;
	private String description;
	private String address;
	
	public void setId(int id) {
		this.id=id;
	}
	public int getId() {
		return this.id;
	}
	
	public void setAuthor(String author) {
		this.author=author;
	}
	public String getAuthor() {
		return this.author;
	}
	
	public void setCensor(String censor) {
		this.censor=censor;
	}
	public String getCensor() {
		return this.censor;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	public String getName() {
		return this.name;
	}
	
	public void setRating(String rating) {
		this.rating=rating;
	}
	public String getRating() {
		return this.rating;
	}
	
	public void setBrowse(int browse) {
		this.browse=browse;
	}
	public int getBrowse() {
		return this.browse;
	}
	
	public void setComplexity(String complexity) {
		this.complexity=complexity;
	}
	public String getComplexity() {
		return this.complexity;
	}
	
	public void setMinute(int minute) {
		this.minute=minute;
	}
	public int getMinute() {
		return this.minute;
	}
	
	public void setTasty(String tasty) {
		this.tasty=tasty;
	}
	public String getTasty() {
		return this.tasty;
	}
	
	public void setMethod(String method) {
		this.method=method;
	}
	public String getMethod() {
		return this.method;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}
	public String getDescription() {
		return this.description;
	}
	
	public void setAddress(String address) {
		this.address=address;
	}
	public String getAddress() {
		return this.address;
	}
	
}
