package Inventory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

import Interface.ControllerInterface;
import Interface.ProcessButtonObjectEvent;
import Interface.ViewController;

import java.util.*;

public class InventoryManagement implements ControllerInterface {
		
	ViewController controller;

	Hashtable<String,String> inventoryTable;
	
	public InventoryManagement(ViewController controller) {
		
		this.controller = controller;
		
		
		String InventoryFile = null;
		
		
		
		inventoryTable = new Hashtable<String,String>();
	};
	
	
	public void readInventory() {
		
		
		try {
			File inventoryObj = new File("\\Users\\Blake Patterson\\Desktop\\Enterprise_Computing\\Enterprise_Computing\\Project_1\\inventory.txt");
			Scanner myReader = new Scanner(inventoryObj);
			while(myReader.hasNext()) {
				String data = myReader.nextLine();
				inventoryFiling(data);
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("Error");
			e.printStackTrace();
			
		}
		
		System.out.println(inventoryTable);
		
	}	
	
	private void inventoryFiling(String inventoryItem) {
	
		String[] s = inventoryItem.split(",");	
		inventoryTable.put(s[0], s[1]+s[2]);
		controller.exitButtonControl();
								
	}

	
	public void findInventoryItem(String inventoryNumber) {
		
		
		
		
		
		
		
		if(!(inventoryTable.containsKey(inventoryNumber))) {
			//return error
		}
		
		
		
		

	}
	
	
	
	

	@Override
	public void processExitButtonClick(ProcessButtonObjectEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
