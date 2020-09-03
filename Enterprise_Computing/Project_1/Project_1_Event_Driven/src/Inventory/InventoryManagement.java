package Inventory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;





public class InventoryManagement {
	
	public InventoryManagement() {
		String InventoryFile = null;
	};
	
	
	public void readInventory() {
		
		try {
			File inventoryObj = new File("\\Users\\Blake Patterson\\Desktop\\Enterprise_Computing\\Enterprise_Computing\\Project_1");
			Scanner myReader = new Scanner(inventoryObj);
			while(myReader.hasNextLine()) {
				String data = myReader.nextLine();
				System.out.println(data);
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Error");
			e.printStackTrace();
			
		}
		
		
		
		
	}
	

}
