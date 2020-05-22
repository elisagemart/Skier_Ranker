import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//THIS CLASS GIVES TOP TEN RANKING, Etc.
public class Display {
	
	 public static void main(String[] args) throws IOException {
		 	
		 
		 	//Read in all data from the database
			BufferedReader reader2 = new BufferedReader(new FileReader("database.csv"));
			ArrayList<String[]> mensData = new ArrayList<String[]>();
			ArrayList<String[]> womensData = new ArrayList<String[]>();
			
			String line2 = "";
			//Switch arraylist when we find it's the girl's results
			 while((line2 = reader2.readLine()).equals("GIRLS RESULTS,-,-,-")==false) {
				 mensData.add(line2.split(","));
			 }
			
			while((line2 = reader2.readLine())!=null) {
				womensData.add(line2.split(","));
			}
			
			BufferedReader reader = new BufferedReader(new FileReader("Nordic_Girls_Run_Order.csv"));
			ArrayList<String[]> girlsQuals = new ArrayList<String[]>();
			
			line2="";
			while((line2 = reader.readLine())!=null) {
				String[] currentLine = line2.split(",");
				String[] currentPlayer = new String[2];
				currentPlayer[1] = currentLine[1].split(" ")[0];
				currentPlayer[0] = currentLine[1].split(" ")[1];
				girlsQuals.add(currentPlayer);
				//System.out.println(currentPlayer[1]);
			}
			
			BufferedReader reader3 = new BufferedReader(new FileReader("Nordic_Boys_Run_Order.csv"));
			ArrayList<String[]> boysQuals = new ArrayList<String[]>();
			
			line2="";
			while((line2 = reader3.readLine())!=null) {
				String[] currentLine = line2.split(",");
				String[] currentPlayer = new String[2];
				currentPlayer[1] = currentLine[1].split(" ")[0];
				currentPlayer[0] = currentLine[1].split(" ")[1];
				boysQuals.add(currentPlayer);
			}
			
			
			//Sort the men's and women's lists
			ArrayList<String[]> sortedMen = sortSkiers(mensData);
			ArrayList<String[]> sortedWomen = sortSkiers(womensData);
		
			//define conferences
			String[] TCNSC = {"Highland","Central","Westonka","Orono","Como","Holy Family","Visitation"};
			String[] IMAC = {"Blake","Minnehaha","Providence","Breck", "Mounds Park", "St. Paul Academy and Summit School"};
			String[] NWSC = {"Andover","Armstrong","Elk River","Irondale","Blaine","Maple Grove","Champlin","Coon Rapids","Park Center","Osseo"};
			String[] LAKE = {"Wayzata","Edina","Hopkins","Minnetonka","Eden Prairie"};
			String[] METROWEST = {"Chaska","Bloomington","Benilde","Louis Park","Richfield","Cooper"};
			String[] METROEAST = {"Sibley","Mahtomedi","Tartan","Hastings","Simley","*North High School","St. Thomas"};
			String[] SSC = {"Prior Lake","Lakeville","Northfield","Burnsville","Winona","ISD 196","Eagan"};
			String[] SEC = {"Stillwater","Forest Lake","White Bear","East Ridge","Mounds View","PWER","Roseville"};
			
			/*
			System.out.println("****************STATE****************");
			printResults(sortedMen,sortedWomen);
			System.out.println("****************TCNSC****************");
			printSubsetResults(TCNSC, sortedMen, sortedWomen);
			System.out.println("****************IMAC*****************");
			printSubsetResults(IMAC, sortedMen, sortedWomen);
			System.out.println("****************NWSC*****************");
			printSubsetResults(NWSC, sortedMen, sortedWomen);
			System.out.println("****************LAKE*****************");
			printSubsetResults(LAKE, sortedMen, sortedWomen);
			System.out.println("****************METRO WEST*****************");
			printSubsetResults(METROWEST, sortedMen, sortedWomen);
			System.out.println("****************METRO EAST*****************");
			printSubsetResults(METROEAST, sortedMen, sortedWomen);
			System.out.println("****************SSC*****************");
			printSubsetResults(SSC, sortedMen, sortedWomen);
			System.out.println("****************SEC*****************");
			printSubsetResults(SEC, sortedMen, sortedWomen);
			*/
			
			sortedWomen = subsetSkiers(sortedWomen,girlsQuals);
			sortedMen = subsetSkiers(sortedMen,boysQuals);
			
			System.out.println("MEN'S INDIVIDUAL");
			System.out.println(getTopSkiers(160,sortedMen));
			
			System.out.println("WOMEN'S INDIVIDUAL");
			System.out.println(getTopSkiers(160,sortedWomen));
			
			String[] womenTeams = {"Duluth East","Ely","Stillwater","Forest Lake","Southwest","Edina","Andover","Mora","Winona","Eastview","Highland","*South","Bemidji","Alexandria","Armstrong","Wayzata"};
			String[] mensTeams = {"Wayzata","Armstrong","Cathedral","Little Falls","Highland","Minnehaha","Prior Lake","Burnsville","Mora","Blaine","Southwest","Eden Prairie","Forest Lake","Stillwater","Grand Rapids","Ely"};
			
			sortedWomen = subsetList(sortedWomen,womenTeams);
			sortedMen = subsetList(sortedMen,mensTeams);
			
			System.out.println("MEN'S TEAM");
			System.out.println(getTopTeams(16,4,sortedMen));
			
			System.out.println("WOMEN'S TEAM");
			System.out.println(getTopTeams(16,4,sortedWomen));
	 }
	 
	 //PRINT RESULTS FOR A CONFERENCE/SECTION
	 public static void printSubsetResults(String[] subset, ArrayList<String[]> men, ArrayList<String[]> women) {
		 	ArrayList<String[]> menList = subsetList(men, subset);
		 	ArrayList<String[]> womenList = subsetList(women, subset);
			
		 	System.out.println("MEN'S INDIVIDUAL");
			System.out.println(getTopSkiers(10,menList));
			System.out.println("WOMEN'S INDIVIDUAL");
			System.out.println(getTopSkiers(10,womenList));
			System.out.println("MEN'S TEAM");
			System.out.println(getTopTeams(5,5,menList));
			System.out.println("WOMEN'S TEAM");
			System.out.println(getTopTeams(5,5,womenList));
	 }
	 
	 //ALTERNATE FORM OF printResults for whole state
	 public static void printResults(ArrayList<String[]> menList, ArrayList<String[]> womenList) {
			
		 	System.out.println("MEN'S INDIVIDUAL");
			System.out.println(getTopSkiers(20,menList));
			System.out.println("WOMEN'S INDIVIDUAL");
			System.out.println(getTopSkiers(20,womenList));
			System.out.println("MEN'S TEAM");
			System.out.println(getTopTeams(15,4,menList));
			System.out.println("WOMEN'S TEAM");
			System.out.println(getTopTeams(15,4,womenList));
	 }
	 
	 
	 
	 //SORT A LIST OF SKIERS
	 public static ArrayList<String[]> sortSkiers(ArrayList<String[]> genderList) {
		 	
		 
		 	//Sort all the skiers for analysis
			ArrayList<String[]> sortedSkiers = new ArrayList<String[]>();
			sortedSkiers.add(genderList.get(1));
				for(int p = 2; p < genderList.size(); p++) {
					int index = 0;
					while(index < sortedSkiers.size() && Integer.parseInt(sortedSkiers.get(index)[3]) > Integer.parseInt(genderList.get(p)[3])) {
						index++;
					}
					sortedSkiers.add(index, genderList.get(p));
				}
			return sortedSkiers;
	 }
	 
	 //GET A LIST OF TOP SKIERS FROM A LIST
	 public static String getTopSkiers(int n, ArrayList<String[]> skierList) {
		 String topSkiers = "";
		 if(n > skierList.size()) {
			 n = skierList.size();
		 }
		 for(int i = 0; i < n; i++) {
			 topSkiers+=(i+1)+". "+skierList.get(i)[1]+" "+skierList.get(i)[0]+": "+skierList.get(i)[3]+", "+skierList.get(i)[2]+System.lineSeparator();
		 }
		 return topSkiers;
	 }
	 
	 //GET TOP TEAMS
	 public static String getTopTeams(int n, int scorers, ArrayList<String[]> skierList) {
		 
		 //teams represented as {name,score,scorers}
		 ArrayList<String[]> teamScores = new ArrayList<String[]>();
		 int score = skierList.size();
		 
		 //get each team's score, store in an array
		 for(int i = 0; i < skierList.size(); i++) {
			 String[] skier = skierList.get(i);
			 boolean found = false;
			 for(int k = 0; k < teamScores.size(); k++) {
				 if(teamScores.get(k)[0].equals(skier[2])) {
					 found = true;
					 if(Integer.parseInt(teamScores.get(k)[2]) < scorers) {
						 teamScores.get(k)[1] = Integer.toString(Integer.parseInt(teamScores.get(k)[1]) + score);
						 teamScores.get(k)[2] = Integer.toString(Integer.parseInt(teamScores.get(k)[2])+1);
					 }
				 }
			 }
			 if(found == false) {
				 String[] newScore = {skier[2],Integer.toString(score),"1"};
				 teamScores.add(newScore);
			 }
			 score--;
		 }
		 
			//for(int k = 0; k < teamScores.size(); k++) {
				//System.out.println(teamScores.get(k)[0]);
			//}
		 
		 //sort the array of team scores
		 for(int k = 0; k < teamScores.size(); k++) {
			for(int i = 0; i < teamScores.size(); i++) {
				String[] currentTeam = teamScores.get(i);
				teamScores.remove(i);
				int index = 0;
				while(index < teamScores.size() && Integer.parseInt(teamScores.get(index)[1]) > Integer.parseInt(currentTeam[1])) {
					index++;
				}
				teamScores.add(index, currentTeam);
			}
		 }
			
		 if(n > teamScores.size()) {
			 n = teamScores.size();
		 }
		 
		//get the top n teams
		String topTeams = "";
		for(int i = 0; i < n; i++) {
			topTeams+=(i+1)+". "+teamScores.get(i)[0]+": "+teamScores.get(i)[1]+System.lineSeparator();
		}
		
		return topTeams;
			
	 }
	 
	 //GET LIST OF ONLY A SUBSET OF TEAMS
	 public static ArrayList<String[]> subsetList(ArrayList<String[]> skierList, String[] teamNames){
		 ArrayList<String[]> newList = (ArrayList<String[]>) skierList.clone();
		 int index = 0;
		 while(index < newList.size()) {
			 String[] currentSkier = newList.get(index);
			 boolean found = false;
			 for(int k = 0; k < teamNames.length; k++) {
				 //"*" character indicates exact match needed
				 if(teamNames[k].charAt(0)=='*') {
					 if(currentSkier[2].equals(teamNames[k].substring(1))){
						 found = true;
					 }
				 }
				 else {
					 if(currentSkier[2].contains(teamNames[k])) {
					 found = true;
					 }
				 }
			 }
			 if(!found) {
				 newList.remove(index);
			 }
			 else {
				 index++;
			 }
		 }
		 return newList;
	 }
	 
	 
	 //Get a list of skiers from a list of skier names
	 public static ArrayList<String[]> subsetSkiers(ArrayList<String[]> skierList, ArrayList<String[]> skierNames){
		 ArrayList<String[]> newList = (ArrayList<String[]>) skierList.clone();
		 int index = 0;
		 while(index < newList.size()) {
			 String[] currentSkier = newList.get(index);
			 boolean found = false;
			 for(int k = 0; k < skierNames.size(); k++) {
				 String[] currentName = skierNames.get(k);
		
				 	if(currentSkier[0].length()>=3 && currentName[0].length()>=3 && currentSkier[1].length()>=3 && currentName[1].length()>=3) {
					 if(currentSkier[0].substring(0,3).equalsIgnoreCase(currentName[0].substring(0, 3)) && currentSkier[1].substring(0,3).equalsIgnoreCase(currentName[1].substring(0, 3))) {
					 found = true;
					 }
				 	}
				 	else {
				 		if(currentSkier[0].equalsIgnoreCase(currentName[0]) && currentSkier[1].equalsIgnoreCase(currentName[1])) {
							 found = true;
							 }
				 	}
				 
			 }
			 if(!found) {
				 newList.remove(index);
			 }
			 else {
				 index++;
			 }
		 }
		 return newList;
	 }
	
}
