
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.*;
import javax.swing.JOptionPane;
import javax.swing.JTextField;



/*
 * Created on 17-Mar-2005
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
public class DepartmentMaintenanceScreen extends JDialog
{
	private int height =130;
	private int width=350;
	private int siteId =0;
	ButtonListener buttonListener = new ButtonListener();
    MainScreen ms;
	//labels
	JLabel nameLbl = new JLabel("Site Name");
	//JLabel teamMngrLbl = new JLabel("Team Manager");
	
	//textFields
	JTextField nameFld = new JTextField();
	//JTextField managerFld = new JTextField();
	
	//Buttons
	
	JButton addBtn = new JButton("Add");	
	JButton exitBtn = new JButton("Close");
	
	public DepartmentMaintenanceScreen (int siteIdIn,MainScreen msIn)
	{
		super(msIn);
                ms=msIn;
                siteId = siteIdIn;
		setSize(width,height);
		setTitle("Department Maintenance Screen");
		getContentPane().setLayout(null);
		setUpScreen();
                setLocationRelativeTo(ms);
		setVisible(true);
	}
	public void clearFields()
	{
		nameFld.setText("");
	}
	public void addDepartment()
	{
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                MaintainDepartment maintainDepartment = new MaintainDepartment();
		Vector namesVec=maintainDepartment.getAllCurrentDepartmentNames(siteId);
		try
		{
			if(nameFld.getText().equalsIgnoreCase(""))
				throw new ParametrizedException("Please enter Department name");
			
			String departmentName = nameFld.getText().trim();
			if(namesVec.contains(departmentName))
				throw new ParametrizedException("Department name in Use Please re-enter");
			maintainDepartment.storeDepartment(departmentName,siteId);
			namesVec.addElement(departmentName);
			JOptionPane.showMessageDialog(this,"Department"+ departmentName+ " Added to the DB");
			ms.addNewDepartmentNode(departmentName,siteId);
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
                setResizable(false);
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
           addDepartment();
  }

  
	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("Add"))
			{
				addDepartment();
			}
			if(e.getActionCommand().equals("Close"))
			{
				setVisible(false);
			}
		}
	}
}
