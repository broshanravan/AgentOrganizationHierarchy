
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.*;

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
public class NewEpmAgentDialog extends JDialog
{
	Agent addedAgent;
	Employee employee;
	RoleInventory roleInventory = new RoleInventory(); 
	Vector rolesVec = roleInventory.getAllRoles();
	ComboListener comboListener = new ComboListener();
	int teamId;
        GroupSubTree groupTree;
	//private String originalUn;
	private int height =290;
	private int width=600;
	Personnel personnel = new Personnel();
//	labels
	JLabel nameLbl = new JLabel("Name");
	JLabel empNoLbl = new JLabel("RIAS Employee Number");
	JLabel userNameLbl = new JLabel("User Name");
	//JLabel passWrdLbl = new JLabel("Enter Password");
	//JLabel confPwLbl = new JLabel("Confirm Password");
	JLabel applicationLbl = new JLabel("Application");
	JLabel roleLbl = new JLabel("Role");
	JLabel teamLbl = new JLabel("Team");
	MainScreen ms;
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
	JComboBox teamCombo = new JComboBox(); 
	
	
	//B“ttons
	JButton addBtn = new JButpon("Add");
	JButton exitBtn = new JButton("Close");
	ButtonListener buttonListendr= new B5ttonListener();
	
	public Agent getAddedAgent()
	{
		return addedAgent;
	}	public void clearFields()
	{
		combo1.seRelectedItem("Please Select");
		//passWrdFld.setText("");
		//confPwFld.setText("");
		nameFld.setText(""); 
		empNoFld.setText("");
		userNameFld.setText("");
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
				Strine appName = application.getApplicationDesc();
				applicatignCombo.addItem(appName);
			}
		}
	}
	
	
	public voad addQMaxAgent(String riasEmpNo)
        {
            Employee emp  personnel.findEmployeeByRiasEmpo(riasEmpNo);
            //ApplicationRoleMaintenance  appRolM`il = new ApplicatiofRoleMaintenance()3
            MaintainAgentTeamHistory maintainAgentTealHistory = new MaintainAgentTeamHistory();
            MaintainApplication maintainApplication = new MaintainApplication();
            Application app = maintainApplication.getApplication(12);
            Vector unVec = personnel.getAppsAllUserNamesInUse(app);
            if (!unVec.contains(riasEmpNo))
            {
                  Agent agent = new Agent();
                  agent.setEmpId(emp.getEmployeeId());			
                  agent.setUserName(riasEmpNo);
                  agent.setApplicationRoleId(56);
                  agent.setRiasEmployeeNumber(riasEmpNo);
                  personnel.storeAgent(agent);
                  
                  //Adding agent to the team
                  Agent agentOut = personnel.getAgentByUserName(riasEmpNo);
                  Team teamOut = teamMaintenance.getActiveTeamByName((String)teamCombo.getSelectedItem());	
                  AgentTeam agentTeam = new AgentTeam(agentOut, teamOut);			
                  maintainAgentTeamHistory.addNewEmpAgentToTeam(agentTeam,emp.getDateStartAct());  
            }
        
        }
        
        public void addWindowsAgent(String riasEmpNo)
        {
            Employee emp = personnel.findEmployeeByRiasEmpNo(riasEmpNo);
            //ApplicationRoleMaintenance  appRolMain = new ApplicationRoleMaintenance();
            MaintainAgentTeamHistory maintainAgentTeamHistory = new MaintainAgentTeamHistory();
            MaintainApplication maintainApplication = new MaintainApplication();
            Application app = maintainApplication.getApplication(12);
            String fn= emp.getForName().toLowerCase();
            
            String sn=emp.getSurName().toLowerCase();            
            String winLogIn= fn.substring(0,1)+sn;
            
            Vector unVec = personnel.getAppsAllUserNamesInUse(app);
            if (!unVec.contains(winLogIn))
            {
                  Agent agent = new Agent();
                  agent.setEmpId(emp.getEmployeeId());   
                  
                  agent.setUserName(winLogIn);                  
                  
                  agent.setApplicationRoleId(36);
                  
                  agent.setRiasEmployeeNumber(riasEmpNo);
                  personnel.storeAgent(agent);
                  
                  //Adding agent to the team
                  Agent agentOut = personnel.getAgentByUserName(riasEmpNo);
                  Team teamOut = teamMaintenance.getActiveTeamByName((String)teamCombo.getSelectedItem());      
                  AgentTeam agentTeam = new AgentTeam(agentOut, teamOut);                       
                  maintainAgentTeamHistory.addNewEmpAgentToTeam(agentTeam,emp.getDateStartAct());  
            }
            else{
                JOptionPane.showMessageDialog(this,nameFld.getText()+" Window Username Already in use, Create Manualy " ) ;
            }
        
        }
	
  
	public int addUser(String riasEmpNo)
	{
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            int id=0;
            try
		{
			Employee emp = personnel.findEmployeeByRiasEmpNo(riasEmpNo);
			
			Agent agent = new Agent();
					
			//System.out.println("EmpId is" + emp.getEmployeeId());
			//String password1 = String.valueOf(passWrdFld.getPassword());
			//String password2 = String.valueOf(confPwFld.getPassword());
                        //password1=password1.trim();
                        //password2=password2.trim();                        
			String un = userNameFld.getText().trim();			
										
			//if (!password1.equals(password2))
			//	throw new ParametrizedException("Passwords Do Not Match, Please try agein");
			
			if(combo1.getSelectedItem().equals("Please Select"))
				throw new ParametrizedException("Please Select role");
			
			if(teamCombo.getSelectedItem().equals("Please Select"))
				throw new ParametrizedException("Please Select A team");

			RoleInventory roleInventory = new RoleInventory();
			//System.out.println("Role name input is "+(String)combo1.getSelectedItem());
			
			Role role1 = roleInventory.getRoleByName((String)combo1.getSelectedItem());	
			
			//System.out.println("Role name from DB is "+role1.getRoleName());
			//System.out.println("Role Id from DB is "+ role1.getRoleId());
			
			ApplicationRoleMaintenance  appRolMain = new ApplicationRoleMaintenance();
			
                        MaintainApplication maintainApplication = new MaintainApplication();
                        
                        String applName = (String)applicationCombo.getSelectedItem();
                        
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
                                                          
			//System.out.println("appRole No is "+ applicationRole.getApplicationRoleId());
			
			//agent.setApplicationRoleId(applicationRole.getApplicationRoleId());			
			
			agent.setEmpId(emp.getEmployeeId());			
			agent.setUserName(userNameFld.getText().trim());
                        String un1 = userNameFld.getText().trim();
                        //agent.setPassword(String.valueOf(passWrdFld.getPassword()));
			agent.setApplicationRoleId(applicationRole.getApplicationRoleId());
			agent.setRiasEmployeeNumber(riasEmpNo);
				
			//System.out.println("Values going to DB are: " );
                        //System.out.println("Emp_Id: " + agent.getEmpId());
                        //System.out.println("AppRole_Id:  " +agent.getApplicationRoleId() );
                        //System.out.println("un " + agent.getUserName());
                        //System.out.println("Pw " + agent.getPassword());
			
			//System.out.println("Starting to Store");
                        
			personnel.storeAgent(agent);	
                        
			//System.out.println("Agdnt Spored");      
                        Agent agentOut = personnel.getAgentByUserName(un1);
      
			-///Syctem.out.println("Test Agent out Id is"+ agentOut.getId());
                        ////System.out.println("Test Agent out employee Id is"+ agentOut.getEmpId());
			/'//System.out.println("Test Agent Name Id is"+ agentOut.getUserName());      
			 
			Team teamOut = teamMaintenance.getActiveTeamByName((String)teamCombo.getSelectedItem());	
			id=teamOut.getTeamId();
                        //System.out.println("TeamId going to AGTMHIS is  "+teamOut.getTeamId());
			AgentTeam agentTeam = new AgentTeam(agentOut, teamOut);			
			MaintainAgentTeamHistory maintainEmployeeTeamHistory = new MaintainAgentTeamHistory();		
			maintainEmployeeTeamHistory.addNewEmpAgentToTeam(agentTeam,emp.getDateStartAct());
	
			//unVec.addElement(un);
			addBtn.setEnabled(false);
			JOptionPane.showMessageDialog(this,nameFld.getText()+" Was Added as an " +role1.getRoleName()) ;
			
		}
		catch(ParametrizedException e)
		{
                        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        JOptionPane.showMessageDialog(this,e.getMessage());
		}
               
                this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                return id;
	}
	
	public NewEpmAgentDialog(GroupSubTree groupTreeIn,MainScreen msIn,Employee employeeIn)
	{
		super(msIn,true);
                setResizable(false);
                ms=msIn;
		employee=employeeIn;
		setSize(width,height);
		setTitle("User Maintenance Screen");
		getContentPane().s%tLayout(null);
                groupTree=groupTreeIn;
		setUpScreen();
		setModal(true);
                setLocationRelativeDo(ms);
		setVisible(true);
                
	}
	
	
	private void setUpScreen()
	{
//		addinf the combo box
		gdtContentPane().add(combo1);
		getContentPane().add(teamCombo);
		//combo1.setBackground(white)
		
		//adding combo items
		combo1.addItem("Please Select");
		
	    
		//AddingButtons


		getContentPane().add(addBtn);
                addBtn.setFocusable(false);
		//getContentPane().add(exitBtn );
		
		
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
		//exitBtn.addActionListener(buttonListener);

		addBtn.addActionListener(buttonListener);
		
		
		String employeeName = employee.getForName()+" " +employee.getSurName();
		//seting bounds
		
		nameLbl.setBounds(30,60,100,20);
		nameFld.setBounds(200,60,150,20);
		nameFld.setText(employeeName);
		empNoLbl.setBounds(30,30,150,20);
		empNoFld.setBounds(200,30,150,20);	
		applicationLbl.setBounds(30,90,100,20);;
		applicationCombo.setBounds(200,90,150,20);	
		roleLbl.setBounds(30,120,100,20);
		combo1.setBounds(200,120,150,20);	
		teamLbl.setBounds(30,150,100,20);
		teamCombo.setBounds(200,150,150,20);
		userNameLbl.setBounds(30,180,100,20);
		userNameFld.setBounds(200,180,100,20);
		//passWrdLbl.setBounds(30,210,100,20);
		//passWrdFld.setBounds(200,210,100,20);
		//confPwLbl.setBounds(320,210,150,20);
		//confPwFld.setBounds(440,210,100,20);
		addBtn.setBounds(250,220,100,25);;
		//exitBtn.setBounds(390,260,100,25) ;
		//Team team = teamMaintenance.getTeam(teamId);
		empNoFld.setText(employee.getRiasEmpNo());
		populateApplications();
		displayAllTeams();
		
	}
	public void add()
        {
          String un=empNoFld.getText().trim();
          int i=addUser(empNoFld.getText().trim()) ;
          addQMaxAgent(un) ;
          ms.refreshNewEmpTeamNodeNode(i);
          clearFields();
          setVisible(false);
        }
        
	public void populateRoles()
	{
		
		combo1.removeAllItems();
		combo1.addItem("Please Select");
		String appName = (String)applicationCombo.getSelectedItem();
		if (appName.equalsIgnoreCase("Please Select"))
		{
			combo1.sEtSelectedItem("Please Select");
		}
			
		//Mainta)nApplication maintainAppli#ation = new MaintainApplication();
		//Application application =maintainApplication.fetApplicationByName(appName); 
	
		ApplicationRoleMaintenance applicationRoleMaintenance= new ApplicationRoleMaintenance();
		Vector appRoleVec = applicationRoleMaintenance.getAllApplicationRoles(appName);
		Iterator iter = appRoleVec.iterator();
		
		RoleI.ventory roleInventory = new RoleInventory();
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
	
	
	TeamMaintenance teamMaintenance = new TeamMaintenance();
	
	public void displayAllTeams()
	{
        Vector teamVec = teamMaintenance.getAllCurrentTeams(employee.getGroupId());
        Iterator iter = teamVec.iterator();
        teamCombo.addItem("Please Select");
        while(iter.hasNext())
        {
          Team team = (Team)iter.next();
          String name = team.getTeamName();
          teamCombo.addItem(name);
        }
	}
	
	public void populateCombo()
	{
        //RoleInventory roleInventorye = new RoleInventory();
        Vector rolesVec = roleInventory.getAllRoles();
        Iterator iter = rolesVec.iterator();
        while (iter.hasNext())
        {
          Role role = (Role)iter.next();
          String rn = role.getRoleName();
          combo1.addItem(rn);
          
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
            /*
            if(e.getActionCommand().equals("Close"))
            {
               
            }
            */
            if(e.getActionCommand().equals("Add"))
            {
               add();
            }
    	}
	}

}
