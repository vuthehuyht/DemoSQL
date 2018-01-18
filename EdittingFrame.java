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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class EdittingFrame extends JFrame {

	private JPanel contentPane;

	private static int id;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtClass;
	private JTextField txtHometown;
	

	/**
	 * Create the frame.
	 */
	public EdittingFrame(int id) {
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

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Connection con = ConnectionUtil.getConnection();
					String sql = "UPDATE students SET name = ?, class = ?, hometown = ? WHERE id = ?";
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setInt(4, id);
					stmt.setString(1, txtName.getText());
					stmt.setString(2, txtClass.getText());
					stmt.setString(3, txtHometown.getText());
					stmt.executeUpdate();
					EdittingFrame.this.setVisible(false);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnSave, "flowx,cell 1 4");

		JButton btnCancle = new JButton("Cancle");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EdittingFrame.this.setVisible(false);
			}
		});
		contentPane.add(btnCancle, "cell 1 4");
		
		this.id = id;
		
		try {
			initStudent();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private void initStudent() throws SQLException {
		Connection conn = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM students WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			txtName.setText(rs.getString(2));
			txtClass.setText(rs.getString(3));
			txtHometown.setText(rs.getString(4));
			txtID.setText(String.valueOf(id));
		}
	}

}
