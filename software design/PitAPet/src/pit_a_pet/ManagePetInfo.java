package pit_a_pet;

import java.util.Scanner;

class Pet { //반려동물 정보를 가지고 있는 클래스 
	public User_GUI gui = new User_GUI();
	public String type;
	public String name;
	public int age;
	public String breed;
	public int weight;
	public String illness;
	public String drug;
	public String hospital;
	public String hairshop;
	
	public void RegisterPet() //반려동물 정보를 등록하는 method
	{
			
			type = gui.typeGui.getText();
			System.out.println(type);
			age = Integer.parseInt(gui.ageGui.getText());
			name = gui.nameGui.getText();
			breed= gui.breedGui.getText();
			weight = Integer.parseInt(gui.weightGui.getText());
			illness = gui.illnessGui.getText();
			drug = gui.drugGui.getText();
			hospital = gui.hospitalGui.getText();
			hairshop = gui.hairshopGui.getText();
			
	}
}

public class ManagePetInfo {
	public static int n=0; //등록한 반려동물 수  
	public static Pet [] pet;
	public static void main(String[] args) {

		pet = new Pet[5];
		
		for(int i=0; i<5; i++)
			pet[i] = new Pet();
	}
	
	public static void petNumber() {
		pet[n].RegisterPet();
		n++;
	}
	
	public static void DeletePet(Pet[] pet) {
		
		int deleteNum;
		
		for(int i=0; i<n; i++)
			System.out.println(i+1+"번: "+pet[i].name+"("+pet[i].breed+")\n");
		
		System.out.println("삭제할 반려동물 번호를 입력하세요 : ");
		Scanner scanner = new Scanner(System.in);
		deleteNum = scanner.nextInt();
		
		if((deleteNum<1)||(deleteNum>n)) {
			System.out.println("번호에 해당하는 반려동물이 없습니다.\n");
			return;
		}
		
		for(int i=deleteNum-1; i<n-1; i++)
			pet[i] = pet[i+1];
		n--; //총 반려동물 마릿수 -1
		
		for(int i=0; i<n; i++)
			System.out.println(i+1+"번: "+pet[i].name+"("+pet[i].breed+")\n");
	}
	
}
