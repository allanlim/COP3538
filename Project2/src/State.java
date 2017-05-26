import java.text.NumberFormat;

/**
 * Represents a State object that holds
 * information describing it.
 *
 * @author Allan Lim
 * @version February 26, 2017
 */
public class State implements Comparable<State> {

	private String name;
	
	private String capitalCity;
	
	private String abbreviation;
	
	private int population;
	
	private String region;
	
	private int houseSeats;
	
	public State(String name, String capitalCity, String abbreviation, int population, String region, int houseSeats) {
		this.name = name;
		this.capitalCity = capitalCity;
		this.abbreviation = abbreviation;
		this.population = population;
		this.region = region;
		this.houseSeats = houseSeats;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCapitalCity() {
		return this.capitalCity;
	}
	
	public void setCapitalCity(String capitalCity) {
		this.capitalCity = capitalCity;
	}
	
	public String getAbbreviation() {
		return this.abbreviation;
	}
	
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	
	public int getPopulation() {
		return this.population;
	}
	
	public void setPopulation(int population) {
		this.population = population;
	}
	
	public String getRegion() {
		return this.region;
	}
	
	public void setRegion(String region) {
		this.region = region;
	}
	
	public int getHouseSeats() {
		return this.houseSeats;
	}
	
	public void setHouseSeats(int houseSeats) {
		this.houseSeats = houseSeats;
	}

	@Override
	public int compareTo(State state) {
		return Integer.compare(state.population, population);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("%1$-20s", name));
		sb.append(String.format("%1$-20s", capitalCity));
		sb.append(String.format("%1$-10s", abbreviation));
		sb.append(String.format("%1$-16s", NumberFormat.getInstance().format(population)));
		sb.append(String.format("%1$-20s", region));
		sb.append(String.format("%1$-20d", houseSeats));
		
		return sb.toString();
	}
	
}
