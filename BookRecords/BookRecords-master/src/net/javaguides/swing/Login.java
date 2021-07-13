package net.javaguides.swing;

import java.sql.*;
import java.util.Base64;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField usrname;
	private JPasswordField usrpwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 400, 542, 357);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login ");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 26));
		lblNewLabel.setBounds(223, 12, 254, 57);
		contentPane.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Dialog", Font.BOLD, 14));
		lblUsername.setBounds(115, 95, 99, 33);
		contentPane.add(lblUsername);
		
		usrname = new JTextField();
		usrname.setBounds(232, 95, 205, 33);
		contentPane.add(usrname);
		usrname.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPassword.setBounds(119, 157, 95, 33);
		contentPane.add(lblPassword);
		
		usrpwd = new JPasswordField();
		usrpwd.setBounds(232, 157, 205, 33);
		contentPane.add(usrpwd);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "Test@123");
					Statement stmt = con.createStatement();
					String userName = usrname.getText();
					String userPwd = Base64.getEncoder().encodeToString(usrpwd.getText().getBytes());
					String sql = "Select * from authentication where usrname = '"+userName+"' and usrpwd = '"+userPwd+"'";
					ResultSet rs = stmt.executeQuery(sql);
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Login Sucessfully");
						dispose();
						Book_Shop_Project b = new Book_Shop_Project();
						b.frame.setVisible(true);
					}else {
						usrname.setText("");
						usrpwd.setText("");
						usrname.requestFocus();
						JOptionPane.showMessageDialog(null, "Incorrect Password/Username");
					}
					con.close();
				}catch(Exception e) {
					System.out.println(e);
					}
				
			}
				
		});
		btnLogin.setBounds(118, 252, 117, 44);
		contentPane.add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(326, 252, 117, 44);
		contentPane.add(btnExit);
	}
}
