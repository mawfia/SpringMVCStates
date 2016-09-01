package data;

public class State {
	
	private int index;
	private String abbrev;
	private String name;
	private String capital;
	private String latitude;
	private String longitude;
	private int population;
	private String taxrate;
	private String dob;
	
	public State() {
	}
	
	public State(int index, String abbreviation, String name, String capital, String latitude, String longitude, int population) {
		this.index = index;
		this.abbrev = abbreviation;
		this.name = name;
		this.capital = capital;
		this.latitude = latitude;
		this.longitude = longitude;
		this.population = population;
	}

	public String getAbbreviation() {
		return abbrev;
	}
	public String getName() {
		return name;
	}
	public String getDashname() {
		return name.replace(' ', '-');
	}
	public String getCapital() {
		return capital;
	}
	public String getLatitude() {
		return latitude;
	}
	public String getLongitude() {
		return longitude;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbrev = abbreviation;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public String getTaxrate() {
		return taxrate;
	}

	public void setTaxrate(String taxrate) {
		this.taxrate = taxrate;
	}
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public String getDob() {
		return dob;
	}
	
	public void setDob(String dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "State [abbreviation=" + abbrev + ", name=" + name + ", capital=" + capital + "]";
	}

}
