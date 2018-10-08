
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;


public class MainScreen extends JFrame {
    MenuListener menuListener = new MenuListener();
    ButtonListener buttonListener = new ButtonListener();
    GroupInventory groupInventory = new GroupInventory();
    TeamMaintenance teamMaintenance = new TeamMaintenance();
    MaintainDepartment maintainDepartment = new MaintainDepartment();
    ManagerMaintenance managerMaintenance = new ManagerMaintenance();
    int refSiteId;
    int refDepId;
    int refGroupId;
    int refTeamId;
    protected TreePath s_clickedPath;
    protected TreePath m_clickedPath;
    private int hight = 650;
    private int width = 850;
    MaintainSites maintainSites = new MaintainSites();
    Personnel personnel = new Personnel();
    MainTree mainTree = new MainTree(this);
    JTree tree;
    JTree tree1;
    JButton exitBtn = new JButton("Exit");
    JButton refreshBtn = new JButton("Refresh");

    //menu Bar
    JMenuBar menuBar = new JMenuBar();

    //menues
    JMenu maintenanceMenu = new JMenu("Maintenance");
    JMenu searchMenu = new JMenu("Find");

    //Menu Items
    JMenuItem roleMen = new JMenuItem("Maintain Roles");
    JMenuItem appMen = new JMenuItem("Maintain Applications");
    JMenuItem appRoleMen = new JMenuItem("Maintain App/Roles");
    JMenuItem reactMen = new JMenuItem("Reactivate Team");
    JMenuItem reactGMen = new JMenuItem("Reactivate Group");
    JMenuItem reactDMen = new JMenuItem("Reactivate Department");
    JMenuItem cobMen = new JMenuItem("Add Class of Business");
    JMenuItem bfMen = new JMenuItem("Add Business Function");
    JMenuItem agentMen = new JMenuItem("Agent");
    JMenuItem employeeMen = new JMenuItem("Employee");
    JMenuItem userMen = new JMenuItem("Maintain Users");

    JScrollPane scrollPane = new JScrollPane();
    JPanel detailPanel = new JPanel();
    JScrollPane detailPane = new JScrollPane();
    JPanel managerPnl = new JPanel();
    private List showVec;
    Border b = BorderFactory.createLineBorder(Color.black);
    JLabel nameLbl = new JLabel("Managers Name");
    JComboBox nameBox = new JComboBox();
    JTextField nameFld = new JTextField();
    
    JPanel topPanel = new JPanel();
    JPanel scrolPanel = new JPanel();;
    JPanel backPanel = new JPanel();
    JPanel buttonPanel= new JPanel();
    
    
    public void setTreeCursor(Cursor c){
        scrollPane.setCursor(c);
    }

    public void refreshPanel() {
        MainTree mainTree1 = new MainTree(this);
        detailPanel = mainTree1.getdetailPanel();
    }

    public MainScreen(SplachScreen splachScreenIn) {        
        setTitle("Agent Organization Hierarchy Version 8.0.0");        
        
        setUpFlexyScreen();
        setVisible(true);        
        splachScreenIn.setVisible(false);
        LoginDialog loginDialog = new LoginDialog(this);        
        loginDialog.setVisible(true);
    }

    
    public void setUpFlexyScreen(){
        
        //hight = 700;
        ///width = 1100;
        setSize(width, hight);
        getContentPane().setLayout(new BorderLayout());
        
        backPanel.setBorder(BorderFactory.createEmptyBorder(10,10,20,20)); 
        backPanel.setLayout(new BorderLayout());
       
        
        Dimension d = new Dimension(100, 25);
        refreshBtn.setPreferredSize(d);
        exitBtn.setPreferredSize(d);

        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
        
        buttonPanel.add(refreshBtn,BorderLayout.WEST);
       // buttonPanel.add(new JPanel(),BorderLayout.CENTER);
        buttonPanel.add(exitBtn,BorderLayout.EAST);
        
        //buttonPanel.setPreferredSize(new Dimension(1000, 25));
        
        backPanel.add(buttonPanel,BorderLayout.SOUTH);
        
        getContentPane().add(backPanel);
        
        topPanel.setLayout( new BorderLayout());
        topPanel.setPreferredSize(new Dimension(1000, 700));        
        
        tree = mainTree.getTree();
        scrollPane.setPreferredSize(new Dimension(260, 700));
        tree.requestFocus();
        tree.addTreeSelectionListener(new MyTreeSelectionListener());
        scrollPane.setFocusable(true);
        scrollPane.setViewportView(tree);
        detailPanel.setBackground(Color.WHITE); 
        
        
        
        detailPane.setViewportView(detailPanel);
        managerPnl.setBackground(Color.WHITE);
        managerPnl.setBorder(b);
        managerPnl.add(nameLbl);
        
        topPanel.add(scrollPane, BorderLayout.WEST);
        topPanel.add(detailPane,BorderLayout.CENTER);
        //detailPane.setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
        
        detailPane.setPreferredSize(new Dimension(700, 700));        
        backPanel.add(topPanel,BorderLayout.CENTER);
        
        
        //////////////////////////////////////////////////////////////////////////////
         
         //Adding the menubar
         setJMenuBar(menuBar);

         //adding the menues
         menuBar.add(maintenanceMenu);       
        
        
        //Adding Button Listener
        exitBtn.addActionListener(buttonListener);
        refreshBtn.addActionListener(buttonListener);
        
        //Adding menueItems
        maintenanceMenu.add(roleMen);
        maintenanceMenu.add(appMen);
        maintenanceMenu.add(appRoleMen);
        maintenanceMenu.add(bfMen);
        maintenanceMenu.add(cobMen);

        maintenanceMenu.add(reactMen);
        maintenanceMenu.add(reactGMen);
        maintenanceMenu.add(reactDMen);        
        maintenanceMenu.add(userMen);
        maintenanceMenu.add(searchMenu);
        searchMenu.add(agentMen);
        searchMenu.add(employeeMen);
        

        nameBox.setBackground(Color.WHITE);

        //Adding Menu Listeners
        appMen.addActionListener(menuListener);
        appRoleMen.addActionListener(menuListener);
        roleMen.addActionListener(menuListener);
        reactMen.addActionListener(menuListener);
        reactGMen.addActionListener(menuListener);
        reactDMen.addActionListener(menuListener);
        agentMen.addActionListener(menuListener);
        employeeMen.addActionListener(menuListener);
        bfMen.addActionListener(menuListener);
        cobMen.addActionListener(menuListener);
        userMen.addActionListener(menuListener);
        
        addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent evt) {
                        ConnectionDetails.closeConnection();
                        System.exit(0);
                    }
                });
                
              
        
    }
    
    
    
    public void setUpScreen() {
        
        setSize(width, hight);
        getContentPane().setLayout(null);
        setResizable(false);
        tree = mainTree.getTree();
        
        tree.requestFocus();
        tree.addTreeSelectionListener(new MyTreeSelectionListener());
        scrollPane.setFocusable(true);
        scrollPane.setViewportView(tree);
        detailPanel.setBackground(Color.WHITE);
        scrollPane.setBorder(b);
        getContentPane().add(scrollPane);
        getContentPane().add(exitBtn);
        getContentPane().add(refreshBtn);

        getContentPane().add(detailPane);
        getContentPane().add(managerPnl);

        refreshBtn.setBounds(20, 570, 100, 25);
        scrollPane.setBounds(20, 20, 280, 540);
        detailPane.setViewportView(detailPanel);
        managerPnl.setBackground(Color.WHITE);
        managerPnl.setBorder(b);
        managerPnl.add(nameLbl);

        //managerPnl.add(nameBox); 
        managerPnl.setLayout(null);

        nameLbl.setBounds(10, 10, 150, 20);
        //nameBox.setBounds(170,10,240,20);

        //managerPnl.setBounds(270,20,500,40);

        detailPane.setBounds(320, 20, 500, 540);

        exitBtn.setBounds(720, 570, 100, 25);

        //Adding the menubar
        setJMenuBar(menuBar);

        //adding the menues
        menuBar.add(maintenanceMenu);

        //Adding Button Listener
        exitBtn.addActionListener(buttonListener);
        refreshBtn.addActionListener(buttonListener);
        
        //Adding menueItems
        maintenanceMenu.add(roleMen);
        maintenanceMenu.add(appMen);
        maintenanceMenu.add(appRoleMen);
        maintenanceMenu.add(bfMen);
        maintenanceMenu.add(cobMen);

        maintenanceMenu.add(reactMen);
        maintenanceMenu.add(reactGMen);
        maintenanceMenu.add(reactDMen);        
        maintenanceMenu.add(userMen);
        maintenanceMenu.add(searchMenu);
        searchMenu.add(agentMen);
        searchMenu.add(employeeMen);
        

        nameBox.setBackground(Color.WHITE);

        //Adding Menu Listeners
        appMen.addActionListener(menuListener);
        appRoleMen.addActionListener(menuListener);
        roleMen.addActionListener(menuListener);
        reactMen.addActionListener(menuListener);
        reactGMen.addActionListener(menuListener);
        reactDMen.addActionListener(menuListener);
        agentMen.addActionListener(menuListener);
        employeeMen.addActionListener(menuListener);
        bfMen.addActionListener(menuListener);
        cobMen.addActionListener(menuListener);
        userMen.addActionListener(menuListener);
        /*
        tree.setEnabled(false);
        refreshBtn.setEnabled(false);
        maintenanceMenu.setEnabled(false);
        */
        addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent evt) {
                        ConnectionDetails.closeConnection();
                        System.exit(0);
                    }
                });
        
    }
    
    /**
     * @deprecated
     */
    public void enableScreen(){
        tree.setEnabled(true);
        refreshBtn.setEnabled(true);
        maintenanceMenu.setEnabled(true);
    }

    public void populateAgentDetailsOnPanel(Agent agent) {
        
        JPanel detailPanel7 = new JPanel();
        detailPanel7.setBackground(Color.WHITE);

        JLabel empNoLbl = new JLabel("Employee Number");
        JLabel teamLbl = new JLabel("Agent Team");


        JTextField empNoFld = new JTextField();
        JTextField teamFld = new JTextField();

        Personnel personnel = new Personnel();
        MaintainAgentTeamHistory maintainAgentTeamHistory =  new MaintainAgentTeamHistory();
        Team team = maintainAgentTeamHistory.getAgentsCurrentTeam(agent);
        String teamName = "";
        Vector agentVec = personnel.getAllEmployeeAgents(team, agent.getEmpId());
        
        int empId = agent.getEmpId();
        Employee emp = personnel.findEmployeeByEmpId(empId);
        empNoFld.setText(emp.getRiasEmpNo());

        empNoFld.setEditable(false);
        teamFld.setEditable(false);

        teamName = team.getTeamName();
        teamFld.setText(teamName);

        Iterator iter = agentVec.iterator();
        //detailPanel7.setLayout(new FlowLayout());
        detailPanel7.setLayout(new BoxLayout(detailPanel7, BoxLayout.PAGE_AXIS)); 

        JPanel empPanel = new JPanel();
        JPanel teamPanel = new JPanel();

        empPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); 
        teamPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); 
        
         empPanel.add(empNoLbl);
         teamPanel.add(teamLbl);

         empPanel.add(empNoFld);
         teamPanel.add(teamFld);
         
         empNoLbl.setPreferredSize(new Dimension(120, 20));
         teamLbl.setPreferredSize(new Dimension(120, 20));
         empNoFld.setPreferredSize(new Dimension(150, 20));
         teamFld.setPreferredSize(new Dimension(150, 20));

        detailPanel7.add(empPanel);
        detailPanel7.add(teamPanel);
        
        JPanel titlePnl = new JPanel();
        
        int R=245;
        int G=255;
        int B=255;
        empPanel.setBackground(new Color(R,G,B));
        teamPanel.setBackground(new Color(R,G,B));
        detailPanel7.setBackground(new Color(R,G,B));
        titlePnl.setBackground(new Color(R,G,B));
        
        teamFld.setBackground(new Color(R,G,B));
        empNoFld.setBackground(new Color(R,G,B));

        titlePnl.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel userNameLbl = new JLabel("  User Name");
        JLabel applicationLbl = new JLabel(" Agent Application");
        JLabel roleLbl = new JLabel(" Agent Role");
        JLabel activeLbl = new JLabel("Active");
        
        userNameLbl.setForeground(Color.BLUE);
        applicationLbl.setForeground(Color.BLUE);
        roleLbl.setForeground(Color.BLUE);
        activeLbl.setForeground(Color.BLUE);

        titlePnl.add(userNameLbl);
        titlePnl.add(applicationLbl);
        titlePnl.add(roleLbl);
        titlePnl.add(activeLbl);
        
        titlePnl.setMaximumSize(new Dimension(1000, 40));
        titlePnl.setMinimumSize(new Dimension(2000, 40));
        
        empPanel.setMaximumSize(new Dimension(1000, 40));
        teamPanel.setMaximumSize(new Dimension(1000, 40));
        
        empPanel.setMinimumSize(new Dimension(2000, 40));
        teamPanel.setMinimumSize(new Dimension(2000, 40));
        
        /*
        userNameLbl.setBounds(10, 10, 100, 20);
        applicationLbl.setBounds(140, 10, 100, 20);
        roleLbl.setBounds(270, 10, 100, 20);
        activeLbl.setBounds(400, 10, 100, 20);
        */
        
        userNameLbl.setPreferredSize(new Dimension(125, 20));
        applicationLbl.setPreferredSize(new Dimension(125, 20));
        roleLbl.setPreferredSize(new Dimension(125, 20));
        activeLbl.setPreferredSize(new Dimension(125, 20));
        
        detailPanel7.add(titlePnl);


        int i = 150;
        while (iter.hasNext()) {
            Agent agent1 = (Agent)iter.next();
            AgentStripPanel agentStripPanel = new AgentStripPanel(agent1,this);
            //detailPane.setPreferredSize(new Dimension(500,50));

            agentStripPanel.setMaximumSize(new Dimension(1000, 40));
            agentStripPanel.setMinimumSize(new Dimension(2000, 40));
            
            //agentStripPanel.setBackground(new Color(R,G,B));
            
            detailPanel7.add(agentStripPanel);
            //detailPanel7.add(agentStripPanel, 
            //                 new AbsoluteConstraints(0, i, 500, 40));
            //agentStripPanel.setPreferredSize(new Dimension(600, 50));                        
            //i = i + 50;

        }
        detailPane.setViewportView(detailPanel7);
    }

    public void populateSitesOnPanel() {
        detailPanel.setLayout(new FlowLayout()); 
        
        JPanel sitePanel = new JPanel();
        sitePanel.setBackground(Color.WHITE);
        
        int c = showVec.size();

        int r = (c / 3) + 1;
        sitePanel.setLayout(new GridLayout(r, 3, 20, 20));

        Iterator iter = showVec.iterator();
        while (iter.hasNext()) {

            Site s = (Site)iter.next();
            String name = s.getSiteName();
            JLabel label = new JLabel(name);
            JLabel imagelabel = new JLabel();
            imagelabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icons/site_big.png")));
            JPanel imagePanel = new JPanel();
            imagePanel.setLayout(new BoxLayout(imagePanel,(BoxLayout.PAGE_AXIS)));
            imagePanel.setBackground(Color.WHITE);
            
            imagePanel.add(imagelabel);
            imagePanel.add(label);
            
            imagelabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            imagelabel.setAlignmentY(Component.CENTER_ALIGNMENT);
            label.setAlignmentY(Component.CENTER_ALIGNMENT);            
            
            label.setBackground(Color.GREEN);
            imagePanel.setPreferredSize(new Dimension(70,70));
            sitePanel.add(imagePanel);

        }
        detailPane.setViewportView(sitePanel);
    }

    public void populateDepartmentsOnPanel() {         
        managerPnl.remove(nameFld);
        managerPnl.add(nameBox);
        nameBox.setBounds(170, 10, 280, 20);
        
        managerPnl.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        managerPnl.setMaximumSize(new Dimension(1000,50));
        managerPnl.setMinimumSize(new Dimension(2000,50));
        
        
        detailPane.setPreferredSize(new Dimension(500, 490));
        JPanel detailPanel1 = new JPanel();
        detailPanel1.setBackground(Color.WHITE);
        detailPanel1.setLayout(new BoxLayout(detailPanel1, BoxLayout.PAGE_AXIS)); 
        detailPanel1.add(managerPnl);
        Iterator iter = showVec.iterator();
        Site site = maintainSites.getSite(refSiteId);
        int managerId = site.getManagerId();


        if (managerId == 0) {
            nameBox.removeAllItems();
            nameBox.setForeground(Color.RED);
            nameBox.addItem("No Manager Assigned Yet   ");
        } else {
            nameBox.removeAllItems();
            Employee employee = personnel.findEmployeeByEmpId(managerId);
            String name = employee.getSurName() + " " + employee.getForName();
            nameBox.setForeground(Color.BLACK);
            nameBox.addItem("Site Manager Assigned  ");
            nameBox.addItem(name);
        }

        int c = showVec.size();
        int r = (c / 3) + 1;

        JPanel departmentPnl = new JPanel();
        departmentPnl.setBackground(Color.WHITE);
        
        departmentPnl.setLayout(new GridLayout(r, 3, 20, 20));
        
        
        
        while (iter.hasNext()) {

            Department d = (Department)iter.next();
            String name = d.getDepartmentName();
            //System.out.println("Department Name is: " + name);
            JLabel label = new JLabel(name);
            JLabel imagelabel = new JLabel();
            imagelabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icons/dept_big.png")));
            JPanel imagePanel = new JPanel();
            imagePanel.setLayout(new BoxLayout(imagePanel,(BoxLayout.PAGE_AXIS)));
            imagePanel.setBackground(Color.WHITE);
            
            imagePanel.add(imagelabel);
            imagePanel.add(label);
            
            imagelabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            imagelabel.setAlignmentY(Component.CENTER_ALIGNMENT);
            label.setAlignmentY(Component.CENTER_ALIGNMENT);            
            
            label.setBackground(Color.GREEN);
            imagePanel.setPreferredSize(new Dimension(70,70));
            departmentPnl.add(imagePanel);

        }
        detailPanel1.add(departmentPnl);
        detailPane.setViewportView(detailPanel1);


    }

    public void populateGroupsOnPanel() {
        detailPanel.setLayout(new FlowLayout()); 
        managerPnl.remove(nameFld);
        managerPnl.add(nameBox);
        nameBox.setBounds(170, 10, 280, 20);
        managerPnl.setBounds(320, 20, 500, 50);
        detailPane.setPreferredSize(new Dimension(500, 490));
        JPanel detailPanel1 = new JPanel();
        
        detailPanel1.setLayout(new BoxLayout(detailPanel1, BoxLayout.PAGE_AXIS));
        detailPanel1.add(managerPnl);
        managerPnl.setMaximumSize(new Dimension(1000,60));
        managerPnl.setMinimumSize(new Dimension(2000,60));
        
        detailPanel1.setBackground(Color.WHITE);
        Department department = maintainDepartment.getDepartment(refDepId);
        Vector managersVec = managerMaintenance.getAllManagers(department);

        if (managersVec.isEmpty()) {
            nameBox.removeAllItems();
            nameBox.setForeground(Color.RED);
            nameBox.addItem("No Manager Assigned Yet   ");
        } else {
            Iterator iter = managersVec.iterator();
            nameBox.setForeground(Color.BLACK);
            nameBox.removeAllItems();
            nameBox.addItem("Department Assigned Managers   ");
            while (iter.hasNext()) {
                Manager manager = (Manager)iter.next();
                int managerId = manager.getEmployeeId();
                Employee employee = personnel.findEmployeeByEmpId(managerId);
                String name = 
                employee.getSurName() + " " + employee.getForName();
                nameBox.addItem(name);
            }
        }

        int c = showVec.size();
        int r = (c / 3) + 1;
        
        JPanel groupPnl = new JPanel();
        groupPnl.setBackground(Color.WHITE);
        groupPnl.setLayout(new GridLayout(r, 3, 20, 20));
        
        
        Iterator iter = showVec.iterator();
        while (iter.hasNext()) {
            Group g = (Group)iter.next();
            String name = g.getGroupName();
            JLabel label = new JLabel(name);
            JLabel imagelabel = new JLabel();
            imagelabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icons/group_big.png")));
            JPanel imagePanel = new JPanel();
            
            imagePanel.setLayout(new BoxLayout(imagePanel,(BoxLayout.PAGE_AXIS)));
            imagePanel.setBackground(Color.WHITE);
            
            imagePanel.add(imagelabel);
            imagePanel.add(label);
            
            imagelabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            imagelabel.setAlignmentY(Component.CENTER_ALIGNMENT);
            label.setAlignmentY(Component.CENTER_ALIGNMENT);            
            
            label.setBackground(Color.GREEN);
            imagePanel.setPreferredSize(new Dimension(70,70));
            groupPnl.add(imagePanel);
            
            
            
            
            
            
            
            
            
            
            
            imagePanel.setLayout(null);
            imagePanel.setBackground(Color.WHITE);
            imagePanel.add(label);
            imagePanel.add(imagelabel);
            imagelabel.setBounds(10, 0, 60, 40);
            label.setBounds(5, 35, 100, 20);
            imagePanel.setPreferredSize(new Dimension(140, 70));
            groupPnl.add(imagePanel);


        }
        detailPanel1.add(groupPnl);
        detailPane.setViewportView(detailPanel1);


    }

    public void populateTeamsOnPanel() {
        detailPanel.setLayout(new FlowLayout()); 
        managerPnl.remove(nameFld);
        managerPnl.add(nameBox);
        nameBox.setBounds(170, 10, 240, 20);
       
        JPanel detailPanel2 = new JPanel();
        detailPanel2.setBackground(Color.WHITE);
        detailPanel2.setLayout(new BoxLayout(detailPanel2, BoxLayout.PAGE_AXIS));
        detailPanel2.add(managerPnl);
        managerPnl.setMaximumSize(new Dimension(1000,60));
        managerPnl.setMinimumSize(new Dimension(2000,60));
        managerPnl.setSize(new Dimension(500, 60));
        
        Iterator iter = showVec.iterator();
        Group group = groupInventory.getGroup(refGroupId);
        Vector managersVec = managerMaintenance.getAllManagers(group);
        if (managersVec.isEmpty()) {
            nameBox.removeAllItems();
            nameBox.setForeground(Color.RED);
            nameBox.addItem("No Manager Assigned Yet   ");
        } else {
            Iterator iter1 = managersVec.iterator();
            nameBox.setForeground(Color.BLACK);
            nameBox.removeAllItems();
            nameBox.addItem("Group Assigned Managers  ");
            while (iter1.hasNext()) {
                Manager manager = (Manager)iter1.next();
                int managerId = manager.getEmployeeId();
                Employee employee = personnel.findEmployeeByEmpId(managerId);
                String name = 
                    employee.getSurName() + " " + employee.getForName();
                nameBox.addItem(name);
            }
        }

        int c = showVec.size();
        int r = (c / 3) + 1;
        
        JPanel teamPnl = new JPanel();
        teamPnl.setLayout(new GridLayout(r, 3, 20, 20));
        teamPnl.setBackground(Color.WHITE);
        while (iter.hasNext()) {

            Team t = (Team)iter.next();
            String name = t.getTeamName();
            //System.out.println("Team Name is: " + name);
            JLabel label = new JLabel(name);
            JLabel imagelabel = new JLabel();
            imagelabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icons/team_big.png")));
            JPanel imagePanel = new JPanel();
            imagePanel.setLayout(new BoxLayout(imagePanel,(BoxLayout.PAGE_AXIS)));
            imagePanel.setBackground(Color.WHITE);
            
            imagePanel.add(imagelabel);
            imagePanel.add(label);
            
            imagelabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            imagelabel.setAlignmentY(Component.CENTER_ALIGNMENT);
            label.setAlignmentY(Component.CENTER_ALIGNMENT);            
            
            label.setBackground(Color.GREEN);
            imagePanel.setPreferredSize(new Dimension(70,70));
            teamPnl.add(imagePanel);
          

        }
        detailPanel2.add(teamPnl);
        detailPane.setViewportView(detailPanel2);
    }

    public void populateAgentsOnPanel() { 
        managerPnl.remove(nameBox);
        managerPnl.add(nameFld);
        nameFld.setEditable(false);
        nameFld.setBackground(Color.WHITE);
        nameFld.setPreferredSize(new Dimension(240, 20));
        
        managerPnl.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        JPanel detailPanel3 = new JPanel();        
        
        detailPanel3.setBackground(Color.WHITE);
        detailPanel3.setLayout(new BoxLayout(detailPanel3, BoxLayout.PAGE_AXIS)); 
        detailPanel3.add(managerPnl);
        managerPnl.setMaximumSize(new Dimension(1000,60));
        managerPnl.setMinimumSize(new Dimension(2000,60));
        
        Iterator iter = showVec.iterator();

        Team team = teamMaintenance.getTeam(refTeamId);
        int managerNo = team.getManagerId();
        if (managerNo == 0) {
            nameFld.setForeground(Color.RED);
            nameFld.setText("No Manager Assigned yet   ");
        } else {
            Employee manager = personnel.findEmployeeByEmpId(managerNo);

            String name = manager.getForName() + " " + manager.getSurName();
            nameFld.setForeground(Color.BLACK);
            nameFld.setText(name);
        }

        //MultipleManagers
        /*
                        Vector managersVec= managerMaintenance.getAllManagers(team);

                                          if(managersVec.isEmpty())
                                          {
                                                  nameBox.removeAllItems();
                          nameBox.setForeground(Color.RED);
                                                  nameBox.addItem("No Manager Assigned Yet");
                                          }
                                          else
                                          {
                                                  Iterator iter1 = managersVec.iterator();
                          nameBox.setForeground(Color.BLACK);
                          nameBox.removeAllItems();
                          nameBox.addItem("Team Assigned Managers");
                          while(iter1.hasNext())
                          {
                                Manager manager=(Manager)iter1.next();
                                int managerId = manager.getEmployeeId();
                                Employee employee = personnel.findEmployeeByEmpId(managerId);
                                String name = employee.getSurName() + " " +employee.getForName();
                                nameBox.addItem(name);
                          }
                                          }
                          */
        int c = showVec.size();
        int r = (c / 5) + 1;

        
        JPanel agentPanel= new JPanel();
        agentPanel.setBackground(Color.WHITE);
        agentPanel.setLayout(new GridLayout(r, 3, 20, 20));
        //agentPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //agentPanel.setPreferredSize(new Dimension(500, 490));
        

        while (iter.hasNext()) {

            Integer empIdInt= (Integer)iter.next();
            
            int empId= empIdInt.intValue();
            String name= personnel.getEmployeeName(empId);            
            
            JLabel label = new JLabel(name);
            JLabel imagelabel = new JLabel();
            imagelabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icons/agent_big.png")));
            JPanel imagePanel = new JPanel();
            imagePanel.setLayout(new BoxLayout(imagePanel,(BoxLayout.PAGE_AXIS)));
            imagePanel.setBackground(Color.WHITE);
            
            imagePanel.add(imagelabel);
            imagePanel.add(label);
            
            imagelabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            
            imagelabel.setAlignmentY(Component.CENTER_ALIGNMENT);
            label.setAlignmentY(Component.CENTER_ALIGNMENT);            
            
            label.setBackground(Color.GREEN);
            imagePanel.setPreferredSize(new Dimension(70,70));
            agentPanel.add(imagePanel);
            


        }
        detailPanel3.add(agentPanel);
        //detailPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //detailPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        detailPane.setViewportView(detailPanel3);
    }

    public void setShowVec(List vecIn) {
        showVec = vecIn;
    }

    public MainScreen getThis() {
        return this;
    }

    public List getShowVec() {
        return showVec;
    }

    public void refreshTree() {

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

        MainTree mainTree1 = new MainTree(this);
        tree1 = mainTree1.getTree();
        scrollPane.setViewportView(null);
        scrollPane.setViewportView(tree1);
        tree1.addTreeSelectionListener(new MyTreeSelectionListener());
        //System.out.println("refresh done");
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

    }

    public void searchAgent() {
        AgentSearchDialog agentSearchDialog = new AgentSearchDialog(this);
    }

    public void searchEmp() {
        EmpSearchDialoug empSearchDialoug = new EmpSearchDialoug(this);
    }

    public void addbusinessFunction() {
        BusinessFunctionDialoug businessFunctionDialoug = 
            new BusinessFunctionDialoug(this);
    }

    public void addCob() {
        ClassOfBusinessDialoug classOfBusinessDialoug = 
            new ClassOfBusinessDialoug(this);
    }

    public void reactivateTeam() {
        ReactivateTeamDialog reactivateTeamDialog = 
            new ReactivateTeamDialog(this);
    }

    public void reactivateGroup() {
        ReactivateGroupDialog reactivateGroupDialog = 
            new ReactivateGroupDialog(this);
    }

    public void reactivateDepartment() {
        ReactivateDepartmentDialog reactivateDepartmentDialog = 
            new ReactivateDepartmentDialog(this);
    }
    private void showUserMngDialoug(){
        UserManagementDialoug userManagementDialoug = 
            new UserManagementDialoug(this);
    }
    
    private void showRoleMaintenanceScreen(){
        RoleMaintenanceScreen RoleMaintenanceScreen = 
            new RoleMaintenanceScreen(this);
    }
    
    private void showAppRoleMaintenanceScreen(){
        ApplicationRoleDialog applicationRoleDialoge = 
            new ApplicationRoleDialog(this);
    }
    
    private void showAppMaintenanceScreen(){
        ApplicationMaintenanceDialog applicationMaintenanceDialog = 
            new ApplicationMaintenanceDialog(this);
    }
    
    public class MenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            /*
            if (e.getActionCommand().equals("Maintain Employee")) {
                EmployeeMaintenanceScreen employeeMaintenanceScreen = 
                    new EmployeeMaintenanceScreen(refGroupId, getThis());
            }
            */

             if (e.getActionCommand().equals("Maintain Roles")) {
                showRoleMaintenanceScreen();
            }
            
            else if (e.getActionCommand().equals("Maintain Users")) {
                showUserMngDialoug();
            }
            
            
            else if (e.getActionCommand().equals("Maintain App/Roles")) {
                showAppRoleMaintenanceScreen();
                
            } else if (e.getActionCommand().equals("Maintain Applications")) {
                showAppMaintenanceScreen();
                
            } else if (e.getActionCommand().equals("Reactivate Team")) {
                reactivateTeam();
            } else if (e.getActionCommand().equals("Reactivate Group")) {
                reactivateGroup();
            } else if (e.getActionCommand().equals("Reactivate Department")) {
                reactivateDepartment();
            } else if (e.getActionCommand().equals("Agent")) {
                searchAgent();
            } else if (e.getActionCommand().equals("Employee")) {
                searchEmp();
            } else if (e.getActionCommand().equals("Add Class of Business")) {
                addCob();
            } else if (e.getActionCommand().equals("Add Business Function")) {
                addbusinessFunction();
            }
        }
    }

    
    public void refreshNewEmpTeamNodeNode(int teamId){
       mainTree.refreshAgentTeam(teamId);      
    }
    
    
    public void refreshTeamNode(TeamSubTree teamSubTreeIn){ 
       System.out.println("IN MAIN SCREEN REF TEAM NODE");
       mainTree.refreshAgent1(teamSubTreeIn);      
    }
        
    
    public void refreshGroupNode(GroupSubTree groupSubTreeIn){
       mainTree.refreshGroupTeams(groupSubTreeIn);      
    }
    
    public void refreshDepartmentNode(DepartmentSubTree departmentSubTree){ 
       mainTree.refreshGroups1(departmentSubTree);      
    }
    
    public void refreshDepartmentNode(int departmentId){ 
       mainTree.refreshGroups2(departmentId);      
    }
    
    public void addNewDepartmentNode(String depName,int siteId){
       mainTree.dynamicAddNewDepartment(depName,siteId);
    }
    
    public void updateDepartmentNode(int depId,int siteId){
       mainTree.dynamicUpdateDepartment(depId,siteId);
    }
       
    
    public class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Exit")) {
                ConnectionDetails.closeConnection();
                System.exit(0);
            } else if (e.getActionCommand().equals("Refresh")) {
                refreshTree();
            }

        }
    }

    private class MyTreeSelectionListener implements TreeSelectionListener {
        public void valueChanged(TreeSelectionEvent evt) {

            /*
                               if((evt.getKeyChar()==KeyEvent.VK_KP_DOWN)||(evt.getKeyChar() ==KeyEvent.VK_KP_UP))
                                {
                                    //System.out.println("in Key listener method ");
                                  */
            try {
                //int selRow =  evt.;
                TreePath selPath = 
                    evt.getPath(); ///tree1.getPathForRow(evt.getKeyLocation());
                if (selPath.getLastPathComponent().getClass().equals(null)) {
                    throw new Exception();
                }
                //System.out.println("in Key command ");
                //if(selRow != -1) 
                //{
                //if(e.getClickCount() == 1)          	
                //{
                //System.out.println(selPath.getLastPathComponent().getClass());
                ////System.out.println(path.);
                String s = 
                    String.valueOf(selPath.getLastPathComponent().getClass());
                //System.out.println("Mouse Clicked on " + 
                                   //selPath.getLastPathComponent());
                String sl = selPath.getLastPathComponent().toString();
                if (sl.equalsIgnoreCase("RIAS")) {

                    detailPanel.removeAll();
                    String name = selPath.getLastPathComponent().toString();

                    //System.out.println("Top Path is: " + name);

                    MaintainSites sm = new MaintainSites();
                    setShowVec(sm.getAllSites());
                    Iterator iter = showVec.iterator();
                    while (iter.hasNext()) {
                        Site site = (Site)iter.next();
                        //System.out.println("SiteName is: " + 
                                         //  site.getSiteName());
                    }
                    populateSitesOnPanel();

                } else if (s.equalsIgnoreCase("class SiteSubTree")) {

                    detailPanel.removeAll();
                    String siteName = 
                        selPath.getLastPathComponent().toString();

                    //System.out.println("site Name is going to quiery is " + 
                                     //  siteName);

                    MaintainSites sm = new MaintainSites();
                    Site site = sm.getSiteByName(siteName);
                    //System.out.println(selPath.getLastPathComponent().toString());
                    refSiteId = site.getSiteId();
                    //System.out.println("site Name is " + site.getSiteName());
                    //System.out.println("Site id is " + refSiteId);
                    s_clickedPath = selPath;
                    MaintainDepartment maintainDep = new MaintainDepartment();
                    Vector departmentVec = 
                        maintainDep.getAllActiveDepartments(refSiteId);
                    setShowVec(departmentVec);
                    populateDepartmentsOnPanel();

                } else if (s.equalsIgnoreCase("class DepartmentSubTree")) {

                    detailPanel.removeAll();
                    String departmentName = 
                        selPath.getLastPathComponent().toString();

                    //System.out.println("department Name going to quiery is " + 
                                     //  departmentName);

                    MaintainDepartment dm = new MaintainDepartment();
                    Department department = 
                        dm.getDepartmentByName(departmentName);
                    //System.out.println(selPath.getLastPathComponent().toString());
                    refDepId = department.getDepartmentId();
                    //System.out.println("Department Name is " + 
                                      // department.getDepartmentName());
                    //System.out.println("Department id is " + refDepId);
                    s_clickedPath = selPath;
                    GroupInventory groupInv = new GroupInventory();
                    Vector groupVec = groupInv.getAllActiveGroups(refDepId);
                    setShowVec(groupVec);
                    populateGroupsOnPanel();

                }

                else if (s.equalsIgnoreCase("class GroupSubTree")) {
                    detailPanel.removeAll();
                    Object groupObj = selPath.getLastPathComponent();
                    GroupSubTree groupSubTree = (GroupSubTree)groupObj;
                    //System.out.println("group Name is going to quiery is " + 
                                      // groupSubTree.getGroupName());
                    //System.out.println("group Id is going to quiery is " + 
                                     //  groupSubTree.getGroupId());
                    GroupInventory groupInv = new GroupInventory();
                    int groupId = groupSubTree.getGroupId();
                    refGroupId = groupId;
                    Group group = groupInv.getGroup(groupId);
                    //System.out.println("group Name is " + 
                                      // group.getGroupName());
                    //System.out.println("Group id is " + groupId);
                    TeamMaintenance teamMaintenance = new TeamMaintenance();
                    Vector teamVec = 
                        teamMaintenance.getAllCurrentTeams(groupId);
                    setShowVec(teamVec);
                    populateTeamsOnPanel();

                }


                else if (s.equalsIgnoreCase("class TeamSubTree")) {

                    Object groupObj = selPath.getLastPathComponent();
                    TeamSubTree teamSubTree = (TeamSubTree)groupObj;
                    //System.out.println("Team Name is going to quiery is " + 
                                    //   teamSubTree.getTeamName());
                    //System.out.println("Team Id is going to quiery is " + 
                                      // teamSubTree.getTeamId());
                    TeamMaintenance teamMaintenance = new TeamMaintenance();
                    int teamId = teamSubTree.getTeamId();
                    refTeamId = teamId;
                    Team team = teamMaintenance.getTeam(teamId);
                    //System.out.println("Team Name is " + team.getTeamName());
                    //System.out.println("Team id is " + team.getTeamId());
                    Personnel personnel = new Personnel();
                    
                    
                    //List agentVec = personnel.getAllCurrentAgents(team);
                    
                    List agentVec =personnel.getAllEmployeeNumbers(team);
                    
                    setShowVec(agentVec);
                    populateAgentsOnPanel();


                } else if (s.equalsIgnoreCase("class AgentNode")) {

                    detailPanel.removeAll();
                    Object agentObj = selPath.getLastPathComponent();
                    AgentNode agentNode = (AgentNode)agentObj;
                    int agentId = agentNode.getAgentId();
                    Personnel personnel = new Personnel();
                    Agent agent = personnel.getAgentById(agentId);
                    //System.out.println("EmpId going to node is " + 
                                      // agent.getEmpId());
                    agentNode.setEmployeeId(agent.getEmpId());
                    //System.out.println("Agent Name quiery is " + 
                                     //  agentNode.getUserName());
                    //System.out.println("AgentEmpId is " + 
                                    //   agentNode.getEmployeeId());
                    populateAgentDetailsOnPanel(agent);
                    m_clickedPath = selPath;
                }
                //}


            } catch (Exception ex) {
                //System.out.println("not a valid path");
            }
            // }
        }
    }

    

}
