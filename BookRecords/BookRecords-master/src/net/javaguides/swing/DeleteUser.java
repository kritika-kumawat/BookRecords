package net.javaguides.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class DeleteUser extends JFrame {

	private JPanel contentPane;
	private JTextField usrname;
	private JPasswordField pwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteUser frame = new DeleteUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DeleteUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 400, 542, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDeleteAccount = new JLabel("Delete Account");
		lblDeleteAccount.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDeleteAccount.setBounds(198, 12, 254, 57);
		contentPane.add(lblDeleteAccount);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(115, 95, 99, 33);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(119, 157, 95, 33);
		contentPane.add(lblPassword);
		
		usrname = new JTextField();
		usrname.setBounds(232, 95, 205, 33);
		contentPane.add(usrname);
		usrname.setColumns(10);
		
		pwd = new JPasswordField();
		pwd.setBounds(232, 157, 205, 33);
		contentPane.add(pwd);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "Test@123");
					Statement stmt = con.createStatement();
					String sql = "Select * from authentication where usrname = '"+usrname.getText()+"' and usrpwd = '"+pwd.getText().toString()+"'";
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next()) {
						int result = JOptionPane.showConfirmDialog(new JFrame(),"Sure? You want to Delete Account?", "Swing Tester",
					               JOptionPane.YES_NO_OPTION,
					               JOptionPane.QUESTION_MESSAGE);
					    if(result == JOptionPane.YES_OPTION){
					    	sql = "Delete from authentication where usrname = '"+usrname.getText()+"' and usrpwd = '"+pwd.getText().toString()+"'";
							stmt.executeUpdate(sql);
							JOptionPane.showMessageDialog(null, "Account Deleted Sucessfully");
							usrname.setText("");
							pwd.setText("");
							usrname.requestFocus();
					    }else {
					    	usrname.setText("");
							pwd.setText("");
							usrname.requestFocus();
					    }
						
					}else {
						usrname.setText("");
						pwd.setText("");
						usrname.requestFocus();
						JOptionPane.showMessageDialog(null, "Incorrect Password/Username");
					}
					con.close();
				}catch(Exception e) {
					System.out.println(e);
					}
			}
		});
		btnNewButton.setBounds(118, 252, 117, 44);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Book_Shop_Project b = new Book_Shop_Project();
				b.frame.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(326, 252, 117, 44);
		contentPane.add(btnNewButton_1);
	}
}
