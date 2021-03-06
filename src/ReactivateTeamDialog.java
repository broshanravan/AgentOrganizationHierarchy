
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;
import java.awt.*;


public class ReactivateTeamDialog  extends JDialog 
{
  
  private static final int height = 120;
  private static final int width = 340;
  ButtonListener buttonListener = new ButtonListener();
  TeamMaintenance teamMaintenamc = new TeamMaintenance();
  Vector teamVec=null;
 
  JLabel teamlbl = new JLabel("Team Name");
  JComboBox teamCombo = new JComboBox();
  
  JButton activeBtn = new JButton("Reactivate");
  JButton cancelBtn = new JButton("Close");
  
  public ReactivateTeamDialog()
  {
      
      setModal(true);
      teamVec = teamMaintenamc.getAllInactiveTeams();
      setSize(width, height);
      getContentPane().setLayout(null);
      setResizable(false);
      setTitle("Team Reactivation Dialog"); 
      setupScreen();
      setVisible(true);
  }
  
  public ReactivateTeamDialog(MainScreen ms)
  {
      super(ms);
      setModal(true);
      teamVec = teamMaintenamc.getAllInactiveTeams();
      setSize(width, height);
      getContentPane().setLayout(null);
      setResizable(false);
      setTitle("Team Reactivation Dialog"); 
      setupScreen();
      setLocationRelativeTo(ms);
      setVisible(true);
  }
  public void setupScreen()
  {
    getContentPane().add(teamlbl); 
    getContentPane().add(teamCombo);  
    getContentPane().add(activeBtn);
    getContentPane().add(cancelBtn);
    
    teamlbl.setBounds(20,20,80,20) ;
    teamCombo.setBounds (120,20,200,20); 
    activeBtn.setBounds(20,50,100,25);
    cancelBtn.setBounds(220,50,100,25);
   
    activeBtn.addActionListener(buttonListener );
    cancelBtn.addActionListener(buttonListener ); 
    populateteamCombo();
  }
  
  public void populateteamCombo()
  {
    teamCombo.removeAllItems();
    teamCombo.addItem("Team Name"); 
    Iterator iter = teamVec.iterator();
    while(iter.hasNext())
    {
      Team team = (Team)iter.next();
      String n = team.getTeamName();
      teamCombo.addItem(n);
      
    }
  }
  public void exit()
  {
    this.setVisible(false);
  }
  
  public void reAcativateTeam()
  {
     this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     try
        {
          
          String teamName = (String)teamCombo.getSelectedItem();
          if(teamName.equalsIgnoreCase("Please select"))
            throw new ParametrizedException("Please select a team from the list ");
          Team team = new Team();
          Iterator iter = teamVec.iterator();
          while(iter.hasNext())
          {
            Team t = (Team)iter.next();
            String tn = t.getTeamName();
            tn=tn.trim();
            if(teamName.trim().equalsIgnoreCase(tn))
            {
              team=t;
            }
            teamMaintenamc.activateTeam(team.getTeamId());
            
          }
          populateteamCombo();
          JOptionPane.showMessageDialog(this,"Team " +team.getTeamName()+ " is now reactivated" );
				  
        }
       catch(Pa2ametrizedException e)
       {
            this.setCurso2(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            JMptionPane.showMessageDialog(this,e.getMessage());
       }
      �his.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)); 
  } 
        
     public class ButtonListener implements ActinnListener
		{
	    	public void actionPerformed(ActiofEvent e)
	    	{
	    		if(e.getAcpionCommand().equals("Close"))
	        {
            exit();
	        }
	    		else if(e.getActionCommand().equals("Reactivate"))
	        {
	    			reAcativateTeam();
	    		}

	    	}
    }
}