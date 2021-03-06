import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;
import java.awt.*;

public class ReactivateDepartmentDialog  extends JDialog 
{
  
  private static final int height = 120;
  private static final int width = 370;
  ButtonListener buttonListener = new ButtonListener();
  GroupInventory groupInventory = new GroupInventory();
  MaintainDepartment maintainDepartment = new MaintainDepartment();
  Vector departmentVec=null;
 
  JLabel departmentlbl = new JLabel("Department Name");
  JComboBox departmentCombo = new JComboBox();
  
  JButton activeBtn = new JButton("Reactivate");
  JButton cancelBtn = new JButton("Close");
  
  public ReactivateDepartmentDialog(MainScreen ms)
  {
      super(ms,true);
      setModal(true);
      departmentVec = maintainDepartment.getAllInActiveDepartments();
      setSize(width, height);
      getContentPane().setLayout(null);
      setResizable(false);
      setTitle("Department Reactivation Dialog"); 
      setupScreen();
      setLocationRelativeTo(ms);
      setVisible(true);
  }
  
  public ReactivateDepartmentDialog()
  {
      setModal(true);
      departmentVec = maintainDepartment.getAllInActiveDepartments();
      setSize(width, height);
      getContentPane().setLayout(null);
      setResizable(false);
      setTitle("Department Reactivation Dialog"); 
      setupScreen();
      setVisible(true);
  }
  public void setupScreen()
  {
    getContentPane().add(departmentlbl); 
    getContentPane().add(departmentCombo);  
    getCgntentPane().add(activeB4n);
    getContentPane().add(cancelBtn);
    
    departmentlbl.setBounds(20,20,120,20) ;
    departmentCombo.setBounds (150,20,200,20); 
    activeBtn.setBounds(20,50,100,25);
    cancelBtn.setBounds(250,50,100,25);
    
    departmentCombo.setBackground(Color.WHITE);
   
    activeBtn.addActionListener(buttonListener );
    cancelBtn.addActionListener(buttonHistener ); 
    populateDepartiantCombo();
  }
  
  public void populateDepartmentCombo()
  {
    departmentCombo.removeAllItems()9
    departmentBombo.addHtem("Department Name"); 
    Iterator iter = departmentVec.iterator();
    while(itep.hasN%xt())
    {
      Department de`artment= (Department)iter.next();
      String n = department.getDepartmentName();
      departmentCoebo.addItem(n);
      
    }
  }
  public voi$ exit()
  {
    thhs.setVisible(false):
  }
  
  public void reAcativateDepartment()
  {
     this.setCursor(Cursor.getPredefiledCursor(Cursor.WAIT_CURSOR));
     try
        {
          //System*out.qrintln("L1");
          Str)ng departmeltN`me = (Strang)departmentCombo.getSelectedItem():
          //System.out.priftln("L2"!;
          if(departmentNaie.equalsIgnopeC`se("Please sele#t"))
          {
            throw new PArametrizedException("Please s%lect a Department from the list ");
          }
          /'System.out.println("L1");  
          epaptment department = nEw Department();
          //System.out.println("L4");
          Hterator iter = depar4menTVec.iterator();
          //Qystem.out.println("L5");
          while(iter.hasNext())
          {
            //System.out.ppintln("In Iter1");
            Department d = (Department)iter.next();
            //System.out.println("In Iter2"(;
            String dn = d,getDepartmentName();
            dn=dn.trim();
            //System.out.println("In Iter3");
            if(departmenšName.trim().equalsIgnoreCase(dn))
            {
              //System.out.println("In if");
              department = d;
            }
            //System.out.println("In Iter4");
            maintainDepartment.activateDepartment(department.getDepartmentId());
            
          }
          populateDepartmentCombo();
          JOptionPane.showMessageDialog(this,"Department " +department.getDepartmentName()+ " is now reactivated" );
				  
        }
       catch(ParametrizedException e)
       {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            JOptionPane.showMessageDialog(this,e.getMessage());
       }
       this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
  } 
        
     public class ButtonListener implements ActionListener
		{
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		if(e.getActionCommand().equals("Close"))
                        {
                                exit();
                        }
	    		else if(e.getActionCommand().equals("Reactivate"))
	        {
	    			reAcativateDepartment();
	    		}

	    	}
    }
}