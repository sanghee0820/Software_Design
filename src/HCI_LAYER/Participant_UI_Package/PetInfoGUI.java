package HCI_LAYER.Participant_UI_Package;
import PD_LAYER.participant_package.*;
import PD_LAYER.participant_package.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database_package.PetDB;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class PetInfoGUI {

	private JFrame frame;
	private JTextField nameText;
	private JTextField breedText;
	private JTextField weightText;
	private JTextField drugText;
	private JTextField hospitalText;
	private JTextField shopText;
	private JTextField illnessText;
	
	PetDB petDB;
	
	private JComboBox typeCombobox = new JComboBox();
	private JComboBox ageCombobox = new JComboBox();
	private JPanel registerFirstPanel = new JPanel();
	public ManagePetInfo info = new ManagePetInfo();
	private JTable table;
	private DefaultTableModel model = new DefaultTableModel(0,3);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PetInfoGUI window = new PetInfoGUI();
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
	public PetInfoGUI() {
		
		petDB = new PetDB();
		info = new ManagePetInfo();
		for(int i = 0; i < info.n; i++) {
			model.insertRow(i, new Object[] {info.pet[i].Get_name()
					,info.pet[i].Get_breed()
					,info.pet[i].Get_age()});
			
		}
		initialize();
	}
	
	private boolean checkEmpty(String str) {
		if(str.isEmpty())
			return false;
		else return true;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JPanel registerSecondPanel = new JPanel();
		registerSecondPanel.setBounds(154, 0, 646, 572);
		frame.getContentPane().add(registerSecondPanel);
		registerSecondPanel.setVisible(false);
		registerSecondPanel.setLayout(null);
		
		JButton previousButton = new JButton("??????");
		previousButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerSecondPanel.setVisible(false);
				registerFirstPanel.setVisible(true);
			}
		});
		previousButton.setBounds(189, 450, 117, 29);
		registerSecondPanel.add(previousButton);
		
		JLabel registerpageLabel2 = new JLabel("???????????? ?????? ?????? ?????????");
		registerpageLabel2.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		registerpageLabel2.setBounds(229, 70, 188, 16);
		registerSecondPanel.add(registerpageLabel2);
		
		JButton completeButton = new JButton("??????");
		completeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pet pet = info.registerPet();
				if(checkEmpty(nameText.getText()))
						pet.registerName(nameText.getText());
				else {
					JOptionPane.showMessageDialog(null, "?????? ????????? ??????????????????.");
					return;
				}
				
				if(checkEmpty(breedText.getText()))
					pet.registerBreed(breedText.getText());
				else {
					JOptionPane.showMessageDialog(null, "?????? ????????? ??????????????????.");
					return;
				}
				
				String mass = weightText.getText();
				if(checkEmpty(mass)) {
					try {
						pet.registerWeight(Integer.parseInt(mass));
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(null,"????????? ????????? ??????????????????.");
							(info.n)--;
							return;
							}
				}

				
				pet.registerType(typeCombobox.getSelectedItem().toString());
				pet.registerAge(Integer.parseInt(ageCombobox.getSelectedItem().toString()));
				pet.registerIllness(illnessText.getText());
				pet.registerDrug(drugText.getText());
				pet.registerHospital(hospitalText.getText());
				pet.registerShop(shopText.getText());
				
				petDB.dataUpload_pet(pet);
				
				model.insertRow(info.n, new Object[] {info.pet[(info.n)-1].Get_name()
						,info.pet[(info.n)-1].Get_breed()
						,info.pet[(info.n)-1].Get_age()});
				
				nameText.setText("");;
				breedText.setText("");
				drugText.setText("0");
				hospitalText.setText("0");
				shopText.setText("0");
				illnessText.setText("0");
				weightText.setText("0");
				
				System.out.println("name : "+pet.Get_name());
				System.out.println("type : " + pet.Get_Type());
//				System.out.println("breed : "+pet.breed);
//				System.out.println("age : "+pet.age);
//				System.out.println("weight : "+pet.weight);
//				System.out.println("illness : "+pet.illness);
//				System.out.println("drug : "+pet.drug);
//				System.out.println("hospital : "+pet.hospital);
//				System.out.println("shop : "+pet.shop);
				
				registerSecondPanel.setVisible(false);
			}
		});
		completeButton.setBounds(352, 450, 117, 29);
		registerSecondPanel.add(completeButton);
		
		JLabel illnessLabel = new JLabel("?????? ?????? ??????");
		illnessLabel.setBounds(189, 156, 89, 16);
		registerSecondPanel.add(illnessLabel);
		
		illnessText = new JTextField("0");
		illnessText.setColumns(10);
		illnessText.setBounds(334, 145, 130, 40);
		registerSecondPanel.add(illnessText);
		
		JLabel drugLabel = new JLabel("?????? ?????? ??? ??????");
		drugLabel.setBounds(192, 209, 89, 16);
		registerSecondPanel.add(drugLabel);
		
		drugText = new JTextField("0");
		drugText.setBounds(334, 197, 130, 40);
		registerSecondPanel.add(drugText);
		drugText.setColumns(10);
		
		JLabel hospitalLabel = new JLabel("?????? ?????? ?????? ??????");
		hospitalLabel.setBounds(189, 258, 105, 16);
		registerSecondPanel.add(hospitalLabel);
		
		hospitalText = new JTextField("0");
		hospitalText.setColumns(10);
		hospitalText.setBounds(334, 249, 130, 40);
		registerSecondPanel.add(hospitalText);
		
		JLabel shopLabel = new JLabel("?????? ?????? ???????????? ?????????");
		shopLabel.setBounds(189, 314, 152, 16);
		registerSecondPanel.add(shopLabel);
		
		shopText = new JTextField("0");
		shopText.setColumns(10);
		shopText.setBounds(334, 302, 130, 40);
		registerSecondPanel.add(shopText);
		
	
		registerFirstPanel.setBounds(154, 0, 646, 572);
		frame.getContentPane().add(registerFirstPanel);
		registerFirstPanel.setVisible(false);
		registerFirstPanel.setLayout(null);
		
		JLabel noticeLabel = new JLabel("* ????????? ?????? ????????? ????????? ??????????????????.");
		noticeLabel.setBounds(210, 115, 300, 16);
		registerFirstPanel.add(noticeLabel);
		
		JLabel typeLabel = new JLabel("*???????????? ??????");
		typeLabel.setBounds(191, 162, 90, 16);
		registerFirstPanel.add(typeLabel);
		
		JLabel nameLabel = new JLabel("*???????????? ??????");
		nameLabel.setBounds(191, 204, 90, 16);
		registerFirstPanel.add(nameLabel);
		
		JLabel breedLabel = new JLabel("*???????????? ??????");
		breedLabel.setBounds(191, 249, 90, 16);
		registerFirstPanel.add(breedLabel);
		
		JLabel ageLabel = new JLabel("*???????????? ????????????");
		ageLabel.setBounds(191, 299, 150, 16);
		registerFirstPanel.add(ageLabel);
		
		nameText = new JTextField("");
		nameText.setBounds(327, 192, 130, 40);
		registerFirstPanel.add(nameText);
		nameText.setColumns(10);
		
		breedText = new JTextField("");
		breedText.setColumns(10);
		breedText.setBounds(327, 237, 130, 40);
		registerFirstPanel.add(breedText);
		
		typeCombobox.setModel(new DefaultComboBoxModel(new String[] {"???", "?????????", "?????????", "?????????", "??????"}));
		typeCombobox.setBounds(327, 158, 130, 27);
		registerFirstPanel.add(typeCombobox);
		
		ageCombobox.setModel(new DefaultComboBoxModel(new String[] {"2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999"}));
		ageCombobox.setBounds(327, 295, 130, 27);
		registerFirstPanel.add(ageCombobox);
		
		JLabel weightLabel = new JLabel("???????????? ??????");
		weightLabel.setBounds(191, 345, 77, 16);
		registerFirstPanel.add(weightLabel);
		
		weightText = new JTextField("0");
		weightText.setColumns(10);
		weightText.setBounds(327, 334, 130, 40);
		registerFirstPanel.add(weightText);
		
		JLabel kgLabel = new JLabel("kg");
		kgLabel.setBounds(476, 345, 77, 16);
		registerFirstPanel.add(kgLabel);
		
		JButton nextButton = new JButton("??????");
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerFirstPanel.setVisible(false);
				registerSecondPanel.setVisible(true);
			}
		});
		nextButton.setBounds(261, 450, 150, 29);
		registerFirstPanel.add(nextButton);
		
		JLabel registerpageLabel = new JLabel("???????????? ?????? ?????? ?????????");
		registerpageLabel.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		registerpageLabel.setBounds(229, 70, 250, 16);
		registerFirstPanel.add(registerpageLabel);
		
		JPanel deletePanel = new JPanel();
		deletePanel.setBounds(154, 0, 646, 572);
		frame.getContentPane().add(deletePanel);
		deletePanel.setVisible(false);
		deletePanel.setLayout(null);
		
		JLabel deletepageLabel = new JLabel("???????????? ?????? ?????? ?????????");
		deletepageLabel.setBounds(229, 70, 250, 16);
		deletepageLabel.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		deletePanel.add(deletepageLabel);
		
		String header[] = {"??????","??????","????????????"};
		table = new JTable(model);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setIntercellSpacing(new Dimension(5,5));
		table.setGridColor(new Color(0,0,0));
		table.setRowHeight(30);
		table.setBounds(127, 121, 400, 240);
		model.insertRow(0, header);
		deletePanel.add(table);
		
		JButton deleteactionButton = new JButton("????????? ?????? ??????");
		deleteactionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choiceIndex = table.getSelectedRow();
				if(choiceIndex!=0) {
					info.DeletePet(choiceIndex);
					model.removeRow(choiceIndex);
					
					//?????? ????????? ???????????? ?????? 
					File file = new File("./DataBase/Pet DB.txt");		
					String dummy = "";
					try {
						BufferedReader br = new BufferedReader(new FileReader(file));
						
						//1. ??????????????? ?????? position ??????????????? ???????????? dummy??? ??????
						String line;
						int i = 1;
						//3. ??????????????? ?????? position ???????????? dummy??? ??????
						while((line = br.readLine())!=null) {
						    if(choiceIndex != i) dummy += (line + "\n" ); 
						    i++;
						}
						//4. FileWriter??? ???????????? ????????????
						FileWriter fw = new FileWriter("./DataBase/Pet DB.txt");
						fw.write(dummy);			
						
						//bw.close();
						fw.close();
						br.close();
					} catch (Exception ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
				}
			}
		});
		deleteactionButton.setBounds(270, 450, 117, 29);
		deletePanel.add(deleteactionButton);
		table.setVisible(true);
		
		JPanel subPanel = new JPanel();
		subPanel.setBackground(new Color(255, 255, 255));
		subPanel.setBounds(0, 0, 156, 572);
		frame.getContentPane().add(subPanel);
		subPanel.setLayout(null);
		
		JButton registerButton = new JButton("???????????? ?????? ??????");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(info.n>=5)
					JOptionPane.showMessageDialog(null, "??????????????? ?????? 5??????????????? ????????? ???????????????.");
				else {
					registerFirstPanel.setVisible(true);
					registerSecondPanel.setVisible(false);
					deletePanel.setVisible(false);
				}
			}
		});
		registerButton.setBounds(8, 33, 140, 29);
		subPanel.add(registerButton);
		
		JButton deleteButton = new JButton("???????????? ?????? ??????");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerFirstPanel.setVisible(false);
				
				registerSecondPanel.setVisible(false);
				deletePanel.setVisible(true);
			}
		});
		
		deleteButton.setBounds(8, 81, 140, 29);
		subPanel.add(deleteButton);
		
		JButton BackBtn = new JButton("????????????");
		BackBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane confirm = new JOptionPane();
				int result;
				result = confirm.showConfirmDialog(null, "????????????????????????? ", "????????????", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(result == 0) {
						frame.dispose();
				} 
			}
		});
		BackBtn.setBounds(8, 141, 140, 29);
		subPanel.add(BackBtn);
		
	}
}
