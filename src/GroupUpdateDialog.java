


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
 * Created on 02-Mar-2005
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
public class GroupUpdateDialog extends JDialog
{
	private int height =130;
	private int width=350;
	int groupId;
        Group group = new Group();
	ButtonListener buttonListener = new ButtonListener();
        GroupInventory groupInventory = new GroupInventory();
        int departmentId;
        MainScreen ms;
	
	//labels
	JLabel nameLbl = new JLabel("New Name");
	//JLabel teamMngrLbl = new JLabel("Team Manager");
	
	//textFields
	JTextField nameFld = new JTextField();
	//JTextField managerFld = new JTextField();
	
	//Buttons
	
	JButton upDateBtn = new JButton("Update");	
	JButton exitBtn = new JButton("Close");
	
	public GroupUpdateDialog(int groupIdIn,int departmentIdIn, MainScreen msIn)
	{
                super(msIn);
                ms=msIn;
                departmentId=departmentIdIn;
		groupId = groupIdIn;
		setSize(width,height);
		setTitle("Group Maintenance Screen");
		getContentPane().setLayout(null);
		setUpScreen();
                setLocationRelativeTo(ms);
		setVisible(true);
	}
	public void clearFields()
	{
		nameFld.setText("");
	}
	public void renameGroup()
	{
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                
		Vector namesVec=groupInventory .getAllGroupNames();
		try
		{
			if(nameFld.getText().equalsIgnoreCase(""))
				throw new ParametrizedException("Please enter the group name");
			
			String groupName = nameFld.getText();
			if(namesVec.contains(groupName))
				throw new ParametrizedException("Group name in use Please re-enter");
			
                        groupInventory.renameGroup(groupId,groupName);
			namesVec.addElement(groupName);
			JOptionPane.showMessageDialog(this,"Group "+ groupName+ " is renamed");
			ms.refreshDepartmentNode(departmentId);
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
		getContentPane().add(upDateBtn); 
		getContentPane().add(exitBtn );
		
		//Adding Button Listeners
		exitBtn.addActionListener(buttonListener); 
		upDateBtn.addActionListener(buttonListener); 
		
		//setting Bounds
		nameLbl.setBounds(10,30,100,20);
		nameFld.setBounds(110,30,200,20);
		
		upDateBtn.setBounds(10,70,100,20);;
		
		exitBtn.setBounds(220,70,100,20) ;
                group=groupInventory.getGroup(groupId);
                nameFld.setText(group.getGroupName());
                
    
    upDateBtn.addKeyListener(new java.awt.event.KeyAdapter() 
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
          renameGroup();
  }

	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("Update"))
			{
				renameGroup();
			}
			if(e.getActionCommand().equals("Close"))
			{
				setVisible(false);
			}
		}
	}	
}
