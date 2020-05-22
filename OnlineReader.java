import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class OnlineReader {
	
	
	public static void main(String[] args) throws IOException {
	
	FileWriter fw = new FileWriter("database.csv");
	fw.write("Last Name,First Name,Team,Score");
	fw.write(System.lineSeparator());
	
	//Read webpage for every school in MNHSL--BOYS
	for(int schoolNum = 1; schoolNum < 1000; schoolNum++) {
	
	try {
	Document doc = Jsoup.connect("http://mshsl.org/mshsl/showroster.asp?actnum=411&school="+schoolNum).get();
	
	ArrayList<String> Players = new ArrayList<String>();
	
	Elements rows = doc.getElementsByAttribute("href");
	
	Elements title = doc.getElementsByClass("tmedlrg");
	String[] teamNames = title.html().split("&");
	//get Team Name
	String teamName = teamNames[0].substring(3, teamNames[0].length());
	teamName = teamName.replace(',', '.');
	System.out.println(teamName);
	
	String playerName = "Number";
	//cycle through everything with a link, get all the player's names
	for(int i = 0; i < rows.size(); i++) {
		if(rows.get(i).hasText()) {
			playerName = rows.get(i).html();
			if(!(playerName.length() > 30 || playerName.equals("Number") || playerName.equals("Player Name") || playerName.equals("Grade") || playerName.equals("Printable Roster"))) {
				Players.add(playerName);
			}
		}
	
	}

	//write team data to file
	for(int i = 0; i < Players.size(); i++) {
		if(Players.get(i).contains(",")) {
		fw.write(Players.get(i)+","+teamName+",1000");
		}
		else {
			String[] fullName = Players.get(i).split(" ");
			fw.write(fullName[1]+","+fullName[0]+","+teamName+",1000");
		}
		fw.write(System.lineSeparator());
	}
	}
	catch(HttpStatusException e) {
		System.out.println("NO TEAM");
	}
	
	}
	
	//read in extra boys roster
	BufferedReader reader = new BufferedReader(new FileReader("boys_extra_roster.csv"));
	String line = "";
	 while((line = reader.readLine())!=null) {
		 fw.write(line);
		 fw.write(System.lineSeparator());
	 }
	 reader.close();
	
	fw.write("GIRLS RESULTS,-,-,-");
	fw.write(System.lineSeparator());
	
	//Read webpage for every school in MNHSL--GIRLS
	for(int schoolNum = 1; schoolNum < 1000; schoolNum++) {
	
	try {
	Document doc = Jsoup.connect("http://mshsl.org/mshsl/showroster.asp?actnum=427&school="+schoolNum).get();
	
	ArrayList<String> Players = new ArrayList<String>();
	
	Elements rows = doc.getElementsByAttribute("href");
	
	Elements title = doc.getElementsByClass("tmedlrg");
	String[] teamNames = title.html().split("&");
	//get Team Name
	String teamName = teamNames[0].substring(3, teamNames[0].length());
	teamName = teamName.replace(',', '.');
	System.out.println(teamName);
	
	String playerName = "Number";
	//cycle through everything with a link, get all the player's names
	for(int i = 0; i < rows.size(); i++) {
		if(rows.get(i).hasText()) {
			playerName = rows.get(i).html();
			if(!(playerName.length() > 30 || playerName.equals("Number") || playerName.equals("Player Name") || playerName.equals("Grade") || playerName.equals("Printable Roster"))) {
				Players.add(playerName);
			}
		}
	
	}

	//write team data to file
	for(int i = 0; i < Players.size(); i++) {
		if(Players.get(i).contains(",")) {
		fw.write(Players.get(i)+","+teamName+",1000");
		}
		else {
			String[] fullName = Players.get(i).split(" ");
			fw.write(fullName[1]+","+fullName[0]+","+teamName+",1000");
		}
		fw.write(System.lineSeparator());
	}
	}
	catch(HttpStatusException e) {
		System.out.println("NO TEAM");
	}
	
	}
	
	//read in extra girls roster
		BufferedReader reader2 = new BufferedReader(new FileReader("girls_extra_roster.csv"));
		String line2 = "";
		 while((line2 = reader2.readLine())!=null) {
			 fw.write(line2);
			 fw.write(System.lineSeparator());
		 }
		 reader2.close();
	
	
	fw.close();

	}
	

}
