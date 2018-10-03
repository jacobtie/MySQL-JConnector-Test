import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

import java.sql.*;

public class TestGUI {

	private JFrame frmAwsDatabaseConnection;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestGUI window = new TestGUI();
					window.frmAwsDatabaseConnection.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAwsDatabaseConnection = new JFrame();
		frmAwsDatabaseConnection.setTitle("AWS Database Connection Test");
		frmAwsDatabaseConnection.setBounds(100, 100, 450, 300);
		frmAwsDatabaseConnection.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblAwsDatabaseConnection = new JLabel("AWS Database Connection Test");
		lblAwsDatabaseConnection.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frmAwsDatabaseConnection.getContentPane().add(lblAwsDatabaseConnection, BorderLayout.NORTH);

		JList lstMainList = new JList();
		frmAwsDatabaseConnection.getContentPane().add(lstMainList, BorderLayout.CENTER);

		JButton btnRunTest = new JButton("Run Test");
		btnRunTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String url = "jdbc:mysql://comicbookstore.clkbzgufuwb6.us-east-2.rds.amazonaws.com:3306/";
					String userName = "comicbookadmin";
					String password = "comix12345";
					String dbName = "test?useSSL=false";
					String driver = "com.mysql.jdbc.Driver";

					Connection con = DriverManager.getConnection(url + dbName, userName, password);

					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("select * from testtable");

					DefaultListModel<String> model = new DefaultListModel<>();
					lstMainList.setModel(model);
					while (rs.next()) {
						model.addElement(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
					}

					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		frmAwsDatabaseConnection.getContentPane().add(btnRunTest, BorderLayout.SOUTH);
	}

}
