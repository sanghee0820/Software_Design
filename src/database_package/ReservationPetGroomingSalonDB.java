package database_package;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import reservation_package.ReservationPetGroomingSalon;

public class ReservationPetGroomingSalonDB {
	private int resvInt = 7;
	private String path = "./Database/Salon/";
	
	public void saveFile(ReservationPetGroomingSalon resvInfo) {
		int userId = 1;
		String fname = this.path + Integer.toString(userId) + "_Reser_" + Integer.toString(resvInt) + ".txt";
		String resv = resvInfo.Get_Use_Day().format(DateTimeFormatter.ofPattern("yyyy MM dd"))+ " " +
				resvInfo.Get_Use_Time().format(DateTimeFormatter.ofPattern("HH mm")) + " " +
				resvInfo.Get_Company_Name();
//		System.out.println(resv)
	    try {
	    	BufferedWriter writer = new BufferedWriter(new FileWriter(fname));
			writer.write(resv);
			writer.flush();
		    writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
	}
}