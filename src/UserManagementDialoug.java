import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JTree;

public class UserManagementDialoug  extends JDialog{
   
    
    Agent agent= new Agent() ;
    Agent addedAgent;
    LogIn logIn = new LogIn();
    ApplicationRoleMaintenance appRoleMaint = new ApplicationRoleMaintenance();
    MaintainApplication appMaint = new MaintainApplication();
    RoleInventory roleInventory = new RoleInventory(); 
    Vector rolesVec = roleInventory.getAllRoles();
    TeamMaintenance teamMaintenance = new TeamMaintenance();
    Personnel personnel = new Personnel();
    int teamId;
    private String originalUn;
    private int height =250;
    private int width=550;
    //      labels
    JLabel nameLbl = new JLabel("Name");
    JLabel empNoLbl = new JLabel("Employee Number");
    JLabel userNameLbl = new JLabel("User Name");
    JLabel passWrdLbl = new JLabel("Enter Password");
    JLabel confPwLbl = new JLabel("Confirm Password");
    JLabel applicationLbl = new JLabel("Application");
    JLabel roleLbl = new JLabel("Role");
    
    //textFields
    JTextField nameFld = new JTextField();
    JTextField empNoFld = new JTextField();
    JTextField userNameFld = new JTextField();
    
    //Pasword Fields
    JPasswordField passWrdFld = new JPasswordField();
    JPasswordField confPwFld = new JPasswordField();
    
    //Combo Box
    JTextField roleFld = new JTextField();
    JTextField applicationFld = new JTextField();
    
    //Buttons
    JButton editBtn = new JButton("Edit");
    JButton addBtn = new JButton("Add");
    JButton deletBtn = new JButton("Delete");
    JButton findEmpBtn = new JButton("Find Employee");
    JButton findAgentBtn = new JButton("Find User");
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
            
            passWrdFld.setText("");
            confPwFld.setText("");
            nameFld.setText(""); 
            empNoFld.setText("");
            userNameFld.setText("");
    }
    public Employee findEmployee(String riasEmpNo)
    {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Employee emp = new Employee();  
        try {
            if(riasEmpNo.trim().equalsIgnoreCase("")){
                 throw new ParametrizedException("Please Enter a valid Employee Number ");       
             }else{
             
             emp = personnel.findActiveEmployeeByRiasEmpNo(riasEmpNo);
             
             if (emp==null ||emp.getEmployeeId()==0){                    
                    throw new ParametrizedException("Invalid Employee Number, Please Try Agein");
             } 
            
             String n = emp.getForName()+" "+ emp.getSurName();      
             
             nameFld.setText(n); 
             
             userNameFld.setText("");  
             passWrdFld.setText("");  
             confPwFld.setText("");  
             
             addBtn.setEnabled(true);
             }
             
            
        }catch(ParametrizedException e){
            nameFld.setText("");
            JOptionPane.showMessageDialog(this,e.getMessage());
            this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
        userNameFld.setText("");  
        passWrdFld.setText("");  
        confPwFld.setText("");
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        return emp;
    }
    //Vector unVec = personnel.getAllUserName();
    
  
    public void addUser()
    {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            try
            {
                    String riasEmpNo= empNoFld.getText();
                    
                    Employee emp = personnel.findEmployeeByRiasEmpNo(riasEmpNo);

                    //System.out.println("EmpId is" + emp.getEmployeeId() );
                    String password1 = String.valueOf(passWrdFld.getPassword());
                    String password2 = String.valueOf(confPwFld.getPassword());
                    password1=password1.trim();
                    password2=password2.trim();   
                    if (!password1.equals(password2))
                            throw new ParametrizedException("Passwords Do Not Match, Please try agein");   
                    
                    agent.setEmpId(emp.getEmployeeId());  
                    
                    agent.setUserName(userNameFld.getText());
                    
                    if (userNameFld.getText().trim().equalsIgnoreCase(""))
                        throw new ParametrizedException("Please Enter a Valid Username");
                    
                    agent.setPassword(String.valueOf(passWrdFld.getPassword()));    
                    agent.setRiasEmployeeNumber(emp.getRiasEmpNo());
                    
                    addedAgent=agent;
                    logIn.addUser(agent);
                    
                    JOptionPane.showMessageDialog(this,"Details of "+ nameFld.getText()+ " is added to the System" ) ;
                    clearFields();
                    
                    
            }
            catch(ParametrizedException e)
            {
                    JOptionPane.showMessageDialog(this,e.getMessage());
            }finally{
                this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            
    }
    
     public UserManagementDialoug(MainScreen msIn)
    {
            super(msIn,true);
            setSize(width,height);
            setTitle("User Maintenance Screen");
            getContentPane().setLayout(null);
            setUpScreen();
            ms=msIn;
            setLocationRelativeTo(ms);  
            setVisible(true);
           
            
    }
    public void findUser(){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    try{
        if (userNameFld.getText().equals(""))
                throw new ParametrizedException("Please Enter the agents UserName");
        originalUn = userNameFld.getText().trim();        
        
         agent = logIn.getUser(userNameFld.getText()); 
        
        if (agent.getId()==0){
            throw new ParametrizedException("Invalid Usernname, please Try again");
        }
        
        Employee emp = personnel.findEmployeeByEmpId(agent.getEmpId());
        String n = emp.getForName()+" "+ emp.getSurName();      
        nameFld.setText(n);
        empNoFld.setText(emp.getRiasEmpNo());
        passWrdFld.setText(agent.getPassword());
        confPwFld.setText(agent.getPassword());    
                            
      }catch(ParametrizedException e){
            JOptionPane.showMessageDialog(this,e.getMessage());
            nameFld.setText("");
            empNoFld.setText("");
            passWrdFld.setText("");  
            confPwFld.setText("");
       }finally{
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
       }
    }
    
    private void setUpScreen()
    {
            
        
            //AddingButtons

            getContentPane().add(editBtn); 
            getContentPane().add(addBtn);
            getContentPane().add(deletBtn); 
            getContentPane().add(findEmpBtn); 
            getContentPane().add(findAgentBtn);
            getContentPane().add(exitBtn );
            
            editBtn.setFocusable(false);
            addBtn.setFocusable(false);
            deletBtn.setFocusable(false);
            findEmpBtn.setFocusable(false);
            findAgentBtn.setFocusable(false);
            exitBtn.setFocusable(false);
            
            
            addBtn.setEnabled(false);
            //Adding Lables
            getContentPane().add(nameLbl); 
            getContentPane().add(empNoLbl);
            getContentPane().add(userNameLbl); 
            getContentPane().add(passWrdLbl); 
            getContentPane().add(confPwLbl);
            getContentPane().add(roleLbl);
            
            
            getContentPane().add(applicationLbl);            
            
            //Adding Fields
            getContentPane().add(nameFld);
            
            getContentPane().add(empNoFld);
            getContentPane().add(userNameFld);
            getContentPane().add(passWrdFld); 
            getContentPane().add(confPwFld); 
            getContentPane().add(applicationFld); 
            getContentPane().add(roleFld);
            nameFld.setEditable(false);            
            
            nameFld.setFocusable(false);
            applicationFld.setFocusable(false);
            roleFld.setFocusable(false);
            
            //Adding Button Listeners
            exitBtn.addActionListener(buttonListener);
            findEmpBtn.addActionListener(buttonListener);
            findAgentBtn.addActionListener(buttonListener);
            addBtn.addActionListener(buttonListener);
            editBtn.addActionListener(buttonListener);
            deletBtn.addActionListener(buttonListener);
            //seting bounds
            
            nameLbl.setBounds(30,40,100,20);
            nameFld.setBounds(150,40,150,20);
            
            empNoLbl.setBounds(30,10,150,20);
            empNoFld.setBounds(150,10,150,20);
            
            applicationLbl.setBounds(30,70,100,20);;
            applicationFld.setBounds(150,70,150,20);
            
            roleLbl.setBounds(30,100,100,20);
            roleFld.setBounds(150,100,150,20);
            
            userNameLbl.setBounds(30,130,150,20);
            userNameFld.setBounds(150,130,150,20);
            
            passWrdLbl.setBounds(30,160,100,20);
            passWrdFld.setBounds(150,160,100,20);
            
            confPwLbl.setBounds(270,160,150,20);
            confPwFld.setBounds(390,160,100,20);
            
            findEmpBtn.setBounds(390,10,120,20);
            findAgentBtn.setBounds(390,130,120,20);
            
            addBtn.setBounds(30,190,80,20);         
            editBtn.setBounds(150,190,80,20);;
            deletBtn.setBounds(270,190,80,20); 
            exitBtn.setBounds(390,190,80,20) ;
            
            applicationFld.setEditable(false);
            roleFld.setEditable(false);
            
            nameFld.setBackground(Color.WHITE);
            applicationFld.setBackground(Color.WHITE);
            roleFld.setBackground(Color.WHITE);
            applicationFld.setText("Agent HireArchy");
            roleFld.setText("Administrator");
        
        userNameFld.addKeyListener(new java.awt.event.KeyAdapter() 
                                                              {
                                                                  public void keyTyped(java.awt.event.KeyEvent evt) 
                                                                  {
                                                                      KeyTypedAgent(evt);
                                                                  }
                                                              });
        empNoFld.addKeyListener(new java.awt.event.KeyAdapter() 
                                                              {
                                                                  public void keyTyped(java.awt.event.KeyEvent evt) 
                                                                  {
                                                                      KeyTypedEmployee(evt);
                                                                  }
                                                              }) ;                                                     
            
            
    }
    
    public void upDateUser(String riasEmpNo)
    {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    try
            {
                    Employee emp = personnel.findEmployeeByRiasEmpNo(riasEmpNo);    
                    agent.setEmpId(emp.getEmployeeId());
                    //System.out.println("EmpId is" + emp.getEmployeeId() );
                    String password1 = String.valueOf(passWrdFld.getPassword());
                    String password2 = String.valueOf(confPwFld.getPassword());
            
                    if (!password1.equals(password2))
                            throw new ParametrizedException("Passwords Do Not Match, Please try agein");
    
                    agent.setEmpId(emp.getEmployeeId());                    
                    agent.setUserName(userNameFld.getText());
                    agent.setPassword(String.valueOf(passWrdFld.getParent()));
                    agent.setRiasEmployeeNumber(riasEmpNo);
                            
                    agent.setPassword(String.valueOf(passWrdFld.getPassword()));
                    agent.setUserName(userNameFld.getText());
                    
                    logIn.editUser(agent);
                   
                    addBtn.setEnabled(false);
                    JOptionPane.showMessageDialog(this,"Details of "+ nameFld.getText()+ " is up dated" ) ;
                    clearFields();
            }
            catch(ParametrizedException e)
            {
                JOptionPane.showMessageDialog(this,e.getMessage());
            }finally{            
                this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
    }
    
   
    private void KeyTypedAgent(java.awt.event.KeyEvent evt)  {    
            if(evt.getKeyChar() == KeyEvent.VK_ENTER)
             findUser();
    }
    
    private void KeyTypedEmployee(java.awt.event.KeyEvent evt)  {    
            if(evt.getKeyChar() == KeyEvent.VK_ENTER)
            findEmployee(empNoFld.getText()) ;
    }
    
    private void deleteUser(){
        
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try
        {
                if (userNameFld.getText().trim().equalsIgnoreCase(""))
                    throw new ParametrizedException("Please Enter a Valid Username");
                agent = logIn.getUser(userNameFld.getText()); 
                personnel.deActivateAgent(agent);
                JOptionPane.showMessageDialog(this,"Details of "+ nameFld.getText()+ " is removed from the System" ) ;
                clearFields();
        }catch(ParametrizedException e){
            JOptionPane.showMessageDialog(this,e.getMessage());
        }finally{            
            this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }        
    }
    
    public class ButtonListener implements ActionListener
    {
            public void actionPerformed(ActionEvent e)
            {
                if(e.getActionCommand().equals("Close")) {
                   setVisible(false);
                }
                
                if(e.getActionCommand().equals("Find Employee")){
                   findEmployee(empNoFld.getText()) ;
                }
                    
                if(e.getActionCommand().equals("Add")){
                  addUser();            
                }
                
                if(e.getActionCommand().equals("Find User")){
                  findUser() ;
                }
               
                if(e.getActionCommand().equals("Edit")){
                  upDateUser(empNoFld.getText());
                }
                
                if(e.getActionCommand().equals("Delete")){
                  deleteUser();
                }
        
            }
    }
}
