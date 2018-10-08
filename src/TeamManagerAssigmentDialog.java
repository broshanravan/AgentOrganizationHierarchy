


import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;



/*
 * Created on 23-Mar-2005
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
public class TeamManagerAssigmentDialog extends JDialog
{
	private static final int hight = 200;
	private static final int width =450;
	private int teamId;
        private Team team;
        private int managerId=0;
	private int managerEmpNo;
	int refEmpNo =0;
	int groupId ;
	private Vector employeeVec ;
        private Vector managersVec;
	TeamMaintenance teamMaintenance = new TeamMaintenance();
	ButtonListener buttonListener = new ButtonListener();
	
	ComboListener comboListener = new ComboListener();
	ComboListener1 comboListener1 = new ComboListener1();
	//ComboListener2 comboListener2 = new ComboListener2();
  
	ManagerMaintenance managerMaintenance = new ManagerMaintenance();
  
	Personnel personnel = new Personnel();
	MaintainSites maintainSites = new MaintainSites();
	GroupInventory groupInventory = new GroupInventory();
	MaintainDepartment maintainDepartment = new MaintainDepartment();
	
	JLabel nameLbl = new JLabel("Employee Name");
	JLabel empNoLbl = new JLabel("Employee Number");
	JLabel riasEmpNoLbl = new JLabel("RIAS EmployeeNymber");
        JLabel managerLbl = new JLabel("Current Managers");
	
	JButton assignBtn = new JButton("Assign");
	JButton closeBtn = new JButton("Close");
        //JButton updateBtn = new JButton("Update");
	
	JTextField riasEmpNoFld = new JTextField();
  
        JTextField mgrsFld = new JTextField();
	
	
	JComboBox nameBx = new JComboBox();
	JComboBox empNoBx = new JComboBox();
	//JComboBox mgrsBx = new JComboBox();
	MainScreen ms;
        GroupSubTree groupTree;
        public void clearEmpNoBx()
	{
		empNoBx.removeAllItems();
		empNoBx.addItem("Please Select");
	}
	
	
	public TeamManagerAssigmentDialog(MainScreen msIn,int teamIdIn,GroupSubTree groupTreeIn)
	{
		
		super (msIn,true);
                ms=msIn;
		setTitle("Team Manager Assignment Dialog");
		teamId = teamIdIn;
                team = teamMaintenance.getTeam(teamId);
		groupId = teamMaintenance.getGroupId(teamId);
                //Multiple mngrs
                 //managersVec=managerMaintenance.getAllManagers(team);
                
                //single mngr
                managerId = team.getManagerId();
    
		//System.out.println("TeamId for this team is: "+ teamId);
		//System.out.println("Group Id for this team is: "+ groupId);
		
		
		setSize(width,hight);
		setResizable(false);
		setLayout(null);
		getAllGroupEmployees();
		setupScreen();
                groupTree=groupTreeIn;
                setLocationRelativeTo(ms);
                //Multiple mngrs
		//Vector employeeVec = personnel.getAllGroupEmployees(groupId);
		if(!employeeVec.isEmpty())
		{
			setVisible(true);
		}
	}
	
	public void getAllGroupEmployees()
	{
		
            Group group = groupInventory.getGroup(groupId);
            int depId=group.getDepartmentId();
            Department dep = maintainDepartment.getDepartment(depId);
            int siteId = dep.getDepartmentSiteId();
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
    
                //multiple mgrs
                //getContentPane().add(mgrsBx);  
                            getContentPane().add(riasEmpNoFld);
                            
                //Single mgrs
                getContentPane().add(mgrsFld);
                mgrsFld.setBackground(Color.WHITE);
                
                //multiple mgrs
                //mgrsBx.setBackground(Color.WHITE);
                empNoBx.setBackground(Color.WHITE);
                nameBx.setBackground(Color.WHITE);
		
		getContentPane().add(assignBtn); 
		getContentPane().add(closeBtn);
                //Multiple mngrs
                //getContentPane().add(updateBtn);
                            
                managerLbl.setBounds(10,10,150,20);
                
                mgrsFld.setBounds(180,10,250,20);
                //Multiple mngrs
                //mgrsBx.setBounds(180,10,250,20);

		nameLbl.setBounds(10,50,150,20);
		empNoLbl.setBounds(10,80,150,20);
		riasEmpNoLbl.setBounds(10,110,150,20);
		
		nameBx.setBounds(180,50,250,20);
		empNoBx.setBounds(180,80,150,20);
		riasEmpNoFld.setBounds(180,110,150,20);
		
		assignBtn.setBounds(10,140,100,25); 
                //Multiple mngrs
                //updateBtn.setBounds(130,140,100,25); 
		closeBtn.setBounds(280,140,100,25);
		
		assignBtn.addActionListener(buttonListener);
		closeBtn.addActionListener(buttonListener);
		//Multiple mngrs
                //updateBtn.addActionListener(buttonListener);
    
		nameBx.addItem("Please Select");
		empNoBx.addItem("Please Select");
		
                //Multiple mngrs
                //updateBtn.setEnabled(false);
    
                nameBx.addActionListener(comboListener);
		empNoBx.addActionListener(comboListener1);
                //MultipleManagers
                //empNoBx.addActionListener(comboListener2);
		
		assignBtn.setEnabled(false);
		populateEmployeeCombo();
    
		//MultipleManagers
                //populateManagerCombo();
                
                //sinlge Manager
                showSingleManager();
    
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
           
           //MultipleManagers
           //assignManager();
           
           //sinlgeManager
           assignSingleManager();
           
  }

	public void populateEmployeeCombo()
	{
		
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		//Vector employeeVec = personnel.getAllGroupEmployees(groupId);
		try
		{
			if(employeeVec.isEmpty())
				throw new ParametrizedException("No Employees assigned to this Group yet");
			Iterator iter = employeeVec.iterator();
			while(iter.hasNext())
			{
				Employee emp = (Employee)iter.next(); 
				String name =emp.getForName()+ "-" + emp.getSurName()  ;
				nameBx.addItem(name);
			}
		}
		catch (ParametrizedException e)
		{
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
      JOptionPane.showMessageDialog(this,e.getMessage());
		}
    this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	public void populateEmployeeNoCombo()
	{
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		clearEmpNoBx();
		if(!nameBx.getSelectedItem().equals("Please Select"))
		{
			String fullName= (String)nameBx.getSelectedItem();
			int index = fullName.indexOf("-");
			String fn= fullName.substring(0,index);
			String sn =fullName.substring(index+1);
			Vector empVector = personnel.findEmployeeByFullName(fn.trim(),sn.trim());  
                        Iterator iter = empVector.iterator();
			
			while (iter.hasNext()){
				Employee emp = (Employee)iter.next();
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
	//Multiple Managers
	/*
  public void assignManager()
	{

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        Department department= maintainDepartment.getDepartment(teamId);
        Employee emp = personnel.findEmployeeByEmpId(refEmpNo);
        
        Manager manager= new Manager();
        manager.setEmployeeId(refEmpNo);
        manager.setIdentifier("T");
        manager.setT_G_DKey(teamId);
        
        managerMaintenance.assignManager(manager);
        managersVec=managerMaintenance.getAllManagers(team);
        populateManagerCombo();
        JOptionPane.showMessageDialog(this,emp.getForName()+ " " + emp.getSurName()+ " was assigned as Departmrent Manager for " + team.getTeamName());	
        
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    
	}
  */
  public void assignSingleManager()
	{

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        
        Department department= maintainDepartment.getDepartment(teamId);
        Employee emp = personnel.findEmployeeByEmpId(refEmpNo);
        teamMaintenance.assignTeamManager(team.getTeamId(),emp.getEmployeeId());
        
        JOptionPane.showMessageDialog(this,emp.getForName()+ " " + emp.getSurName()+ " was assigned as Team Manager for " + team.getTeamName());	
        showSingleManager();
        ms.refreshGroupNode(groupTree);        
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    
	}
  
	/*
          public void updateManager()
          {
            //System.out.println("in update");
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
               
                        Team team = teamMaintenance.getTeam(teamId);
                        Employee emp = personnel.findEmployeeByEmpId(refEmpNo);  
                        Manager newManager= new Manager();
                        newManager.setEmployeeId(refEmpNo);
                        newManager.setIdentifier("T");
                        newManager.setT_G_DKey(teamId); 
                        managerMaintenance.updateManager(oldManager,newManager);
                        populateManagerCombo();
                        managersVec=managerMaintenance.getAllManagers(team);
                        JOptionPane.showMessageDialog(this,emp.getForName()+ " " + emp.getSurName()+ " was assigned as Team Manager for " + team.getTeamName()+ " Replacing "+" "+fn+" " +sn);	
                }
                catch(ParametrizedException ex)
                {
                    //System.out.println("Exception Thrown");
                    JOptionPane.showMessageDialog(this,ex.getMessage());
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
             this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
          }
	*/
	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("Assign"))
      {
				//Multiple Managers
        //assignManager();
        assignSingleManager();
      }
    	else if(e.getActionCommand().equals("Close"))
      {
    			setVisible(false);
    	}
      else if(e.getActionCommand().equals("Update"))
      {  			
          //MultipleManagers
          //updateManager();
    	}
		}
	}
  
  /*
  public void populateManagerCombo()
  {
    mgrsBx.removeAllItems();
    Vector managersVec = managerMaintenance.getAllManagers(team);
    if(managersVec.isEmpty())
    {
      mgrsBx.setForeground(Color.RED);
      mgrsBx.addItem("No Current Managers");
    }
    else
    {
      mgrsBx.setForeground(Color.BLACK);
      mgrsBx.addItem("Current Team  Managers");
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
  }*/
  
  public void showSingleManager()
  {
        int managerId=team.getManagerId();
        Employee emp = personnel.findEmployeeByEmpId(managerId);
        
        if(emp.getEmployeeId()==0)
        {
          mgrsFld.setForeground(Color.RED);
          mgrsFld.setText("No Current Manager");
        }
        else
        {
          String mrgName = emp.getForName() +" " +emp.getSurName();  
          mgrsFld.setForeground(Color.BLACK);
          mgrsFld.setText(mrgName);
        }

  }
    
	public class ComboListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e){
                     
                     String sel= (String)nameBx.getSelectedItem();
                     if(sel.trim().equalsIgnoreCase("Please Select"))
                      {
                          System.out.println("IN IF TEAM Manager combo");       
                      }else {
                           System.out.println("IN ELSE TEAM Manager combo");
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
  //MultipleManagers
  /*
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
    }*/
}
