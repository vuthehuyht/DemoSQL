package DemoJava;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Create the dialog.
	 */
	public Login() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[][grow]", "[][]"));
		{
			JLabel lblUsername = new JLabel("Username");
			contentPanel.add(lblUsername, "cell 0 0,alignx trailing");
		}
		{
			txtUsername = new JTextField();
			contentPanel.add(txtUsername, "cell 1 0,growx,aligny top");
			txtUsername.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Password");
			contentPanel.add(lblPassword, "cell 0 1,alignx trailing");
		}
		{
			txtPassword = new JPasswordField();
			contentPanel.add(txtPassword, "cell 1 1,growx");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Log In");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Connection con = ConnectionUtil.getConnection();
						String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
						try {
							PreparedStatement stmt = con.prepareStatement(sql);
							stmt.setString(1, txtUsername.getText());
							stmt.setString(2, txtPassword.getText());

							ResultSet rs = stmt.executeQuery();
							if (rs.next()) {
								if (txtUsername.getText().equalsIgnoreCase("admin")) {

									Runner run = new Runner();
									run.setVisible(true);
									Login.this.setVisible(false);
								} else {
									GusetView guest = new GusetView();
									guest.setVisible(true);
									Login.this.setVisible(false);
								}

							} else {
								JOptionPane.showMessageDialog(null, "Tên tài khoản hoặc mật khẩu không đúng!");
								txtUsername.setText("");
								txtPassword.setText("");
							}

						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Login.this.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
