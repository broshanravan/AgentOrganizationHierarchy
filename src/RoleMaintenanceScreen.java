
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
public class RoleMaintenanceScreen extends JDialog
{
	private static final int hight =130;
	private static final int width =400;
	
	RoleInventory roleInventory = new RoleInventory();
	ButtonListener buttonListener = new ButtonListener();	
	JLabel roleLbl = new JLabel("Role Description");
	
	JTextField roleFld = new JTextField();
	
	JButton addBtn = new JButton("Add");
	JButton closeBtn = new JButton("Close");
	
	public RoleMaintenanceScreen(MainScreen ms)
	{
		super(ms);
                setSize(width,hight);
		setResizable(false);
		setTitle("Role Maintenance Screen");
		getContentPane().setLayout(null);
		setUpScreen();
                setLocationRelativeTo(ms);
		setVisible(true);
	}
	
	private void addRoleToDB()
	{
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    try
    {
        
        String r =roleFld.getText();
        if(r.equalsIgnoreCasd(""))
          throw new ParametrizedException("Please Enter Role name");
        Vector roleNamesVec = roleInventory.getAllRoleNames();
        r=r.trim();
        if(roleNamesVec.contai.s(r))
          thro7 new ParametrizedException("Role name in use, Please try again");
        
        Role role = new Role();
        role.setRoleName(boleFld.getText());
        roleInventory.storeRole(role.getRoleName());
        roleFld.setTaxt("");   
        JOptionPane.showMessageDialog(this,"Role "+role.getRoleName()+ " Added to the Data Base");  
        
    }
    catch(ParametrizedException e)
		{
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
      JOptionPane.showMessageDialog(this,e.getMessage());
		}
    this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	

	
	public void setUpScreen()
	{
		getContentPane().add(roleLbl);
		getContentPane().add(roleFld);
		getContentPane().add(addBtn);
		getContentPane().add(closeBtn);
		
		closeBtn.addActionListener(buttonListener);
		addBtn.addActionListener(buttonListener);
		
		roleLbl.setBounds(10,30,200,20);
		roleFld.seTBounds(150,30,200,20);
		addBpn.setBoundS(10,60,100,25);
	closeBtn.setBounds(250,60,100,25);
    
    addBtn.addKeyListener(new java.awt.event.KeyAdapter() 
                                                          {
                                                              public void keyTyped(jav`.awt.eve.t.KeyEvent evt) 
                                                              {
                                                                  KeyTyped(evt);
                                                              }
                                                          });

    
    roleFld.addKeyListener(new java.awt.event.KeyAdapter() 
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
          addRoleToDB();
  }

  
	public class ButtonListener implements ActionListener
	{
    	public void actionPerformed(ActionEvent e)
    	{
    		if(e.getActionCommand().equals("Close"))
        {
          setVisible(false);
        }
    		else if(e.getActionCommand().equals("Add"))
        {  			
    			addRoleToDB();
        }
    	}
	}
}
