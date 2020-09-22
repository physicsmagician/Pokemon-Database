import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Dialog.ModalityType;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class DatabaseGUI extends JFrame {

	public Connection connection;
	private JPanel contentPane;
	private ResultSetTable tblPrimary;
	private ResultSetTable tblSecondary;
	private JScrollPane scpPrimary;
	private JScrollPane scpSecondary;
	private JButton btnAdd1;
	private JButton btnImport1;
	private JButton btnModify1;
	private JButton btnDelete1;
	private JButton btnJoin1;
	private JButton btnAdd2;
	private JButton btnImport2;
	private JButton btnModify2;
	private JButton btnDelete2;
	private PokedexMethods methods = new PokedexMethods();
	private FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv", "csv");
	private JPanel decorationArea3;
	private JPanel panel_1;
	private JLabel lblImage;
	ResultSet primaryResultSet;
	ResultSet secondaryResultSet;
	//REMEMBER: SHIFT + TAB REMOVES EXTRA TAB INDENTATIONS IN CODE
	//for importing just go and c
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatabaseGUI frame = new DatabaseGUI();
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
	public DatabaseGUI() {
		setTitle("Kanto Pokedex");
		connection = MSAccessDatabaseConnection.getConnection("pokedex.accdb");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 908, 527);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 0, 102));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		primaryResultSet = null;
		secondaryResultSet = null;
		try {
			primaryResultSet = DatabaseUtilities.runQuery(connection, "SELECT ID, Name, Type, Type2, Height, Weight FROM Pokemon");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		try {
			secondaryResultSet = DatabaseUtilities.runQuery(connection, "SELECT * FROM Moves");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		tblPrimary = new ResultSetTable(primaryResultSet);
		tblPrimary.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				tblPrimary_valueChanged(arg0);
			}
		});
		tblPrimary.setBounds(10, 11, 483, 160);
//		scpPrimary.add(tblPrimary);
		
		tblSecondary = new ResultSetTable(secondaryResultSet);
		tblSecondary.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				tblSecondary_valueChanged(arg0);
			}
		});
		tblSecondary.setBounds(10, 217, 483, 203);
		contentPane.setLayout(null);

		scpPrimary = new JScrollPane();
		scpPrimary.setBounds(268, 11, 584, 183);
		scpPrimary.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scpPrimary.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scpPrimary.setViewportView(tblPrimary);
		contentPane.add(scpPrimary);
		
		scpSecondary = new JScrollPane();
		scpSecondary.setBounds(268, 239, 584, 195);
		scpSecondary.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scpSecondary.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scpSecondary.setViewportView(tblSecondary);
		contentPane.add(scpSecondary);

		try {
			primaryResultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//GUI LAYOUT//**********************************************
		
		btnAdd1 = new JButton("Add");
		btnAdd1.setForeground(new Color(255, 255, 255));
		btnAdd1.setBackground(new Color(153, 204, 255));
		btnAdd1.setBounds(268, 205, 108, 23);
		btnAdd1.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			btnAdd1_mouseClicked(arg0);
		}
		});
		contentPane.add(btnAdd1);
		
		btnImport1 = new JButton("Import");
		btnImport1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnImport1.setBackground(new Color(153, 204, 255));
		btnImport1.setForeground(new Color(255, 255, 255));
		btnImport1.setBounds(390, 205, 108, 23);
		btnImport1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnImport1_mouseClicked(e);
			}
		});
		contentPane.add(btnImport1);
		
		btnModify1 = new JButton("Modify");
		btnModify1.setForeground(new Color(255, 255, 255));
		btnModify1.setBackground(new Color(153, 204, 255));
		btnModify1.setBounds(508, 205, 108, 23);
		btnModify1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnModify1_mouseClicked(e);
			}
		});
		contentPane.add(btnModify1);
		
		btnDelete1 = new JButton("Delete");
		btnDelete1.setBackground(new Color(153, 204, 255));
		btnDelete1.setForeground(new Color(255, 255, 255));
		btnDelete1.setBounds(626, 205, 108, 23);
		btnDelete1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnDelete1_mouseClicked(e);
			}
		});
		contentPane.add(btnDelete1);
		
		btnJoin1 = new JButton("Join");
		btnJoin1.setForeground(new Color(255, 255, 255));
		btnJoin1.setBackground(new Color(153, 204, 255));
		btnJoin1.setBounds(744, 205, 108, 23);
		btnJoin1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnJoin1_mouseClicked(e);
			}
		});
		contentPane.add(btnJoin1);
		
		btnAdd2 = new JButton("Add");
		btnAdd2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdd2.setForeground(new Color(255, 255, 255));
		btnAdd2.setBackground(new Color(153, 204, 255));
		btnAdd2.setBounds(268, 445, 108, 23);
		btnAdd2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnAdd2_mouseClicked(e);
			}
		});
		contentPane.add(btnAdd2);
		
		btnImport2 = new JButton("Import");
		btnImport2.setForeground(new Color(255, 255, 255));
		btnImport2.setBackground(new Color(153, 204, 255));
		btnImport2.setBounds(390, 445, 108, 23);
		btnImport2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnImport2_mouseClicked(e);
			}
		});
		contentPane.add(btnImport2);
		
		btnModify2 = new JButton("Modify");
		btnModify2.setBackground(new Color(153, 204, 255));
		btnModify2.setForeground(new Color(255, 255, 255));
		btnModify2.setBounds(508, 445, 108, 23);
		btnModify2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnModify2_mouseClicked(e);
			}
		});
		contentPane.add(btnModify2);
		
		btnDelete2 = new JButton("Delete");
		btnDelete2.setForeground(new Color(255, 255, 255));
		btnDelete2.setBackground(new Color(153, 204, 255));
		btnDelete2.setBounds(626, 445, 114, 23);
		btnDelete2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnDelete2_mouseClicked(e);
			}
		});
		contentPane.add(btnDelete2);
		
		JPanel decorationArea1 = new JPanel();
		decorationArea1.setBackground(SystemColor.windowBorder);
		decorationArea1.setForeground(new Color(0, 0, 0));
		decorationArea1.setBounds(187, 327, 24, 74);
		contentPane.add(decorationArea1);
		
		JPanel decorationArea2 = new JPanel();
		decorationArea2.setBackground(SystemColor.windowBorder);
		decorationArea2.setForeground(new Color(0, 0, 0));
		decorationArea2.setBounds(158, 352, 80, 23);
		contentPane.add(decorationArea2);
		
		decorationArea3 = new JPanel();
		decorationArea3.setForeground(Color.BLACK);
		decorationArea3.setBackground(Color.GREEN);
		decorationArea3.setBounds(28, 335, 107, 66);
		contentPane.add(decorationArea3);
		
		JPanel panel = new JPanel();
		panel.setBounds(28, 24, 210, 270);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(143, 188, 143));
		panel_1.setBounds(22, 21, 165, 224);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		lblImage = new JLabel("");
		lblImage.setBounds(22, 23, 122, 179);
		panel_1.add(lblImage);
		BufferedImage image = null;
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("H:/Desktop/full_pokemon_image/bulbasaur.png").getImage().getScaledInstance(120, 120, Image.SCALE_DEFAULT));
		lblImage.setIcon(imageIcon);
		setControls();		
	}

	public void tblPrimary_valueChanged(ListSelectionEvent arg0) {
		if (arg0.getValueIsAdjusting() == false) {
			int[] selectedRows = this.tblPrimary.getSelectedRows();
			String key = "";

			if (selectedRows != null && selectedRows.length >= 1) {
				int row = selectedRows[0];
				key = (String) this.tblPrimary.getValueAt(row,0);
				System.out.println("Primary table: " + key);
				LogoTest images;
				try {
					images = new LogoTest();
					images.setLocationRelativeTo(this);
					images.setVisible(true);
				} catch (SQLException e) {
					//e.printStackTrace();
				} catch (IOException e) {
					//e.printStackTrace();
				}
			}
						
			String query = String.format("SELECT Moves.ID, Moves.Name, Moves.Type, Moves.Power, Moves.PP, Moves.Effect\r\n" + 
					"FROM ([Pokemon] INNER JOIN PokemonMoves ON Pokemon.ID = PokemonMoves.PokemonID) INNER JOIN Moves ON PokemonMoves.MovesID = Moves.ID\r\n" + 
					"WHERE (((Pokemon.ID)=%s));", key);

			ResultSet secondaryResultSet = null;;
			try {
				secondaryResultSet = DatabaseUtilities.runQuery(connection, query);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			tblSecondary.setResultSet(secondaryResultSet);
			
			try {
				secondaryResultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

	public void tblSecondary_valueChanged(ListSelectionEvent arg0) {
		if (arg0.getValueIsAdjusting() == false) {
			int[] selectedRows = this.tblSecondary.getSelectedRows();

			if (selectedRows != null && selectedRows.length >= 1) {
				int row = selectedRows[0];
				String key = (String) this.tblSecondary.getValueAt(row, 1);
				System.out.println("Secondary table: " + key);
			}
		}
	}
	private void setControls() {
		System.out.println("Set Controls");
	}
	private void populateTables() { 
		
		try {
			primaryResultSet = DatabaseUtilities.runQuery(connection, "SELECT ID, Name, Type, Type2, Height, Weight FROM Pokemon");
			DatabaseUtilities.printResultSet(primaryResultSet);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			secondaryResultSet = DatabaseUtilities.runQuery(connection, "SELECT * FROM Moves");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		tblPrimary.setResultSet(primaryResultSet);
		tblSecondary.setResultSet(secondaryResultSet);
		tblSecondary.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				tblSecondary_valueChanged(arg0);
			}
		});
		
		try {
			primaryResultSet.close();
			secondaryResultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.repaint();

	}
	protected void btnAdd1_mouseClicked(MouseEvent arg0) 
	{
		AddDialogue add1 = new AddDialogue(true);
		add1.setLocationRelativeTo(this);
		add1.setModalityType(ModalityType.APPLICATION_MODAL);
		add1.setVisible(true);
		
		setControls();
		if (add1.getChangeMade()) {
			populateTables();
		}
	}
	
	protected void btnImport1_mouseClicked(MouseEvent arg0) 
	{
		JFileChooser chooseFile = new JFileChooser();
		chooseFile.setFileFilter(filter);
		chooseFile.showDialog(null,"Select File");
		chooseFile.setVisible(true);
		 try {	//MUST BE IN THE FORM OF: Name, Type, Type2, Height, Weight
	    	 File fileName = chooseFile.getSelectedFile();
	    	 methods.importPrimaryRecords(fileName.toString());
	    	 populateTables();
	    } catch (Exception e1) {
	    	//e1.printStackTrace();
	    }
		setControls();
		
	}
	
	protected void btnModify1_mouseClicked(MouseEvent arg0) 
	{
		ModifyDialogue modify1 = new ModifyDialogue(true);
		modify1.setLocationRelativeTo(this);
		modify1.setModalityType(ModalityType.APPLICATION_MODAL);
		modify1.setVisible(true);
		
		setControls();
		if (modify1.getChangeMade()) {
			populateTables();
		}
	}
	
	protected void btnDelete1_mouseClicked(MouseEvent arg0) 
	{
		DeleteDialogue delete1 = new DeleteDialogue(true);
		delete1.setLocationRelativeTo(this);
		delete1.setModalityType(ModalityType.APPLICATION_MODAL);
		delete1.setVisible(true);
		
		setControls();
		if (delete1.getChangeMade()) {
			populateTables();
		}
	}
	
	protected void btnJoin1_mouseClicked(MouseEvent arg0) 
	{
		JoinDialogue join1 = new JoinDialogue();
		join1.setLocationRelativeTo(this);
		join1.setModalityType(ModalityType.APPLICATION_MODAL);
		join1.setVisible(true);
		
		setControls();
		if (join1.getChangeMade()) {
			populateTables();
		}
	}
	
	protected void btnAdd2_mouseClicked(MouseEvent arg0) 
	{
		AddDialogue add2 = new AddDialogue(false); //primaryRecord
		add2.setLocationRelativeTo(this);
		add2.setModalityType(ModalityType.APPLICATION_MODAL);
		add2.setVisible(true);
		
		setControls();
		if (add2.getChangeMade()) {
			populateTables();
		}
	}
	
	protected void btnImport2_mouseClicked(MouseEvent arg0) 
	{
		JFileChooser chooseFile = new JFileChooser();
		chooseFile.setFileFilter(filter);
		chooseFile.showDialog(null,"Select File");
		chooseFile.setVisible(true);
	    try {	//MUST BE IN THE FORM OF: Name, Type, Power, PP, Effect
	    	 File fileName = chooseFile.getSelectedFile();
	    	 methods.importSecondaryRecords(fileName.toString());
	    	 populateTables();
	    } catch (Exception e1) {
	    	//e1.printStackTrace();
	    }
		setControls();
	}
	
	protected void btnModify2_mouseClicked(MouseEvent arg0) 
	{
		ModifyDialogue modify2 = new ModifyDialogue(false); //primaryRecord
		modify2.setLocationRelativeTo(this);
		modify2.setModalityType(ModalityType.APPLICATION_MODAL);
		modify2.setVisible(true);
		
		setControls();
		if (modify2.getChangeMade()) {
			populateTables();
		}
	}
	
	protected void btnDelete2_mouseClicked(MouseEvent arg0) 
	{
		DeleteDialogue delete1 = new DeleteDialogue(false);
		delete1.setLocationRelativeTo(this);
		delete1.setModalityType(ModalityType.APPLICATION_MODAL);
		delete1.setVisible(true);
		
		setControls();
		if (delete1.getChangeMade()) {
			populateTables();
		}
	}
}
