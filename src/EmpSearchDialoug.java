
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.KeyEvent;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

public class EmpSearchDialoug extends JDialog
{
  
 RadioListener radioListener = new RadioListener(); 
 ComboListener comboListener = new ComboListener();
  private boolean numScarch= true;
  
  private static final int height = 510;
  private static final int width = 980;
  
  JLabel empLbl = new JLabel ("Employee Number");
  JLabel firstnameLbl = new JLabel ("First Name");
  JLabel surnameLbl = new JLabel ("Surname");
  
  JTextField empFld = new JTextField();
  JTextField firstNameFld = new JTextField();
  JTextField surnameFld = new JTextField();
  
  JPanel detailPanel =new JPanel();
  JScrollPane detailPane = new JScrollPane();
  JPanel titlePnl = new JPanel();
  
  JLabel userLbl = new JLabel("Username");
  JLabel appLbl = new JLabel("Application");
  JLabel roleLbl = new JLabel("Role");
  JLabel teamLbl = new JLabel("Team");
  JLabel groupLbl = new JLabel("Group");
  JLabel departmentLbl = new JLabel("Department");
  
  JButton searchBtn = new JButton("Find");
  JButton closeBtn = new JButton("Close");
  JButton clearBtn = new JButton("Clear");
  
  JRadioButton nameRadio = new JRadioButton("Search by name");
  JRadioButton numRadio = new JRadioButton("Search by number");
  
  ButtonGroup g1 = new ButtonGroup();
  
  JComboBox empNumBox = new JComboBox();
   
  ButtonListener buttonListener = new ButtonListener();
  Personnel personnel = new Personnel();
  
  private void setTitlePanel()
  {
    titlePnl.setLayout(null);
    
    titlePnl.add(userLbl);
    titlePnl.add(appLbl);
    titlePnl.add(roleLbl);
    titlePnl.add(teamLbl);
    titlePnl.add(groupLbl);
    titlePnl.add(departmentLbl);
    
    userLbl.setBounds(10,10,100,20); 
    appLbl.setBounds(140,10,100,20);  
    roleLbl.setBounds(270,10,100,20); 
    teamLbl.setBounds(400,10,100,20); 
    groupLbl.setBounds(580,10,100,20);
    departmentLbl.setBounds(760,10,100,20);
    
  }
  public EmpSearchDialoug(MainScreen ms)
  {
    super(ms,true);
    setSize(width,height);
    setTitle("Employee Search Screen");
    getContentPane().setLayout(null);   
    detailPanel.setBackground(Color.WHITE);
    detailPanel.setLayout(new AbsoluteLayout());
    setupScreen();
    setLocationRelativeTo(ms);
    setVisible(true);
    
    
  }
   public EmpSearchDialoug()
  {
    setSize(width,height);
    getContentPane().setLayout(null);
    setTitle("Employee Search Screen");
    detailPanel.setBackground(Color.WHITE);
    detailPanel.setLayout(new AbsoluteLayout());
    setupScreen();
    setVisible(true);
  }
   public void nameSearch()
   {
     empFld.setVisible(false);;
     empNumBox.setVisible(true);
     empNumBox.setBackground(Color.WHITE);   
     empNumBox.setEnabled(false);
     empNumBox.addItem("Please Select");
     firstNameFld.setEditable(true);
     surnameFld.setEditable(true);
   }
   
   public void findEmployeebyName()
   {
     try
     {
           
           this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
           Vector empVec =new Vector();
           
           String fn1= firstNameFld.getText();
           String sn1= surnameFld.getText();     
           String fn=fn1.trim();
           String sn= sn1.trim();
           if (sn.equalsIgnoreCase(""))
           throw new ParametrizedException("Please Enter surname");
           if(fn.equalsIgnoreCase(""))
           {
             empVec=personnel.findEmployeeBySurName(sn);
           }
           
           else
           {
               //System.out.println("Sruname Going to personnel is" + sn);
               empVec = personnel.findEmployeeByFullName(fn,sn);
           }
     
           if (empVec.isEmpty())
           throw new ParametrizedException("The employee does not exist, Please try again");
           Iterator iter = empVec.iterator();
           empNumBox.setEnabled(true);
           empNumBox.removeAllItems();
           empNumBox.addItem("Please Select");
           while (iter.hasNext())
           {
             Employee emp=(Employee)iter.next();
             String riasEmpNo= emp.getRiasEmpNo();
             String efn=emp.getForName();
             String esn=emp.getSurName();
             String n=  riasEmpNo+"-"+" "+ efn+" "+esn;
             
             empNumBox.addItem(n);
           } 
           
           if(empVec.size()==1){
               empNumBox.setSelectedIndex(1);
               displayEmployeeFromCombo();
           }
     }
     catch(ParametrizedException e)
     {
       JOptionPane.showMessageDialog(this,e.getMessage()); 
     }
     empNumBox.addActionListener(comboListener);
     this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
   }
   public void displayEmployeeFromCombo()
   {
     this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     
       String n=(String)empNumBox.getSelectedItem();
       JPanel detailPanel1 =new JPanel();       
       if (n.equalsIgnoreCase("Please Select")){
          detailPanel1 =new JPanel();
          detailPanel1.setBackground(Color.WHITE); 
       }
       else{
               detailPanel1 =new JPanel();
               detailPanel1.setBackground(Color.WHITE);
               int marker= n.indexOf("-");       
               String riasEmpNo =n.substring(0,marker);
               this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
               detailPanel1 =new JPanel();
               detailPanel1.setLayout(new AbsoluteLayout());
               detailPanel1.setBackground(Color.WHITE); 
               TeamMaintenance teamMaintenance = new TeamMaintenance();
               
               Employee emp = personnel.findEmployeeByRiasEmpNo(riasEmpNo);
               
               firstNameFld.setText(emp.getForName());
               surnameFld.setText(emp.getSurName());               
               
               Vector teamVec = teamMaintenance.getAllActiveTeams();
               Iterator iter = teamVec.iterator();
               int i=0;
               while (iter.hasNext())
               {
                     Team team=(Team)iter.next();                     
                     Vector agentVec = personnel.getAllEmployeeAgents(team,emp.getEmployeeId());
                     Iterator iter2 = agentVec.iterator();
                            
                     while (iter2.hasNext())
                     {
                        
                         Agent agent = (Agent)iter2.next();
                         System.out.println("Agent un = " + agent.getUserName());
                         EmpAgentSearchPanel empAgentSearchPanel = new EmpAgentSearchPanel(agent,team);
                         detailPanel1.add(empAgentSearchPanel, new AbsoluteConstraints(0,i,1050,40));
                         i=i+40;
                         System.out.println("i= " +i);
                     }
                     
              }
      }
      detailPane.setViewportView(detailPanel1);
      this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
   }
       
   public void numSearch()
   {
     
     empNumBox.setVisible(false);
     empFld.setVisible(true);
     empFld.setEnabled(true);
     firstNameFld.setEditable(false);
     surnameFld.setEditable(false);
     firstNameFld.setText("");
     surnameFld.setText("");    
   }
   
   public void clear()
   { 
         numRadio.setSelected(true); 
         JPanel p1 =new JPanel();
         p1.setBackground(Color.WHITE);
         detailPane.setViewportView(p1);
         numSearch();
         empNumBox.removeAllItems();
         empNumBox.addItem("Please Select");
         empNumBox.setEditable(false);  
   }
  public void setupScreen()
  {
    setTitlePanel();
    getContentPane().add(firstNameFld);
    getContentPane().add(surnameFld);
    
    // firstNameFld.
    //surnameFld)
    
    getContentPane().add(empFld);
    getContentPane().add(empNumBox);
    
    empFld.setBounds(180,10,200,20);
    empNumBox.setBounds(180,10,200,20);
    
    nameRadio.addActionListener(radioListener);;
    numRadio.addActionListener(radioListener);;
    
    g1.add(nameRadio);
    g1.add(numRadio);
    
    nameRadio.setSelected(true);
    
    getContentPane().add(empLbl);
    getContentPane().add(firstnameLbl);
    getContentPane().add(surnameLbl);
    
    getContentPane().add(nameRadio);
    getContentPane().add(numRadio );
       
    getContentPane().add(titlePnl);
      
    firstNameFld.setBackground(Color.WHITE);
    surnameFld.setBackground(Color.WHITE);
    
    
    
    getContentPane().add(detailPane);
    
    getContentPane().add(searchBtn);
    getContentPane().add(closeBtn);
    getContentPane().add(clearBtn);
    
    empLbl.setBounds(10,10,150,20);
    firstnameLbl.setBounds(10,40,150,20);
    surnameLbl.setBounds(10,70,150,20);
    
    
    firstNameFld.setBounds(180,40,200,20);
    surnameFld.setBounds(180,70,200,20);
    
     firstNameFld .addKeyListener(new java.awt.event.KeyAdapter() 
                  {
                      public void keyTyped(java.awt.event.KeyEvent evt) 
                      {
                          KeyTyped(evt);
                      }
                  });
    
    
     surnameFld.addKeyListener(new java.awt.event.KeyAdapter() 
                  {
                      public void keyTyped(java.awt.event.KeyEvent evt) 
                      {
                          KeyTyped(evt);
                      }
                  });
      
      empFld.addKeyListener(new java.awt.event.KeyAdapter() 
                          {
                              public void keyTyped(java.awt.event.KeyEvent evt) 
                              {
                                  KeyTyped(evt);
                              }
                          }); 

    
    
    titlePnl.setBounds(10,100,980,40);
    detailPane.setBounds(10,140,950,300);
    
    searchBtn.setBounds(420,10,100,25);
    nameRadio.setBounds(420,35,150,25);
    numRadio.setBounds(420,55,150,25);
    
    closeBtn.setBounds(860,450,100,25);
    clearBtn.setBounds(10,450,100,25);
    
    searchBtn.addActionListener(buttonListener); 
    closeBtn.addActionListener(buttonListener); 
    clearBtn.addActionListener(buttonListener); 
    
    detailPane.setViewportView(detailPanel);
    
    nameSearch();
  }
  
  private void KeyTyped(java.awt.event.KeyEvent evt) 
  {    
            if(evt.getKeyChar() == KeyEvent.VK_ENTER)
            findAgents();
  }
  
  
  public void pobulateAgentsOnPanel()
  {
    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    try
    {
                JPanel detailPanel1 =new JPanel();
                detailPanel1.setLayout(new AbsoluteLayout());
                detailPanel1.setBackground(Color.WHITE);
                String riasEmpId =empFld.getText();
                if (riasEmpId.equalsIgnoreCase(""))
                throw new ParametrizedException("Please enter Rias Employee Number");
                TeamMaintenance teamMaintenance = new TeamMaintenance();
                Employee emp = personnel.findActiveEmployeeByRiasEmpNo(riasEmpId);
                Vector empNoVec = personnel.getAllRiasEmpNums();
                if (!empNoVec.contains(riasEmpId))
                throw new ParametrizedException("Invalid employee number. Please try again");
                firstNameFld.setText(emp.getForName());
                surnameFld.setText(emp.getSurName());
                Vector teamVec = teamMaintenance.getAllActiveTeams();
                Iterator iter = teamVec.iterator();
                int i=0;
                while (iter.hasNext())
                {
                   Team team=(Team)iter.next();
                    Vector agentVec = personnel.getAllEmployeeAgents(team,emp.getEmployeeId());
                    Iterator iter2 = agentVec.iterator();
                    
                    while (iter2.hasNext())
                    {
                      Agent agent = (Agent)iter2.next();
                      EmpAgentSearchPanel empAgentSearchPanel = new EmpAgentSearchPanel(agent,team);
                      detailPanel1.add(empAgentSearchPanel, new AbsoluteConstraints(0,i,920,40));
                      i=i+40;
                    }
                    detailPane.setViewportView(detailPanel1);
                }
                this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
    catch(ParametrizedException e)
		{
			JOptionPane.showMessageDialog(this,e.getMessage());
      this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
  }
  
  public void exit()
  {
    setVisible(false);
  }
  
  public void findAgents()
  {
    if(nameRadio.isSelected())
    {
         findEmployeebyName();  
    }
    if(numRadio.isSelected())
    {
        pobulateAgentsOnPanel();    
    }
  }
  
  public class ButtonListener implements ActionListener
  {
    	public void actionPerformed(ActionEvent e)
    	{
    		if(e.getActionCommand().equals("Find"))
                {
                    findAgents();
                }
                if(e.getActionCommand().equals("Close"))
                {
                      exit();
                }
                if(e.getActionCommand().equals("Clear"))
                {
                      clear();
                }
        
        }
  }

  public class RadioListener implements ActionListener
	{
    	public void actionPerformed(ActionEvent e)
    	{
    		
        if(nameRadio.isSelected())
        {
              nameSearch();
        }
        if(numRadio.isSelected())
        {
              numSearch();
        }
      }
  }
  
  public class ComboListener implements ActionListener
  {
      public void actionPerformed(ActionEvent e)
    	{
     
              if(empNumBox.getSelectedItem()== null)
              {
                System.err.println("Wait a moment"); 
              } 
              else if(empNumBox.getSelectedItem().equals(empNumBox.getSelectedItem()))
              {
                displayEmployeeFromCombo();
              }
   
      }
  }
}