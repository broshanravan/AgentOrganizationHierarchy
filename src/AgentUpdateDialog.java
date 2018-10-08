

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;



/*
 * Created on 03-Mar-2005
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
public class AgentUpdateDialog extends JDialog
{
	Agent addedAgent;
	Agent agent;
	Team team;
	MaintainApplication maintainApplication = new MaintainApplication ();
  RoleInventory roleInventory = new RoleInventory(); 
	Vector rolesVec = roleInventory.getAllRoles();
	ComboListener comboListener = new ComboListener();
	int agentId;
	private String originalUn;
	private int height =190;
	private int width=600;
	Personnel personnel = new Personnel();
//	labels
	JLabel nameLbl = new JLabel("Name");
	JLabel empNoLbl = new JLabel("Employee Number");
	JLabel userNameLbl = new JLabel("User Name");
	JLabel passWrdLbl = new JLabel("Enter Password");
	JLabel confPwLbl = new JLabel("Confirm Password");
	JLabel applicationLbl = new JLabel("Application");
	JLabel roleLbl = new JLabel("Role");
	JLabel teamLbl = new JLabel("Team");
	
	//textFields
	JTextField nameFld = new JTextField();
	JTextField empNoFld = new JTextField();
	JTextField userNameFld = new JTextField();
	
	//Combo Box
	JComboBox combo1 = new JComboBox();
	JComboBox applicationCombo = new JComboBox();
	JTextField teamFld= new JTextField(); 
	
	//Buttons
	JButton editBtn = new JButton("Update");
	JButton exitBtn = new JButton("Close");
	ButtonListener buttonListener= new ButtonListener();
	
	public Agent getAddedAgent()
	{
		return addedAgent;
	}
	
	public void clearFields()
	{
		combo1.setSelectedItem("Please Select");
    applicationCombo.setSelectedItem("Please Select");
		nameFld.setText(""); 
		empNoFld.setText("");
		userNameFld.setText("");
	}
	public Employee findEmployee(String riasEmpNo)
	{
		Employee emp = personnel.findEmployeeByRiasEmpNo(riasEmpNo);
		String n = emp.getForName()+" "+ emp.getSurName();	
		nameFld.setText(n);
		
		return emp;
	}
	
	
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

	
	public AgentUpdateDialog(int agentIdIn, MainScreen ms)
	{
		super(ms);
		agentId = agentIdIn;
		setSize(width,height);
		setTitle("User Maintenance Screen");
		getContentPane().setLayout(null);
                setLocationRelativeTo(ms);
		setVisible(true);
		
		setUpScreen();
		findAgent(agentIdIn);
	}
	public void findAgent(int agentId)
	{
		
			
		agent = personnel.getAgentById(agentId);
		int empId = agent.getEmpId();
		Employee emp = personnel.findEmployeeByEmpId(empId);
		String n = emp.getForName()+" "+ emp.getSurName();	
		nameFld.setText(n);
		empNoFld.setText(emp.getRiasEmpNo());
		originalUn=agent.getUserName();
		userNameFld.setText(agent.getUserName()); 
		//System.out.println("Agent appRole Id is; "+ agent.getApplicationRoleId());
		int appRoleId=agent.getApplicationRoleId();
		ApplicationRoleMaintenance appRolMaintenance = new ApplicationRoleMaintenance();
		AplicationRole appRol = appRolMaintenance.getApplicationRole(appRoleId);
		//System.out.println("first DB query");
		int appId = appRol.getApplicationId();
    if(appId==0)
    {
      applicationCombo.setSelectedItem("Please Select");
    }
    else
    {
      MaintainApplication maintainApplication = new MaintainApplication();
      Application app =maintainApplication.getApplication(appId);
      String appName = app.getApplicationDesc();
      //System.out.println("second DB query");
      //System.out.println("Application name is; "+appName);
      applicationCombo.setSelectedItem(appName);
    }
		int roleId = personnel.getAgentRoleId(agent);
		//System.out.println("Role Id is "+ roleId);
    if(roleId ==0)
    {
      combo1.setSelectedItem("Please Select");
    }
    else
    {
        //System.out.println("Agent Role Id is; "+ roleId);
        RoleInventory roleInventory = new RoleInventory();
        Role role = roleInventory.getRole(roleId);
        String roleName = role.getRoleName();
        //System.out.println("Agent Role is; "+ roleName);
        combo1.setSelectedItem(roleName); 
    }
		MaintainAgentTeamHistory maintainAgentTeamHistory = new MaintainAgentTeamHistory();
		team =maintainAgentTeamHistory.getAgentsCurrentTeam(agent);
		String teamName = team.getTeamName();	
		teamFld.setText(teamName);
				
		
	}
	
	private void setUpScreen()
	{
		//adding the combo box
		getContentPane().add(combo1);
		getContentPane().add(teamFld);
		//combo1.setBackground(white)
		
		//adding combo items
		combo1.addItem("Please Select");
		
	    
		//AddingButtons

		getContentPane().add(editBtn); 
		
		getContentPane().add(exitBtn );
		
		//Adding Lables
		getContentPane().add(nameLbl); 
		getContentPane().add(empNoLbl);
		getContentPane().add(userNameLbl); 
		getContentPane().add(passWrdLbl); 
		getContentPane().add(confPwLbl);
		getContentPane().add(roleLbl);
		getContentPane().add(teamLbl);
		
		
		getContentPane().add(applicationLbl);
		getContentPane().add(applicationCombo);
		applicationCombo.addActionListener(comboListener);
		//Adding Fields
		getContentPane().add(nameFld); 
		getContentPane().add(empNoFld);
		getContentPane().add(userNameFld);
		
		nameFld.setEditable(false);
		
    //Adding Button Listeners
		exitBtn.addActionListener(buttonListener);
		editBtn.addActionListener(buttonListener);
		
    //seting bounds		
		
		empNoLbl.setBounds(30,30,150,20);
		empNoFld.setBounds(150,30,150,20);
                
                nameLbl.setBounds(340,30,100,20);
                nameFld.setBounds(410,30,150,20);
		
		applicationLbl.setBounds(30,60,100,20);;
		applicationCombo.setBounds(150,60,150,20);
		
		roleLbl.setBounds(340,60,100,20);
		combo1.setBounds(410,60,150,20);
		
		teamLbl.setBounds(30,90,100,20);
		teamFld.setBounds(150,90,150,20);
		teamFld.setEditable(false);
		
                userNameLbl.setBounds(340,90,100,20);
		userNameFld.setBounds(410,90,150,20);
		
                editBtn.setBounds(30,120,100,25); 
		exitBtn.setBounds(430,120,100,25) ;	
		populateApplications();
		
		
	}
	TeamMaintenance teamMaintenance = new TeamMaintenance();

	public void populateRoles()
	{
		
		combo1.removeAllItems();
		combo1.addItem("Please Select");
		String appName = (String)applicationCombo.getSelectedItem();
		if (appName.equalsIgnoreCase("Please Select"))
		{
			combo1.setSelectedItem("Please Select");
		}
		else
    {
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
              }
                
              
              Iterator iter1 = rolesVec.iterator();
              while (iter1.hasNext())
              {
                Role role = (Role)iter1.next();
                String rn = role.getRoleName();
                combo1.addItem(rn);
                
              }
    }
		
	}
	public void upDateUser()
	{
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                String appName1 = (String) applicationCombo.getSelectedItem();
                Application app = maintainApplication.getApplicationByName(appName1); 
                Vector unVec = personnel.getAppsAllUserNamesInUse(app);
                try
		{
			//Employee emp = personnel.findEmployeeByRiasEmpNo(riasEmpNo);
			
			Agent agent = new Agent();
                        agent.setId(agentId);
			String un=userNameFld.getText();
			//agent.setEmpId(emp.getEmployeeId());
			////System.out.println("EmpId is" + emp.getEmployeeId() );
			
			if(combo1.getSelectedItem().equals("Please Select"))
				throw new ParametrizedException("Please Select role");
			if(!originalUn.equals(un)& unVec.contains(un))
				throw new ParametrizedException("User name in use. Please try agein ");
			RoleInventory roleInventory = new RoleInventory();
			//System.out.println("Role name input is "+(String)combo1.getSelectedItem());
			
			Role role1 = roleInventory.getRoleByName((String)combo1.getSelectedItem());	
			
			//System.out.println("Role name is "+role1.getRoleName());
			//System.out.println("Role Id inside is "+ role1.getRoleId());
			
			ApplicationRoleMaintenance  appRolMain = new ApplicationRoleMaintenance();
			String appName = (String) applicationCombo.getSelectedItem();
      
			AplicationRole applicationRole =appRolMain.getApplicationRole(appName,role1.getRoleName());

			//System.out.println("appRole No is "+ applicationRole.getApplicationRoleId());
			
			agent.setApplicationRoleId(applicationRole.getApplicationRoleId());			
			
			//agent.setEmpId(emp.getEmployeeId());			
			agent.setUserName(userNameFld.getText().trim());	
			agent.setApplicationRoleId(applicationRole.getApplicationRoleId());
			//agent.setRiasEmployeeNumber(riasEmpNo);
				
			//System.out.println("pw " + String.valueOf( passWrdFld.getPassword()));
                        //System.out.println("un " + userNameFld.getText() );
			
			//System.out.println("Starting to Store");
			
			personnel.editAgent(agent);
			//System.out.println("Agent Stored");
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			JOptionPane.showMessageDialog(this,"Details of "+ nameFld.getText()+ " is updated" ) ;
			clearFields();
		}
		catch(ParametrizedException e)
		{
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
      JOptionPane.showMessageDialog(this,e.getMessage());
		}
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


    		if(e.getActionCommand().equals("Update"))
            {
              upDateUser();
            }

    	}
	}
}
