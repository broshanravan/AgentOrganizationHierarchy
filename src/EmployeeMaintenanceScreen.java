
/*
 * Created on 14-Feb-2005
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
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FocusTraversalPolicy;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.*;
import javax.swing.tree.TreePath;
public class EmployeeMaintenanceScreen extends JDialog 
{
	
	
        int refGroupId;
	private static final int hight = 430;
	private static final int width = 550;
	ButtonListener buttonListener = new ButtonListener();
	Personnel personnel = new Personnel();
	int groupId;
	MainScreen mainScreen;
        String universalEmpNo="2";
        GroupSubTree groupTree;
	//Labels
	JLabel  riasEmpNoLbl = new JLabel("RIAS Employee Number");
	JLabel  empIdLbl = new JLabel(" Employee Id Number");
	JLabel  forNameLbl = new JLabel("ForeName");
	JLabel  surnameLbl = new JLabel("Surname");
	JLabel startPlanedLbl = new JLabel("Date Start Planned");
	JLabel  endPlanedLbl = new JLabel("Date End Planned");
	JLabel  startActLbl = new JLabel("Date Start Act");
	JLabel  endActLbl = new JLabel("Date End Act");
	JLabel dobLbl = new JLabel("Date of Birth");
        JLabel emailLbl = new JLabel("Employee E-Mail");
	
	
	//TextFields
	
	JTextField   empIdFld = new JTextField ();
	JTextField   forNameFld = new JTextField ();
	JTextField  surnameFld = new JTextField ();
	JTextField  riasEmpNoFld = new JTextField ();
        JTextField  emailFld = new JTextField ();
	
	DateFormat formatter1 = new SimpleDateFormat("dd/MM/yy");
	Format formatter = new SimpleDateFormat("dd/MM/yy");
	JFormattedTextField  startPlanedFld = new JFormattedTextField (DateFormat.getDateInstance(DateFormat.SHORT));
	JFormattedTextField   endPlanedFld = new JFormattedTextField (DateFormat.getDateInstance(DateFormat.SHORT));
	JFormattedTextField   startActFld = new JFormattedTextField (DateFormat.getDateInstance(DateFormat.SHORT));
	JFormattedTextField   endActFld = new JFormattedTextField (DateFormat.getDateInstance(DateFormat.SHORT));
	JFormattedTextField  dobFld = new JFormattedTextField (DateFormat.getDateInstance(DateFormat.SHORT));
	
	
	//Buttons
	
	JButton searchButton = new JButton("Find");
	JButton addButton = new JButton("Add");
	JButton editButton = new JButton("Edit");
	JButton closeButton = new JButton("Close");
	JButton newButton = new JButton("New");
  
	public EmployeeMaintenanceScreen(int refGroupIdIn,GroupSubTree groupTreeIn,MainScreen mainScreenIn)
	{
		
                super(mainScreenIn,true);
                refGroupId=refGroupIdIn;
                mainScreen = mainScreenIn;
		setSize(width,hight);
		setTitle("Employee Maintenance Screen");
		setResizable(false);
		getContentPane().setLayout(null);
                setUpScreen();
                groupTree=groupTreeIn;                
                //this.setFocusTraversalPolicy(new FieldPolicy());
                setLocationRelativeTo(mainScreenIn);
                setVisible(true);
		
	}
        
   /*
    public EmployeeMaintenanceScreen(int refGroupIdIn,MainScreen mainScreenIn)
    {
            
            super(mainScreenIn,true);
            refGroupId=refGroupIdIn;
            mainScreen = mainScreenIn;
            setSize(width,hight);
            setTitle("Employee Maintenance Screen");
            setResizable(false);
            getContentPane().setLayout(null);
            setUpScreen();
            setVisible(true);
            
    }
    */
	public void disableFields()
	{
		forNameFld.setEditable(false); 
		surnameFld.setEditable(false); 
		//riasEmpNoFld.setEditable(false); 		
		startPlanedFld.setEditable(false);  
		endPlanedFld.setEditable(false);  
		startActFld.setEditable(false); 
		endActFld.setEditable(false);  
		dobFld.setEditable(false); 
    emailFld.setEditable(false); 
	}
	
	public void enableFields()
	{
		forNameFld.setEditable(true); 
		surnameFld.setEditable(true); 
		//riasEmpNoFld.setEditable(false); 		
		startPlanedFld.setEditable(true);  
		endPlanedFld.setEditable(true);  
		startActFld.setEditable(true); 
		endActFld.setEditable(true);  
		dobFld.setEditable(true);
    emailFld.setEditable(true);
	}
	
	public void editEmployee()
	{
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    try
		{
			Vector riasEmpNoVec = personnel.getAllRiasEmpNums();
			if(riasEmpNoFld.getText().equals(""))
				throw new ParametrizedException("PLEASE ENTER RIAS EMPLOYEE NUMBER");
			if(forNameFld.getText().equals("" ))
				throw new ParametrizedException("PLEASE ENTER RIAS EMPLOYEE FORENAME");
			if(surnameFld.getText().equals("" ))
				throw new ParametrizedException("PLEASE ENTER RIAS EMPLOYEE SURNAME");	
			
			if(startActFld.getText().equals("" ))
				throw new ParametrizedException("PLEASE ENTER RIAS EMPLOYEE START DAY ACT");
			                        
			Employee employee = new Employee();
			employee.setEmployeeId(Integer.parseInt(empIdFld.getText()));
                       
                        String newEmpNo=riasEmpNoFld.getText();
                        if((!(newEmpNo.equalsIgnoreCase(universalEmpNo)))&riasEmpNoVec.contains(newEmpNo))
                          throw new ParametrizedException(" RIAS EMPLOYEE NUMBER IN USE,PLEASE TRY AGAIN");
                        
                        employee.setRiasEmpNo(newEmpNo);
                        
			employee.setGroupId(groupId);
			employee.setForName(forNameFld.getText().trim());
			employee.setSurName(surnameFld.getText().trim());			
			employee.setDateStartPlaned((Date)startPlanedFld.getValue());			
			employee.setDateEndPlaned((Date)endPlanedFld.getValue());			
			employee.setDateStartAct((Date)startActFld.getValue());			
			employee.setDateEndAct((Date)endActFld.getValue());			
			employee.setDateOfBirth((Date)dobFld.getValue());      
                        employee.setEmail(emailFld.getText());
                        String rNum = riasEmpNoFld.getText();			
			employee.setRiasEmpNo(riasEmpNoFld.getText());
		
			personnel.editEmployee(employee);
                        if(employee.getDateEndAct()!=null) {
                          personnel.deActivateEmployeesAgent(employee.getEmployeeId());
                        }
                        
                        if(startPlanedFld.getText().equals("01-Jan-1970")){
                            startPlanedFld.setText("");
                            employee.setDateStartPlaned((Date)startPlanedFld.getValue());
			}
			if(endPlanedFld.getText().equals("01-Jan-1970")){
                            endPlanedFld.setText("");
                            employee.setDateEndPlaned((Date)endPlanedFld.getValue());
			}
			if(startActFld.getText().equals("01-Jan-1970")){
                            startActFld.setText("");
                            employee.setDateStartAct((Date)startActFld.getValue());
			}
                        if(endActFld.getText().equals("01-Jan-1970")){
                            endActFld.setText("");
                            employee.setDateEndAct((Date)endActFld.getValue());
                        }
			
			JOptionPane.showMessageDialog(this,"Employee: "+ employee.getForName()+ " " + employee.getSurName()+ " Altered");
			
                        clearFields();
		}
		catch(ParametrizedException e){
                    JOptionPane.showMessageDialog(this,e.getMessage());
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
                this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
//====================================================================================================================	
	public void AddEmployee(){
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            try{
                    Vector riasEmpNoVec = personnel.getAllRiasEmpNums();
                    if(riasEmpNoFld.getText().equals(""))
                            throw new ParametrizedException("PLEASE ENTER RIAS EMPLOYEE NUMBER");
                    if(forNameFld.getText().equals("" ))
                            throw new ParametrizedException("PLEASE ENTER RIAS EMPLOYEE FORENAME");
                    if(surnameFld.getText().equals("" ))
                            throw new ParametrizedException("PLEASE ENTER RIAS EMPLOYEE SURNAME");
                    if(startPlanedFld.getText().equals("" ))
                            throw new ParametrizedException("PLEASE ENTER RIAS EMPLOYEE START DAY PLANED");
                    if(startActFld.getText().equals("" ))
                            throw new ParametrizedException("PLEASE ENTER RIAS EMPLOYEE START DAY ACT");			
                    Employee employee = new Employee();
                    employee.setGroupId(refGroupId);
                    employee.setForName(forNameFld.getText().trim());
                    employee.setSurName(surnameFld.getText().trim());
                    
                    employee.setDateStartPlaned((Date)startPlanedFld.getValue());
                    ////System.out.println("Start Date planed is "+startPlanedFld.getValue());
                    
                    employee.setDateEndPlaned((Date)endPlanedFld.getValue());
                    ////System.out.println("End Date Planed is "+endPlanedFld.getValue());
                    
                    employee.setDateStartAct((Date)startActFld.getValue());
                    ////System.out.println("Start Date Act is "+startActFld.getValue());
                    
                    employee.setDateEndAct((Date)endActFld.getValue());
                    ////System.out.println("End Date Act is "+endActFld.getValue());
                    
                    employee.setDateOfBirth((Date)dobFld.getValue());
                    ////System.out.println("DOB "+dobFld.getValue());
  
                    employee.setEmail(emailFld.getText().trim());;
                    
                    if(startPlanedFld.getText().equals("01-Jan-1970")) {
                         startPlanedFld.setText("");
                        employee.setDateStartPlaned((Date)startPlanedFld.getValue());
                    }
                    
                    if(endPlanedFld.getText().equals("01-Jan-1970")){
                        endPlanedFld.setText("");
                        employee.setDateEndPlaned((Date)endPlanedFld.getValue());
                    }
                    
                    if(startActFld.getText().equals("01-Jan-1970")) {
                        startActFld.setText("");
                        employee.setDateStartAct((Date)startActFld.getValue());
                    }
                    if(endActFld.getText().equals("01-Jan-1970")){
                        endActFld.setText("");
                        employee.setDateEndAct((Date)endActFld.getValue());
                    }

                    String rNum = riasEmpNoFld.getText();
                    if(riasEmpNoVec.contains(rNum))
                            throw new ParametrizedException(" RIAS EMPLOYEE NUMBER IN USE");
                    employee.setRiasEmpNo(riasEmpNoFld.getText());
                    personnel.addEmployee(employee);
                    riasEmpNoVec.addElement(rNum);
                    JOptionPane.showMessageDialog(this,"Employee: "+ employee.getForName()+ " " + employee.getSurName()+ " Added");
                    clearFields();
                    setVisible(false);
                    
                    if(groupTree==null){
                        
                    }
                    
                    NewEpmAgentDialog newEpmAgentDialog = new NewEpmAgentDialog(groupTree,mainScreen , employee);			
		}
		catch(ParametrizedException e)
		{
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    JOptionPane.showMessageDialog(this,e.getMessage());
		}
                this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	public void findEmployee()
	{
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    String empNo=riasEmpNoFld.getText();
                    //System.out.println("Employee number is " +empNo);
                    //riasEmpNoFld.setText(empNo);
                    Employee emp = personnel.findEmployeeByRiasEmpNo(empNo);
                    //System.out.println("RIAS EMP NO is " +emp.getRiasEmpNo());
                    //System.out.println("firstName is is " +emp.getForName());
                    //System.out.println("SurName is " +emp.getSurName());
                    //System.out.println("DOB is " +emp.getDateOfBirth());
                    //System.out.println("Employee number is " +emp.getEmployeeId());
                    //clearFields();
                    riasEmpNoFld.setText(emp.getRiasEmpNo());
                    universalEmpNo=emp.getRiasEmpNo();
                    empIdFld.setText(String.valueOf(emp.getEmployeeId()));	
                    forNameFld.setText(emp.getForName().trim());
                    surnameFld .setText(emp.getSurName().trim());
		
                    String d1="";
                    try
                    {
                        if(!emp.getDateStartPlaned().equals(null))
                        {
                          d1= formatter1.format( emp.getDateStartPlaned());
                        }
                    }
                                         
                    catch(Exception e)
                    {
                      d1="";
                    }
                    
                    //System.out.println("d1 = " +d1);
                    String d2="";
                    
                    try
                    {
                        if(!emp.getDateEndPlaned().equals(null))
                        {
                          d2= formatter1.format( emp.getDateEndPlaned());
                        }
                    }
                     
                    catch(Exception e)
                    {
                      d2="";
                    }
                    
                    //System.out.println("d2 = " +d2);
                    String d3="";
                    
                     try
                    {
                        if(!emp.getDateStartAct().equals(null))
                        {
                          d3= formatter1.format( emp.getDateStartAct());
                        }
                    }
                    
                     
                    catch(Exception e)
                    {
                      d3="";
                    }
                    
                    //System.out.println("d3 = " +d3);
                    
                    String d4= "";
                    
                    try
                    {
                      if(!emp.getDateEndAct().equals(null))
                      {
                        d4= formatter1.format( emp.getDateEndAct());
                      }
                    }
                    catch(Exception e)
                    {
                      d4="";
                    }
                    
                    String d5="";
                    try
                    {
                      if(!emp.getDateOfBirth().equals(null))
                      {
                        d5= formatter1.format( emp.getDateOfBirth());
                      }
                    }
                    catch(Exception e)
                    {
                      d5="";
                    }
                       
                    startPlanedFld.setValue(emp.getDateStartPlaned());
                    endPlanedFld .setValue(emp.getDateEndPlaned());
                    startActFld .setValue(emp.getDateStartAct());
                    endActFld .setValue(emp.getDateEndAct());
                    dobFld .setValue(emp.getDateOfBirth());
                    
                    emailFld.setText(emp.getEmail());
                    riasEmpNoFld.setText(emp.getRiasEmpNo());
                    enableFields();
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
	public void clearFields()
	{
		riasEmpNoFld.setText(""); 
		empIdFld.setText(""); 
		forNameFld .setText(""); 
		surnameFld .setText(""); 
		startPlanedFld .setText(""); 
		endPlanedFld .setText(""); 
		startActFld .setText(""); 
		endActFld .setText(""); 
		dobFld.setText(""); 
                emailFld.setText("");
	}
	public void setUpScreen()
	{
		
		
		
		//adding textfields
		getContentPane().add(riasEmpNoFld) ;
		
		getContentPane().add(forNameFld);
		getContentPane().add(surnameFld);
		getContentPane().add(startPlanedFld);
		getContentPane().add(endPlanedFld);
		getContentPane().add(startActFld);
		getContentPane().add(endActFld);
		getContentPane().add(dobFld);
                
                getContentPane().add(empIdFld);
                empIdFld.setEditable(false);
                empIdFld.setFocusable(false);
                
                //AddingLabels
                getContentPane().add(riasEmpNoLbl) ;
                getContentPane().add(empIdLbl);
                getContentPane().add(forNameLbl);
                getContentPane().add(surnameLbl);
                getContentPane().add(startPlanedLbl);
                getContentPane().add(endPlanedLbl);
                getContentPane().add(startActLbl);
                getContentPane().add(endActLbl);
                getContentPane().add(dobLbl);
	    
	    getContentPane().add(emailLbl);
	    getContentPane().add(emailFld);
            
                
		//Adding Buttons
		getContentPane().add(searchButton) ;
		getContentPane().add(addButton) ;
		addButton.setEnabled(false);
		getContentPane().add(editButton) ;
		getContentPane().add(closeButton) ;
		getContentPane().add(newButton) ;
                
                searchButton.setFocusable(false);
                addButton.setFocusable(false);
                editButton.setFocusable(false);
                closeButton.setFocusable(false);
                newButton.setFocusable(false);
		
		//Adding Button Listeners
		searchButton.addActionListener(buttonListener);
		addButton.addActionListener(buttonListener); 
		editButton.addActionListener(buttonListener); 
		closeButton.addActionListener(buttonListener);
		newButton.addActionListener(buttonListener);
		
		//Setting Bounds
		riasEmpNoLbl.setBounds(20,30,150,20) ;
		riasEmpNoFld.setBounds(170,30,150,20) ;
		
		empIdLbl.setBounds(20,60,150,20);
		empIdFld.setBounds(170,60,150,20);
		
		forNameLbl.setBounds(20,90,150,20);
		forNameFld.setBounds(170,90,150,20);
		
		surnameLbl.setBounds(20,120,150,20);
		surnameFld.setBounds(170,120,150,20);
		
		startPlanedLbl.setBounds(20,150,150,20);
		startPlanedFld.setBounds(170,150,150,20);
		
		endPlanedLbl.setBounds(20,180,150,20);
		endPlanedFld.setBounds(170,180,150,20);
		
		startActLbl.setBounds(20,210,150,20);
		startActFld.setBounds(170,210,150,20);
		
		endActLbl.setBounds(20,240,150,20);
		endActFld.setBounds(170,240,150,20);
		
		dobLbl.setBounds(20,270,150,20);
		dobFld.setBounds(170,270,150,20);
    
                emailLbl.setBounds(20,300,150,20);
		emailFld.setBounds(170,300,150,20);
		
		searchButton .setBounds(400,30,100,25);
		newButton.setBounds(20,330,100,25);
		addButton.setBounds(150 ,330,100,25);
		editButton.setBounds(290,330,100,25) ;
		closeButton.setBounds(420,330,100,25);
		//startPlanedFld.setValue( date) ;
		startPlanedFld.setText("");
		//endPlanedFld.setValue( date) ;
		endPlanedFld.setText("");
		//startActFld.setValue( date) ;
		startActFld.setText("");
		//endActFld.setValue( date) ;
		endActFld.setText("");
		//dobFld.setValue( date) ;
		dobFld.setText("");
		disableFields();
		
		
                startPlanedFld.addKeyListener(new java.awt.event.KeyAdapter() 
                {
                    public void keyTyped(java.awt.event.KeyEvent evt) 
                    {
                        KeyTyped1(evt);
                    }

               });
              
              endPlanedFld.addKeyListener(new java.awt.event.KeyAdapter() 
              {
                  public void keyTyped(java.awt.event.KeyEvent evt) 
                  {
                      KeyTyped2(evt);
                  }

             });
            startActFld.addKeyListener(new java.awt.event.KeyAdapter() 
            {
                public void keyTyped(java.awt.event.KeyEvent evt) 
                {
                    KeyTyped3(evt);
                }

            });
            endActFld.addKeyListener(new java.awt.event.KeyAdapter() 
            {
                public void keyTyped(java.awt.event.KeyEvent evt) 
                {
                    KeyTyped4(evt);
                }

            });
 
	}
	

  private void KeyTyped1(java.awt.event.KeyEvent evt) 
  {
    //System.out.println("keyTyped" +evt.getKeyChar());
    if((evt.getKeyChar() == KeyEvent.VK_DELETE)||(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE))
    {
      if(startPlanedFld.getText().equalsIgnoreCase(""))
      {
        startPlanedFld.setValue(null);
      }
    }
          
  }
  private void KeyTyped2(java.awt.event.KeyEvent evt) 
  {
    //System.out.println("keyTyped" +evt.getKeyChar());
    if((evt.getKeyChar() == KeyEvent.VK_DELETE)||(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE))
    {
      if(endPlanedFld.getText().equalsIgnoreCase(""))
      {
        endPlanedFld.setValue(null);
      }
    }
          
  }
  private void KeyTyped3(java.awt.event.KeyEvent evt) 
  {
    //System.out.println("keyTyped" +evt.getKeyChar());
    if((evt.getKeyChar() == KeyEvent.VK_DELETE)||(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE))
    {
      if(startActFld.getText().equalsIgnoreCase(""))
      {
        startActFld.setValue(null);
      }
    }
           
  }
  
  private void KeyTyped4(java.awt.event.KeyEvent evt) 
  {
    //System.out.println("keyTyped" +evt.getKeyChar());
    if((evt.getKeyChar() == KeyEvent.VK_DELETE)||(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE))
    {
      if(endActFld.getText().equalsIgnoreCase(""))
      {
        endActFld.setValue(null);
      }
    }
           
  }
	
	public class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getActionCommand().equals("New"))
			{
				clearFields();
				enableFields();
				addButton.setEnabled(true);
			    riasEmpNoFld.setFocusable(true);
			}
			
			if(e.getActionCommand().equals("Add"))
			{
				AddEmployee();		
			}
			if(e.getActionCommand().equals("Find"))
			{	
				findEmployee();
				
			}
			if(e.getActionCommand().equals("Edit"))
			{
				editEmployee();
			}
			if(e.getActionCommand().equals("Close"))
			{
				setVisible(false);
			}
		}
	}
        
        
        
  

    //AlphaButtonPolicy.java
    //A custom focus traversal policy that uses alphabetical ordering of button
    //labels to determine "next" and "previous" buttons for keyboard traversal.
    //

    class FieldPolicy extends FocusTraversalPolicy {

      private SortedMap getSortedButtons(Container focusCycleRoot) {
        if (focusCycleRoot == null) {
          throw new IllegalArgumentException("focusCycleRoot can't be null");
        }
        SortedMap result = new TreeMap(); // Will sort all buttons by text.
        sortRecursive(result, focusCycleRoot);
        return result;
      }

      private void sortRecursive(Map fields, Container container) {
        for (int i = 0; i < container.getComponentCount(); i++) {
          Component c = container.getComponent(i);
          if (c instanceof JTextField) { // Found another button to sort.
            fields.put(((JTextField) c).getText(), c);
          }
          if (c instanceof Container) { // Found a container to search.
            sortRecursive(fields, (Container) c);
          }
        }
      }

      // The rest of the code implements the FocusTraversalPolicy interface.

      public Component getFirstComponent(Container focusCycleRoot) {
        SortedMap fields = getSortedButtons(focusCycleRoot);
        if (fields.isEmpty()) {
          return null;
        }
        return (Component) fields.get(fields.firstKey());
      }

      public Component getLastComponent(Container focusCycleRoot) {
        SortedMap fields = getSortedButtons(focusCycleRoot);
        if (fields.isEmpty()) {
          return null;
        }
        return (Component) fields.get(fields.lastKey());
      }

      public Component getDefaultComponent(Container focusCycleRoot) {
        return getFirstComponent(focusCycleRoot);
      }

      public Component getComponentAfter(Container focusCycleRoot,
          Component aComponent) {
        if (!(aComponent instanceof JTextField)) {
          return null;
        }
        SortedMap fields = getSortedButtons(focusCycleRoot);
        // Find all buttons after the current one.
        String nextName = ((JTextField) aComponent).getText() + "\0";
        SortedMap nextFields = fields.tailMap(nextName);
        if (nextFields.isEmpty()) { // Wrapped back to beginning
          if (!fields.isEmpty()) {
            return (Component) fields.get(fields.firstKey());
          }
          return null; // Degenerate case of no buttons.
        }
        return (Component) nextFields.get(nextFields.firstKey());
      }

      public Component getComponentBefore(Container focusCycleRoot,
          Component aComponent) {
        if (!(aComponent instanceof JButton)) {
          return null;
        }

        SortedMap fields = getSortedButtons(focusCycleRoot);
        SortedMap prevFields = // Find all buttons before this one.
        fields.headMap(((JButton) aComponent).getText());
        if (prevFields.isEmpty()) { // Wrapped back to end.
          if (!fields.isEmpty()) {
            return (Component) fields.get(fields.lastKey());
          }
          return null; // Degenerate case of no buttons.
        }
        return (Component) prevFields.get(prevFields.lastKey());
      }
    }

        
        

}

