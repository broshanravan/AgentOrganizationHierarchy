import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;
import java.awt.*;

public class ReactivateGroupDialog  extends JDialog 
{
  
  private static final int height = 120;
  private static final int width = 340;
  ButtonListener buttonListener = new ButtonListener();
  GroupInventory groupInventory = new GroupInventory();
  Vector groupVec=null;
 
  JLabel grouplbl = new JLabel("Group Name");
  JComboBox groupCombo = new JComboBox();
  
  JButton activeBtn = new JButton("Reactivate");
  JButton cancelBtn = new JButton("Close");
  
  public ReactivateGroupDialog(MainScreen ms)
  {
      super(ms,true);
      setModal(true);
      groupVec = groupInventory.getAllInActiveGroups();
      setSize(width, height);
      getContentPane().setLayout(null);
      setResizable(false);
      setTitle("Group Reactivation Dialog"); 
      setupScreen();
      setLocationRelativeTo(ms);
      setVisible(true);
  }
  public ReactivateGroupDialog()
  {
      setModal(true);
      groupVec = groupInventory.getAllInActiveGroups();
      setSize(width, height);
      getContentPane().setLayout(null);
      setResizable(false);
      setTitle("Group Reactivation Dialog"); 
      setupScreen();
      setVisible(true);
  }
  public void setupScreen()
  {
    getContentPane().add(grouplbl); 
    getContentPane().add(groupCombo);  
    getContentPane().add(activeBtn);
    getContentPane().add(cancelBtn);
    
    grouplbl.setBounds(20,20,80,20) ;
    groupCombo.setBounds (120,20,200,20); 
    activeBtn.setBounds(20,50,100,25);
    cancelBtn.setBounds(220,50,100,25);
    groupCombo.setBackground(Color.WHITE);
    activeBtn.addActionListener(buttonListener );
    cancelBtn.addActionListener(buttonListener ); 
    populateGroupCombo();
  }
  
  public void populateGroupCombo()
  {
    groupCombo.removeAllItems();
    groupCombo.addItem("Group Name"); 
    Iterator iter = groupVec.iterator();
    while(iter.hasNext())
    {
      Group group = (Group)iter.next();
      String n = group.getGroupName();
      groupCombo.addItem(n);
      
    }
  }
  public void exit()
  {
    this.setVisible(false);
  }
  
  public void reAcativateGroup()
  {
     this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     try
        {
          String groupName = (String)groupCombo.getSelectedItem();
          if(groupName.equalsIgnoreCase("Please select"))
            throw new ParametrizedException("Please select a Group from the list ");
          Group group = null;
          Iterator iter = groupVec.iterator();
          while(iter.hasNext())
          {
            Group g = (Group)iter.next();
            String gn = g.getGroupName();
            gn=gn.trim();
            if(groupName.trim().equalsIgnoreCase(gn))
            {
              group = g;
            }
            groupInventory.activateGroup(group.getGroupId());
            
          }
          populateGroupCombo();
          JOptionPane.showMessageDialog(this,"Group " +group.getGroupName()+ " is now reactivated" );
				  
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
	    			reAcativateGroup();
	    		}

	    	}
    }
}