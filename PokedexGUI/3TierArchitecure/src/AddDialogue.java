import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.Icon;

public class AddDialogue extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEnterName;
	private JTextField txtEnterHeight;
	private JTextField txtEnterWeight;
	private JComboBox cboEnterType1;
	private JComboBox cboEnterType2;
	private JComboBox cboEnterPP;
	private JComboBox cboEnterEffect;
	private JTextField txtEnterMoveName;
	private JTextField txtEnterMovePower;
	
	private JLabel lblName;
	private JLabel lblHeight;
	private JLabel lblWeight;
	private JLabel lblType;
	private JLabel lblMoveName;
	private JLabel lblMoveType;
	private JLabel lblMovePower;
	private JLabel lblMovePP;
	private JLabel lblMoveEffect;
	
	private JButton btnOK;
	private JButton btnCancel;
	private JLabel lblWarning1;
	private JLabel lblWarning2;
	private JLabel lblWarning3;
	private JLabel lblWarning4;
	private String[] Types = {"Grass", "Fire", "Water", "Bug", "Normal", "Flying", "Fairy", "Electric", "Rock", "Ground", "Fighting", "Dark", "Psychic"
			, "Ice", "Steel", "Dragon"};
	private int[] PP = {5, 10, 15, 20, 25, 30, 35, 40};
	private String[] Effects = {"Null", "Increase HP", "Decrease HP", "Increase Attack", "Decrase Attack", "Increase Defense", "Decrease Defense", "Increase Speed", "Decrease Speed"};

	private boolean changeMade = false;
	private PokedexMethods change = new PokedexMethods();
	public boolean primaryRecord;

	/**
	 * Create the dialog.
	 */
	public AddDialogue(boolean primaryRecord) {
		this.primaryRecord = primaryRecord;
		
		if (primaryRecord) {	//adding to the first database
			this.setTitle("Add Pokemon");
			getContentPane().setLayout(null);
			setBounds(100, 100, 275, 250);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				contentPanel.setLayout(null);
				{
					lblName = new JLabel("Name");
					lblName.setBounds(11, 13, 32, 14);
					lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
					contentPanel.add(lblName);
				}
				{
					txtEnterName = new JTextField("");
					txtEnterName.setBounds(97, 10, 108, 20);
					contentPanel.add(txtEnterName);
					txtEnterName.setColumns(10);
					
				}
				{
					txtEnterName.getDocument().addDocumentListener(new DocumentListener() {

						public void changedUpdate(DocumentEvent arg0) {
							setControls();
							
						}

						public void insertUpdate(DocumentEvent arg0) {
							setControls();
							
						}

						public void removeUpdate(DocumentEvent arg0) {
							setControls();
							
						}
					});
				}
				{	//TYPE 1
					cboEnterType1 = new JComboBox(Types);
					cboEnterType1.addActionListener(new ActionListener() {
					 	public void actionPerformed(ActionEvent e) {
					 		cboEnterType_actionPerformed(e);
					 	}
					 });
					cboEnterType1.setBackground(Color.WHITE);
					cboEnterType1.setBounds(97, 41, 108, 20);
					contentPanel.add(cboEnterType1);
				}
				{
					lblType = new JLabel("Type 2");
					lblType.setBounds(11, 69, 38, 14);
					lblType.setFont(new Font("Tahoma", Font.BOLD, 11));
					contentPanel.add(lblType);
				}
				{	//TYPE 2
					cboEnterType2 = new JComboBox(Types);
					cboEnterType2.addItem("null");
					cboEnterType2.addActionListener(new ActionListener() {
					 	public void actionPerformed(ActionEvent e) {
					 		cboEnterType_actionPerformed(e);
					 	}
					 });
					cboEnterType2.setBackground(Color.WHITE);
					cboEnterType2.setBounds(97, 66, 108, 20);
					contentPanel.add(cboEnterType2);
				}
				{
					lblHeight = new JLabel("Height (m)");
					lblHeight.setBounds(11, 100, 61, 14);
					lblHeight.setFont(new Font("Tahoma", Font.BOLD, 11));
					contentPanel.add(lblHeight);
				}
				{
					lblType = new JLabel("Type 1");
					lblType.setBounds(11, 38, 38, 14);
					lblType.setFont(new Font("Tahoma", Font.BOLD, 11));
					contentPanel.add(lblType);
				}
				{
					txtEnterHeight = new JTextField("");
					txtEnterHeight.setBounds(97, 97, 108, 20);
					contentPanel.add(txtEnterHeight);
					txtEnterHeight.setColumns(10);
				}
				{
					txtEnterHeight.getDocument().addDocumentListener(new DocumentListener() {

						public void changedUpdate(DocumentEvent arg0) {
							setControls();
							
						}

						public void insertUpdate(DocumentEvent arg0) {
							setControls();
							
						}

						public void removeUpdate(DocumentEvent arg0) {
							setControls();
							
						}
					});
				}
				{
					lblWeight = new JLabel("Weight (kg)");
					lblWeight.setBounds(11, 133, 67, 14);
					lblWeight.setFont(new Font("Tahoma", Font.BOLD, 11));
					contentPanel.add(lblWeight);
				}
				{
					txtEnterWeight = new JTextField("");
					txtEnterWeight.setBounds(97, 128, 108, 20);
					contentPanel.add(txtEnterWeight);
					txtEnterWeight.setColumns(10);
				}
				{
					txtEnterWeight.getDocument().addDocumentListener(new DocumentListener() {

						public void changedUpdate(DocumentEvent arg0) {
							setControls();
							
						}

						public void insertUpdate(DocumentEvent arg0) {
							setControls();
							
						}

						public void removeUpdate(DocumentEvent arg0) {
							setControls();
							
						}
					});
				}
				{
					btnOK = new JButton("OK");
					btnOK.setActionCommand("OK");
					buttonPane.add(btnOK);
					getRootPane().setDefaultButton(btnOK);
					btnOK.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							btnOK_mouseClicked(arg0);
						}
					});
				}
				{
					btnCancel = new JButton("Cancel");
					btnCancel.setActionCommand("Cancel");
					buttonPane.add(btnCancel);
					btnCancel.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							btnCancel_mouseClicked(arg0);
						}
					});
				}
				{
					lblWarning1 = new JLabel("");
					lblWarning1 = new JLabel((UIManager.getIcon("OptionPane.warningIcon")));
					lblWarning1.setToolTipText("Invalid Input");
					lblWarning1.setBounds(215, 5, 32, 32);
					contentPanel.add(lblWarning1);
				}
				
			}
			{
				lblWarning2 = new JLabel("");
				lblWarning2 = new JLabel((UIManager.getIcon("OptionPane.warningIcon")));
				lblWarning2.setToolTipText("Invalid Type");
				lblWarning2.setBounds(215, 43, 32, 32);
				contentPanel.add(lblWarning2);
			}
			{
				lblWarning3 = new JLabel("");
				lblWarning3 = new JLabel((UIManager.getIcon("OptionPane.warningIcon")));
				lblWarning3.setToolTipText("Invalid Input");
				lblWarning3.setBounds(215, 86, 32, 32);
				contentPanel.add(lblWarning3);
			}
			{
				lblWarning4 = new JLabel("");
				lblWarning4 = new JLabel((UIManager.getIcon("OptionPane.warningIcon")));
				lblWarning4.setToolTipText("Invalid Input");
				lblWarning4.setBounds(215, 120, 32, 32);
				contentPanel.add(lblWarning4);
			}
			setControls();
		} 
			else {	
				this.setTitle("Add Moves");
				setBounds(100, 110, 285, 265);
				getContentPane().setLayout(new BorderLayout());
				contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
				getContentPane().add(contentPanel, BorderLayout.CENTER);
				{
					JPanel buttonPane = new JPanel();
					buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
					getContentPane().add(buttonPane, BorderLayout.SOUTH);
					contentPanel.setLayout(null);
					{
						lblMoveName = new JLabel("Name:");
						lblMoveName.setBounds(18, 23, 40, 20);
						lblMoveName.setFont(new Font("Tahoma", Font.BOLD, 11));
						contentPanel.add(lblMoveName);
					}
					{
						txtEnterMoveName = new JTextField("");
						txtEnterMoveName.setBounds(124, 20, 100, 20);
						contentPanel.add(txtEnterMoveName);
						txtEnterMoveName.setColumns(10);
					}
					{
						txtEnterMoveName.getDocument().addDocumentListener(new DocumentListener() {

							public void changedUpdate(DocumentEvent arg0) {
								setControls();
								
							}

							public void insertUpdate(DocumentEvent arg0) {
								setControls();
								
							}

							public void removeUpdate(DocumentEvent arg0) {
								setControls();
								
							}
						});
					}
					{
						lblMoveType = new JLabel("Type:");
						lblMoveType.setBounds(18, 54, 38, 14);
						lblMoveType.setFont(new Font("Tahoma", Font.BOLD, 11));
						contentPanel.add(lblMoveType);
					}
					{	//TYPE 1
						cboEnterType1 = new JComboBox(Types);
						cboEnterType1.addActionListener(new ActionListener() {
						 	public void actionPerformed(ActionEvent e) {
						 		cboEnterType_actionPerformed(e);
						 	}
						 });
						cboEnterType1.setBackground(Color.WHITE);
						cboEnterType1.setBounds(124, 51, 100, 20);
						contentPanel.add(cboEnterType1);
					}
					{
						lblMovePower = new JLabel("Power:");
						lblMovePower.setBounds(18, 85, 46, 14);
						lblMovePower.setFont(new Font("Tahoma", Font.BOLD, 11));
						contentPanel.add(lblMovePower);
					}
					{
						txtEnterMovePower = new JTextField("");
						txtEnterMovePower.setBounds(124, 82, 100, 20);
						contentPanel.add(txtEnterMovePower);
						txtEnterMovePower.setColumns(10);
					}
					{
						txtEnterMovePower.getDocument().addDocumentListener(new DocumentListener() {

							public void changedUpdate(DocumentEvent arg0) {
								setControls();
								
							}

							public void insertUpdate(DocumentEvent arg0) {
								setControls();
								
							}

							public void removeUpdate(DocumentEvent arg0) {
								setControls();
								
							}
						});
					}
					{
						lblMovePP = new JLabel("Power Points:");
						lblMovePP.setBounds(18, 116, 81, 14);
						lblMovePP.setFont(new Font("Tahoma", Font.BOLD, 11));
						contentPanel.add(lblMovePP);
					}
					{
						cboEnterPP = new JComboBox();
						for (int i = 0; i < PP.length; i++) {
							cboEnterPP.addItem(PP[i]);
						}
						cboEnterPP.addActionListener(new ActionListener() {
						 	public void actionPerformed(ActionEvent e) {
						 		cboEnterPP_actionPerformed(e);
						 	}

							
						 });
						cboEnterPP.setBounds(124, 113, 100, 20);
						contentPanel.add(cboEnterPP);
					}
					{
						lblMoveEffect = new JLabel("Effect:");
						lblMoveEffect.setBounds(18, 153, 100, 14);
						lblMoveEffect.setFont(new Font("Tahoma", Font.BOLD, 11));
						contentPanel.add(lblMoveEffect);
					}
					{	//TYPE 1
						cboEnterEffect = new JComboBox(Effects);
						cboEnterEffect.addActionListener(new ActionListener() {
						 	public void actionPerformed(ActionEvent e) {
						 		cboEnterEffect_actionPerformed(e);
						 	}
						 });
						cboEnterEffect.setBackground(Color.WHITE);
						cboEnterEffect.setBounds(124, 150, 100, 20);
						contentPanel.add(cboEnterEffect);
					}
					{
						btnOK = new JButton("OK");
						btnOK.setActionCommand("OK");
						buttonPane.add(btnOK);
						getRootPane().setDefaultButton(btnOK);
						btnOK.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent arg0) {
								btnOK_mouseClicked(arg0);
							}
						});
					}
					{
						btnCancel = new JButton("Cancel");
						btnCancel.setActionCommand("Cancel");
						buttonPane.add(btnCancel);
						btnCancel.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent arg0) {
								btnCancel_mouseClicked(arg0);
								
							}
						});
					}
					{
						lblWarning1 = new JLabel("");
						lblWarning1 = new JLabel((UIManager.getIcon("OptionPane.warningIcon")));
						lblWarning1.setBounds(230, 10, 32, 32);
						lblWarning1.setToolTipText("Input non-numeric");
						contentPanel.add(lblWarning1);
					}
				}
				{
					lblWarning2 = new JLabel((Icon) null);
					lblWarning2 = new JLabel((UIManager.getIcon("OptionPane.warningIcon")));
					lblWarning2.setToolTipText("Input non-numeric");
					lblWarning2.setBounds(230, 70, 32, 32);
					contentPanel.add(lblWarning2);
				}
				{
					lblWarning3 = new JLabel("");
					lblWarning3 = new JLabel((UIManager.getIcon("OptionPane.warningIcon")));
					lblWarning3.setToolTipText("Usually only moves with 0 power have effects.");
					lblWarning3.setBounds(230, 140, 32, 32);
					contentPanel.add(lblWarning3);
				}
	
				setControls();
			}
		
	}
	protected void setControls() {
		System.out.println("Set Controls");
		//ENABLING AND DISABLING CONTROLS (to edit once GUI design is available)
		//overflow values and absolute values
		if (primaryRecord) {
			boolean nameGiven = (this.txtEnterName.getText().isEmpty() == false);
			boolean heightGiven = (this.txtEnterHeight.getText().isEmpty() == false);
			boolean weightGiven = (this.txtEnterWeight.getText().isEmpty() == false);
			boolean heightNonNumericalValue = true; 
			boolean weightNonNumericalValue = true; 
			boolean invalidType = ( (cboEnterType1.getSelectedItem() == null && cboEnterType2.getSelectedItem() == null)) 
					|| (cboEnterType1.getSelectedItem() == cboEnterType2.getSelectedItem());
			
			try {
				heightNonNumericalValue = Double.valueOf(this.txtEnterHeight.getText()) == 0;
				weightNonNumericalValue = Double.valueOf(this.txtEnterWeight.getText()) == 0;
			}	catch (NumberFormatException e) {
			}
			this.lblWarning1.setVisible(!nameGiven);	
			this.lblWarning2.setVisible(invalidType);
			this.lblWarning3.setVisible(!heightGiven || heightNonNumericalValue); 
			this.lblWarning4.setVisible(!weightGiven || weightNonNumericalValue); 
			
			boolean validValue = (!heightNonNumericalValue && !weightNonNumericalValue && !invalidType);  
			if (nameGiven && heightGiven && weightGiven && validValue) {
				this.btnOK.setEnabled(true);
			} else {
				this.btnOK.setEnabled(false);
			}
			
		} else {
			boolean moveNameGiven = (this.txtEnterMoveName.getText().isEmpty() == false);
			boolean powerGiven = (this.txtEnterMovePower.getText().isEmpty() == false);
			boolean powerNonNumerical = true;
			
			try {
				powerNonNumerical = Integer.valueOf(txtEnterMovePower.getText()) == 0;
			}	catch (NumberFormatException e) {
			}
			
			this.lblWarning3.setVisible(true);
			this.lblWarning1.setVisible(!moveNameGiven);	
			this.lblWarning2.setVisible(!powerGiven || powerNonNumerical);
			 
			if (moveNameGiven && powerGiven && !powerNonNumerical) {
				this.btnOK.setEnabled(true);
			} else {
				this.btnOK.setEnabled(false);
			}
		}
		
	}

	private void cboEnterType_actionPerformed(ActionEvent e) {
		boolean invalidType;
		
		if (primaryRecord) {
			invalidType = ( (cboEnterType1.getSelectedItem() == null)
					|| (cboEnterType1.getSelectedItem() == null && cboEnterType2.getSelectedItem() == null)) 
					|| (cboEnterType1.getSelectedItem() == cboEnterType2.getSelectedItem());
		} else {
			invalidType = ( (cboEnterType1.getSelectedItem() == null)
					|| (cboEnterType1.getSelectedItem() == null && cboEnterType2.getSelectedItem() == null));
		}
		
		this.lblWarning2.setVisible(invalidType);
		
	}
	private void cboEnterPP_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	private void cboEnterEffect_actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	protected void btnOK_mouseClicked(MouseEvent e) {
		
		if (this.btnOK.isEnabled() == false) {
			return;
		}
		//apply changes to database
		if (primaryRecord) {
			change.addPrimaryRecord(txtEnterName.getText().toLowerCase(), cboEnterType1.getSelectedItem(), 
						cboEnterType2.getSelectedItem(), Math.abs(Double.parseDouble(txtEnterHeight.getText())), 
						Math.abs(Double.parseDouble(txtEnterWeight.getText())) );
			changeMade = true;
		
		} else {
			change.addSecondaryRecord(txtEnterMoveName.getText(), cboEnterType1.getSelectedItem(), 
					Math.abs(Double.parseDouble(txtEnterMovePower.getText())), cboEnterPP.getSelectedItem(), cboEnterEffect.getSelectedItem());
			changeMade = true;
		}
		this.setVisible(false);
		
	}
	protected void btnCancel_mouseClicked(MouseEvent e)
	{
		if (this.btnCancel.isEnabled() == false) {
			return;
		}
		this.setVisible(false);	
	}
	
	public boolean getChangeMade() {
		return changeMade;
	}
}
