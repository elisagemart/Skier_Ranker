import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//THIS CLASS HANDLES INPUT OF CSV FILES--STRIPS THEM DOWN INTO APPROPRIATE RESULT LISTS

public class Input {
	
	//Check if a String is a number
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	public static BufferedReader br;
	
	 public static void main(String[] args) throws IOException {
/*
		 Scanner reader = new Scanner(System.in);
		 
		 System.out.println("Enter CSV file names, seperate with comma:");
		 String csvFiles = reader.nextLine();
		 
		 String[] fileNames = csvFiles.split(",");
		 for(int i = 0; i < fileNames.length; i++) {
			 updateDatabase(fileNames[i]);
		 }
		 */
		 File folder = new File("results/small_meets");
		 File[] listOfFiles = folder.listFiles();
		 for(int i=0; i<listOfFiles.length; i++) {
			 updateDatabase("results/small_meets/"+listOfFiles[i].getName(),12,12);
		 }
		 
		 folder = new File("results/important_meets");
		 listOfFiles = folder.listFiles();
		 for(int i=0; i<listOfFiles.length; i++) {
			 updateDatabase("results/important_meets/"+listOfFiles[i].getName(),36,36);
		 }
		 
		 folder = new File("results/conference_champs");
		 listOfFiles = folder.listFiles();
		 for(int i=0; i<listOfFiles.length; i++) {
			 updateDatabase("results/conference_champs/"+listOfFiles[i].getName(),36,36);
		 }
		 
		 folder = new File("results/national_meets");
		 listOfFiles = folder.listFiles();
		 for(int i=0; i<listOfFiles.length; i++) {
			 updateDatabase("results/national_meets/"+listOfFiles[i].getName(),36,22);
		 }
		 
		 folder = new File("results/section_meets");
		 listOfFiles = folder.listFiles();
		 for(int i=0; i<listOfFiles.length; i++) {
			 updateDatabase("results/section_meets/"+listOfFiles[i].getName(),36,36);
		 }
		 
		 clean();
		
		
	 }
	 
	 public static void clean() throws IOException {
		 	BufferedReader reader2 = new BufferedReader(new FileReader("database.csv"));
			ArrayList<String[]> allData = new ArrayList<String[]>();
			
			String line2 = "";
			 while((line2 = reader2.readLine())!=null) {
				 String[] currentLine = line2.split(",");
				 if(currentLine[1].charAt(0)==' ') {
					 currentLine[1] = currentLine[1].substring(1);
				 }
				 allData.add(currentLine);
			 }
			 
			 reader2.close();
			 
			 //remove non-ranked skiers
			 int i = 0;
			 while(i < allData.size()) {
				 if(allData.get(i)[3].equals("1000")) {
					 System.out.println("Deleted"+allData.get(i)[1]+" "+allData.get(i)[0]);
					 allData.remove(i);
					 
				 }
				 else {
					 i++;
				 }
			 }
			 
			 
			//write back to database
			 FileWriter fw = new FileWriter("database.csv");
				//fw.write("Last Name,First Name,Team,Score");
				//fw.write(System.lineSeparator());
			for(int k = 0; k < allData.size(); k++) {
				fw.write(allData.get(k)[0]+","+allData.get(k)[1]+","+allData.get(k)[2]+","+allData.get(k)[3]);
				fw.write(System.lineSeparator());
			}
			fw.close();
	 }
	 
	 public static void updateDatabase(String csvFile, int kValue, int loserKValue) throws IOException {
		 br = new BufferedReader(new FileReader(csvFile));
		 
		 ArrayList<String[]> results = new ArrayList<String[]>();
		 
		 String line = "";
		 while((line = br.readLine())!=null) {
			 results.add(line.split(","));
		 }
		 
		 int headingRow = 0; 
		 int nameCol=0; //We need to find which column has the names
		 while(!(results.get(headingRow)[nameCol].toLowerCase().contains("name") || (results.get(headingRow)[nameCol].toLowerCase().contains("skier") && !results.get(headingRow)[nameCol].toLowerCase().contains("top")))) { //"Top five skiers score" is causing problems
			 if(nameCol < results.get(headingRow).length-1) {
				 nameCol++;
			 }
			 else {
				 nameCol=0;
				 headingRow++;
			 }
		 }
		 
		 int placeCol=0; //we need to find which column has the places
		 while(results.get(headingRow)[placeCol].toLowerCase().contains("place")==false && results.get(headingRow)[placeCol].toLowerCase().contains("rank")==false && results.get(headingRow)[placeCol].toLowerCase().contains("overall")==false && results.get(headingRow)[placeCol].toLowerCase().contains("pl")==false && results.get(headingRow)[placeCol].toLowerCase().contains("pos")==false) {
			 if(placeCol < results.get(headingRow).length -1) {
				 placeCol++;
			 }
		 }
		 
		 if(placeCol > nameCol && results.get(headingRow+1)[nameCol].contains("\"")) {
			 placeCol++;
		 }
		 //System.out.println(placeCol + " "+ nameCol);
		
		 boolean bibName = false;
		 if(results.get(headingRow)[nameCol].toUpperCase().equals("BIB-NAME")) {
			bibName = true; 
			System.out.println("BIB NAME");
		 }
		 
			 //remove all non-names and JV results
			int index = 0;
			boolean JV = false;
			int JVcounter = 0;
			 while(index < results.size()) {
				 if(results.get(index)!=null) {
					 //remove ) from number written 1)
					 if(results.get(index).length >= placeCol+1 && 
							 results.get(index)[placeCol].length()>0 && 
							 results.get(index)[placeCol].contains(")")){
						 results.get(index)[placeCol] = 
								 results.get(index)[placeCol].substring(0,results.get(index)[placeCol].length()-1);
					 }
					 //Determine if the following results are JV
					 for(int i = 0; i < results.get(index).length; i++) {
						 if(i < 5 && results.get(index)[i]!=null && 
								 (results.get(index)[i].toLowerCase().contains("jv") || 
										 results.get(index)[i].toLowerCase().contains("junior varsity") || 
										 results.get(index)[i].toLowerCase().contains("j.v.") || 
										 results.get(index)[i].toLowerCase().contains("jr"))){
							 JV = true;
							 JVcounter = 0;
							 System.out.println("JV");
						 }
					 }
					 //Determine if a new results list has started--7 is used b/c I figure there will probably be at least 7 results in a JV race
					 if(results.get(index).length >= placeCol+1 && 
							 results.get(index)[placeCol]!=null && 
							 results.get(index)[placeCol].equals("1") && JVcounter > 7) {
						 JV = false;
					 }
				 }
				 if(JV) {
					 results.remove(index);
					 JVcounter++;
				 }
				 else if(results.get(index)==null || 
						 results.get(index).length <= placeCol || 
						 results.get(index).length <= nameCol || 
						 isNumeric(results.get(index)[placeCol])==false || 
						 (results.get(index)[nameCol].contains(" ")==false && 
						 results.get(index)[nameCol].contains("\"")==false)) {
					 results.remove(index);
				 }
				 else {
					 index++;
				 }
			 }
			 

		//We need to split our results in men's and women's. All are stored in one ArrayList
		ArrayList<ArrayList<String[]>> allResults = new ArrayList<ArrayList<String[]>>();
		
		allResults.add(new ArrayList<String[]>());
		
		int currentArray = 0;
		for(int k = 0; k < results.size(); k++) {
			//split up by first/last name-- newName has three parts: LAST, FIRST, SCORE
			String[] newName = new String[3];
			
			//Shorten names written "789-Luc Golin" to "Luc Golin"
			if(bibName&&results.get(k)[nameCol].contains("-")) {
				String[] nName = results.get(k)[nameCol].split("-",2);
				results.get(k)[nameCol] = nName[1];
			}
			
			//this option for names written "Sage-Martinson,Eli" or "Eli Sage-Martinson, 12"
			if(results.get(k)[nameCol].contains("\"")) {
				
				//this option for names written "Eli Sage-Martinson, 12"
				if(results.get(k)[nameCol+1].contains("7") || 
						results.get(k)[nameCol+1].contains("8") || 
						results.get(k)[nameCol+1].contains("9") || 
						results.get(k)[nameCol+1].contains("10") || 
						results.get(k)[nameCol+1].contains("11") || 
						results.get(k)[nameCol+1].contains("12")) {
					String[] splitName = results.get(k)[nameCol].substring(1,results.get(k)[nameCol].length()).split(" ");
					newName[0] = splitName[1];
					newName[1] = splitName[0];
				}
				
				//this option for names written "Sage-Martinson, Eli"
				else {
					newName[0] = results.get(k)[nameCol].substring(1, results.get(k)[nameCol].length());
					if(results.get(k)[nameCol+1].length()-1 > 0) {
						newName[1] = results.get(k)[nameCol+1].substring(1, results.get(k)[nameCol+1].length()-1);
					}
				}
			}
			//this option for names written "Eli Sage-Martinson"
			else {
				String[] splitName = results.get(k)[nameCol].split(" ");
				newName[0] = splitName[1];
				newName[1] = splitName[0];
			}
			
			//if this person is in first place (new results!) add a new list
			if(results.get(k)[placeCol].equals("1")==true && k!=0) {
				allResults.add(new ArrayList<String[]>());
				currentArray++;
				allResults.get(currentArray).add(newName);
			}
			//if this person is not in first place, add them to current list
			else {
				allResults.get(currentArray).add(newName);
			}
		}
		
		
		//Read in all data from the database
		BufferedReader reader2 = new BufferedReader(new FileReader("database.csv"));
		ArrayList<String[]> allData = new ArrayList<String[]>();
		
		String line2 = "";
		 while((line2 = reader2.readLine())!=null) {
			 String[] currentLine = line2.split(",");
			 if(currentLine[1].charAt(0)==' ') {
				 currentLine[1] = currentLine[1].substring(1);
			 }
			 allData.add(currentLine);
		 }
		 
		 reader2.close();
		 
		 //cycle through results and get each competitor's score for analysis
		 for(int k = 0; k < allResults.size(); k++) {
			 for(int p = 0; p < allResults.get(k).size(); p++) {
				 String first = allResults.get(k).get(p)[1];
				 String last = allResults.get(k).get(p)[0];
				 
				 for(int c = 0; c < allData.size(); c++) {
					 
					 //Use first three letters of the first name to eliminate nickname problems
					 String nameFirst,dataFirst;
					 if(first != null && first.length() >2) {
						 nameFirst = first.substring(0, 3);
					 }
					 else {
						 nameFirst = first;
					 }
					 if(allData.get(c)[1] != null && allData.get(c)[1].length() >2) {
						 dataFirst = allData.get(c)[1].substring(0, 3);
					 }
					 else {
						 dataFirst = allData.get(c)[1];
					 }
					 
					 if(allData.get(c)[0].equalsIgnoreCase(last) && dataFirst.equalsIgnoreCase(nameFirst)) {
						 int currentScore = Integer.parseInt(allData.get(c)[3]);
						 //save people's scores from the database into the allResults Array
						 allResults.get(k).get(p)[2] = Integer.toString(currentScore);
					 }
				 }
				 
			 }
		 }
		 
		 
		 //Cycle through results and update each ranking
		 for(int k = 0; k < allResults.size(); k++) {
			 for(int p = 0; p < allResults.get(k).size(); p++) {
				 String[] currentPlayer = allResults.get(k).get(p);
				 if(currentPlayer[2]!=null) {
				 for(int z = p+1; z < allResults.get(k).size(); z++) {
					 String[] defeatedPlayer = allResults.get(k).get(z);
					 if(defeatedPlayer[2]!=null) {
					 int[] newRankings = getNewRanking(Integer.parseInt(currentPlayer[2]), Integer.parseInt(defeatedPlayer[2]),kValue,loserKValue);
					 currentPlayer[2] = Integer.toString(newRankings[0]);
					 defeatedPlayer[2] = Integer.toString(newRankings[1]);
					 }
				 }
			 }
		 }
		 }
		 
		 //cycle through results and save scores for each competitor
		 for(int k = 0; k < allResults.size(); k++) {
			 for(int p = 0; p < allResults.get(k).size(); p++) {
				 String first = allResults.get(k).get(p)[1];
				 String last = allResults.get(k).get(p)[0];
				 String score = allResults.get(k).get(p)[2];
				 
				 if(first != null && first.length() >2) {
					 first = first.substring(0, 3);
				 }
				
				 
				 for(int c = 0; c < allData.size(); c++) {
					 String dataFirst = allData.get(c)[1];
					 
					 if(dataFirst != null && dataFirst.length() >2) {
						 dataFirst = dataFirst.substring(0, 3);
					 }
					 
					 if(allData.get(c)[0].equalsIgnoreCase(last) && dataFirst.equalsIgnoreCase(first)) {
						 allData.get(c)[3] = score;
					 }
				 }
				 
			 }
		 }
		 

		 //write back to database
		 FileWriter fw = new FileWriter("database.csv");
			//fw.write("Last Name,First Name,Team,Score");
			//fw.write(System.lineSeparator());
		for(int k = 0; k < allData.size(); k++) {
			fw.write(allData.get(k)[0]+","+allData.get(k)[1]+","+allData.get(k)[2]+","+allData.get(k)[3]);
			fw.write(System.lineSeparator());
		}
		fw.close();
		
		
		//print out results to check
		 for(int k = 0; k < allResults.size(); k++) {
			 System.out.println("Results "+(k+1)+"**************************************");
			 for(int p = 0; p < allResults.get(k).size(); p++) {
				 System.out.println(allResults.get(k).get(p)[1] + " "+allResults.get(k).get(p)[0]+": "+allResults.get(k).get(p)[2]);
			 }
		 } 
	 }
	 
	 public static int[] getNewRanking(int winnerRank, int loserRank, int kValue, int loserKValue) {
		 double r1 = Math.pow(10, ((double)winnerRank/400));
		 double r2 = Math.pow(10, ((double)loserRank/400));
		 double e1 = r1 / (r1+r2);
		 double e2 = r2 / (r1+r2);
		 double newR1 = winnerRank + kValue * (1-e1);
		 double newR2 = loserRank + loserKValue * (0-e2);
		 int[] rankings =  {(int)newR1, (int)newR2};
		 return rankings;
	 }
}
