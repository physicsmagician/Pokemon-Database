import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class JoinDialogue extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JButton btnOK;
	private JButton btnCancel;
	private ArrayList<Integer> primaryTableIDS = new ArrayList<Integer>();
	private ArrayList<Integer> secondaryTableID = new ArrayList<Integer>();
	private ArrayList<Integer> deleteIDS = new ArrayList<Integer>();
	private PokedexMethods change = new PokedexMethods();
	private JComboBox cboChoosePrimaryID;
	private JComboBox cboChooseSecondaryID;
	private JComboBox cboAddDeleteRelationship;
	private JLabel lblAddOrDelete;
	private JLabel lblChoosePrimaryID;
	private JLabel lblChooseSecondaryID;
	private boolean changeMade = false;
	/**
	 * Create the dialog.
	 */
	public JoinDialogue() {
		setBounds(100, 100, 416, 218);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		cboChoosePrimaryID = new JComboBox();
		cboChoosePrimaryID.setBounds(206, 64, 159, 20);
		contentPanel.add(cboChoosePrimaryID);
		
		cboChooseSecondaryID = new JComboBox();
		cboChooseSecondaryID.setBounds(206, 95, 159, 20);
		contentPanel.add(cboChooseSecondaryID);
		
		lblChoosePrimaryID = new JLabel("Choose Primary ID:");
		lblChoosePrimaryID.setBounds(19, 67, 132, 14);
		contentPanel.add(lblChoosePrimaryID);
		
		lblChooseSecondaryID = new JLabel("Choose Secondary ID:");
		lblChooseSecondaryID.setBounds(19, 98, 151, 14);
		contentPanel.add(lblChooseSecondaryID);
		
		cboAddDeleteRelationship = new JComboBox();
		cboAddDeleteRelationship.setBounds(206, 22, 159, 20);
		cboAddDeleteRelationship.addItem("Add");
		cboAddDeleteRelationship.addItem("Delete");
		contentPanel.add(cboAddDeleteRelationship);
		
		lblAddOrDelete = new JLabel("Add or Delete Relationship:");
		lblAddOrDelete.setBounds(19, 25, 186, 14);
		contentPanel.add(lblAddOrDelete);
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
		updateIDs();
		setControls();
	}
	protected void setControls() {
		System.out.println("Set Controls");
	}
	protected void updateIDs() {
		primaryTableIDS = change.getIdsList(true);
		for (int i = 0; i < primaryTableIDS.size(); i++) {
			cboChoosePrimaryID.addItem(primaryTableIDS.get(i));
		}
		secondaryTableID = change.getIdsList(false);
		for (int i = 0; i < secondaryTableID.size(); i++) {
			cboChooseSecondaryID.addItem(secondaryTableID.get(i));
		}
		if (cboAddDeleteRelationship.getSelectedItem().equals("Delete")) {
			deleteIDS = change.getRelationshipIDS(cboChoosePrimaryID.getSelectedItem());
			for (int i = 0; i < deleteIDS.size(); i++) {
				cboChooseSecondaryID.addItem(secondaryTableID.get(i));
			}
			this.repaint();
		}
	}
	protected void btnOK_mouseClicked(MouseEvent e) {
		
		if (this.btnOK.isEnabled() == false) {
			return;
		}
		if (cboAddDeleteRelationship.getSelectedItem().equals("Add")) {
			change.addRelationship(cboChoosePrimaryID.getSelectedItem(), cboChooseSecondaryID.getSelectedItem());
			changeMade = true;
		} else {
			change.deleteRelationship(cboChoosePrimaryID.getSelectedItem(), cboChooseSecondaryID.getSelectedItem());
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
