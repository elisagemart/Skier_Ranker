import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JTextPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JCheckBox;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Ranker2 {

	private JFrame frame;
	private final JCheckBox chckbxWomen = new JCheckBox("WOMEN");
	private final JCheckBox chckbxMen = new JCheckBox("MEN");
	private final JCheckBox chckbxTeam = new JCheckBox("TEAM");
	private final JCheckBox chckbxIndividual = new JCheckBox("INDIVIDUAL");
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final JPanel panel_2 = new JPanel();
	private final JComboBox comboBox = new JComboBox();
	private final JPanel panel_3 = new JPanel();
	private final JLabel lblCustomMeetTeams = new JLabel("Custom Meet Teams:");
	private final JTextPane customTeamInputs = new JTextPane();
	private final JPanel panel_4 = new JPanel();
	private final JPanel panel_5 = new JPanel();
	private final JPanel panel_6 = new JPanel();
	private final JPanel panel_7 = new JPanel();
	private final JLabel lblResults = new JLabel("Results:");
	private final JLabel lblScorers = new JLabel("Scorers: ");
	private final JPanel panel_8 = new JPanel();
	private final JPanel panel_9 = new JPanel();
	private final JButton btnNewButton = new JButton("Get Results");
	private final JLabel lblNewLabel = new JLabel("");
	private final JLabel lblNewLabel_1 = new JLabel("");
	private final JTextArea outputField = new JTextArea();
	private final JScrollPane scrollPane = new JScrollPane(outputField);
	private final JTextField nTextField = new JTextField();
	private final JTextField scorersTextField = new JTextField();
	
	String[] TCNSC = {"Highland","Central","Westonka","Orono","Como","Holy Family","Visitation"};
	String[] IMAC = {"Blake","Minnehaha","Providence","Breck", "Mounds Park", "St. Paul Academy and Summit School"};
	String[] NWSC = {"Andover","Armstrong","Elk River","Irondale","Blaine","Maple Grove","Champlin","Coon Rapids","Park Center","Osseo"};
	String[] LAKE = {"Wayzata","Edina","Hopkins","Minnetonka","Eden Prairie"};
	String[] METROWEST = {"Chaska","Bloomington","Benilde","Louis Park","Richfield","Cooper"};
	String[] METROEAST = {"Sibley","Mahtomedi","Tartan","Hastings","Simley","*North High School","St. Thomas"};
	String[] SSC = {"Prior Lake","Lakeville","Northfield","Burnsville","Winona","ISD 196","Eagan"};
	String[] SEC = {"Stillwater","Forest Lake","White Bear","East Ridge","Mounds View","PWER","Roseville"};


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ranker2 window = new Ranker2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ranker2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		outputField.setEditable(false);
		scorersTextField.setText("4");
		scorersTextField.setColumns(10);
		nTextField.setText("10");
		nTextField.setColumns(10);
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 493);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		scrollPane.setPreferredSize(new Dimension(330, 24));
		
		frame.getContentPane().add(scrollPane);
		
		
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(5, 4, 0, 0));
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(3, 2, 0, 0));
		
		panel_1.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		chckbxMen.setSelected(true);
		panel_4.add(chckbxMen);
		chckbxWomen.setSelected(true);
		panel_4.add(chckbxWomen);
		panel_1.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));
		chckbxTeam.setSelected(true);
		panel_5.add(chckbxTeam);
		chckbxIndividual.setSelected(true);
		panel_5.add(chckbxIndividual);
		
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(3, 1, 0, 0));
		
		panel_2.add(panel_6);
		
		panel_6.add(lblResults);
		
		panel_6.add(nTextField);
		
		panel_2.add(panel_7);
		
		panel_7.add(lblScorers);
		
		panel_7.add(scorersTextField);
		
		panel.add(panel_8);
		panel_8.setLayout(new GridLayout(3, 0, 0, 0));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().equals("Custom Virtual Meet")) {
					lblCustomMeetTeams.setEnabled(true);
					customTeamInputs.setEnabled(true);
				}
				else {
					lblCustomMeetTeams.setEnabled(false);
					customTeamInputs.setEnabled(false);
				}
			}
		});
		panel_8.add(comboBox);
		comboBox.setPreferredSize(new Dimension(28, 10));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"State Meet", "Section 1", "Section 2", "Section 3", "Section 4", "Section 5", "Section 6", "TCNSC", "SSC", "SEC", "IMAC", "NWSC", "Metro East Conference", "Metro West Conference", "Lake Conference", "Custom Virtual Meet"}));
		
		panel_8.add(lblNewLabel_1);
		lblCustomMeetTeams.setEnabled(false);
		lblCustomMeetTeams.setHorizontalAlignment(SwingConstants.CENTER);
		panel_8.add(lblCustomMeetTeams);
		
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));
		customTeamInputs.setEnabled(false);
		
		panel_3.add(customTeamInputs);
		panel.add(panel_9);
		panel_9.setLayout(new GridLayout(3, 0, 0, 0));
		
		panel_9.add(lblNewLabel);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//Read in data
				BufferedReader reader2 = null;
				try {
					reader2 = new BufferedReader(new FileReader("database.csv"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ArrayList<String[]> mensData = new ArrayList<String[]>();
				ArrayList<String[]> womensData = new ArrayList<String[]>();
				
				String line2 = "";
				//Switch arraylist when we find it's the girl's results
				 try {
					while((line2 = reader2.readLine()).equals("GIRLS RESULTS,-,-,-")==false) {
						 mensData.add(line2.split(","));
					 }
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					while((line2 = reader2.readLine())!=null) {
						womensData.add(line2.split(","));
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//Sort the men's and women's lists
				ArrayList<String[]> sortedMen = sortSkiers(mensData);
				ArrayList<String[]> sortedWomen = sortSkiers(womensData);
				
				if(!comboBox.getSelectedItem().equals("State Meet")) {
					if(comboBox.getSelectedItem().equals("TCNSC")) {
						sortedMen = subsetList(sortedMen, TCNSC);
						sortedWomen = subsetList(sortedWomen, TCNSC);
					}
					if(comboBox.getSelectedItem().equals("NWSC")) {
						sortedMen = subsetList(sortedMen,NWSC);
						sortedWomen = subsetList(sortedWomen,NWSC);
					}
					if(comboBox.getSelectedItem().equals("IMAC")) {
						sortedMen = subsetList(sortedMen,IMAC);
						sortedWomen = subsetList(sortedWomen,IMAC);
					}
					if(comboBox.getSelectedItem().equals("SSC")) {
						sortedMen = subsetList(sortedMen,SSC);
						sortedWomen = subsetList(sortedWomen,SSC);
					}
					if(comboBox.getSelectedItem().equals("SEC")) {
						sortedMen = subsetList(sortedMen,SEC);
						sortedWomen = subsetList(sortedWomen,SEC);
					}
					if(comboBox.getSelectedItem().equals("Lake Conference")) {
						sortedMen = subsetList(sortedMen,LAKE);
						sortedWomen = subsetList(sortedWomen,LAKE);
					}
					if(comboBox.getSelectedItem().equals("Metro West Conference")) {
						sortedMen = subsetList(sortedMen,METROWEST);
						sortedWomen = subsetList(sortedWomen,METROWEST);
					}
					if(comboBox.getSelectedItem().equals("Metro East Conference")) {
						sortedMen = subsetList(sortedMen,METROEAST);
						sortedWomen = subsetList(sortedWomen,METROEAST);
					}
					if(comboBox.getSelectedItem().equals("Custom Virtual Meet")) {
						String[] teams = customTeamInputs.getText().split(",");
						sortedMen = subsetList(sortedMen,teams);
						sortedWomen = subsetList(sortedWomen,teams);
					}
				}
				
				int n = Integer.parseInt(nTextField.getText());
				int scorers = Integer.parseInt(scorersTextField.getText());
				String results = "";
				
				if(chckbxIndividual.isSelected()) {
					if(chckbxMen.isSelected()) {
						results+="MEN'S INDIVIDUAL"+System.lineSeparator()+getTopSkiers(n,sortedMen)+System.lineSeparator();
					}
					if(chckbxWomen.isSelected()) {
						results+="WOMEN'S INDIVIDUAL"+System.lineSeparator()+getTopSkiers(n,sortedWomen)+System.lineSeparator();
					}
				}
				if(chckbxTeam.isSelected()) {
					if(chckbxMen.isSelected()) {
						results+="MEN'S TEAM"+System.lineSeparator()+getTopTeams(n,scorers,sortedMen)+System.lineSeparator();
					}
					if(chckbxWomen.isSelected()) {
						results+="WOMEN'S TEAM"+System.lineSeparator()+getTopTeams(n,scorers,sortedWomen)+System.lineSeparator();
					}
				}
				
				outputField.setText(results);
				
			}
			
		});
		
		panel_9.add(btnNewButton);
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



}
