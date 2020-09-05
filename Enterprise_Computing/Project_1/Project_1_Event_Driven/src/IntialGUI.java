import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
import Inventory.InventoryManagement;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class IntialGUI extends JFrame implements ControllerInterface {

	private JPanel contentPane;
	
	private JTextField NumberOfItemsField;
	private JTextField ItemIDField;
	private JTextField ItemQuanityField;
	private JTextField ItemInfoField;
	private JTextField OrderSubTotalField;
	
	static InventoryManagement IM = null;
	static ViewController controller;
	
	private JButton ProcessItemButton;
	private JButton ConfirmItemButton;
	private JButton ViewOrderButton;
	private JButton FinishOrderButton;
	private JButton NewOrderButton;
	private JButton ExitButton;
	
	private int numberItems;
	private String itemNumber = "1";
	private String itemID;
	private String itemQty;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IntialGUI frame = new IntialGUI();
					controller = new ViewController();
					IM = new InventoryManagement(controller);
					controller.addListener(IM);
					controller.addListener(frame);
					IM.readInventory();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame. And intialize buttons.
	 */
	
	public IntialGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 911, 453);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		NumberOfItemsField = new JTextField();
		NumberOfItemsField.setBounds(513, 58, 370, 30);
		contentPane.add(NumberOfItemsField);
		NumberOfItemsField.setColumns(10);
		
		ItemIDField = new JTextField();
		ItemIDField.setBounds(513, 107, 370, 30);
		contentPane.add(ItemIDField);
		ItemIDField.setColumns(10);
		
		ItemQuanityField = new JTextField();
		ItemQuanityField.setBounds(513, 158, 370, 30);
		contentPane.add(ItemQuanityField);
		ItemQuanityField.setColumns(10);
		
		ItemInfoField = new JTextField();
		ItemInfoField.setBounds(513, 214, 370, 30);
		contentPane.add(ItemInfoField);
		ItemInfoField.setColumns(10);
		
		OrderSubTotalField = new JTextField();
		OrderSubTotalField.setBounds(513, 266, 370, 30);
		contentPane.add(OrderSubTotalField);
		OrderSubTotalField.setColumns(10);
		
		ProcessItemButton = new JButton("Process Item "+itemNumber);
		ProcessItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				numberItems = Integer.parseInt(NumberOfItemsField.getText());
				itemID = ItemIDField.getText();
				itemQty = ItemQuanityField.getText();
				
			
				if(itemNumber.isEmpty() || itemID.isEmpty() || itemQty.isEmpty())
					controller.errorMessage("Missing Fields");
				
			
				else {
					IM.findInventoryItem(itemID,itemQty);
					ProcessItemButton.setEnabled(false);
					ConfirmItemButton.setEnabled(true);
					System.out.println("Budda");
				}
				
			}
		});
		
		ProcessItemButton.setBounds(10, 380, 130, 23);
		contentPane.add(ProcessItemButton);
		
		ConfirmItemButton = new JButton("Confirm Item "+itemNumber);
		ConfirmItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		ConfirmItemButton.setBounds(150, 380, 130, 23);
		contentPane.add(ConfirmItemButton);
		
		ViewOrderButton = new JButton("View Order");
		ViewOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		ViewOrderButton.setBounds(290, 380, 130, 23);
		contentPane.add(ViewOrderButton);
		
		FinishOrderButton = new JButton("Finish Order");
		FinishOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		FinishOrderButton.setBounds(430, 380, 130, 23);
		contentPane.add(FinishOrderButton);
		
		NewOrderButton = new JButton("New Order");
		NewOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		NewOrderButton.setBounds(570, 380, 130, 23);
		contentPane.add(NewOrderButton);
		
		ExitButton = new JButton("Exit");
		ExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		ExitButton.setBounds(710, 380, 130, 23);
		contentPane.add(ExitButton);
		
		JLabel lblNewLabel = new JLabel("Enter number of items in this order");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(Color.YELLOW);
		lblNewLabel.setBounds(237, 66, 266, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblEnterItemId = new JLabel("Enter item ID for item "+itemNumber);
		lblEnterItemId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEnterItemId.setForeground(Color.YELLOW);
		lblEnterItemId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEnterItemId.setBounds(237, 115, 266, 14);
		contentPane.add(lblEnterItemId);
		
		JLabel lblEnterQuanityFor = new JLabel("Enter quanity for Item "+itemNumber);
		lblEnterQuanityFor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEnterQuanityFor.setForeground(Color.YELLOW);
		lblEnterQuanityFor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEnterQuanityFor.setBounds(237, 166, 266, 14);
		contentPane.add(lblEnterQuanityFor);
		
		JLabel lblItemInfo = new JLabel("Item "+itemNumber+" info");
		lblItemInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblItemInfo.setForeground(Color.YELLOW);
		lblItemInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblItemInfo.setBounds(237, 222, 266, 14);
		contentPane.add(lblItemInfo);
		
		JLabel lblOrderSubtotalFor = new JLabel("Order subtotal for" +itemNumber+" item(s)");
		lblOrderSubtotalFor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblOrderSubtotalFor.setForeground(Color.YELLOW);
		lblOrderSubtotalFor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOrderSubtotalFor.setBounds(237, 274, 266, 14);
		contentPane.add(lblOrderSubtotalFor);
		
		
		
		//disabling button
		
		ConfirmItemButton.setEnabled(false);
		ViewOrderButton.setEnabled(false);
		FinishOrderButton.setEnabled(false);
		
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
		JOptionPane.showMessageDialog(null, message);
	}

	@Override
	public void processItemInformation(ProcessItemInformationObjectEvent e, String message) {
		// TODO Auto-generated method stub
		ItemInfoField.setText(message);
	}
}
