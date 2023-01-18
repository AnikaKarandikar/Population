import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *	Population - This program effectively sorts almost 30,000 US 
 	Cities based upon population and city name in a multitude of
	orders.
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Anika Karandikar
 *	@since	Jan 16 2023
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	private ArrayList<City> temp;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";

	//constructor
	public Population()
	{
		cities = new ArrayList<City>();
	}

	//main method
	public static void main(String [] args)
	{
		Population p = new Population();
		p.readFile();
	}
	
	/*
	 * readFile method to read the usPopData2017.txt and
	 * essentially run the rest of the program
	 */
	public void readFile()
	{

		int counter = 0;
		// FileUtils fileUtils = new FileUtils();
		Scanner read = FileUtils.openToRead(DATA_FILE);
		read.useDelimiter("[\t\n]");
		String stateName = "";
		String cityName = "";
		String cityType = "";
		int population = 0;

		while(read.hasNext()){
			// String oneLine = read.next();

			// System.out.println(oneLine);
			City city = new City();
			city.setStateName(read.next());
			city.setCityName(read.next());
			city.setCityType(read.next());
			city.setPopulation(Integer.parseInt(read.next()));
			cities.add(city);
		}

		Prompt p = new Prompt();
		
		int choice = 0;

		System.out.println("31765 cities in database");

		while(choice!=9)
		{
			choice = Prompt.getInt("\n\n"+
		"1. Fifty least populous cities in USA (Selection Sort)\n"+
		"2. Fifty most populous cities in USA (Merge Sort)\n"+
		"3. First fifty cities sorted by name (Insertion Sort)\n"+
		"4. Last fifty cities sorted by name descending (Merge Sort)\n"+
		"5. Fifty most populous cities in named state\n" +
		"6. All cities matching a name sorted by population\n"+
		"9. Quit\n" +
		"Enter selection");
		
		if(choice==1)
		{
			long startMillisec = System.currentTimeMillis();

			populationAscend(cities);
			String state1 = "State";
			String city1 = "City";
			String type = "Type";
			String pop = "Population";
			System.out.println("\nFifty least populous cities");
			System.out.printf("    %-22s %-22s %-12s %12s\n", state1, city1, type, pop);
			for(int i = 0; i<50; i++)
			{
				if(i<9) System.out.println(" " + (i+1)+ ": " +cities.get(i).toString());
				else System.out.println((i+1)+ ": " +cities.get(i).toString());
			}

			long endMillisec = System.currentTimeMillis();

			long timetaken = endMillisec - startMillisec;

			System.out.println("\nElapsed Time " + timetaken + " milliseconds");

		}
		else if(choice==2)
		{
			long startMillisec = System.currentTimeMillis();

			populationDescend(cities);
			String state1 = "State";
			String city1 = "City";
			String type = "Type";
			String pop = "Population";
			System.out.println("\nFifty most populous cities");
			System.out.printf("    %-22s %-22s %-12s %12s\n", state1, city1, type, pop);
			for(int i = 0; i<50; i++)
			{
				if(i<9) System.out.println(" " + (i+1)+ ": " +cities.get(i).toString());
				else System.out.println((i+1)+ ": " +cities.get(i).toString());
			}
			long endMillisec = System.currentTimeMillis();

			long timetaken = endMillisec - startMillisec;

			System.out.println("\nElapsed Time " + timetaken + " milliseconds");

		}
		else if(choice==3)
		{
			long startMillisec = System.currentTimeMillis();

			namesAscend(cities);
			String state1 = "State";
			String city1 = "City";
			String type = "Type";
			String pop = "Population";
			System.out.println("\nFirst fifty cities sorted by name");
			System.out.printf("    %-22s %-22s %-12s %12s\n", state1, city1, type, pop);
			for(int i = 0; i<50; i++)
			{
				if(i<9) System.out.println(" " + (i+1)+ ": " +cities.get(i).toString());
				else System.out.println((i+1)+ ": " +cities.get(i).toString());
			}
			long endMillisec = System.currentTimeMillis();

			long timetaken = endMillisec - startMillisec;

			System.out.println("\nElapsed Time " + timetaken + " milliseconds");
		}
		else if(choice==4)
		{
			long startMillisec = System.currentTimeMillis();

			namesDescend(cities);
			String state1 = "State";
			String city1 = "City";
			String type = "Type";
			String pop = "Population";
			System.out.println("\nFifty cities sorted by name descending");
			System.out.printf("    %-22s %-22s %-12s %12s\n", state1, city1, type, pop);
			for(int i = 0; i<50; i++)
			{
				if(i<9) System.out.println(" " + (i+1)+ ": " +cities.get(i).toString());
				else System.out.println((i+1)+ ": " +cities.get(i).toString());
			}

			long endMillisec = System.currentTimeMillis();

			long timetaken = endMillisec - startMillisec;

			System.out.println("\nElapsed Time " + timetaken + " milliseconds");
		}
		else if(choice==5)
		{
			int check = 0;
			String stateNameStr = "";

			System.out.println("");

			while(check==0)
			{
				stateNameStr = p.getString("Enter state name (ie. Alabama)");
				for(int i = 0; i<cities.size(); i++)
				{
					if(cities.get(i).getStateName().equalsIgnoreCase(stateNameStr))
					{
						check = 1;
						i = 100000;
					}
				}

				if(check==0)
				{
					System.out.println("ERROR: "+stateNameStr +" is not valid");
				}
			}
			
			
			sortStateCities(cities, stateNameStr);
		}
		else if(choice==6)
		{
			String sameCity = "";

			int check = 0;

			while(check==0)
			{
				sameCity = p.getString("Enter city name ");
				for(int i = 0; i<cities.size(); i++)
				{
					if(cities.get(i).getCityName().equalsIgnoreCase(sameCity))
					{
						check = 1;
						i = 100000;
					}
				}
				if(check==0)
				{
					System.out.println("ERROR: "+sameCity +" is not valid");
				}
			}


			sameName(cities, sameCity);
		}
		}

		System.out.println("\nThanks for using Population!");
		
		
	}

	/**
	 * populationAscend changes the population of the 
	 * array to be in ascending order (uses selection sort)
	 * 
	 * @param cities is the list of City that is passed to sort
	 * 
	 */
	public void populationAscend(List<City> cities)
	{
		int i, j, smallestIndex = 0;

 		for (i = 0; i < cities.size()-1; i++)
		{
			smallestIndex = i;
			for (j = i+1; j < cities.size(); j++)
			{
				if (cities.get(j).getPopulation() < cities.get(smallestIndex).getPopulation())
					smallestIndex = j;
		
		    } 
			if(smallestIndex!=i)
			{
				City temp = cities.get(smallestIndex);
				cities.set(smallestIndex, cities.get(i));
				cities.set(i, temp);
			}
		}
	}

	/**
	 * populationDescend changes the population of the 
	 * array to be in descending order (uses merge sort)
	 * 
	 * @param a is the list of City that is passed to sort
	 * 
	 */
	public void populationDescend(List<City> a){
        int n = a.size();
        temp = new ArrayList<City>();
		for(int i = 0; i<n; i++)
		{
			temp.add(new City());
		}
        recursiveSort(a, 0, n-1);
    }

	/**
	 * recursiveSort recursively breaks up the ArrayList in order to
	 * complete the divide and conquer portion of MergeSort
	 * 
	 * @param a is the list of City that is passed to sort
	 * @param from is the lowest index of the list
	 * @param to is the highest index of the list
	 * 
	 */
    private void recursiveSort(List<City> a, int from, int to){

        if((to - from) < 2){

            if(to > from && a.get(to).getPopulation() > a.get(from).getPopulation()){
                City aTemp = a.get(to);
				a.set(to, a.get(from));
                a.set(from, aTemp);
            }
        }
        else{
            int middle = (from+to)/2;
            recursiveSort(a, from, middle);
            recursiveSort(a, middle+1, to);
            merge(a, from, middle, to);
        }

    }

/**
	 * merge remerges the ArrayList back together
	 * 
	 * @param a is the list of City that is passed to sort
	 * @param from is the lowest index of the list
	 * @param middle is the middle index of the list
	 * @param to is the highest index of the list
	 * 
	 */
    private void merge(List<City> a, int from, int middle, int to){
        int i=from, j = middle+1, k=from;

        while(i <= middle && j <= to){
            if(a.get(i).getPopulation() > a.get(j).getPopulation()){
				temp.set(k, a.get(i));
                i++;
            }
            else{
                temp.set(k, a.get(j));
                j++;
            }
            k++;
        }

            while(i <= middle){
                temp.set(k, a.get(i));
                i++;
                k++;

            }

            while(j <= to){
                temp.set(k, a.get(j));
                j++;
                k++;
            }

            for(k=from; k<=to;k++){
                a.set(k, temp.get(k));
            }
    }


	/**
	 * namesAscend changes the order of array
	 *  to be in ascending order based on city name 
	 * (uses insertion sort)
	 * 
	 * @param a is the list of City that is passed to sort
	 * 
	 */
	public void namesAscend(List<City> a){
        
        for(int n=1; n < a.size(); n++){

            City aTemp = a.get(n);

            int i = n;

            while(i > 0 && (aTemp.getCityName().compareTo(a.get(i-1).getCityName()) < 1)){
                a.set(i, a.get(i-1));
                i--;

            }
            a.set(i, aTemp);

        }
    }
	
/**
	 * namesDescend changes the population of the 
	 * array to be in descending order (uses merge sort)
	 * 
	 * @param a is the list of City that is passed to sort
	 * 
	 */
	public void namesDescend(List<City> a){
        int n = a.size();
        temp = new ArrayList<City>();
		for(int i = 0; i<n; i++)
		{
			temp.add(new City());
		}
        recursiveSortName(a, 0, n-1);
    }
	/**
	 * recursiveSortName recursively breaks up the ArrayList in order to
	 * complete the divide and conquer portion of MergeSort
	 * 
	 * @param a is the list of City that is passed to sort
	 * @param from is the lowest index of the list
	 * @param to is the highest index of the list
	 * 
	 */
    private void recursiveSortName(List<City> a, int from, int to){

        if((to - from) < 2){

            if(to > from && (a.get(to).getCityName().compareTo(a.get(from).getCityName()))>0){
                City aTemp = a.get(to);
				a.set(to, a.get(from));
                a.set(from, aTemp);
            }
        }
        else{
            int middle = (from+to)/2;
            recursiveSortName(a, from, middle);
            recursiveSortName(a, middle+1, to);
            mergeName(a, from, middle, to);
        }

    }
	/**
	 * mergeName remerges the ArrayList back together
	 * 
	 * @param a is the list of City that is passed to sort
	 * @param from is the lowest index of the list
	 * @param middle is the middle index of the list
	 * @param to is the highest index of the list
	 * 
	 */
    private void mergeName(List<City> a, int from, int middle, int to){
        int i=from, j = middle+1, k=from;

        while(i <= middle && j <= to){
            if((a.get(i).getCityName().compareTo(a.get(j).getCityName()))>0 ){
				temp.set(k, a.get(i));
                i++;
            }
            else{
                temp.set(k, a.get(j));
                j++;
            }
            k++;
        }

            while(i <= middle){
                temp.set(k, a.get(i));
                i++;
                k++;

            }

            while(j <= to){
                temp.set(k, a.get(j));
                j++;
                k++;
            }

            for(k=from; k<=to;k++){
                a.set(k, temp.get(k));
            }
    }

	/**
	 * sortStateCities collects only the parts of the array
	 * that include the given state name and creates a new array
	 * to store the values
	 * 
	 * @param cities is the list of City that is passed to sort
	 * @param state is the state that the cities must belong to
	 * 
	 */
	public void sortStateCities(List<City> cities, String state)
	{
		List<City> stateCities = new ArrayList<City>();

		for(int i = 0; i<cities.size(); i++)
		{
			if(cities.get(i).getStateName().equalsIgnoreCase(state))
			{
				stateCities.add(cities.get(i));
			}
		}

		populationDescend(stateCities);
		String state1 = "State";
			String city1 = "City";
			String type = "Type";
			String pop = "Population";
			System.out.println("\nFifty most populous cities in " +state);
			System.out.printf("    %-22s %-22s %-12s %12s\n", state1, city1, type, pop);
			for(int i = 0; i<50; i++)
			{
				if(i<9) System.out.println(" " + (i+1)+ ": " +stateCities.get(i).toString());
				else System.out.println((i+1)+ ": " +stateCities.get(i).toString());
			}

	}

	/**
	 * sortStateCities collects only the parts of the array
	 * that include the given city name and creates a new array
	 * to store the values
	 * 
	 * @param cities is the list of City that is passed to sort
	 * @param cityName is the city name all must have
	 * 
	 */
	public void sameName(List<City> cities, String cityName)
	{
		List<City> sameCities = new ArrayList<City>();

		for(int i = 0; i<cities.size(); i++)
		{
			if(cities.get(i).getCityName().equalsIgnoreCase(cityName))
			{
				sameCities.add(cities.get(i));
			}
		}

		populationDescend(sameCities);

		String state1 = "State";
			String city1 = "City";
			String type = "Type";
			String pop = "Population";
			System.out.println("\nCity " +cityName +" by population");
			System.out.printf("    %-22s %-22s %-12s %12s\n", state1, city1, type, pop);
			for(int i = 0; i<sameCities.size(); i++)
			{
				if(i<9) System.out.println(" " + (i+1)+ ": " +sameCities.get(i).toString());
				else System.out.println((i+1)+ ": " +sameCities.get(i).toString());
			}


	}



	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
}