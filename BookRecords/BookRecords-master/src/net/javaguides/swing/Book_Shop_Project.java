package net.javaguides.swing;

import java.sql.*;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Book_Shop_Project {

	JFrame frame;
	private JTextField txtBName;
	private JTextField txtBEdition;
	private JTextField txtBPrice;
	private JTextField txtId;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Book_Shop_Project window = new Book_Shop_Project();
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
	public Book_Shop_Project() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
 
	 public void Connect() {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "Test@123");
	        }catch (ClassNotFoundException ex) {
	          ex.printStackTrace();
	        }catch (SQLException ex) {
	        	   ex.printStackTrace();
	        }
 
	 }
	 
	 public void table_load() {
		 try {
		    pst = con.prepareStatement("select * from bookrecords");
		    rs = pst.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
		 } catch (SQLException e) {
	    		e.printStackTrace();
		 } 
	 }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 30));
		frame.setBounds(300, 100, 1198, 692);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblBookShop = new JLabel("Book Shop");
		lblBookShop.setFont(new Font("Dialog", Font.BOLD, 30));
		lblBookShop.setBounds(532, 12, 286, 82);
		frame.getContentPane().add(lblBookShop);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(553, 106, 606, 199);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Book Name");
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(31, 42, 118, 17);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Book Eddition");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1.setBounds(31, 85, 118, 28);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Book Price");
		lblNewLabel_1_2.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(31, 146, 118, 28);
		panel.add(lblNewLabel_1_2);
		
		txtBName = new JTextField();
		txtBName.setBounds(268, 41, 265, 28);
		panel.add(txtBName);
		txtBName.setColumns(10);
		
		txtBEdition = new JTextField();
		txtBEdition.setColumns(10);
		txtBEdition.setBounds(268, 86, 265, 28);
		panel.add(txtBEdition);
		
		txtBPrice = new JTextField();
		txtBPrice.setColumns(10);
		txtBPrice.setBounds(268, 147, 265, 28);
		panel.add(txtBPrice);
		
		JButton btnNewButton = new JButton("Clear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtBName.setText("");
				txtBEdition.setText("");
				txtBPrice.setText("");
				txtId.requestFocus();
			}
		});
		btnNewButton.setBounds(573, 342, 117, 47);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bname,edition,price;
				bname = txtBName.getText();
				edition = txtBEdition.getText();
				price = txtBPrice.getText();
							
				 try {
					pst = con.prepareStatement("insert into bookrecords(book_name,book_edition,book_price)values(?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.executeUpdate();
					table_load();
					JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
					
						           
					txtBName.setText("");
					txtBEdition.setText("");
					txtBPrice.setText("");
					txtBName.requestFocus();
				   }
			 
				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
			}
		});
		btnSave.setBounds(800, 342, 117, 47);
		frame.getContentPane().add(btnSave);
		
		JButton btnExit = new JButton("Logout");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(frame,"Sure? You want to exit?", "Swing Tester",
			               JOptionPane.YES_NO_OPTION,
			               JOptionPane.QUESTION_MESSAGE);
			            if(result == JOptionPane.YES_OPTION){
			            	System.exit(0);
			            }
//				System.exit(0);
			}
		});
		btnExit.setBounds(1000, 342, 117, 47);
		frame.getContentPane().add(btnExit);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(593, 444, 510, 88);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_3 = new JLabel("Book Id");
		lblNewLabel_1_3.setFont(new Font("Dialog", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(63, 37, 118, 28);
		panel_1.add(lblNewLabel_1_3);
		
		txtId = new JTextField();
		txtId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
			          
			        String id = txtId.getText();
	
			        pst = con.prepareStatement("select book_name, book_edition, book_price from bookrecords where book_id = ?");
			        pst.setString(1, id);
			        ResultSet rs = pst.executeQuery();
	
			        if(rs.next()==true) {
			              
				        String name = rs.getString(1);
				        String edition = rs.getString(2);
				        String price = rs.getString(3);
				                
				        txtBName.setText(name);
				        txtBEdition.setText(edition);
				        txtBPrice.setText(price);
		                
			        }else {
			        	txtBName.setText("");
			        	txtBEdition.setText("");
			        	txtBPrice.setText("");
		            }
		       }catch (SQLException ex) {
		           
		        }
			}
		});
		txtId.setColumns(10);
		txtId.setBounds(215, 38, 265, 28);
		panel_1.add(txtId);
		
		JButton btnUserRegister = new JButton("User Register");
		btnUserRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterUser r = new RegisterUser();
				frame.dispose();
				r.setVisible(true);
			}

		});
		btnUserRegister.setBounds(970, 577, 131, 39);
		frame.getContentPane().add(btnUserRegister);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 106, 511, 339);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bname,edition,price,bid;
				
				bname = txtBName.getText();
				edition = txtBEdition.getText();
				price = txtBPrice.getText();
				bid  = txtId.getText();
				
				try {
					pst = con.prepareStatement("update bookrecords set book_name = ?, book_edition = ?, book_price = ? where book_id = ?");
					pst.setString(1, bname);
			        pst.setString(2, edition);
			        pst.setString(3, price);
			        pst.setString(4, bid);
			        pst.executeUpdate();
			        table_load();
			        JOptionPane.showMessageDialog(null, "Record Updated");
			        
			           
			        txtBName.setText("");
			        txtBEdition.setText("");
			        txtBPrice.setText("");
			        txtId.setText("");
			        txtId.requestFocus();
				}catch (SQLException e1) {
						
					e1.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(70, 495, 117, 47);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bid;
				bid  = txtId.getText();
				
				try {
					int result = JOptionPane.showConfirmDialog(frame,"Sure? You want to Delete?", "Swing Tester",
				               JOptionPane.YES_NO_OPTION,
				               JOptionPane.QUESTION_MESSAGE);
				    if(result == JOptionPane.YES_OPTION){
				    	pst = con.prepareStatement("delete from bookrecords where book_id = ? ");
				        pst.setString(1, bid);
				        pst.executeUpdate();
				        table_load();
				        JOptionPane.showMessageDialog(null, "Record Deleted");
				        
				           
				        txtBName.setText("");
				        txtBEdition.setText("");
				        txtBPrice.setText("");
				        txtId.setText("");
				        txtId.requestFocus();
				    }
					   		        
				    txtId.requestFocus();
				}catch (SQLException e1) {
						
					e1.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(279, 495, 117, 47);
		frame.getContentPane().add(btnDelete);
		
		JButton btnNewButton_1 = new JButton("User Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DeleteUser d = new DeleteUser();
				frame.dispose();
				d.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(748, 577, 131, 39);
		frame.getContentPane().add(btnNewButton_1);
		
	}

}
