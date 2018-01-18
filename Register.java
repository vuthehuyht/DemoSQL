package DemoJava;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JTextField txtFullname;

	/**
	 * Create the frame.
	 */
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][][]"));
		
		JLabel lblNewLabel = new JLabel("ID");
		contentPane.add(lblNewLabel, "cell 0 0,alignx trailing");
		
		txtId = new JTextField();
		contentPane.add(txtId, "cell 1 0,growx");
		txtId.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		contentPane.add(lblNewLabel_1, "cell 0 1,alignx trailing");
		
		txtUsername = new JTextField();
		contentPane.add(txtUsername, "cell 1 1,growx");
		txtUsername.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		contentPane.add(lblNewLabel_2, "cell 0 2,alignx trailing");
		
		txtPassword = new JPasswordField();
		contentPane.add(txtPassword, "cell 1 2,growx");
		
		JLabel lblNewLabel_3 = new JLabel("Full Name");
		contentPane.add(lblNewLabel_3, "cell 0 3,alignx trailing");
		
		txtFullname = new JTextField();
		contentPane.add(txtFullname, "cell 1 3,growx");
		txtFullname.setColumns(10);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = ConnectionUtil.getConnection();
				String sql = "INSERT INTO user VALUES (?,?,?,?)";
				try {
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, txtId.getText());
					stmt.setString(2, txtUsername.getText());
					stmt.setString(3, txtPassword.getText());
					stmt.setString(4, txtFullname.getText());
					stmt.execute();
					Register.this.setVisible(false);
					Login login = new Login();
					login.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		contentPane.add(btnNewButton, "flowx,cell 1 4");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register.this.setVisible(false);
			}
		});
		contentPane.add(btnCancel, "cell 1 4");
	}

}
