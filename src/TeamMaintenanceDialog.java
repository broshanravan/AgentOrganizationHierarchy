
/*
 * Created on 18-Feb-2005
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.*;
import java.text.DecimalFormat;

public class TeamMaintenanceDialog extends JDialog
{
	private int height =210;
	private int width=400;
	int groupId;
	ButtonListener buttonListener= new ButtonListener();
	TeamMaintenance teamMaintenance = new TeamMaintenance();  
        CheckBoxListener checkBoxListener = new CheckBoxListener();
  
	//labels
	JLabel nameLbl = new JLabel("Team Name");
        JLabel bFLbl = new JLabel("Business Function");
	JLabel cobLbl = new JLabel("ClassOf Business");
        JLabel permLbl = new JLabel("Is Induction?");
  
	//textFields
	JTextField nameFld = new JTextField();
	JTextField ansFld = new JTextField();
	
	//Buttons
	//JButton editBtn = new JButton("Edit");
	JButton addBtn = new JButton("Add");
	
	//JButton findBtn = new JButton("Find");
	JButton exitBtn = new JButton("Close");
  
        //Combo box
        JComboBox bFCombo = new JComboBox();
        JComboBox cobCombo = new JComboBox();
        
        JCheckBox indBox = new JCheckBox();
        GroupSubTree groupTree;
        MainScreen ms;
	public TeamMaintenanceDialog(int groupIdIn, GroupSubTree groupTreeIn,MainScreen msIn)
	{
                super(msIn,true);
                ms=msIn;                
		groupId = groupIdIn;
		setSize(width,height);
		setTitle("Teams Maintenance Screen");
		getContentPane().setLayout(null);
		setUpScreen();
                groupTree=groupTreeIn;
                setLocationRelativeTo(ms);
		setVisible(true);
	}
	
	public void addTeam()
	{
              this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
              TeamMaintenance  teamMaintenance = new TeamMaintenance();
              GroupInventory groupInv= new GroupInventory();
              MaintainDepartment maintainDep = new MaintainDepartment();
              try
              {
                        
                      if (nameFld.getText().equalsIgnoreCase(""))  
                        throw new ParametrizedException("Please Enter team Name");
                        
                      String function = (String)bFCombo.getSelectedItem();
                      if (function.equalsIgnoreCase("Please Select"))  
                        throw new ParametrizedException("Please Select a Bussiness function");
                       
                       String cobName =  (String)cobCombo.getSelectedItem();  
                      if (cobName.equalsIgnoreCase("Please Select"))  
                        throw new ParametrizedException("Please Select a Class of Buisness");  
                        
                      String teamName = nameFld.getText().trim();
                      
                      Group g=groupInv.getGroup(groupId);
                      int depId= g.getDepartmentId();                    
                      Department dep = maintainDep.getDepartment(depId);
                      int siteId = dep.getDepartmentSiteId();                      
                      Vector teamNamesVec= teamMaintenance.getAllSiteActiveTeamNames(siteId);
                      
                      if (teamNamesVec.contains(teamName))  
                        throw new ParametrizedException("Team name in use. Please try again");
                                            
                      //System.out.println("groupId going to Db is" + groupId);
                      //System.out.println("Team Name  to Db is" + teamName);
                      BusinessFunctionInventory bFInv = new BusinessFunctionInventory();                      
                      String bfName =(String) bFCombo.getSelectedItem();                      
                      //System.out.println("BF Name going to inventory is: "+ bfName);                      
                      BusinessFunction bf = bFInv.getBusinessFunctionByName(bfName);
                      int bFId = bf.getBusFunId();                      
                      ClassOfBusinessInventory cobInv = new ClassOfBusinessInventory();                      
                      ClassOfBusiness cob = cobInv.getCobByName(cobName);
                      String cobId = cob.getCobId();                       
                      cob.show();
                      bf.show();
                      
                      boolean perm=true;                      
                      if(indBox.isSelected())
                      {
                        perm=false;
                      }                      
                      teamMaintenance.storeTeam(teamName,groupId,bFId,cobId,perm);
                      
                      clearFields();
                      
                      JOptionPane.showMessageDialog(this, "Team " + teamName + " is now added to system");
                      ms.refreshGroupNode(groupTree);
                      this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                      setVisible(false);
              }
              catch(ParametrizedException e)
              {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                JOptionPane.showMessageDialog(this,e.getMessage());
                this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
              }
              
	}
  
  public void clearFields()
  {
    nameFld.setText("");
    bFCombo.setSelectedItem("Please Select");
    cobCombo.setSelectedItem("Please Select");
  }
	private void setUpScreen()
	{
		//adding Labels
		getContentPane().add(nameLbl);
    
    getContentPane().add(indBox);
   
    getContentPane().add(ansFld); 
		getContentPane().add(permLbl);
    
		//adding text Fields
		getContentPane().add(nameFld);
    
    getContentPane().add(bFCombo);
    getContentPane().add(bFLbl);
    
    getContentPane().add(cobCombo);
    getContentPane().add(cobLbl);
       	
    //AddingButtons	 
    getContentPane().add(addBtn);
    getContentPane().add(exitBtn );
		
    ansFld.setEditable(false);
    ansFld.setBackground(Color.WHITE);
    ansFld.setText("NO");
    
    indBox.setSelected(false);    
    //Adding Button Listeners
    exitBtn.addActionListener(buttonListener); 
    addBtn.addActionListener(buttonListener);     
    bFCombo.addItem("Please Select");    
    cobCombo.addItem("Please Select");
    
    //setting Bounds
    nameLbl.setBounds(10,30,140,20);
    nameFld.setBounds(150,30,210,20);
		
    bFLbl.setBounds(10,60,140,20);
    bFCombo.setBounds(150,60,210,20);
    
    cobLbl.setBounds(10,90,140,20);
    cobCombo.setBounds(150,90,210,20);
    
    permLbl.setBounds(10,120,140,20);
    indBox.setBounds(210,120,20,20);
    ansFld.setBounds(150,120,50,20);
    
    addBtn.setBounds(10,150,100,20);;		
    exitBtn.setBounds(260,150,100,20);
    
    indBox.addActionListener(checkBoxListener);
    
    pupulateCobCombo();
    pobulateFunctionCombo();
		
    addBtn.addKeyListener(new java.awt.event.KeyAdapter() 
                                                          {
                                                              public void keyTyped(java.awt.event.KeyEvent evt) 
                                                              {
                                                                  KeyTyped(evt);
                                                              }
                                                          });
	 
   nameFld.addKeyListener(new java.awt.event.KeyAdapter() 
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
           addTeam();
  }

  
  public void pupulateCobCombo()
  {
    ClassOfBusinessInventory cobInv = new ClassOfBusinessInventory();
    Vector cobVec = cobInv.getAllCobs();
    Iterator iter = cobVec.iterator();
    while(iter.hasNext())
    {
        ClassOfBusiness cob = (ClassOfBusiness)iter.next();
        String name = cob.getCobName();
        cobCombo.addItem(name);
    } 
  }
  
  public void pobulateFunctionCombo()
  {
    BusinessFunctionInventory bFInv = new BusinessFunctionInventory();
    Vector bFVec = bFInv.getAllBusinessFunctions();
    Iterator iter = bFVec.iterator();
    while(iter.hasNext())
    {
        BusinessFunction bf = (BusinessFunction)iter.next();
        String bFName = bf.getBusFunName();
        bFCombo.addItem(bFName);
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
    		
    		if(e.getActionCommand().equals("Add"))
            {
               addTeam();
            }

    	}
  }
  
  public class CheckBoxListener implements ActionListener
  {
          public void actionPerformed(ActionEvent e)
          {
                  if(indBox.isSelected())
                  {
                          ansFld.setText("YES");
                  }
                  else
                  {
                          ansFld.setText("NO");
                  }
          }
 }
}

