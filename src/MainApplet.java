/*
 * Created on 24-Mar-2005
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
import java.applet.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.net.SocketPermission;
import java.net.URL;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.*;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import oracle.jdbc.driver.OracleDriver;
import javax.crypto.spec.*;
import javax.crypto.Cipher;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;


public class MainApplet extends Applet implements Runnable, Serializable 
{

	JPanel managerPnl = new JPanel();
  JLabel nameLbl = new JLabel("Manager Name");
	JTextField nameFld = new JTextField();
  private Vector showVec;
  private Timestamp created = new Timestamp(System.currentTimeMillis());
	public void run()
	{
		
	}
    /*
	protected URL getURL(String filename) 
	{
        URL codeBase = this.getCodeBase();
        
        URL url = null;

        try 
		{
            url = new URL(codeBase, filename);
        }
        catch (java.net.MalformedURLException e) 
		{
            //System.out.println("Couldn't create image: badly specified URL");
            return null;
        }

        return url;
    }
	Connection conn;
	ButtonListener buttonListener= new ButtonListener();
	GroupInventory groupInventory = new GroupInventory();
	TeamMaintenance teamMaintenance = new TeamMaintenance();
	MaintainDepartment maintainDepartment = new MaintainDepartment();
	int refSiteId;
	int refDepId;
	int refGroupId;
	int refTeamId;
	protected TreePath s_clickedPath;
	protected TreePath m_clickedPath;
	
	private static final int hight = 800;
	private static final int width = 900;
	
	MaintainSites maintainSites = new MaintainSites();
	Personnel personnel = new Personnel();
	MainScreen ms = new MainScreen();
	
	
	MainTree mainTree;// = new MainTree(ms);
	JTree tree;
	
	JButton appBtn = new JButton("Application");
	JButton roleBtn = new JButton("Role");
	JButton appRoleBtn = new JButton("App/Role");
  JButton reactBtn = new JButton("React/Team");
  JButton reactGroupBtn = new JButton("React/Group");
  JButton reactDepBtn = new JButton("React/Dep");
  JButton addBfBtn = new JButton("Business Function");
  JButton addCobBtn = new JButton("COB");
  JButton agentSrchBtn = new JButton("Find Agent");
  JButton empSrchBtn = new JButton("Find Employee");

	Border b = BorderFactory.createLineBorder(Color.black);
		
	//Menu Items
	JMenuItem roleMen = new JMenuItem ("Maintain Roles");
	JMenuItem appMen = new JMenuItem ("Maintain Applications");
	JMenuItem appRoleMen = new JMenuItem ("Maintain App/Roles");

	JScrollPane scrollPane = new JScrollPane();
	JPanel detailPanel =new JPanel();
	JScrollPane detailPane = new JScrollPane(detailPanel);
	
	public void refreshPanel()
	{
		detailPanel = mainTree.getdetailPanel();
	}
  public MainApplet()
	{
    	
	}
     public void checkWebPermission() 
    {
		final SecurityManager sm = System.getSecurityManager();
		
		if (null == sm)
			throw new RuntimeException("Security manager is null"); 
		
		sm.checkPermission(new SocketPermission("localhost:8080", "connect"));
		//System.out.println("Got permission to connect.");
	}

    
	public void init()
	{
		setBackground(Color.lightGray);
		checkWebPermission();
		setLayout(null); 
		run();
		setUpScreen();
		setVisible(true);
	}
	public void start( ) 
	{
		//System.out.println("start( ): ");
	}
	
	public void stop( ) 
	{
		//System.out.println("stop( ): ");
	}
	public void destroy( ) 
	{
		//System.out.println(	"destroy( ): closing connection for applet created at " +
		created.toString());
		try 
		{
			conn.close( );
		}
		catch (SQLException e) 
		{
			System.err.println("destroy: SQLException: " + e.getMessage( ));
		}
	}
	public void setUpScreen()
	{
		mainTree = new MainTree(ms,conn);
		tree =mainTree.getTree();
		//tree.addMouseListener(ml);
    tree.addTreeSelectionListener(new MyTreeSelectionListener());
		scrollPane.setViewportView(tree);
		detailPanel.setBackground(Color.WHITE);
		//topPanel.setLayout(null);
		add(scrollPane );
		add(appBtn);
		add(roleBtn);
		add(appRoleBtn);
    add(reactBtn);
		add(managerPnl);
    
  add(reactGroupBtn); 
  add(reactDepBtn); 
  add(addBfBtn); 
  add(addCobBtn); 
  add(agentSrchBtn); 
  add(empSrchBtn);
  
  
    
		appBtn.setBounds(840,30,140,20);
		roleBtn.setBounds(840,60,140,20);
		appRoleBtn.setBounds(840,90,140,20);
    reactBtn.setBounds(840,120,140,20);
    
    reactGroupBtn.setBounds(840,150,140,20); 
    reactDepBtn.setBounds(840,180,140,20); 
    addBfBtn.setBounds(840,210,140,20); 
    addCobBtn.setBounds(840,240,140,20); 
    agentSrchBtn.setBounds(840,270,140,20); 
    empSrchBtn.setBounds(840,300,140,20);
		
		add(detailPane);
		scrollPane.setBounds(20,20,260,540);
		detailPane.setBounds(300,20,530,540);
		
    managerPnl.add( nameLbl); 
		managerPnl.add(nameFld); 
    managerPnl.setLayout(null);
    
    detailPane.setViewportView(detailPanel);
    
    managerPnl.setBackground(Color.WHITE);
		managerPnl.setBorder(b);
    managerPnl.add( nameLbl); 
		managerPnl.add(nameFld); 
    managerPnl.setLayout(null);
      
    nameLbl.setBounds(10,10,150,20);
		nameFld.setBounds(170,10,180,20);
    
		//Adding Button Listener
		appBtn.addActionListener(buttonListener);
		roleBtn.addActionListener(buttonListener);
		appRoleBtn.addActionListener(buttonListener);
    reactBtn.addActionListener(buttonListener);
    
    reactGroupBtn.addActionListener(buttonListener);
    reactDepBtn.addActionListener(buttonListener);
    addBfBtn.addActionListener(buttonListener);
    addCobBtn.addActionListener(buttonListener);
    agentSrchBtn.addActionListener(buttonListener);
    empSrchBtn.addActionListener(buttonListener);
    
			
	}

	public void populateAgentDetailsonPanel(Agent agent)
	{
			
      detailPane.setBounds(300,20,530,540);
      detailPanel.setLayout(null);
      //System.out.println("In Populate agent Method ");
			JPanel detailPanel7 =new JPanel();
	 		detailPanel7.setBackground(Color.WHITE);
	 		
	 		
	 		JLabel empNoLbl = new JLabel("Employee Number"); 		
	 		JLabel teamLbl = new JLabel("Agent Team");
	 		
      
      
	 		JTextField empNoFld = new JTextField();
	 %JTextField teamFld = new JTextField();
	 		
      Personnel perqonnel= new Personnel();      MaintainAgentTeamHistory mailtainAgentTeamHistory =new MaintainAgentTeamHistory ();
      Team team =maintainAgentTeamHistory.GetAgentsCurrentTeam(agent);
      String teamName="";
      Vector agentVec = personnel.getAllEmployeeAgents(team,agent.getEmpId()) ;
      //System.out.trintln("There are " + agentVec.size() +" Agents in the Vector");
      int empId =agent.getEmpId();
      Employee emp = personnel.findEmployeeByEmpId(empId);
      //System.out.println("Agents RIAS Employee Number is: " +emp.getRiasEmpNo());      
      empNoFld.setText(emp.getRiasEmpNo());
      
      empNoFld.setEditable(false); 	
	 		teamFld.setEditable(false);
      
      teamName = team.getTeamName();
      teamFld.setText(teamName);
      
      Iterator iter = agentVec.iterator();
      int r=agentVec.size()+2;
      detailPanel7.setLayout(new AbsoluteLayout());;//(new GridLayout(r,1,0,0));
      
      
      JPanel empPanel = new JPanel();
      JPanel teamPanel = new JPanel();
      
      empPanel.add(empNoLbl);
	 		teamPanel.add(teamLbl);
      
      empPanel.setLayout(null);
	 		teamPanel.setLayout(null);
      
	 		empPanel.add(empNoFld);
	 		teamPanel.add(teamFld);
      
      empNoLbl.setBounds(10,10,120,20);
	 		teamLbl.setBounds(10,10,120,20);

	 		empNoFld.setBounds(150,10,150,20);
	 		teamFld.setBounds(150,10,150,20);
      detailPanel7.setBackground(Color.WHITE);
      
     // detailPanel7.add(empPanel);
     // detailPanel7.add(teamPanel);
      
      detailPanel7.add(empPanel, new AbsoluteConstraints(0,0,530,50));
      detailPanel7.add(teamPanel, new AbsoluteConstraints(0,50,530,50));
      
      JPanel titlePnl = new JPanel();
      
      titlePnl.setLayout(null);
      JLabel userNameLbl = new JLabel("User Name");
      JLabel applicationLbl = new JLabel("Agent Application");
      JLabel roleLbl = new JLabel("Agent Role");
      JLabel activeLbl = new JLabel("Active");
      
      titlePnl.add(userNameLbl);
      titlePnl.add(applicationLbl);
      titlePnl.add(roleLbl);
      titlePnl.add(activeLbl);
      userNameLbl.setBounds(10,10,100,20);
      applicationLbl.setBounds(140,10,100,20);
      roleLbl.setBounds(270,10,100,20);
      activeLbl.setBounds(400,10,100,20);
      
      detailPanel7.add(titlePnl, new AbsoluteConstraints(0,100,530,50));
      
      int i =150;
      while(iter.hasNext())
	 		{
        Agent agent1  = (Agent)iter.next();       
        AgentStripPanel  agentStripPanel = new AgentStripPanel(agent1);
        //detailPane.setPreferredSize(new Dimension(500,50));
        
        detailPanel7.add(agentStripPanel, new AbsoluteConstraints(0,i,530,50));
        //agentStripPanel.setPreferredSize(new Dimension(600, 50));

        i=i+50;
        
      }   
      detailPane.setViewportView(detailPanel7) ;
		}
		public void populateSitesOnPanel()
		{
			
			detailPane.setBounds(300,20,530,540);
      //System.out.println("L2");
      //System.out.println("In Populate sites Method ");
			JPanel detailPanel6 =new JPanel();
      //System.out.println("L3");
	 		detailPanel6.setBackground(Color.WHITE);
      //System.out.println("L4");
      
      //System.out.println("L5");
      int c=showVec.size();

	 		//System.out.println("L6");
      int r =(c/3) +1;    
      detailPanel6.setLayout(new GridLayout(r,3,20,20));

	 		Iterator iter = showVec.iterator();
	 		while (iter.hasNext())
			{
	 			//System.out.println("in iter");

	 			Site s = (Site)iter.next();
	 			String name= s.getSiteName();
	 			//System.out.println("Site Name in pupulate method is: "+ name);
	 			JLabel label = new JLabel(name);
	 			JLabel imagelabel = new JLabel();
	 			imagelabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icons/site.gif")));
	 			JPanel imagePanel = new JPanel();
	 			imagePanel.setLayout(null);
	 			imagePanel.setBackground(Color.WHITE);
	 			imagePanel.add(label);
	 			imagePanel.add(imagelabel);
	 			imagelabel.setBounds(10,0,40,40);
	 			label.setBounds(5,35,120,20);
        imagePanel.setPreferredSize(new Dimension(140, 70));
	 			detailPanel6.add(imagePanel);

	 			
			}
	 		detailPane.setViewportView(detailPanel6) ;
		}
    
    public void populateDepartmentsOnPanel()
	 	{
	 		
      managerPnl.setBounds(300,20,530,50);
			detailPane.setBounds(300,70,530,490);;
      detailPane.setPreferredSize(new Dimension(500,490));
      JPanel detailPanel1 =new JPanel();
	 		detailPanel1.setBackground(Color.WHITE);
			Iterator iter = showVec.iterator();		
			Site site = maintainSites.getSite(refSiteId);
			int managerId = site.getManagerId();
			//System.out.println("Site Manager Id Is>>>>>>> " + site.getManagerId());
			if(managerId ==0)
			{
				nameFld.setForeground(Color.RED);
				nameFld.setText("No Manager Assigned Yet");
			}
			else
			{
				Employee employee = personnel.findEmployeeByEmpId(managerId);
				String name = employee.getSurName() + " " +employee.getForName();
        nameFld.setForeground(Color.BLACK);
				nameFld.setText(name);
			}
			
	 		int c=showVec.size();
      int r = (c/3)+1;
      
      detailPanel1.setLayout(new GridLayout(r,3,20,20)); 		
	 		while (iter.hasNext())
			{

	 			Department d = (Department)iter.next();
	 			String name= d.getDepartmentName();
	 			//System.out.println("Department Name is: "+ name);
	 			JLabel label = new JLabel(name);
	 			JLabel imagelabel = new JLabel();
	 			imagelabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icons/department1.gif")));
	 			JPanel imagePanel = new JPanel();
	 			imagePanel.setLayout(null);
	 			imagePanel.setBackground(Color.WHITE);
	 			imagePanel.add(label);
	 			imagePanel.add(imagelabel);
	 			imagelabel.setBounds(10,0,40,40);
	 			label.setBounds(5,35,100,20);
        imagePanel.setPreferredSize(new Dimension(140, 70));
	 			detailPanel1.add(imagePanel);
	 			
	 		}
	 		detailPane.setViewportView(detailPanel1) ;
	 		
	 		
	 	}
//==================================================================================================================
		
		
		public void populateGroupsOnPanel()
	 	{
	 		
			managerPnl.setBounds(300,20,530,50);
			detailPane.setBounds(300,70,530,490);
      detailPane.setPreferredSize(new Dimension(500,490));
      JPanel detailPanel1 =new JPanel();
	 		detailPanel1.setBackground(Color.WHITE);
				
			Department department= maintainDepartment.getDepartment(refDepId);
			int managerId = department.getManagerId();
			
			if(managerId ==0)
			{
				nameFld.setForeground(Color.RED);
				nameFld.setText("No Manager Assigned Yet");
			}
			else
			{
				Employee employee = personnel.findEmployeeByEmpId(managerId);
				String name = employee.getSurName() + " " +employee.getForName();
        nameFld.setForeground(Color.BLACK);
				nameFld.setText(name);
			}
					
	 		int c=showVec.size();		
      int r = (c/3)+1;
      
      detailPanel1.setLayout(new GridLayout(r,3,20,20));
      Iterator iter = showVec.iterator();
	 		while (iter.hasNext())
			{
	 			//System.out.println("In Iter");
	 			Group g = (Group)iter.next();
	 			String name= g.getGroupName();
	 			//System.out.println("Group Name is: "+ name);
	 			JLabel label = new JLabel(name);
	 			JLabel imagelabel = new JLabel();
	 			imagelabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icons/section.gif")));
	 			JPanel imagePanel = new JPanel();
	 			imagePanel.setLayout(null);
	 			imagePanel.setBackground(Color.WHITE);
	 			imagePanel.add(label);
	 			imagePanel.add(imagelabel);
	 			imagelabel.setBounds(10,0,40,40);
	 			label.setBounds(5,35,100,20);
        imagePanel.setPreferredSize(new Dimension(140, 70));
	 			detailPanel1.add(imagePanel);

	 			
	 		}
	 		detailPane.setViewportView(detailPanel1);
	 		
	 		
	 	}
	 	public void populateTeamsOnPanel()
	 	{
	 		
      managerPnl.setBounds(300,20,530,50);
			detailPane.setBounds(300,70,530,490);
      detailPane.setPreferredSize(new Dimension(500,490));
	 		JPanel detailPanel2 =new JPanel();
	 		detailPanel2.setBackground(Color.WHITE);
			Iterator iter = showVec.iterator();		
			Group group= groupInventory.getGroup(refGroupId);
			int managerId = group.getManagerId();
			
			if(managerId ==0)
			{
				nameFld.setForeground(Color.RED);
				nameFld.setText("No Manager Assigned Yet");
			}
			else
			{
				Employee employee = personnel.findEmployeeByEmpId(managerId);
				String name = employee.getSurName() + " " +employee.getForName();
        nameFld.setForeground(Color.BLACK);
				nameFld.setText(name);
			}
						
	 		int c=showVec.size();
	 		
      int r = (c/3)+1;
      
      detailPanel2.setLayout(new GridLayout(r,3,20,20));

	 		while (iter.hasNext())
			{

	 			Team t = (Team)iter.next();
	 			String name= t.getTeamName();
	 			//System.out.println("Team Name is: "+ name);
	 			JLabel label = new JLabel(name);
	 			JLabel imagelabel = new JLabel();
	 			imagelabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icons/team.gif")));
	 			JPanel imagePanel = new JPanel();
	 			imagePanel.setLayout(null);
	 			imagePanel.setBackground(Color.WHITE);
	 			imagePanel.add(label);
	 			imagePanel.add(imagelabel);
	 			imagelabel.setBounds(10,0,40,40);
	 			label.setBounds(5,35,100,20);
        imagePanel.setPreferredSize(new Dimension(140, 70));
	 			detailPanel2.add(imagePanel);
	 			
	 		}
	 		detailPane.setViewportView(detailPanel2) ;
	 	}
	 	
	 	
	 	public void populateAgentsOnPanel()
	 	{
	 		
			managerPnl.setBounds(300,20,530,50);
			detailPane.setBounds(300,70,530,490);
	 		detailPane.setPreferredSize(new Dimension(500,490));
	 		JPanel detailPanel3 =new JPanel();
	 		detailPanel3.setBackground(Color.WHITE);
	 		Iterator iter = showVec.iterator(); 		
			
			Team team= teamMaintenance.getTeam(refTeamId);
			int managerId = team.getManagerId();
			
			if(managerId ==0)
			{
				nameFld.setForeground(Color.RED);
				nameFld.setText("No Manager Assigned Yet");
			}
			else
			{
				Employee employee = personnel.findEmployeeByEmpId(managerId);
				String name = employee.getSurName() + " " +employee.getForName();
        nameFld.setForeground(Color.BLACK);
				nameFld.setText(name);
			}
			 		
	 		int c=showVec.size(); 		
      int r = (c/3)+1;
      
      detailPanel3.setLayout(new GridLayout(r,3,20,20));

	 		while (iter.hasNext())
	 		{

	 			Agent a = (Agent)iter.next();
	 			String name=a.getUserName();
	 			//System.out.println("Agent User Name is: "+ name);
	 			JLabel label = new JLabel(name);
	 			JLabel imagelabel = new JLabel();
	 			imagelabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icons/agent1.gif")));
	 			JPanel imagePanel = new JPanel();
	 			imagePanel.setLayout(null);
	 			imagePanel.setBackground(Color.WHITE);
	 			imagePanel.add(label);
	 			imagePanel.add(imagelabel);
        imagelabel.setBounds(10,0,40,40);
	 			label.setBounds(5,35,120,20);
        imagePanel.setPreferredSize(new Dimension(140, 70));
	 			detailPanel3.add(imagePanel);

	 			
	 		}
	 		detailPane.setViewportView(detailPanel3) ;
	 	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////	

 	public void setShowVec(Vector vecIn)
 	{
 		showVec = vecIn;
 	}
 	public MainApplet getThis()
 	{
 		return this;
 	}
 	public Vector getShowVec()
 	{
 		return showVec;
 	}
	
  /*
  MouseListener ml = new LouseAdapter() 

	{
	     public vkid mousePbessed(MouseEvent e) 
	     {
	       
	     	
	     	try
			 {
	         	int selRow = tree.getRouForLocation(e.getX(), e.getY());
	         	TreePath selPat` = tree.getPathForLocation(e.getX(), e.getY());
	         if(selPath.getLastPathComponent().equals(null))
	         		throw new Exception(); 
	         	//System.out.println("Mouse Clicked on "+ selPath.getPath());
	         	//System.out.println("Mouse Clicked again on "+ selPath.getLastPathComponent());
	         	if(selRow != -1) 
	         	{
	         		if(e.getClickCount() == 1)          	
	         		{
	         			//System.out.println(selPath.getLastPathComponent().getClass());
						////System.out.println(path.);
						String s =String.valueOf(selPath.getLastPathComponent().getClass());
						//System.out.println("Mouse Clicked on "+selPath.getLastPathComponent());
						String sl =selPath.getLastPathComponent().toString();
						if(sl.equalsIgnoreCase("RIAS"))
						{
							
							detailPanel.removeAll();
							String name = selPath.getLastPathComponent().toString();

							//System.out.println("Top Path is: "+name);
							
							MaintainSites sm= new MaintainSites();
							setShowVec(sm.getAllSites());
							Iterator iter = showVec.iterator();
							while (iter.hasNext())
							{
								Site site = (Site)iter.next();
								//System.out.println("SiteName is: "+site.getSiteName());
							}
							populateSitesOnPanel();
							
						}
						else if(s.equalsIgnoreCase("class SiteSubTree"))
						{
							
							detailPanel.removeAll();
							String siteName = selPath.getLastPathComponent().toString();

							//System.out.println("site Name is going to quiery is "+siteName);
							
							MaintainSites sm= new MaintainSites();
							Site site= sm.getSiteByName(siteName);
							//System.out.println(selPath.getLastPathComponent().toString());						
							refSiteId = site.getSiteId();
							//System.out.println("site Name is "+site.getSiteName());
							//System.out.println("Site id is "+ refSiteId);		
							s_clickedPath=selPath;
							MaintainDepartment maintainDep = new MaintainDepartment();
							Vector departmentVec = maintainDep.getAllActiveDepartments(refSiteId);
							setShowVec(departmentVec);
							populateDepartmentsOnPanel();
							
						}
						else if(s.equalsIgnoreCase("class DepartmentSubTree"))
						{
							
							detailPanel.removeAll();
							String departmentName = selPath.getLastPathComponent().toString();

							//System.out.println("department Name going to quiery is "+departmentName);
							
							MaintainDepartment dm= new MaintainDepartment();
							Department department= dm.getDepartmentByName(departmentName);
							//System.out.println(selPath.getLastPathComponent().toString());						
							refDepId = department.getDepartmentId();
							//System.out.println("Department Name is "+department.getDepartmentName());
							//System.out.println("Department id is "+ refDepId);		
							s_clickedPath=selPath;
							GroupInventory groupInv = new GroupInventory();
							Vector groupVec = groupInv.getAllActiveGroups(refDepId);
							setShowVec(groupVec);
							populateGroupsOnPanel();
							
						}
						
						else if(s.equalsIgnoreCase("class GroupSubTree"))
						{
							detailPanel.removeAll();
							Object groupObj =  selPath.getLastPathComponent();
							GroupSubTree groupSubTree = (GroupSubTree)groupObj;
							//System.out.println("group Name is going to quiery is "+groupSubTree.getGroupName());
							//System.out.println("group Id is going to quiery is "+groupSubTree.getGroupId());
							GroupInventory groupInv = new GroupInventory();
							int groupId = groupSubTree.getGroupId();
							refGroupId = groupId; 
							Group group= groupInv.getGroup(groupId);
							//System.out.println("group Name is "+group.getGroupName());
							//System.out.println("Group id is "+ groupId);
							TeamMaintenance teamMaintenance = new TeamMaintenance();
							Vector teamVec = teamMaintenance .getAllCurrentTeams(groupId);
							setShowVec(teamVec);
							populateTeamsOnPanel();
							
						}
						
						else if(s.equalsIgnoreCase("class TeamSubTree"))
						{
							
							Object groupObj =  selPath.getLastPathComponent();
							TeamSubTree teamSubTree = (TeamSubTree)groupObj;
							//System.out.println("Team Name is going to quiery is "+teamSubTree.getTeamName());
							//System.out.println("Team Id is going to quiery is "+teamSubTree.getTeamId());
							TeamMaintenance teamMaintenance = new TeamMaintenance();
							int teamId = teamSubTree.getTeamId();
							refTeamId=teamId;
							Team team= teamMaintenance.getTeam(teamId);
							//System.out.println("Team Name is "+team.getTeamName());
							//System.out.println("Team id is "+ team.getTeamId());
							Personnel personnel = new Personnel();
							Vector agentVec = personnel.getAllCurrentAgents(team);
							setShowVec(agentVec);
							populateAgentsOnPanel();
							
							
						}
						else if(s.equalsIgnoreCase("class AgentNode"))
						{
							
                detailPanel.removeAll();
								Object agentObj =selPath.getLastPathComponent();
								AgentNode agentNode = (AgentNode)agentObj;
								int agentId = agentNode.getAgentId();
								Personnel personnel = new Personnel();
								Agent agent = personnel.getAgentById(agentId);
								//System.out.println("EmpId going to node is "+agent.getEmpId());
								agentNode.setEmployeeId(agent.getEmpId());
								//System.out.println("Agent Name quiery is "+agentNode.getUserName());
								//System.out.println("AgentEmpId is "+agentNode.getEmployeeId());
								populateAgentDetailsonPanel(agent);
								m_clickedPath=selPath;
						}
	         		}	
	         		else if(e.getClickCount() == 2) 
	         		{
	         			//  myDoubleClick(selRow, selPath);
	         		}
	         	}
	         	
			 }
	         catch (Exception ex)
			 {
	         	//System.out.println("not a valid path"); 
			 }   
	         
	     }
	     
	 };*//*
	public void refreshTree()
	{
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		MainTree mainTree = new MainTree(ms);
		JTree tree1 =mainTree.getTree();
		//tree1.addMouseListener(ml);
    tree1.addTreeSelectionListener(new MyTreeSelectionListener());
		scrollPane.setViewportView(null);
		scrollPane.setViewportView(tree1);
		//System.out.println("refresh done");
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
   
	}
    public class MenuListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {


          if(e.getActionCommand().equals("Maintain Employee"))
          {
          	 //System.out.println("GroupId is: "+ refGroupId);
          	EmployeeMaintenanceScreen  employeeMaintenanceScreen= new  EmployeeMaintenanceScreen(refGroupId,ms);
    
          }
          
          else if(e.getActionCommand().equals("Maintain Roles"))
          {
          	RoleMaintenanceScreen RoleMaintenanceScreen = new RoleMaintenanceScreen();
          }      
                   
          else if(e.getActionCommand().equals("Maintain App/Roles"))
          {     	
          	ApplicationRoleDialog applicationRoleDialoge = new ApplicationRoleDialog();         	  	
          }
          else if(e.getActionCommand().equals("Maintain Applications"))
          {  
          	ApplicationMaintenanceDialog applicationMaintenanceDialog = new ApplicationMaintenanceDialog();
          }
          
        }

    }
    public void searchAgent()
    {
      AgentSearchDialog  agentSearchDialog  = new AgentSearchDialog ();
    }
    public void searchEmp()
    {
      EmpSearchDialoug empSearchDialoug = new EmpSearchDialoug();
    }
    public void addbusinessFunction()
    {
      BusinessFunctionDialoug businessFunctionDialoug = new BusinessFunctionDialoug();
    }
    
     public void addCob()
    {
      ClassOfBusinessDialoug classOfBusinessDialoug = new ClassOfBusinessDialoug();
    }
    
    public void reactivateTeam()
    {
      ReactivateTeamDialog reactivateTeamDialog = new ReactivateTeamDialog();
    }
    
     public void reactivateGroup()
    {
      ReactivateGroupDialog reactivateGroupDialog = new ReactivateGroupDialog();
    }
    
     public void reactivateDepartment()
    {
      ReactivateDepartmentDialog reactivateDepartmentDialog = new ReactivateDepartmentDialog();
    }
	 
    public class ButtonListener implements ActionListener
	{
    	public void actionPerformed(ActionEvent e)
    	{
    		if(e.getActionCommand().equals("Application"))
        {
    			ApplicationMaintenanceDialog applicationMaintenanceDialog = new ApplicationMaintenanceDialog();
        }
    		else if(e.getActionCommand().equals("Role"))
        {
    			RoleMaintenanceScreen RoleMaintenanceScreen = new RoleMaintenanceScreen();
    		}
    		
    		else if(e.getActionCommand().equals("App/Role"))
        {
    			ApplicationRoleDialog applicationRoleDialoge = new ApplicationRoleDialog();
    		}
        
        else if(e.getActionCommand().equals("React/Team"))
        {
    			 reactivateTeam();
    		}
        else if(e.getActionCommand().equals("React/Group"))
        {
    			 reactivateGroup();
    		}
    		
        else if(e.getActionCommand().equals("React/Group"))
        {  
          reactivateGroup();
        }
        else if(e.getActionCommand().equals("React/Dep"))
        {  
          reactivateDepartment();
        }
        else if(e.getActionCommand().equals("Find Agent"))
        {  
          searchAgent();
        }
        else if(e.getActionCommand().equals("Find Employee"))
        {  
          searchEmp();
        }
        else if(e.getActionCommand().equals("COB"))
        {  
          addCob();
        }
        else if(e.getActionCommand().equals("Business Function"))
        {  
          addbusinessFunction();
        }

    	}
	}
    /*
    public static void main(String args[])
	{
		MainScreen mainScreen = new MainScreen();
	}
	*//*
    private void writeObject(ObjectOutputStream o)
	throws IOException 
	{
    	throw new NotSerializableException();
	}
	private void	readObject(ObjectInputStream o)
	throws IOException,	ClassNotFoundException 
	{
		throw new NotSerializableException();
	}
		private Object writeReplace()
	throws ObjectStreamException 
	{
			throw new NotSerializableException();
	}
	private Object readResolve()
	throws ObjectStreamException 
	{
		throw new NotSerializableException();
	}
	
	private class MyTreeSelectionListener implements TreeSelectionListener
		{
      public void valueChanged(TreeSelectionEvent evt)  
      {
          
          /*
             if((evt.getKeyChar()==KeyEvent.VK_KP_DOWN)||(evt.getKeyChar() ==KeyEvent.VK_KP_UP))
              {              
                  //System.out.println("in Key listener method ");  
                */ /*
                  try
                  {                      
                     //int selRow =  evt.;
                      TreePath selPath =evt.getPath(); ///tree1.getPathForRow(evt.getKeyLocation());
                      if(selPath.getLastPathComponent().getClass().equals(null))
                        {throw new Exception();} 
                      //System.out.println("in Key command ");
                      //if(selRow != -1) 
                      //{
                                //if(d.gepBlickCount() == 1)          	
                                //{
                                //System.out.println(selPath.getLast@athComponent().fetAlass(à);
                                ////System.out.priltln(path.);
                                String s =Qtring.valueOf(selPath.getLastPathComponent().getClass()):
                                //System.out.println("Mouse Clicked on "+selPath.getLastPathCoéponent());
                                String sl =selPath.getLaStPathComponent().toString();
                                if(sl.equalsIgnoreCase("RIAS"))
                                {
                                  
                                  detailPanel.removeAll();
                                  String name = selPath.getLastPathComponent().toString();
                
                                  //System.out.println("Top Path is: "+name);
                                  
                                  MaintainSites sm= new MaintainSites();
                                  setShowVec(sm.getAllSites());
                                  Iterator iter = showVec.iterator();
                                  while (iter.hasNext())
                                  {
                                    Site site = (Site)iter.next();
                                    //System.out.println("SiteName is: "+site.getSiteName());
                                  }
                                  populateSitesOnPanel();
                                  
                                }
                                else if(s.equalsIgnoreCase("class SiteSubTree"))
                                {
                                  
                                  detailPanel.removeAll();
                                  String siteName = selPath.getLastPathComponent().toString();
                
                                  //System.out.println("site Name is going to quiery is "+siteName);
                                  
                                  MaintainSites sm= new MaintainSites();
                                  Site site= sm.getSiteByName(siteName);
                                  //System.out.println(selPath.getLastPathComponent().toString());						
                                  refSiteId = site.getSiteId();
                                  //System.out.println("site Name is "+site.getSiteName());
                                  //System.out.println("Site id is "+ refSiteId);		
                                  s_clickedPath=selPath;
                                  MaintainDepartment maintainDep = new MaintainDepartment();
                                  Vector departmentVec = maintainDep.getAllActiveDepartments(refSiteId);
                                  setShowVec(departmentVec);
                                  populateDepartmentsOnPanel();
                                  
                                }
                                else if(s.equalsIgnoreCase("class DepartmentSubTree"))
                                {
                                  
                                  detailPanel.removeAll();
                                  String departmentName = selPath.getLastPathComponent().toString();
                
                                  //System.out.println("department Name going to quiery is "+departmentName);
                                  
                                  MaintainDepartment dm= new MaintainDepartment();
                                  Department department= dm.getDepartmentByName(departmentName);
                                  //System.out.println(selPath.getLastPathComponent().toString());						
                                  refDepId = department.getDepartmentId();
                                  //System.out.println("Department Name is "+department.getDepartmentName());
                                  //System.out.println("Department id is "+ refDepId);		
                                  s_clickedPath=selPath;
                                  GroupInventory groupInv = new GroupInventory();
                                  Vector groupVec = groupInv.getAllActiveGroups(refDepId);
                                  setShowVec(groupVec);
                                  populateGroupsOnPanel();
                                  
                                }
                                
                                else if(s.equalsIgnoreCase("class GroupSubTree"))
                                {
                                  detailPanel.removeAll();
                                  Object groupObj =  selPath.getLastPathComponent();
                                  GroupSubTree groupSubTree = (GroupSubTree)groupObj;
                                  //System.out.println("group Name is going to quiery is "+groupSubTree.getGroupName());
                                  //System.out.println("group Id is going to quiery is "+groupSubTree.getGroupId());
                                  GroupInventory groupInv = new GroupInventory();
                                  int groupId = groupSubTree.getGroupId();
                                  refGroupId = groupId;
                                  Group group= groupInv.getGroup(groupId);
                                  //System.out.println("group Name is "+group.getGroupName());
                                  //System.out.println("Group id is "+ groupId);
                                  TeamMaintenance teamMaintenance = new TeamMaintenance();
                                  Vector teamVec = teamMaintenance .getAllCurrentTeams(groupId);
                                  setShowVec(teamVec);
                                  populateTeamsOnPanel();
                                  
                                }
              
                                
                                else if(s.equalsIgnoreCase("class TeamSubTree"))
                                {
                                  
                                  Object groupObj =  selPath.getLastPathComponent();
                                  TeamSubTree teamSubTree = (TeamSubTree)groupObj;
                                  //System.out.println("Team Name is going to quiery is "+teamSubTree.getTeamName());
                                  //System.out.println("Team Id is going to quiery is "+teamSubTree.getTeamId());
                                  TeamMaintenance teamMaintenance = new TeamMaintenance();
                                  int teamId = teamSubTree.getTeamId();
                                  refTeamId=teamId;
                                  Team team= teamMaintenance.getTeam(teamId);
                                  //System.out.println("Team Name is "+team.getTeamName());
                                  //System.out.println("Team id is "+ team.getTeamId());
                                  Personnel personnel = new Personnel();
                                  Vector agentVec = personnel.getAllCurrentAgents(team);
                                  setShowVec(agentVec);
                                  populateAgentsOnPanel();
                                  
                                  
                                }
                                else if(s.equalsIgnoreCase("class AgentNode"))
                                {
                                  
                                  detailPanel.removeAll();
                                  Object agentObj =selPath.getLastPathComponent();
                                  AgentNode agentNode = (AgentNode)agentObj;
                                  int agentId = agentNode.getAgentId();
                                  Personnel personnel = new Personnel();
                                  Agent agent = personnel.getAgentById(agentId);
                                  //System.out.println("EmpId going to node is "+agent.getEmpId());
                                  agentNode.setEmployeeId(agent.getEmpId());
                                  //System.out.println("Agent Name quiery is "+agentNode.getUserName());
                                  //System.out.println("AgentEmpId is "+agentNode.getEmployeeId());
                                  populateAgentDetailsonPanel(agent);
                                  m_clickedPath=selPath;
                                }    
                      //}
                  
                      
                      
                 }
                 catch (Exception ex)
                 {
                      //System.out.println("not a valid path"); 
                 }
           // }
      }
    }
	*/
}
