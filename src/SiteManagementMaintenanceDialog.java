


import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;

/*
 * Created on 18-Mar-2005
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
public class SiteManagementMaintenanceDialog extends JDialog
{
	private static final int hight = 250;
	private static final int width =450;
	private int siteId;
	private int managerEmpNo;
	int refEmpNo =0;
	private Vector employeeVec ;
	
	ButtonListener buttonListener = new ButtonListener();
	
	ComboListener comboListener = new ComboListener();
	ComboListener1 comboListener1 = new ComboListener1();
	
	
	Personnel personnel = new Personnel();
	MaintainSites maintainSites = new MaintainSites();
	GroupInventory groupInventory = new GroupInventory();
	MaintainDepartment maintainDepartment = new MaintainDepartment();
	
	JLabel nameLbl = new JLabel("Employee Name");
	JLabel empNoLbl = new JLabel("Employee Number");
	JLabel riasEmpNoLbl = new JLabel("RIAS Employee Number");
	
	JButton assignBtn = new JButton("Assign");
	JButton closeBtn = new JButton("Close");
	
	JTextField riasEmpNoFld = new JTextField();
	
	
	JComboBox nameBx = new JComboBox();
	JComboBox empNoBx = new JComboBox();
	
	public void clearEmpNoBx()
	{
		empNoBx.removeAllItems();
		empNoBx.addItem("Please Select");
	}
	
	
	public SiteManagementMaintenanceDialog(MainScreen ms,int siteIdIn)
	{
		
		super (ms,true);
		setTitle("Site Manager Assignmeni Dialog");
		siteId = siteIdIn;
		setSize(width,hight);
		setResizable(false);
		setLayout(null);
		siteId = siteIdIn;
		getAllSiteEmployee();
		setupScreen();
		Vector employeeVec = personnel.getAllSiteEmployees(siteId);                
		if(!employeeVec.isEmpty())
		{
                        setLocationRelativeTo(ms);
                        setVisible(true);
		}
                
	}
	
	public void getAllSiteEmployee()
	{
		Personnel personnel = new Personnel();
		employeeVec = personnel.getAllSiteEmployees(siteId);
		
	}
		
	private void setupScreen()
	{
		
		getContentPane().add(nameLbl);
		getContentPane().add(empNoLbl);
		getContentPane().add(riasEmpNoLbl);
			
		
		getContentPane().add(nameBx);
		getContentPane().add(empNoBx);
		getContentPane().add(riasEmpNoFld);
		
		
		getContentPane().add(assignBtn); 
		getContentPane().add(closeBtn);
		
		nameLbl.setBounds(10,10,150,20);
		empNoLbl.setBounds(10,60,150,20);
		riasEmpNoLbl.setBounds(10,110,150,20);
		
		
		nameBx.setBounds(180,10,250,20);
		empNoBx.setBounds(180,60,150,20);
		riasEmpNoFld.setBounds(180,110,150,20);
		
		assignBtn.setBounds(10,160,100,25); 
		closeBtn.setBounds(280,160,100,25);
		
		assignBtn.addActionListener(buttonListener);
		closeBtn.addActionListener(buttonListener);
		
		nameBx.addItem("Please Select");
		empNoBx.addItem("Please Select");
		nameBx.addActionListener(comboListener);
		empNoBx.addActionListener(comboListener1);
		
		assignBtn.setEnabled(false);
		populateEmployeeCombo();
		
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
           assignManager();
  }

  
	public void populateEmployeeCombo()
	{
		
		
		Vector employeeVec = personnel.getAllSiteEmployees(siteId);
		try
		{
			if(employeeVec.isEmpty())
				throw new ParametrizedException("No Employees assigned to this Site yet");
			Iterator iter = employeeVec.iterator();
			while(iter.hasNext())
			{
				Employee emp = (Employee)iter.next(); 
				String name = emp.getSurName() + "-" + emp.getForName() ;
				nameBx.addItem(name);
			}
		}
		catch (ParametrizedException e)
		{
			JOptionPane.showMessageDialog(this,e.getMessage());
		}
	}
	
	public void populateEmployeeNoCombo()
	{
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
    clearEmpNoBx();
		if(!nameBx.getSelectedItem().equals("Please Select"))
		{
			String fullName= (String)nameBx.getSelectedItem();
			int index = fullName.indexOf("-");
			String sn= fullName.substring(0,index);
			String fn =fullName.substring(index+1);
			//System.out.println("Surname "+ sn);
			//System.out.println("FisrtName " +fn);	
			Vector empVector = personnel.findEmployeeByFullName(fn,sn);
			Iterator iter = empVector.iterator();
			
			while (iter.hasNext())
			{
				Employee emp = (Employee)iter.next();
				//System.out.println("Name is; "+emp.getSurName()+ " "+emp.getForName());
				int id =emp.getEmployeeId();
				empNoBx.addItem(String.valueOf(id));
			}
			
		}
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	public void displayRiasEmployeeNo()
	{
		if(!empNoBx.getSelectedItem().equals("Please Select"))
		{
			String ns = (String)empNoBx.getSelectedItem();
			managerEmpNo =Integer.parseInt(ns) ;
			Employee emp = personnel.findEmployeeByEmpId(managerEmpNo);
			//System.out.println("RIAS empNo is; "+emp.getRiasEmpNo());
			refEmpNo=managerEmpNo;
			riasEmpNoFld.setText(emp.getRiasEmpNo());
		}
	}
	
	public void assignManager()
	{
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    MaintainSites maintainSites = new MaintainSites();
		maintainSites.asignSiteManager(siteId,refEmpNo);
		Site site= maintainSites.getSite(siteId);
		Employee emp = personnel.findEmployeeByEmpId(refEmpNo);
		JOptionPane.showMessageDialog(this,emp.getForName()+ " " + emp.getSurName()+ " was assigned as site manager for " + site.getSiteName());
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	
	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("Assign"))
       {
              assignManager();
        }
    		else if(e.getActionCommand().equals("Close"))
        {
    			setVisible(false);
    		}
		}
	}
	public class ComboListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(nameBx.getSelectedItem().equals("Please Select"))
            {
                
            }
    		else if(nameBx.getSelectedItem().equals(nameBx.getSelectedItem()))
        {
    			populateEmployeeNoCombo();
    		}
  		
		}
	}
	
	public class ComboListener1 implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{	
					if(empNoBx.getSelectedItem()==null)
					throw new ParametrizedException("");
					if(empNoBx.getSelectedItem().equals("Please Select"))
		      {
						assignBtn.setEnabled(false);
		      }
		    	else if(empNoBx.getSelectedItem().equals(empNoBx.getSelectedItem()))
		      {
		    			displayRiasEmployeeNo();
		    			assignBtn.setEnabled(true);
		    	}
					
					
			}
			catch(ParametrizedException e1)
			{
    			
			}
		}
	}
	

}
