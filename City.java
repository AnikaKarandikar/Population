/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Anika Karandikar
 *	@since	January 9th, 2023
 */
public class City implements Comparable<City> {
	
	// fields
	private String cityName;
	private String stateName;
	private String cityType;
	private int population;
	
	// constructor
	public City(String cityName, String stateName, String cityType, int population)
	{
		this.cityName = cityName;
		this.stateName = stateName;
		this.cityType = cityType;
		this.population = population;
	}

	public City()
	{
	}
	
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 
	 */
	public int compareTo(City city)
	{
		if(this.population!=city.population) {return this.population-city.population;}
		else if(!(this.stateName.equals(city.stateName))) 
		{return this.stateName.compareTo(city.stateName);}
		return this.cityName.compareTo(city.cityName);
	}
	
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	public boolean equals(City city)
	{
		if((this.cityName.equals(city.cityName))&&
		(this.stateName.equals(city.stateName))) {return true;}
		return false;
	}
	/**	Accessor methods */
	public String getCityName() {
		return cityName;
	}
	public int getPopulation() {
		return population;
	}
	public String getCityType() {
		return cityType;
	}
	public String getStateName() {
		return stateName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public void setCityType(String cityType) {
		this.cityType = cityType;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", stateName, cityName, cityType,
						population);
	}
}