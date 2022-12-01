package pit_a_pet;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;



public class User_GUI extends JFrame implements ActionListener {
	
	public static int n; //등록한 반려동물 수  
	public static Pet [] pet = new Pet[5];
	
	protected JButton rgstrButton = new JButton("반려동물 정보 등록");
	protected JButton dltButton = new JButton("반려동물 정보 삭제");
	protected JPanel mainPanel = new JPanel();
	protected JButton nextButton = new JButton("다음");
	protected JButton completeButton = new JButton("완료");
	protected JPanel subPanel = new JPanel(new GridLayout(5,3));
	protected int panelCheck = 0;
	protected JPanel cards = new JPanel(new CardLayout());
	protected CardLayout cl = new CardLayout();
	protected JPanel borderPanel = new JPanel();
	//protected String [] type = {"개","고양이","햄스터","양서류","파충류"};
	
	public JTextField typeGui = new JTextField("",10);
	public JTextField nameGui = new JTextField("",10);
	public JTextField ageGui = new JTextField("",10);
	public JTextField breedGui = new JTextField("",10);
	public JTextField weightGui = new JTextField("",10);
	public JTextField illnessGui = new JTextField("",10);
	public JTextField drugGui = new JTextField("",10);
	public JTextField hospitalGui = new JTextField("",10);
	public JTextField hairshopGui = new JTextField("",10);
	
	public User_GUI() {
		setTitle("반려동물 정보 관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout(5,10));
		JPanel westPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(50,50));
		westPanel.setLayout(new GridLayout(10,1));
		rgstrButton.addActionListener(this);
		dltButton.addActionListener(this);
		completeButton.addActionListener(this);
		westPanel.add(rgstrButton);
		westPanel.add(dltButton);
		
		mainPanel.add(borderPanel,BorderLayout.SOUTH);
		completeButton.setPreferredSize(new Dimension(150, 50));
		
		c.add(westPanel,BorderLayout.WEST);
		c.add(mainPanel,BorderLayout.CENTER);
		c.add(new JPanel(),BorderLayout.SOUTH);
		setSize(800,600);
		setVisible(true);
		cl = (CardLayout) cards.getLayout();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==rgstrButton && panelCheck==0) {
			borderPanel.add(nextButton);
			RegisterInfo_GUI();
		}
		
		else if(e.getSource()==nextButton) {
			if(nextButton.getText()=="다음") {
				borderPanel.add(completeButton);
				completeButton.setVisible(true);
				nextButton.setText("이전");
			}
			else {
				nextButton.setText("다음");
				completeButton.setVisible(false);
			}
			cl.next(cards);
		}
		
		else if(e.getSource()==completeButton) {
			ManagePetInfo.petNumber();
			System.out.println("이름 : "+pet[0].name+"\n");
			System.out.println("나이 : "+pet[0].age+"\n");
			System.out.println("품종 : "+pet[0].breed+"\n");
		}
		
	}
	
	public void RegisterInfo_GUI() {
		panelCheck = 1;
		mainPanel.add(cards);
		nextButton.setText("다음");
		JPanel subPanel1 = new JPanel(new GridLayout(5,3));
		mainPanel.add(new JPanel(),BorderLayout.NORTH);
		mainPanel.add(new JPanel(),BorderLayout.EAST);
		mainPanel.add(new JPanel(),BorderLayout.WEST);
		mainPanel.add(subPanel,BorderLayout.CENTER);
		subPanel1.add(new JLabel(" 반려동물 종류"));
		subPanel1.add(typeGui);
		subPanel1.add(new JLabel(""));
		
		subPanel1.add(new JLabel(" 이름"));
		subPanel1.add(nameGui);
		subPanel1.add(new JLabel(""));
		
		subPanel1.add(new JLabel(" 나이"));
		subPanel1.add(ageGui);
		subPanel1.add(new JLabel(""));
		
		subPanel1.add(new JLabel(" 품종"));
		subPanel1.add(breedGui);
		subPanel1.add(new JLabel(""));
		
		subPanel1.add(new JLabel(" 무게"));
		subPanel1.add(weightGui);
		subPanel1.add(new JLabel(" kg"));
		
		nextButton.setBackground(Color.BLACK);
		nextButton.setPreferredSize(new Dimension(150, 50));
		nextButton.addActionListener(this);
		
		
		//next Panel
		JPanel subPanel2 = new JPanel(new GridLayout(4,3));
		
		subPanel2.add(new JLabel(" 질병의 유무"));
		subPanel2.add(illnessGui);
		subPanel2.add(new JLabel(""));
		
		subPanel2.add(new JLabel(" 복용중인 약 명칭"));
		subPanel2.add(drugGui);
		subPanel2.add(new JLabel(""));
		
		subPanel2.add(new JLabel(" 이용중인 동물 병원"));
		subPanel2.add(hospitalGui);
		subPanel2.add(new JLabel(""));
		
		subPanel2.add(new JLabel(" 이용중인 반려동물 미용실"));
		subPanel2.add(hairshopGui);
		subPanel2.add(new JLabel(""));
		
		
		cards.add(subPanel1,"subPanel1");
		cards.add(subPanel2,"subPanel2");
		
		mainPanel.add(cards);
		mainPanel.setVisible(true);
		
	}
	
	
	public static void main(String[] args) {
		new User_GUI();
	}
}