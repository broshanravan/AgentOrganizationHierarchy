import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTree;

/*
 * Created on 21-Feb-2005
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
public class UserMaintenanceScreen extends JDialog
{
	
	Agent addedAgent;
	ApplicationRoleMaintenance appRoleMaint = new ApplicationRoleMaintenance();
        MaintainApplication appMaint = new MaintainApplication();
        RoleInventory roleInventory = new RoleInventory(); 
	Vector rolesVec = roleInventory.getAllRoles();
	ComboListener comboListener = new ComboListener();
        TeamMaintenance teamMaintenance = new TeamMaintenance();
        Personnel personnel = new Personnel();
	int teamId;
	private String originalUn;
	private int height =290;
	private int width=550;
//	labels
	JLabel nameLbl = new JLabel("Name");
	JLabel empNoLbl = new JLabel("Employee Number");
	JLabel userNameLbl = new JLabel("User Name");
	//JLabel passWrdLbl = new JLabel("Enter Password");
	//JLabel confPwLbl = new JLabel("Confirm Password");
	JLabel applicationLbl = new JLabel("Application");
	JLabel roleLbl = new JLabel("Role");
	JLabel teamLbl = new JLabel("Team");
	
	//textFields
	JTextField nameFld = new JTextField();
	JTextField empNoFld = new JTextField();
	JTextField userNameFld = new JTextField();
	
	//Pasword Fields
	//JPasswordField passWrdFld = new JPasswordField();
	//JPasswordField confPwFld = new JPasswordField();
	
	//Combo Box
	JComboBox combo1 = new JComboBox();
	JComboBox applicationCombo = new JComboBox();
	JTextField teamFld= new JTextField(); 
	
	//Buttons
	JButton editBtn = new JButton("Edit");
	JButton addBtn = new JButton("Add");
	JButton deletBtn = new JButton("Delete");
	JButton findEmpBtn = new JButton("Find Employee");
	JButton findAgentBtn = new JButton("Find Agent");
	JButton exitBtn = new JButton("Close");
	ButtonListener buttonListener= new ButtonListener();
        MainScreen ms;
        JTree mainTree;
        TeamSubTree teamSubTree;
	
        public Agent getAddedAgent()
	{
		return addedAgent;
	}
	
	public void clearFields()
	{
		combo1.setSelectedItem("Please Select");
		//passWrdFld.setText("");
		//confPwFld.setText("");
		nameFld.setText(""); 
		empNoFld.setText("");
		userNameFld.setText("");
	}
	public Employee findEmployee(String riasEmpNo)
	{
		Employee emp = personnel.findActiveEmployeeByRiasEmpNo(riasEmpNo);
		String n = emp.getForName()+" "+ emp.getSurName();	
		nameFld.setText(n);
		
		addBtn.setEnabled(true);
		return emp;
	}
	//Vector unVec = personnel.getAllUserName();
	
	public void populateApplications()
	{
		
		applicationCombo.addItem("Please Select");
		MaintainApplication maintainApplication = new MaintainApplication();
		Vector applicationsVec = maintainApplication.getAllApplications(); 
		Iterator iter = applicationsVec.iterator();
		{
			while (iter.hasNext())
			{
				Application application = (Application)iter.next();
				String appName = application.getApplicationDesc();
				applicationCombo.addItem(appName);
			}
		}
	}
	public Agent addUser(String riasEmpNo)
	{
		Agent agent = new Agent();
		try
		{
			Employee emp = personnel.findEmployeeByRiasEmpNo(riasEmpNo);

			//System.out.println("EmpId is" + emp.getEmployeeId() );
			//String password1 = String.valueOf(passWrdFld.getPassword());
			//String password2 = String.valueOf(confPwFld.getPassword());
                        //password1=password1.trim();
                        //password2=password2.trim();   
			String un = userNameFld.getText().trim();
			
			//if (!password1.equals(password2))
			//	throw new ParametrizedException("Passwords Do Not Match, Please try agein");
			
			if(combo1.getSelectedItem().equals("Please Select"))
				throw new ParametrizedException("Please Select role");

			RoleInventory roleInventory = new RoleInventory();
			//System.out.println("Role name input is "+(String)combo1.getSelectedItem());
			
			Role role1 = roleInventory.getRoleByName((String)combo1.getSelectedItem());	
			
			//System.out.println("Role name is "+role1.getRoleName());
			//System.out.println("Role Id inside is "+ role1.getRoleId());
			
			ApplicationRoleMaintenance  appRolMain = new ApplicationRoleMaintenance();
			
			String applName = (String)applicationCombo.getSelectedItem();
      
                        MaintainApplication maintainApplication = new MaintainApplication();
                        Application app = maintainApplication.getApplicationByName(applName);
			
                        int appId =app.getApplicationId();
                        if(!(appId==9))
                        {
                              Vector unVec = personnel.getAppsAllUserNamesInUse(app);
                              if (unVec.contains(un))
                              throw new ParametrizedException("Username in use Please try agein");
                        }
                        else
                        {
                            Vector unVec = personnel.getAllNonInductionUserNamesInUse();
                            if (unVec.contains(un))
                              throw new ParametrizedException("This Username is in use as a permanent un, Please try agein");
                            
                        }
                        AplicationRole applicationRole =appRolMain.getApplicationRole(app.getApplicationDesc(),role1.getRoleName());
			
			agent.setApplicationRoleId(applicationRole.getApplicationRoleId());			
			
			agent.setEmpId(emp.getEmployeeId());			
			agent.setUserName(userNameFld.getText());
			//agent.setPassword(String.valueOf(passWrdFld.getPassword()));	
			agent.setApplicationRoleId(applicationRole.getApplicationRoleId());
			agent.setRiasEmployeeNumber(riasEmpNo);
			personnel.storeAgent(agent);
			Agent agentOut = personnel.getAgentByUserName(agent.getUserName());
      
			Team teamOut = teamMaintenance.getTeam(teamId);	
			AgentTeam agentTeam = new AgentTeam(agentOut, teamOut);			
			MaintainAgentTeamHistory maintainEmployeeTeamHistory = new MaintainAgentTeamHistory();		
			maintainEmployeeTeamHistory.addAgentToTeam(agentTeam);		
			//unVec.addElement(un);
			addBtn.setEnabled(false);
			JOptionPane.showMessageDialog(this,nameFld.getText()+" Was Added as an " +role1.getRoleName()) ;
			clearFields();
                        ms.refreshTeamNode(teamSubTree);
			
			
		}
		catch(ParametrizedException e)
		{
			JOptionPane.showMessageDialog(this,e.getMessage());
		}
		return agent;
	}
	
	public UserMaintenanceScreen(TeamSubTree teamSubTreeIn,int teamIdIn,MainScreen msIn)
	{
		super(msIn,true);
                teamId = teamIdIn;
		setSize(width,height);
		setTitle("User Maintenance Screen");
		getContentPane().setLayout(null);
		setUpScreen();
                ms=msIn;
                teamSubTree=teamSubTreeIn;
                setLocationRelativeTo(msIn);  
		setVisible(true);
               
                
	}
	public void findAgent()
	{
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    try
		{
			if (userNameFld.getText().equals(""))
				throw new ParametrizedException("Please Enter the agents UserName");
				
				originalUn = userNameFld.getText().trim();
				Agent agent = personnel.getAgentByUserName(userNameFld.getText());
				Employee emp = personnel.findEmployeeByEmpId( agent.getEmpId());
				String n = emp.getForName()+" "+ emp.getSurName();	
				nameFld.setText(n);
				empNoFld.setText(emp.getRiasEmpNo());
				//passWrdFld.setText(agent.getPassword());
				//confPwFld.setText(agent.getPassword());
        
        int appRoleId = agent.getApplicationRoleId();
        AplicationRole appRole = appRoleMaint.getApplicationRole(appRoleId);
        int appId = appRole.getApplicationId();
        Application application = appMaint.getApplication(appId);
        String appName = application.getApplicationDesc();
        applicationCombo.setSelectedItem(appName);
        
				
				int roleId =appRole.getRoleId();
				if(roleId==0)
        {
          combo1.setSelectedItem("Please Select");
        }
        else
        {
            RoleInventory roleInventory = new RoleInventory();
            Role role = roleInventory.getRole(roleId);
            String roleName = role.getRoleName();
            combo1.setSelectedItem(roleName); 
				}
				MaintainAgentTeamHistory maintainAgentTeamHistory = new MaintainAgentTeamHistory();
				Team team =maintainAgentTeamHistory.getAgentsCurrentTeam(agent);
				String teamName = team.getTeamName();
				
				teamFld.setText(teamName);
				
		}
		catch(ParametrizedException e)
		{
			JOptionPane.showMessageDialog(this,e.getMessage());
      this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
    this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	private void setUpScreen()
	{
		//adding the combo box
		getContentPane().add(combo1);
		getContentPane().add(teamFld);
		//combo1.setBackground(white)
		teamFld.setEditable(false);
		//adding combo items
		combo1.addItem("Please Select");
		
	    
		//AddingButtons

		getContentPane().add(editBtn); 
		getContentPane().add(addBtn);
		getContentPane().add(deletBtn); 
		getContentPane().add(findEmpBtn); 
		getContentPane().add(findAgentBtn);
		getContentPane().add(exitBtn );
		
		addBtn.setEnabled(false);
		//Adding Lables
		getContentPane().add(nameLbl); 
		getContentPane().add(empNoLbl);
		getContentPane().add(userNameLbl); 
		//getContentPane().add(passWrdLbl); 
		//getContentPane().add(confPwLbl);
		getContentPane().add(roleLbl);
		getContentPane().add(teamLbl);
		
		
		getContentPane().add(applicationLbl);
		getContentPane().add(applicationCombo);
		applicationCombo.addActionListener(comboListener);
		//Adding Fields
		getContentPane().add(nameFld); 
		getContentPane().add(empNoFld);
		getContentPane().add(userNameFld);
		//getContentPane().add(passWrdFld); 
		//getContentPane().add(confPwFld); 
		
		nameFld.setEditable(false);
		//Adding Button Listeners
		exitBtn.addActionListener(buttonListener);
		findEmpBtn.addActionListener(buttonListener);
		findAgentBtn.addActionListener(buttonListener);
		addBtn.addActionListener(buttonListener);
		editBtn.addActionListener(buttonListener);
		//seting bounds
		
		nameLbl.setBounds(30,60,100,20);
		nameFld.setBounds(150,60,150,20);
		
		empNoLbl.setBounds(30,30,150,20);
		empNoFld.setBounds(150,30,150,20);
		
		applicationLbl.setBounds(30,90,100,20);;
		applicationCombo.setBounds(150,90,150,20);
		
		roleLbl.setBounds(30,120,100,20);
		combo1.setBounds(150,120,150,20);
		
		teamLbl.setBounds(30,150,100,20);
		teamFld.setBounds(150,150,150,20);
		
		userNameLbl.setBounds(30,180,100,20);
		userNameFld.setBounds(150,180,100,20);
		
		//passWrdLbl.setBounds(30,210,100,20);
		//passWrdFld.setBounds(150,210,100,20);
		
		//confPwLbl.setBounds(270,210,150,20);
		//confPwFld.setBounds(390,210,100,20);
		
		findEmpBtn.setBounds(390,60,120,25);
		findAgentBtn.setBounds(390,30,120,25);
		
		editBtn.setBounds(30,220,100,25); 
		addBtn.setBounds(150,220,100,25);;
		deletBtn.setBounds(270,220,100,25); 
		exitBtn.setBounds(390,220,100,25) ;
		Team team = teamMaintenance.getTeam(teamId);
		teamFld.setText(team.getTeamName());
		
		populateApplications();
		
		
	}
	

	public void populateRoles()
	{
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		//System.out.println("In populate Roles");
                combo1.removeAllItems();
		combo1.addItem("Please Select");
		String appName = (String)applicationCombo.getSelectedItem();
                //System.out.println("App name is"+appName);
		if (appName.equalsIgnoreCase("Please Select"))
		{
			combo1.setSelectedItem("Please Select");
		}
			
		//MaintainApplication maintainApplication = new MaintainApplication();
		//Application application =maintainApplication.getApplicationByName(appName); 
		//System.out.println("App Id is"+application.getApplicationId());
    
		ApplicationRoleMaintenance applicationRoleMaintenance= new ApplicationRoleMaintenance();
		Vector appRoleVec = applicationRoleMaintenance.getAllApplicationRoles(appName);
		Iterator iter = appRoleVec.iterator();
		
		RoleInventory roleInventory = new RoleInventory();
		Vector rolesVec = new Vector();
		while (iter.hasNext())
		{
			AplicationRole applicationRole = (AplicationRole)iter.next();
			int roleId = applicationRole.getRoleId();
			Role role = roleInventory.getRole(roleId);
			rolesVec.addElement(role);
      combo1.addItem(role.getRoleName());
		}
    this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
  }
  
  
  
	public void upDateUser(String riasEmpNo)
	{
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    try
		{
			Employee emp = personnel.findEmployeeByRiasEmpNo(riasEmpNo);
			
			Agent agent = new Agent();
			String un=userNameFld.getText().trim();
			agent.setEmpId(emp.getEmployeeId());
			//System.out.println("EmpId is" + emp.getEmployeeId() );
			//String password1 = String.valueOf(passWrdFld.getPassword());
			//String password2 = String.valueOf(confPwFld.getPassword());
		
			//if (!password1.equals(password2))
			//	throw new ParametrizedException("Passwords Do Not Match, Please try agein");
			
			if(combo1.getSelectedItem().equals("Please Select"))
				throw new ParametrizedException("Please Select role");
      
                        String appName = (String)applicationCombo.getSelectedItem();
                        
                        MaintainApplication maintainApplication = new MaintainApplication();
                        Application app = maintainApplication.getApplicationByName(appName);
                        
                        Vector unVec = personnel.getAppsAllUserNamesInUse(app);
      
			if(!originalUn.equals(un)& unVec.contains(un))
				throw new ParametrizedException("User name in use. Please try agein ");
       
        
			RoleInventory roleInventory = new RoleInventory();
			//System.out.println("Role name input is "+(String)combo1.getSelectedItem());
			
			
                        Role role1 = roleInventory.getRoleByName((String)combo1.getSelectedItem());		
			//System.out.println("Role name is "+role1.getRoleName());
			//System.out.println("Role Id inside is "+ role1.getRoleId());
			
     
      
			ApplicationRoleMaintenance  appRolMain = new ApplicationRoleMaintenance();
			
			AplicationRole applicationRole =appRolMain.getApplicationRole(appName,role1.getRoleName());
			
			
			//System.out.println("appRole No is "+ applicationRole.getApplicationRoleId());
			
			agent.setApplicationRoleId(applicationRole.getApplicationRoleId());			
			
			agent.setEmpId(emp.getEmployeeId());			
			agent.setUserName(userNameFld.getText());
			//agent.setPassword(String.valueOf(passWrdFld.getParent()));
		
			agent.setApplicationRoleId(applicationRole.getApplicationRoleId());
			agent.setRiasEmployeeNumber(riasEmpNo);
				
			//System.out.println("pw " + String.valueOf( passWrdFld.getPassword()));
                        //System.out.println("un " + userNameFld.getText() );                              
                        //agent.setPassword(String.valueOf(passWrdFld.getPassword()));
			agent.setUserName(userNameFld.getText());
			//System.out.println("Starting to Store");
			
			personnel.editAgent(agent);
			unVec.addElement(un);
			//System.out.println("Agent Stored");
			Team teamOut = teamMaintenance.getTeam(teamId);	
			//System.out.println("Team going to DB is" + teamOut.getTeamName());
			AgentTeam agenTeam = new AgentTeam(agent, teamOut);	
			
			MaintainAgentTeamHistory maintainAgentTeamHistory = new MaintainAgentTeamHistory();		
			maintainAgentTeamHistory.addAgentToTeam(agenTeam);
			
			addBtn.setEnabled(false);
			JOptionPane.showMessageDialog(this,"Details of "+ nameFld.getText()+ " is up dated" ) ;
			clearFields();
		}
		catch(ParametrizedException e)
		{
                    JOptionPane.showMessageDialog(this,e.getMessage());
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
                this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	public class ComboListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
    	{
            String cs=(String)applicationCombo.getSelectedItem();
			
            if(cs.equalsIgnoreCase("Please Select"))
            {
               
            }
            else
            {
                populateRoles() ;
            }
    	}
	}
	
	public class ButtonListener implements ActionListener
	{
    	public void actionPerformed(ActionEvent e)
    	{
    		if(e.getActionCommand().equals("Close"))
            {
               setVisible(false);
            }
    		if(e.getActionCommand().equals("Find Employee"))
            {
               findEmployee(empNoFld.getText()) ;
            }
    		
    		if(e.getActionCommand().equals("Add"))
            {
              addedAgent = addUser(empNoFld.getText()) ;
                
            }
    		if(e.getActionCommand().equals("Find Agent"))
            {
              findAgent() ;
            }
    		if(e.getActionCommand().equals("Edit"))
            {
              upDateUser(empNoFld.getText());
            }

    	}
	}
}
