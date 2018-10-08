
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;



/*
 * Created on 22-Mar-2005
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
public class GroupManagerAssignmentDialog extends JDialog
{
	private static final int hight = 200;
	private static final int width =450;
	private int groupId;
	private int managerEmpNo;
	int refEmpNo =0;
	public Vector employeeVec ;
        private Vector managersVec;
	Group group;
	ButtonListener buttonListener = new ButtonListener();
	MainScreen ms;
	ComboListener comboListener = new ComboListener();
	ComboListener1 comboListener1 = new ComboListener1();
        ComboListener2 comboListener2 = new ComboListener2();
	
	
	Personnel personnel = new Personnel();
	MaintainSites maintainSites = new MaintainSites();
	GroupInventory groupInventory = new GroupInventory();
	MaintainDepartment maintainDepartment = new MaintainDepartment();
	
        ManagerMaintenance managerMaintenance = new ManagerMaintenance();
  
	JLabel nameLbl = new JLabel("Employee Name");
	JLabel empNoLbl = new JLabel("Employee Number");
	JLabel riasEmpNoLbl = new JLabel("RIAS EmployeeNymber");
        JLabel managerLbl = new JLabel("Current Managers");
	
	JButton assignBtn = new JButton("Assign");
	JButton closeBtn = new JButton("Close");
        JButton updateBtn = new JButton("Update");
	
	JTextField riasEmpNoFld = new JTextField();
	
	
	JComboBox nameBx = new JComboBox();
	JComboBox empNoBx = new JComboBox();
        JComboBox mgrsBx = new JComboBox();
	
	public void clearEmpNoBx()
	{
		empNoBx.removeAllItems();
		empNoBx.addItem("Please Select");
	}
	
	
	public GroupManagerAssignmentDialog(MainScreen msIn,int groupIdIn)
	{
		
		super (msIn,true);
                ms= msIn;
		setTitle("Group Manager Assignment Dialog");
		groupId = groupIdIn;
                group=groupInventory.getGroup(groupId);
                managersVec=managerMaintenance.getAllManagers(group);
		setSize(width,hight);
		setResizable(false);
		setLayout(null);
		getAllGroupEmployees();
		setupScreen();
		//Vector employeeVec = personnel.getAllGroupEmployees(groupId);
		if(!employeeVec.isEmpty())
		{
                        setLocationRelativeTo(ms);
                        setVisible(true);
		}
                
	}
	
	public void getAllGroupEmployees()
	{
		Personnel personnel = new Personnel();
		employeeVec = personnel.getAllGroupEmployees(groupId);
		
	}
		
	private void setupScreen()
	{
		
		getContentPane().add(nameLbl);
		getContentPane().add(empNoLbl);
		getContentPane().add(riasEmpNoLbl);
		getContentPane().add(managerLbl);	
		
		getContentPane().add(nameBx);
		getContentPane().add(empNoBx);
                getContentPane().add(mgrsBx);
		getContentPane().add(riasEmpNoFld);
		
		
		getContentPane().add(assignBtn); 
		getContentPane().add(closeBtn);
                getContentPane().add(updateBtn);
                            
                managerLbl.setBounds(10,10,150,20);
                mgrsBx.setBounds(180,10,250,20);
                
                mgrsBx.setBackground(Color.WHITE);
                empNoBx.setBackground(Color.WHITE);
                nameBx.setBackground(Color.WHITE);
    
		nameLbl.setBounds(10,50,150,20);
		empNoLbl.setBounds(10,80,150,20);
		riasEmpNoLbl.setBounds(10,110,150,20);
		
		nameBx.setBounds(180,50,250,20);
		empNoBx.setBounds(180,80,150,20);
		riasEmpNoFld.setBounds(180,110,150,20);
		
		assignBtn.setBounds(10,140,100,25); 
                updateBtn.setBounds(130,140,100,25); 
		closeBtn.setBounds(280,140,100,25);
		
		assignBtn.addActionListener(buttonListener);
		closeBtn.addActionListener(buttonListener);
		updateBtn.addActionListener(buttonListener);
    
		nameBx.addItem("Please Select");
		empNoBx.addItem("Please Select");
		nameBx.addActionListener(comboListener);
		empNoBx.addActionListener(comboListener1);
                empNoBx.addActionListener(comboListener2);
                            
                updateBtn.setEnabled(false);
    
		assignBtn.setEnabled(false);
		populateEmployeeCombo();
		populateManagerCombo();
    
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
		Group group = groupInventory.getGroup(groupId);
                int depId=group.getDepartmentId();
                Department dep = maintainDepartment.getDepartment(depId);
                int siteId = dep.getDepartmentSiteId();
		Personnel personnel = new Personnel();
		//employeeVec = personnel.getAllSiteEmployees(siteId);
		Vector employeeVec = personnel.getAllGroupEmployees(groupId);
		try
		{
			if(employeeVec.isEmpty())
				throw new ParametrizedException("No Employees assigned to this Group yet");
			Iterator iter = employeeVec.iterator();
			while(iter.hasNext())
			{
				Employee emp = (Employee)iter.next(); 
				String name = emp.getForName() + "-" + emp.getSurName()  ;
				nameBx.addItem(name);
			}
		}
		catch (ParametrizedException e)
		{
			JOptionPane.showMessageDialog(this,e.getMessage());
			setVisible(false);
		}
	}
	
	public void populateEmployeeNoCombo()
	{
		
		clearEmpNoBx();
		if(!nameBx.getSelectedItem().equals("Please Select"))
		{
			String fullName= (String)nameBx.getSelectedItem();
			int index = fullName.indexOf("-");
			String fn= fullName.substring(0,index);
			String sn =fullName.substring(index+1);
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
            
            Group group= groupInventory.getGroup(groupId);
            Employee emp = personnel.findEmployeeByEmpId(refEmpNo);
            
            Manager manager= new Manager();
            manager.setEmployeeId(refEmpNo);
            manager.setIdentifier("G");
            manager.setT_G_DKey(groupId);
            
            managerMaintenance.assignManager(manager);
            populateManagerCombo();
            JOptionPane.showMessageDialog(this,emp.getForName()+ " " + emp.getSurName()+ " was assigned as Group Manager for " + group.getGroupName());	
            managersVec=managerMaintenance.getAllManagers(group);
            ms.refreshDepartmentNode(group.getDepartmentId());
            this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    
	}
	
  public void updateManager()
  {
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        try
        {
                Manager oldManager=null;
                if(mgrsBx.getSelectedItem().equals("Please Select")) 
                    throw new ParametrizedException("Please select the manager you want to update");  
                if(managersVec.isEmpty()) 
                    throw new ParametrizedException("No managers to update. Please enter a new manager");  
                   
                String fullName= (String)mgrsBx.getSelectedItem();
                int index = fullName.indexOf("-");
                String sn= fullName.substring(0,index);
                String fn =fullName.substring(index+1);
                //System.out.println("Surname "+ sn);
                //System.out.println("FisrtName " +fn);	
                                
               Iterator iter= managersVec.iterator();
               while(iter.hasNext())
               {
                 Manager manager = (Manager)iter.next();
                 int empId = manager.getEmployeeId();
                 Employee e = personnel.findEmployeeByEmpId(empId);
                 if((e.getForName().equalsIgnoreCase(fn))&&(e.getSurName().equalsIgnoreCase(sn)))
                 {
                   oldManager=manager;
                 }
               }

                Group group =groupInventory.getGroup(groupId);
                Employee emp = personnel.findEmployeeByEmpId(refEmpNo);
                
                
                Manager newManager= new Manager();
                newManager.setEmployeeId(refEmpNo);
                newManager.setIdentifier("G");
                newManager.setT_G_DKey(groupId);
                
                managerMaintenance.updateManager(oldManager,newManager);
                populateManagerCombo();
                managersVec=managerMaintenance.getAllManagers(group);
                JOptionPane.showMessageDialog(this,emp.getForName()+ " " + emp.getSurName()+ " was assigned as Group Manager for " + group.getGroupName()+ " Replacing "+" "+fn+" " +sn);	
        }
        catch(ParametrizedException ex)
        {
            //System.out.println("Exception Thrown");
            JOptionPane.showMessageDialog(this,ex.getMessage());
            this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
     this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
  }
	
  public void populateManagerCombo()
  {
    mgrsBx.removeAllItems();
    Vector managersVec = managerMaintenance.getAllManagers(group);
    if(managersVec.isEmpty())
    {
      mgrsBx.setForeground(Color.RED);
      mgrsBx.addItem("No Current Managers");
    }
    else
    {
      mgrsBx.setForeground(Color.BLACK);
      mgrsBx.addItem("Current Group  Managers");
      Iterator iter = managersVec.iterator();
      while(iter.hasNext())
      {
        Manager m = (Manager)iter.next();
        int empId = m.getEmployeeId();
        Employee e = personnel.findEmployeeByEmpId(empId);
        String name = e.getSurName() + "-" + e.getForName() ;
				mgrsBx.addItem(name);
        
      }
      
    }
    
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
      
      else if(e.getActionCommand().equals("Update"))
      {
    			updateManager();
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
      
      public class ComboListener2 implements ActionListener
      {
        public void actionPerformed(ActionEvent e)
        {
                try
                {	
                    if(empNoBx.getSelectedItem()==null)
                    throw new ParametrizedException("");
                    if(mgrsBx.getSelectedItem().equals("Please Select"))
                    {
                      updateBtn.setEnabled(false);
                    }
                    else if(mgrsBx.getSelectedItem().equals("No Current Managers"))
                    {
                      updateBtn.setEnabled(false);
                    }
                    else if(mgrsBx.getSelectedItem().equals(mgrsBx.getSelectedItem()))
                    {
                        updateBtn.setEnabled(true);
                    }
                    
                    
                }
                catch(ParametrizedException e1)
                {
                    
                }
        }
      }
	
}
