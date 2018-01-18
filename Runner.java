package DemoJava;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Runner extends JFrame {

	private JPanel contentPane;
	private static JTable table;
	private JPanel panel;
	private JButton btnRefresh;
	private JButton btnAdd;
	private JButton btnDelete;
	private JButton btnUpdate;
	private static DefaultTableModel model;
	private JButton btnExit;

	private static void itinModel() throws SQLException {
		model = new DefaultTableModel();
		model.addColumn("ID");
		model.addColumn("Name");
		model.addColumn("Class");
		model.addColumn("Hometown");

		Connection con = ConnectionUtil.getConnection();
		String sql = "SELECT * FROM students";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String[] row = new String[4];
			row[0] = String.valueOf(rs.getInt(1));
			row[1] = rs.getString(2);
			row[2] = rs.getString(3);
			row[3] = rs.getString(4);
			model.addRow(row);
		}
		table.setModel(model);
	}


	/**
	 * Create the frame.
	 */
	public Runner() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		scrollPane.setViewportView(table);

		panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);

		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					itinModel();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel.add(btnRefresh);

		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFrame frame = new AddFrame();
				frame.setVisible(true);
				try {
					itinModel();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		panel.add(btnAdd);

		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con = ConnectionUtil.getConnection();
				String sql = "DELETE  FROM students WHERE id = ?";
				try {
					PreparedStatement stmt = con.prepareStatement(sql);
					String sid = (String) model.getValueAt(table.getSelectedRow(), 0);
					int id = Integer.parseInt(sid);
					stmt.setInt(1, id);
					stmt.executeUpdate();
					itinModel();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		panel.add(btnDelete);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sid = (String) model.getValueAt(table.getSelectedRow(), 0);
				int id = Integer.parseInt(sid);
				EdittingFrame frame = new EdittingFrame(id);
				frame.setVisible(true);
			}
		});
		panel.add(btnUpdate);
		
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel.add(btnExit);
	}

}
