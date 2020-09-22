import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModifyDialogue extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnOK;
	private JButton btnCancel;
	private JTextField txtEnterModification;
	private JComboBox cboSelectID;
	private JComboBox cboSelectRecord;
	private JComboBox cboSelectType;
	private JComboBox cboSelectEffect;
	private JLabel lblWarning;
	private List ids;
	private ArrayList<String> primaryRecordsList = new ArrayList<String>();
	private ArrayList<String> secondaryRecordsList = new ArrayList<String>();
	private String[] types = {"Grass", "Fire", "Water", "Bug", "Normal", "Flying", "Fairy", "Electric", "Rock", "Ground", "Fighting", "Dark", "Psychic"
			, "Ice", "Steel", "Dragon", "null"};
	private String[] effects = {"Increase HP", "Decrease HP", "Increase Attack", "Decrase Attack", "Increase Defense", "Decrease Defense", "Increase Speed", "Decrease Speed"};
	private boolean primary = true;
	private PokedexMethods change = new PokedexMethods();
	private boolean changeMade = false;
	/**
	 * Create the dialog.
	 */
	public ModifyDialogue(boolean primary) {
		this.primary = primary;
		if (primary) {
			this.setTitle("Modify Pokemon");
			ids = change.getIdsList(true);
		} else {
			this.setTitle("Modify Stats");
			ids = change.getIdsList(false);
			System.out.println(ids);
		}
		primaryRecordsList.add("Name");
		primaryRecordsList.add("Type");
		primaryRecordsList.add("Type2");
		primaryRecordsList.add("Height");
		primaryRecordsList.add("Weight");
		
		secondaryRecordsList.add("Move Name");
		secondaryRecordsList.add("Move Type");
		secondaryRecordsList.add("Power");
		secondaryRecordsList.add("PP");
		secondaryRecordsList.add("Effect");
		
		setBounds(100, 100, 359, 208);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		contentPanel.setLayout(null);
		cboSelectID = new JComboBox();
		cboSelectID.setBounds(142, 19, 150, 20);
		contentPanel.add(cboSelectID);
		
		cboSelectRecord = new JComboBox();
		cboSelectRecord.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		setControls();
		 		refreshFields();
		 	}
		 });
		cboSelectRecord.setBounds(142, 50, 150, 20);
		contentPanel.add(cboSelectRecord);
		
		JLabel lblSelectRecord = new JLabel("Select Record:");
		lblSelectRecord.setBounds(25, 53, 98, 14);
		contentPanel.add(lblSelectRecord);
		
		JLabel lblEnterModification = new JLabel("Enter Modification:");
		lblEnterModification.setBounds(25, 96, 125, 14);
		contentPanel.add(lblEnterModification);
		
		txtEnterModification = new JTextField();
		txtEnterModification.setBounds(142, 93, 151, 20);
		contentPanel.add(txtEnterModification);
		txtEnterModification.setColumns(10);		
		{
			txtEnterModification.getDocument().addDocumentListener(new DocumentListener() {

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
	
		cboSelectType = new JComboBox();
		cboSelectType.setBounds(142, 93, 151, 20);
		contentPanel.add(cboSelectType);
		
		cboSelectEffect = new JComboBox();
		cboSelectEffect.setBounds(142, 93, 151, 20);
		contentPanel.add(cboSelectEffect);
		
		JLabel lblSelectId = new JLabel("Select ID:");
		lblSelectId.setBounds(25, 22, 71, 14);
		contentPanel.add(lblSelectId);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnOK = new JButton("OK");
				btnOK.setActionCommand("OK");
				buttonPane.add(btnOK);
				getRootPane().setDefaultButton(btnOK);
			}
			btnOK.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					btnOK_mouseClicked(arg0);
				}
			});
			{
				btnCancel = new JButton("Cancel");
				btnCancel.setActionCommand("Cancel");
				buttonPane.add(btnCancel);
			}
			btnCancel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					btnCancel_mouseClicked(arg0);
				}
			});
		}
		{
			lblWarning = new JLabel("");
			lblWarning = new JLabel((UIManager.getIcon("OptionPane.warningIcon")));
			lblWarning.setToolTipText("Invalid Input");
			lblWarning.setBounds(301, 93, 32, 32);
			contentPanel.add(lblWarning);
		}
		
		refreshModification();
		setControls();
	}
	
	/**
	 * This method refreshes the number of ID's (as they change when the user adds or deletes)
	 */
	protected void refreshModification() {
		System.out.println("refresh Modification");
		if (primary) {
			for (int i = 0; i < primaryRecordsList.size(); i++) {
				cboSelectRecord.addItem(primaryRecordsList.get(i));
			}
			for (int i = 0; i < ids.size(); i++) {
				cboSelectID.addItem(ids.get(i));
			}
			
		} else {
			for (int i = 0; i < secondaryRecordsList.size(); i++) {
				cboSelectRecord.addItem(secondaryRecordsList.get(i));
			}
			for (int i = 0; i < ids.size(); i++) {
				cboSelectID.addItem(ids.get(i));
			}
		}
	}
	protected void refreshFields() {
		if (cboSelectRecord.getSelectedItem().equals("Type") || cboSelectRecord.getSelectedItem().equals("Type2") 
				|| cboSelectRecord.getSelectedItem().equals("Move Type")) {
			
			contentPanel.remove(txtEnterModification);
			contentPanel.remove(cboSelectEffect);
			contentPanel.add(cboSelectType);
			contentPanel.revalidate();
			contentPanel.repaint();
			
		} else if (cboSelectRecord.getSelectedItem().equals("Height") || cboSelectRecord.getSelectedItem().equals("Weight")
				||cboSelectRecord.getSelectedItem().equals("PP") || cboSelectRecord.getSelectedItem().equals("Power")) {
			System.out.println("SELECTED NUMERIC INPUT");
			//.add(txtEnterModification);
			//contentPanel.remove(cboSelectType);
			contentPanel.remove(cboSelectType);
			contentPanel.remove(cboSelectEffect);
			contentPanel.add(txtEnterModification);
			contentPanel.revalidate();
			contentPanel.repaint();
			
		
		} else if (cboSelectRecord.getSelectedItem().equals("Effect")){
			
			///contentPanel.add(txtEnterModification);
			//contentPanel.remove(cboSelectType);
			System.out.println("SELECTED EFFFECTS");
			contentPanel.remove(txtEnterModification);
			contentPanel.remove(cboSelectType);
			contentPanel.add(cboSelectEffect);
			contentPanel.revalidate();
			contentPanel.repaint();
			
			
		} else if (cboSelectRecord.getSelectedItem().equals("Name") || cboSelectRecord.getSelectedItem().equals("Move Name")) {
			//acontentPanel.remove(cboSelectType);
			//contentPanel.add(txtEnterModification);
			contentPanel.add(txtEnterModification);
			contentPanel.remove(cboSelectType);
			contentPanel.remove(cboSelectEffect);
			contentPanel.revalidate();
			contentPanel.repaint();
		}
	}
	protected void setControls() {
		System.out.println("Set Controls");
		boolean inputGiven = txtEnterModification.getText().isEmpty() == false;
		boolean inputNonNumericalValue = true;
		try {
			inputNonNumericalValue = Double.valueOf(this.txtEnterModification.getText()) == 0;
		}	catch (NumberFormatException e1) {
		}
		
		boolean validValue = (!inputNonNumericalValue);  
		
		if (cboSelectRecord.getSelectedItem().equals("Type") || cboSelectRecord.getSelectedItem().equals("Type2") 
				|| cboSelectRecord.getSelectedItem().equals("Move Type")) {
			
			for (int i = 0; i < types.length; i++) {
				cboSelectType.addItem(types[i]);
			}
			
			this.lblWarning.setVisible(false);
			btnOK.setEnabled(true);
			
		} else if (cboSelectRecord.getSelectedItem().equals("Height") || cboSelectRecord.getSelectedItem().equals("Weight")
				||cboSelectRecord.getSelectedItem().equals("PP") || cboSelectRecord.getSelectedItem().equals("Power")) {

			this.lblWarning.setVisible(!inputGiven || inputNonNumericalValue);
			
			if (inputGiven && validValue) {
				btnOK.setEnabled(true);
			} else {
				btnOK.setEnabled(false);
			}
		
		} else if (cboSelectRecord.getSelectedItem().equals("Effect")){
			
			for (int i = 0; i < effects.length; i++) {
				cboSelectEffect.addItem(effects[i]);
			}
			this.lblWarning.setVisible(false);
			btnOK.setEnabled(true);
			
			
		} else if (cboSelectRecord.getSelectedItem().equals("Name") || cboSelectRecord.getSelectedItem().equals("Move Name")) {
		
			this.lblWarning.setVisible(!inputGiven);	
			if (inputGiven) {
				btnOK.setEnabled(true);
			} else {
				btnOK.setEnabled(false);
			}
		
		}
		
	}
	protected void btnOK_mouseClicked(MouseEvent e) {
	
		if (this.btnOK.isEnabled() == false) {
			return;
		}
		if (primary) {
			if (cboSelectRecord.getSelectedItem().equals("Name")) {
				change.modifyPrimaryRecord(cboSelectID.getSelectedItem(), txtEnterModification.getText(), null, null, null, null);
			
			} else if (cboSelectRecord.getSelectedItem().equals("Type")) {
				change.modifyPrimaryRecord(cboSelectID.getSelectedItem(), null, cboSelectType.getSelectedItem().toString(), null, null, null);
				
			} else if (cboSelectRecord.getSelectedItem().equals("Type2")) {
				change.modifyPrimaryRecord(cboSelectID.getSelectedItem(), null, null, cboSelectType.getSelectedItem().toString(), null, null);
				
			} else if (cboSelectRecord.getSelectedItem().equals("Height")) {
				change.modifyPrimaryRecord(cboSelectID.getSelectedItem(), null, null, null, txtEnterModification.getText(), null);
				
			} else {
				change.modifyPrimaryRecord(cboSelectID.getSelectedItem(), null, null, null, null, txtEnterModification.getText());

			}
			changeMade = true;
			
		} else {
			if (cboSelectRecord.getSelectedItem().equals("Move Name")) {
				change.modifySecondaryRecord(cboSelectID.getSelectedItem(), txtEnterModification.getText(), null, null, null, null);
			
			} else if (cboSelectRecord.getSelectedItem().equals("Move Type")) {
				change.modifySecondaryRecord(cboSelectID.getSelectedItem(), null, cboSelectType.getSelectedItem(), null, null, null);
				
			} else if (cboSelectRecord.getSelectedItem().equals("Power")) {
				change.modifySecondaryRecord(cboSelectID.getSelectedItem(), null, null, txtEnterModification.getText(), null, null);
				
			} else if (cboSelectRecord.getSelectedItem().equals("PP")) {
				change.modifySecondaryRecord(cboSelectID.getSelectedItem(), null, null, null, txtEnterModification.getText(), null);
				
			} else {
				change.modifySecondaryRecord(cboSelectID.getSelectedItem(), null, null, null, null, cboSelectEffect.getSelectedItem());
			}
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
		// TODO Auto-generated method stub
		return changeMade;
	}
}
