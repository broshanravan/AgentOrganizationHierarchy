
/*
 * Created on 22-Feb-2005
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

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JDialog;
import javax.swing.*;

/*
 * Created on 19-Jan-2005
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
public class ApplicationRoleDialog extends JDialog
{
	private static final int hight =170;
	private static final int width = 400;
	
	RoleInventory roleInventory = new RoleInventory();
	
	Vector roleVec = roleInventory.getAllRoles();
	MaintainApplication maintainApplication = new MaintainApplication();
	Vector appVec = maintainApplication.getAllApplications();
	
	ButtonListener buttonListener = new ButtonListener();
	
	JLabel applicationLbl = new JLabel("Application");
	JLabel roleLbl = new JLabel("Role");
	
	JComboBox applicationBx = new JComboBox();
	JComboBox roleBx = new JComboBox();
	
	JButton assignBtn = new JButton("Assign");
	JButton cancelBtn = new JButton("Close");
	
	public ApplicationRoleDialog(MainScreen mainScreenIn)
	{
		super(mainScreenIn);
                setResizable(false);
		setSize(width,hight);
		getContentPane().setLayout(null);
		setUpScreen();
                setLocationRelativeTo(mainScreenIn);
		setVisible(true);
	}
	
	private void setUpScreen()
	{
		getContentPane().add(applicationLbl);
		getContentPane().add(roleLbl);
		
		getContentPane().add(applicationBx);
		getContentPane().add(roleBx);
		
		getContentPane().add(assignBtn);
		getContentPane().add(cancelBtn);
		
		applicationLbl.setBounds(20,30,100,20);
		applicationBx.setBounds(100,30,200,20);
		
		roleLbl.setBounds(20,60,100,20);
		roleBx.setBounds(100,60,200,20);
		
		assignBtn.setBounds(50,100,100,25);
		cancelBtn.setBounds(200,100,100,25);
		//Add Button listener
		assignBtn.addActionListener(buttonListener);
		cancelBtn.addActionListener(buttonListener);
		
		pupulateRoleCombo();
		pupulateApplicationCombo();
    
    assignBtn.addKeyListener(new java.awt.event.KeyAdapter() 
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
           StoreApplicationRole();
  }

  
	private void pupulateRoleCombo()
	{
		Iterator iter = roleVec.iterator();
		roleBx.addItem("Please Select Role");
		while(iter.hasNext())
		{
			Role role =(Role)iter.next();
			String n = role.getRoleName();
			roleBx.addItem(n);
			
		}
	}
	private void pupulateApplicationCombo()
	{
		Iterator iter = appVec.iterator();
		applicationBx.addItem("Please Select Application");
		while(iter.hasNext())
		{
			Application app =(Application)iter.next();
			String n = app.getApplicationDesc();
			applicationBx.addItem(n);
			
		}
	}
	private void StoreApplicationRole()
	{
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    try
		{
			if(applicationBx.getSelectedItem().equals("Please Select Application"))
			throw new ParametrizedException("Please Select Application");	
			
			if(roleBx.getSelectedItem().equals("Please Select Role"))
				throw new ParametrizedException("Please Select Role");	
			
			ApplicationRoleMaintenance applicationRoleMaintenace = new ApplicationRoleMaintenance();
			
			String appName=(String)applicationBx.getSelectedItem();
			String roleName=(String)roleBx.getSelectedItem();
			
			applicationRoleMaintenace.StoreApplicationRole(appName,roleName);
			
			
			JOptionPane.showMessageDialog(this,"The Role" +roleBx.getSelectedItem() +" is assigned to application " + applicationBx.getSelectedItem());
			
			applicationBx.setSelectedItem("Please Select Application");
			roleBx.setSelectedItem("Please Select Role");
		}
		catch(ParametrizedException e)
		{
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
      JOptionPane.showMessageDialog(this,e.getMessage());
		}
    this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	public class ButtonListener implements ActionListener
	{
    	public void actionPerformed(ActionEvent e)
    	{
    		if(e.getActionCommand().equals("Close"))
        {
           setVisible(false);
        }
    		else if(e.getActionCommand().equals("Assign"))
        {  			
    			StoreApplicationRole();
        }
    	}
	}
	
}

