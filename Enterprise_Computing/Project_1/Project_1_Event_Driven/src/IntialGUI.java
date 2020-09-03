import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Interface.ControllerInterface;
import Interface.ProcessButtonObjectEvent;
import Interface.ViewController;
import Inventory.InventoryManagement;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class IntialGUI extends JFrame implements ControllerInterface {

	private JPanel contentPane;
	
	private JTextField NumberOfItemsField;
	private JTextField Item1IDField;
	private JTextField Item1QuanityField;
	private JTextField Item1InfoField;
	private JTextField OrderSubTotalField;
	
	static InventoryManagement IM = null;
	
	private JButton ProcessItemButton;
	private JButton ConfirmItemButton;
	private JButton ViewOrderButton;
	private JButton FinishOrderButton;
	private JButton NewOrderButton;
	private JButton ExitButton;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IntialGUI frame = new IntialGUI();
					ViewController controller = new ViewController();
					IM = new InventoryManagement(controller);
					controller.addListener(IM);
					controller.addListener(frame);
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
		
		Item1IDField = new JTextField();
		Item1IDField.setBounds(513, 107, 370, 30);
		contentPane.add(Item1IDField);
		Item1IDField.setColumns(10);
		
		Item1QuanityField = new JTextField();
		Item1QuanityField.setBounds(513, 158, 370, 30);
		contentPane.add(Item1QuanityField);
		Item1QuanityField.setColumns(10);
		
		Item1InfoField = new JTextField();
		Item1InfoField.setBounds(513, 214, 370, 30);
		contentPane.add(Item1InfoField);
		Item1InfoField.setColumns(10);
		
		OrderSubTotalField = new JTextField();
		OrderSubTotalField.setBounds(513, 266, 370, 30);
		contentPane.add(OrderSubTotalField);
		OrderSubTotalField.setColumns(10);
		
		ProcessItemButton = new JButton("Process Item #1");
		ProcessItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				IM.readInventory();
			}
		});
		ProcessItemButton.setBounds(10, 380, 130, 23);
		contentPane.add(ProcessItemButton);
		
		ConfirmItemButton = new JButton("Confirm Item #1");
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
	}

	
	

	@Override
	public void processExitButtonClick(ProcessButtonObjectEvent e) {
		// TODO Auto-generated method stub
		
	}
}
