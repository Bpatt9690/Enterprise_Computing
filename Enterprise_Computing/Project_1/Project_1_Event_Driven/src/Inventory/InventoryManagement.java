package Inventory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;

import Interface.ControllerInterface;
import Interface.ProcessButtonObjectEvent;
import Interface.ProcessConfirmItemObjectEvent;
import Interface.ProcessErrorMessageObjectEvent;
import Interface.ProcessFinishOrderObjectEvent;
import Interface.ProcessItemInformationObjectEvent;
import Interface.ProcessNewOrderObjectEvent;
import Interface.ProcessProcessItemObjectEvent;
import Interface.ProcessViewOrderObjectEvent;
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
		inventoryTable.put(s[0], s[1]+','+s[2]);

								
	}

	
	public void findInventoryItem(String inventoryNumber,String itemQty) {
		
		
		String item;
		String[] itemInfo;
		Double itemPrice;
		Double finalPrice;
		int itemAmount;
		Double discount = .0;
		
		
		if(!(inventoryTable.containsKey(inventoryNumber))) {
			controller.errorMessage("Item ID not found");	
		}
		

		else {
			
			item = inventoryTable.get(inventoryNumber);		
			itemInfo = item.split(",");
			
			item = itemInfo[0];
			itemPrice = Double.parseDouble(itemInfo[1].replaceAll("\\s+", ""));
			itemAmount = Integer.parseInt(itemQty);
			
			
			if(itemAmount <= 4 && itemAmount != 0)
				discount = .0;
			else if(itemAmount > 4 && itemAmount <= 9)
				discount =.10;
			
		
			finalPrice = itemPrice*itemAmount;
			
			item = inventoryNumber +" "+ itemInfo[0] + " $" + itemPrice.toString() +" " + itemQty +" "+discount*100+"% " + finalPrice;
			
			controller.itemInformation(item);
		}
	
	}
	
	
	@Override
	public void processExitButtonClick(ProcessButtonObjectEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void processNewOrderButtonClick(ProcessNewOrderObjectEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void processFinishOrderButtonClick(ProcessFinishOrderObjectEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void processViewOrderButtonClick(ProcessViewOrderObjectEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void processConfirmItemButtonClick(ProcessConfirmItemObjectEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void processProcessIButtonClick(ProcessProcessItemObjectEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void processErrorMessage(ProcessErrorMessageObjectEvent e, String message) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void processItemInformation(ProcessItemInformationObjectEvent e, String message) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
