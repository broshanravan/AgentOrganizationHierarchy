


import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.*;
import java.util.Date;
import java.util.Vector;


/*
 * Created on 14-Feb-2005
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
public class SiteMaintenanceDialog extends JDialog
{
	private int height =130;
	private int width=350;
	
	ButtonListener buttonListener = new ButtonListener();
	
	//labels
	JLabel nameLbl = new JLabel("Site Name");
	//JLabel teamMngrLbl = new JLabel("Team Manager");
	
	//textFields
	JTextField nameFld = new JTextField();
	//JTextField managerFld = new JTextField();
	
	//Buttons
	
	JButton addBtn = new JButton("Add");	
	JButton exitBtn = new JButton("Close");
	
	public SiteMaintenanceDialog(MainScreen ms)
	{
		super(ms);
                setSize(width,height);
		setTitle("Site Maintenance Screen");
		getContentPane().setLayout(null);
		setUpScreen();
                setLocationRelativeTo(ms);
		setVisible(true);
	}
	public void clearFields()
	{
		nameFld.setText("");
	}
	public void addSite()
	{
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    MaintainSites maintainSites = new MaintainSites();
		Vector namesVec=maintainSites .getAllSiteNames();
		try
		{
			if(nameFld.getText().equalsIgnoreCase(""))
				throw new ParametrizedException("Please enter site name");
			
			String siteName = nameFld.getText().trim();
			if(namesVec.contains(siteName))
				throw new ParametrizedException("SiteName in Use Please re-enter");
			maintainSites.storeSite(siteName);
			namesVec.addElement(siteName);
			JOptionPane.showMessageDialog(this,"Site"+ siteName+ " Added to the DB");
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
           addSite();
  }

  
	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("Add"))
			{
				addSite();
			}
			if(e.getActionCommand().equals("Close"))
			{
				setVisible(false);
			}
		}
	}
}
