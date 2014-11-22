package containers;

public class DemographicInfo {
	
	private String regionName = null;
	private int population = 0;
	private int dryDaysPerYear = 0;
	private int gdp = 0;
	private int averagePopSalary = 0;
	
	public DemographicInfo(String regionName){
		this.regionName = regionName;
	}
	
	 public void setRegionName(String regionName){
         this.regionName = regionName;
     }
     public String getRegionName(){
         return regionName;
     }

     public void setPopulation(int population){
         this.population = population;
     }
     public int getPopulation(){
         return population;
     }

     public void setDryDaysPerYear(int dryDaysPerYear){
         this.dryDaysPerYear = dryDaysPerYear;
     }
     public int getDryDaysPerYear(){
         return dryDaysPerYear;
     }

     public void setGdp(int gdp){
         this.gdp = gdp;
     }
     public int getGdp(){
         return gdp;
     }

     public void setAveragePopSalary(int averagePopSalary){
         this.averagePopSalary = averagePopSalary;
     }
     public int getAveragePopSalary(){
         return averagePopSalary;
     }
}
