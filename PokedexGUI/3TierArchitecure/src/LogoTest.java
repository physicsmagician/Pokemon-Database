import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.ucanaccess.complex.Attachment;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LogoTest extends JFrame {

	private JPanel contentPane;
	private JLabel lblLogo;
	private JLabel lblColour1;
	private JLabel lblColour2;
	private JLabel lblColour3;
	private JLabel lblColour4;
	
	public final int IMAGE_COLUMN = 6;
	
	private ResultSet rs = null;
	private Connection connection = null;
	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public LogoTest() throws SQLException, IOException {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				try {
					this_keyPressed(arg0);
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 190, 175);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblLogo = new JLabel("");
//		lblNewLabel.setIcon(new ImageIcon("res/CGY.png"));
		lblLogo.setBounds(64, 0, 128, 128);
		contentPane.add(lblLogo);
		
						
		lblColour1 = new JLabel("");
		lblColour1.setBounds(0, 96, 256, 32);
		lblColour1.setOpaque(true);
		contentPane.add(lblColour1);
		
		lblColour2 = new JLabel("");
		lblColour2.setBounds(0, 64, 256, 32);
		lblColour2.setOpaque(true);
		contentPane.add(lblColour2);
		
		lblColour3 = new JLabel("");
		lblColour3.setBounds(0, 32, 256, 32);
		lblColour3.setOpaque(true);
		contentPane.add(lblColour3);
		
		lblColour4 = new JLabel("");
		lblColour4.setBounds(0, 0, 256, 32);
		lblColour4.setOpaque(true);
		contentPane.add(lblColour4);		

		connection = MSAccessDatabaseConnection.getConnection("pokedex.accdb");
		rs =  DatabaseUtilities.runQuery(connection, "SELECT * FROM Pokemon");
		
		DatabaseUtilities.printResultSet(rs);

		rs.beforeFirst();
		
		this_keyPressed(null);
		
	}
	
	private void this_keyPressed(KeyEvent arg0) throws SQLException, IOException {
		
		rs.next();

		if (rs.isAfterLast()) {
			rs.beforeFirst();
			rs.next();
		}
				
		DatabaseUtilities.printResultSetMeta(rs);
		
//		String colour1 = rs.getString(3);
//		lblColour1.setBackground(hex2Rgb(colour1));
//		this.getContentPane().setComponentZOrder(lblColour1, 0);
//		
//		String colour2 = rs.getString(4);
//		lblColour2.setBackground(hex2Rgb(colour2));
//		this.getContentPane().setComponentZOrder(lblColour2, 0);
//
//		String colour3 = rs.getString(5);
//		lblColour3.setBackground(hex2Rgb(colour3));
//		this.getContentPane().setComponentZOrder(lblColour3, 0);
//
//		String colour4 = rs.getString(6);
//		lblColour4.setBackground(hex2Rgb(colour4));
//		this.getContentPane().setComponentZOrder(lblColour4, 0);
			
		Attachment[] attachments = (Attachment[])rs.getObject(IMAGE_COLUMN);
		Attachment attachment1 = attachments[0];
		byte[] data = attachment1.getData();
		
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		BufferedImage bImage2 = ImageIO.read(bis);
		ImageIcon i = new ImageIcon(bImage2);
		lblLogo.setIcon(i);
		this.getContentPane().setComponentZOrder(lblLogo, 0);
		
		this.repaint();
		
	};

	
	private Color hex2Rgb(String colorStr) {
		
		Color c;
		try {
			c = new Color(
			        Integer.valueOf( colorStr.substring( 1, 3 ), 16 ),
			        Integer.valueOf( colorStr.substring( 3, 5 ), 16 ),
			        Integer.valueOf( colorStr.substring( 5, 7 ), 16 ) );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			c = Color.WHITE;
		}
	    
	    return c;
	}
}
