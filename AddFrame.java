package DemoJava;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AddFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtClass;
	private JTextField txtHometown;


	/**
	 * Create the frame.
	 */
	public AddFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][grow]", "[][][][][]"));
		
		JLabel lblId = new JLabel("ID");
		contentPane.add(lblId, "cell 0 0,alignx trailing");
		
		txtID = new JTextField();
		contentPane.add(txtID, "cell 1 0,growx");
		txtID.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		contentPane.add(lblName, "cell 0 1,alignx trailing");
		
		txtName = new JTextField();
		contentPane.add(txtName, "cell 1 1,growx");
		txtName.setColumns(10);
		
		JLabel lblClass = new JLabel("Class");
		contentPane.add(lblClass, "cell 0 2,alignx trailing");
		
		txtClass = new JTextField();
		contentPane.add(txtClass, "cell 1 2,growx");
		txtClass.setColumns(10);
		
		JLabel lblHometown = new JLabel("Hometown");
		contentPane.add(lblHometown, "cell 0 3,alignx trailing");
		
		txtHometown = new JTextField();
		contentPane.add(txtHometown, "cell 1 3,growx");
		txtHometown.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = ConnectionUtil.getConnection();
				String sql = "INSERT INTO students VALUES (?,?,?,?)";
				try {
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, txtID.getText());
					stmt.setString(2, txtName.getText());
					stmt.setString(3, txtClass.getText());
					stmt.setString(4, txtHometown.getText());
					stmt.execute();
					AddFrame.this.setVisible(false);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		contentPane.add(btnAdd, "flowx,cell 1 4");
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFrame.this.setVisible(false);
			}
		});
		contentPane.add(btnCancel, "cell 1 4");
	}

}
