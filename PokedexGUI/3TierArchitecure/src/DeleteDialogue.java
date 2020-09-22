import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class DeleteDialogue extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private boolean primaryRecord;
	private JButton btnOK;
	private JButton btnCancel;
	private JComboBox cboSelectPrimaryID;
	private JComboBox cboSelectSecondaryID;
	private PokedexMethods change = new PokedexMethods();
	private ArrayList<Integer> ids;
	private boolean changeMade = false;
	/**
	 * Create the dialog.
	 */
	public DeleteDialogue(boolean primaryRecord) {
		this.primaryRecord = primaryRecord;
		if (primaryRecord) {
			ids = change.getIdsList(true);
		} else {
			ids = change.getIdsList(false);
		}
		if (primaryRecord) {
			setTitle("Delete Pokemon");
			setBounds(100, 100, 345, 136);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);
			{
				cboSelectPrimaryID = new JComboBox();
				cboSelectPrimaryID.setBounds(118, 22, 177, 20);
				contentPanel.add(cboSelectPrimaryID);
				for (int i = 0; i < ids.size(); i++) {
					cboSelectPrimaryID.addItem(ids.get(i));
				}
			}
			
			JLabel lblPokemonName = new JLabel("Pokemon ID:");
			lblPokemonName.setBounds(24, 25, 90, 14);
			contentPanel.add(lblPokemonName);
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
		} else {
			setTitle("Delete Move");
			setBounds(100, 100, 345, 136);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);
			{
				cboSelectSecondaryID = new JComboBox();
				cboSelectSecondaryID.setBounds(118, 22, 177, 20);
				for (int i = 0; i < ids.size(); i++) {
					cboSelectSecondaryID.addItem(ids.get(i));
				}
				
				contentPanel.add(cboSelectSecondaryID);
			}
			
			JLabel lblPokemonStat = new JLabel("Move ID:");
			lblPokemonStat.setBounds(20, 25, 90, 14);
			contentPanel.add(lblPokemonStat);
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
		}
		
	}
	protected void setControls() {
		System.out.println("Set Controls");
	}
	protected void btnOK_mouseClicked(MouseEvent e) {
		
		if (this.btnOK.isEnabled() == false) {
			return;
		}
		if (primaryRecord) {
			change.deletePrimaryRecord(cboSelectPrimaryID.getSelectedItem());
			changeMade = true;
		} else {
			change.deleteSecondaryRecord(cboSelectSecondaryID.getSelectedItem());
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
