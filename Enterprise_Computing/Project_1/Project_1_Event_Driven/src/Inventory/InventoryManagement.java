package Inventory;

import java.io.File;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.util.Hashtable;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Interface.ControllerInterface;
import Interface.ProcessButtonObjectEvent;
import Interface.ProcessConfirmItemObjectEvent;
import Interface.ProcessErrorMessageObjectEvent;
import Interface.ProcessFinishOrderObjectEvent;
import Interface.ProcessItemAddedMessageObjectEvent;
import Interface.ProcessItemInformationObjectEvent;
import Interface.ProcessNewOrderObjectEvent;
import Interface.ProcessOrderSubTotalObjectEvent;
import Interface.ProcessProcessItemObjectEvent;
import Interface.ProcessViewOrderObjectEvent;
import Interface.ViewController;

import java.util.*;

public class InventoryManagement implements ControllerInterface {
		
	ViewController controller;

	Hashtable<String,String> inventoryTable;
	
	List<String> currentItems;
	
	String[] currentItemsArray;
	
	String[] finishedItemsArray;
	
	double currentAmount;
	
	
	private static DecimalFormat df = new DecimalFormat("#.##");
	
	private int count = 0;
	
	
	public InventoryManagement(ViewController controller) {
		
		this.controller = controller;
		String InventoryFile = null;	
		inventoryTable = new Hashtable<String,String>();
		this.currentItems = new ArrayList<String>();
		this.currentAmount = 0.00;
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
		
	}	
	
	private void inventoryFiling(String inventoryItem) {
	
		String[] s = inventoryItem.split(",");	
		inventoryTable.put(s[0], s[1]+','+s[2]);

								
	}

	
	public boolean findInventoryItem(String inventoryNumber,String itemQty) {
		
		
		String item;
		String[] itemInfo;
		Double itemPrice;
		Double finalPrice;
		Double discountAmount;
		int itemAmount;
		Double discount = 0.0;
		
		
		if(!(inventoryTable.containsKey(inventoryNumber))) {
			controller.errorMessage("Item ID not found");	
			return false;
		}
		

		else {
			
			item = inventoryTable.get(inventoryNumber);		
			itemInfo = item.split(",");
			
			item = itemInfo[0];
			itemPrice = Double.parseDouble(itemInfo[1].replaceAll("\\s+", ""));
			itemAmount = Integer.parseInt(itemQty);
				
			//Applying discount
			if(itemAmount <= 4 && itemAmount != 0)
				discount=0.0;
			else if(itemAmount > 4 && itemAmount <= 9)
				discount=10.0;
			else if(itemAmount >= 10 && itemAmount <= 14)
				discount=15.0;
			else if(itemAmount >= 15)
				discount=20.0;
			
			
			if(discount == 0)
				finalPrice = itemPrice*itemAmount;
			
			else {
				discountAmount = (discount/100.0);
				finalPrice = (itemPrice*itemAmount) - ((itemPrice*itemAmount)*discountAmount);
	
			}
			
			item = inventoryNumber +" "+ itemInfo[0] + " $" + itemPrice.toString() +" " + itemQty +" "+(int)Math.round(discount)+"% $" + df.format(finalPrice);
			
			controller.itemInformation(item);
			currentOrder(item, finalPrice);
			return true;
		}
	
	}
	
	
	public void currentOrder(String itemInfo, double amount) {
		count++;
		currentItems.add(itemInfo);
		currentAmount+=amount;	

	}

	public void currentOrder() {
		
		int j = 0;
		
		
		currentItemsArray = new String[currentItems.size()];
	
		for(int i = 0; i < currentItems.size(); i++) {
			currentItemsArray[i] = (j=i+1)+")."+currentItems.get(i);
		}
				
		JOptionPane.showMessageDialog(null, currentItemsArray);
		
	}
	
	
	public void FinishOrder() {
		
		
		
		String itemized = "";
		String itemizedMessage = "";
		int j = 0;
		finishedItemsArray = new String[currentItems.size()];
		currentItemsArray = new String[currentItems.size()];
		
		for(int i = 0; i < currentItems.size(); i++) {
			currentItemsArray[i] = (j=i+1)+")."+currentItems.get(i);
			itemizedMessage += currentItemsArray[i]+'\n';
		}
			
		
		
		for(int i = 0; i < currentItems.size(); i++) {
			finishedItemsArray[i] = currentItems.get(i);
			itemized += finishedItemsArray[i] +'\n';
		}
				
				
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter formatDate  = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm:ss");
		DateTimeFormatter formatDateFile  = DateTimeFormatter.ofPattern("ddMMyyyyHHmm");
		
		
		String formattedDate = "Date: "+date.format(formatDate)+"\n";
		String formattedDateFile = "Date: "+date.format(formatDateFile)+"\n";
		String numberOfLine = "Number of line items: "+currentItems.size()+"\n";
		String categorize = "Item#/ ID/ Price/ Qty/ Disc %/ Subtotal:\n\n";

		String orderSubTotal = "\nOrder subtotal: $"+currentAmount+"\n";
		String taxRate = "Tax Rate: 6%\n";
		Double taxAmount = (currentAmount*.06);
		String taxAmountText = "Tax Amount: $";
		Double orderTotal = taxAmount+currentAmount;
		String orderTotalText = "\nOrder Total: $";
		String thankYou = "\nThank you for shopping at Bobs\n";
		
		
		String orderInformation = formattedDate+numberOfLine+categorize+itemizedMessage+orderSubTotal+taxRate+taxAmountText+df.format(taxAmount)+orderTotalText+df.format(orderTotal)+thankYou;
		String orderInformationFile = (date.format(formatDateFile))+itemized+(date.format(formatDate));
		
		
		JOptionPane.showMessageDialog(null, orderInformation);
		
	
		try {
			
			File transactionFile = new File("\\Users\\Blake Patterson\\Desktop\\transaction.txt");
			
			if(transactionFile.exists() && !transactionFile.isDirectory()) {
				for(int i = 0; i < currentItemsArray.length;i++) {
					Files.write(Paths.get("\\Users\\Blake Patterson\\Desktop\\transaction.txt"), orderInformationFile.replace('\n',',').getBytes(),StandardOpenOption.APPEND);
				}
			}
				
			
			else {
				File newTransactionFile = new File("\\Users\\Blake Patterson\\Desktop\\transaction.txt");
				newTransactionFile.createNewFile();
				Files.write(Paths.get("\\Users\\Blake Patterson\\Desktop\\transaction.txt"), orderInformationFile.replace('\n', ',').getBytes(),StandardOpenOption.APPEND);
			}
				
			
		}
		catch(IOException e) {
			e.printStackTrace();
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


	@Override
	public void processItemAddedMessage(ProcessItemAddedMessageObjectEvent e, String message) {
		// TODO Auto-generated method stub
		controller.orderSubTotal(currentAmount);
		
	}


	@Override
	public void processOrderSubTotalMessage(ProcessOrderSubTotalObjectEvent e, Double amount) {
		// TODO Auto-generated method stub
	
		
	}
	
	
	

}
