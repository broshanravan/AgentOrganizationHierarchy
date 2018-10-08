


import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



/*
 * Created on 17-Feb-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author broshanravan
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GroupMaintenanceDialog extends JDialog
{
	private int height =130;
	private int width=350;
	int departmentId;
	ButtonListener buttonListener = new ButtonListener();
        MainScreen mainScreen;
        GroupInventory groupInventory = new GroupInventory();
	//labels
	JLabel nameLbl = new JLabel("Group Name");
	//JLabel teamMngrLbl = new JLabel("Team Manager");
	
	//textFields
	JTextField nameFld = new JTextField();
	//JTextField managerFld = new JTextField();
	
	//Buttons
	
	JButton addBtn = new JButton("Add");	
	JButton exitBtn = new JButton("Close");
        DepartmentSubTree departmentSubtree;
	
        Vector namesVec=groupInventory.getAllGroupNames();
        
	public GroupMaintenanceDialog(int departmentIdIn, DepartmentSubTree departmentSubtreeIn,MainScreen mainScreenIn)
	{
                super(mainScreenIn);
                mainScreen=mainScreenIn;
                departmentSubtree=departmentSubtreeIn;
		departmentId = departmentIdIn;
		setSize(width,height);
		setTitle("Group Maintenance Screen");
		getContentPane().setLayout(null);
		setUpScreen();
                setLocationRelativeTo(mainScreenIn);
		setVisible(true);
	}
	public void clearFields()
	{
		nameFld.setText("");
	}
	
        
        
        public void addGroup()
	{
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                
		
		try
		{
			if(nameFld.getText().equalsIgnoreCase(""))
                              throw new ParametrizedException("Please enter Group name");
			
			String groupName = nameFld.getText().trim();
			if(namesVec.contains(groupName))
				throw new ParametrizedException("Group name in use Please re-enter");
			groupInventory.storeGroup(groupName,departmentId);
			
                        namesVec.addElement(groupName);
                        
			JOptionPane.showMessageDialog(this,"Group "+ groupName+ " Added to the DB");
                        mainScreen.refreshDepartmentNode(departmentSubtree);
			setVisible(false);
		}
		catch(ParametrizedException e)
		{
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        JOptionPane.showMessageDialog(this,e.getMessage());
		}
                this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	private void setUpScreen()
	{
		//adding Labels
		getContentPane().add(nameLbl);
		
		//adding text Fields
		
		getContentPane().add(nameFld);
		
		//AddingButtons 
		getContentPane().add(addBtn); 
		getContentPane().add(exitBtn );
		
		//Adding Button Listeners
		exitBtn.addActionListener(buttonListener); 
		addBtn.addActionListener(buttonListener); 
		
		//setting Bounds
		nameLbl.setBounds(10,30,100,20);
		nameFld.setBounds(110,30,200,20);
		
		addBtn.setBounds(10,70,100,20);;
		
		exitBtn.setBounds(220,70,100,20) ;
    
    addBtn.addKeyListener(new java.awt.event.KeyAdapter() 
                                                          {
                                                              public void keyTyped(java.awt.event.KeyEvent evt) 
                                                              {
                                                                  KeyTyped(evt);
                                                              }
                                                          });

		nameFld.addKeyListener(new java.awt.event.KeyAdapter() 
                                                          {
                                                              public void keyTyped(java.awt.event.KeyEvent evt) 
                                                              {
                                                                  KeyTyped(evt);
                                                              }
                                                          });

	}
  
  private void KeyTyped(java.awt.event.KeyEvent evt) 
  {
    //System.out.println("keyTyped");
          if(evt.getKeyChar() == KeyEvent.VK_ENTER)
           addGroup();
  }

  
	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("Add"))
			{
				addGroup();
			}
			if(e.getActionCommand().equals("Close"))
			{
				setVisible(false);
			}
		}
	}
}
