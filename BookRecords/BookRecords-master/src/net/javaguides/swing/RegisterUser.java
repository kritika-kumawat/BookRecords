package net.javaguides.swing;

import java.sql.*;
import java.util.*;

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
import java.awt.event.ActionEvent;

public class RegisterUser extends JFrame {

	private JPanel contentPane;
	private JTextField first_name;
	private JTextField user_name;
	private JTextField last_name;
	private JTextField email_id;
	private JTextField mobile;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterUser frame = new RegisterUser();
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
	public RegisterUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New User Register");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 22));
		lblNewLabel.setBounds(362, 52, 325, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Dialog", Font.BOLD, 14));
		lblLastName.setBounds(58, 243, 110, 29);
		contentPane.add(lblLastName);
		
		last_name = new JTextField();
		last_name.setColumns(10);
		last_name.setBounds(214, 235, 228, 50);
		contentPane.add(last_name);
		last_name.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Dialog", Font.BOLD, 14));
		lblFirstName.setBounds(58, 152, 99, 43);
		contentPane.add(lblFirstName);
		
		first_name = new JTextField();
		first_name.setBounds(214, 151, 228, 50);
		contentPane.add(first_name);
		first_name.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email Id");
		lblEmail.setFont(new Font("Dialog", Font.BOLD, 14));
		lblEmail.setBounds(58, 324, 124, 36);
		contentPane.add(lblEmail);
		
		email_id = new JTextField();
		email_id.setColumns(10);
		email_id.setBounds(214, 320, 228, 50);
		contentPane.add(email_id);
		email_id.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Dialog", Font.BOLD, 14));
		lblUsername.setBounds(542, 160, 96, 26);
		contentPane.add(lblUsername);
		
		user_name = new JTextField();
		user_name.setColumns(10);
		user_name.setBounds(707, 151, 228, 50);
		contentPane.add(user_name);
		user_name.setColumns(10);
		
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPassword.setBounds(542, 245, 99, 24);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(707, 235, 228, 50);
		contentPane.add(passwordField);
		
		JLabel lblMobileNo = new JLabel("Mobile no.");
		lblMobileNo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblMobileNo.setBounds(542, 329, 139, 26);
		contentPane.add(lblMobileNo);
		
		mobile = new JTextField();
		mobile.setColumns(10);
		mobile.setBounds(707, 320, 228, 50);
		contentPane.add(mobile);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String firstName = first_name.getText();
                String lastName = last_name.getText();
                String emailId = email_id.getText();
                String userName = user_name.getText();
                String mobileNumber = mobile.getText();
                int len = mobileNumber.length();
                String password = Base64.getEncoder().encodeToString(passwordField.getText().getBytes());
                
                String msg = "" + firstName;
                msg += " \n";
                if (len != 10) {
                    JOptionPane.showMessageDialog(btnRegister, "Enter a valid mobile number");
                }
                try {
                	Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "Test@123");

                    String query = "INSERT INTO authentication values('" + firstName + "','" + lastName + "','" + userName + "','" +
                        password + "','" + emailId + "','" + mobileNumber + "')";

                    Statement sta = connection.createStatement();
                    int x = sta.executeUpdate(query);
                    
                        JOptionPane.showMessageDialog(btnRegister,
                            "Welcome, " + msg + "Your account is sucessfully created");
                       
                        
                    
                    first_name.setText("");
                    last_name.setText("");
                    email_id.setText("");
                    user_name.setText("");
                    mobile.setText("");
                    passwordField.setText("");
                    first_name.requestFocus();
                
                    connection.close();
                } catch (Exception exception) {
                	JOptionPane.showMessageDialog(btnRegister, "Account with this Username is already Exist");
                    exception.printStackTrace();
                }
			}
		});
		btnRegister.setBounds(346, 420, 117, 50);
		contentPane.add(btnRegister);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				Book_Shop_Project b = new Book_Shop_Project();
				b.frame.setVisible(true);
			}
		});
		btnCancel.setBounds(598, 420, 117, 50);
		contentPane.add(btnCancel);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
