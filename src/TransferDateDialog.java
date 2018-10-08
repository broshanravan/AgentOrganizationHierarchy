
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.*;
import javax.swing.*;



/*
 * Created on 01-Mar-2005
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
public class TransferDateDialog extends JDialog

{
	
		private int height =130;
		private int width=250;
		Date transferDate;
		int destNodeId;
		int dragedNodeId;
		String destinationNodeClass;
                DateFormat formatter1 = new SimpleDateFormat("dd/MM/yy");
		int key;
		ButtonListener buttonListener= new ButtonListener();
		TeamMaintenance teamMaintenance = new TeamMaintenance();
		//labels
		JLabel nameLbl = new JLabel("Transfer Date");
		//JLabel teamMngrLbl = new JLabel("Team Manager");
		Vector agentsVec = null;
		//textFields
		JFormattedTextField dateFld = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));
		
		
		//Buttons
		JButton okBtn = new JButton("OK");
                JButton cancelBtn = new JButton("Cancel");
                TeamSubTree teamSubTree;
                GroupSubTree destGroupSubTree ;
                GroupSubTree origGroupSubTree;
                TeamSubTree origTeamSubTree;
                TeamSubTree destTeamSubTree;
    
                MainScreen ms;
		
		public TransferDateDialog(int nodeId, int destId, String destClass,TeamSubTree teamSubTreeIn, MainScreen msIn)
		{
                        super(msIn);
			destNodeId=destId;
			dragedNodeId=nodeId;
                        //System.out.println("draged node Id in dialouge is "+ dragedNodeId);
			destinationNodeClass=destClass;
			setSize(width,height);
			setTitle("Transfer Date Screen");
			getContentPane().setLayout(null);
			setUpScreen();
                        teamSubTree=teamSubTreeIn;
                        ms=msIn;
                        setLocationRelativeTo(ms);
			setVisible(true);
		}
                
                public TransferDateDialog(int nodeId, int destId, String destClass,GroupSubTree origGroupSubTreeIn,GroupSubTree destGroupSubTreeIn, MainScreen msIn) {                        
                        super(msIn);
                        destNodeId=destId;
                        dragedNodeId=nodeId;
                        //System.out.println("draged node Id in dialouge is "+ dragedNodeId);
                        destinationNodeClass=destClass;
                        setSize(width,height);
                        setTitle("Transfer Date Screen");
                        getContentPane().setLayout(null);
                        setUpScreen();
                        origGroupSubTree=origGroupSubTreeIn;
                        destGroupSubTree=destGroupSubTreeIn;
                        ms=msIn;
                    setLocationRelativeTo(ms);
                        setVisible(true);
                }
                
                public TransferDateDialog(Vector agentsVecIn, int destId, String destClass,TeamSubTree origTeamSubTreeIn,TeamSubTree destTeamSubTreeIn, MainScreen msIn){
			super(msIn);
                        destNodeId=destId;
                        agentsVec =agentsVecIn;
			//dragedNodeId=nodeId;
			destinationNodeClass=destClass;
			setSize(width,height);
			setTitle("Transfer Date Screen");
			getContentPane().setLayout(null);
			setUpScreen();
                        origTeamSubTree=origTeamSubTreeIn;
                        destTeamSubTree=destTeamSubTreeIn;
                        ms=msIn;
                        setLocationRelativeTo(ms);
                        setModal(true);
			setVisible(true);
		}
		
		public void comitTransfer(){
			this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        transferDate=(Date)dateFld.getValue();
			if(destinationNodeClass.equalsIgnoreCase("class GroupSubTree"))
			{
                            TeamMaintenance teamMaintenance = new TeamMaintenance();
                            teamMaintenance.relocateTeam(dragedNodeId,destNodeId,transferDate);
				
			}
			else if(destinationNodeClass.equalsIgnoreCase("class TeamSubTree"))
			{
				MaintainAgentTeamHistory maintainAgentTeamHistory = new MaintainAgentTeamHistory();
				Iterator iter = agentsVec.iterator();
                                while (iter.hasNext())
                                {
                                    Agent agent = (Agent)iter.next();
                                    maintainAgentTeamHistory.transferAgent(agent.getId(),destNodeId,transferDate);
                                }
                       }
                       this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			
		}
		private void setUpScreen()
		{
			//adding Labels
			getContentPane().add(nameLbl);

			
			//adding text Fields
			getContentPane().add(dateFld);

			
			//AddingButtons
			getContentPane().add(okBtn );
			getContentPane().add(cancelBtn );
      
			//Adding Button Listeners
			okBtn.addActionListener(buttonListener);
                        cancelBtn.addActionListener(buttonListener);
      
			//setting Bounds
			dateFld.setValue(new Date());
			nameLbl.setBounds(10,30,100,20);
			dateFld.setBounds(110,30,100,20);	
			okBtn.setBounds(10,70,100,20) ;
                        cancelBtn.setBounds(130,70,100,20) ;
				
			
		}
		
		public class ButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				if(e.getActionCommand().equals("OK"))
				{
					comitTransfer();
					setVisible(false);
				}
                                if(e.getActionCommand().equals("Cancel"))
				{
					if(destinationNodeClass.equalsIgnoreCase("class GroupSubTree")){                                            
                                            
                                            System.out.println("GRoups is Transfer dialoug are"+destGroupSubTree.getGroupName()+" "+
                                            origGroupSubTree.getGroupName());
                                            ms.refreshGroupNode(destGroupSubTree);
                                            ms.refreshGroupNode(origGroupSubTree);
                                        }
                                        else{
                                            
                                            //dest Teams go hese
                                            ms.refreshTeamNode(origTeamSubTree );
                                            ms.refreshTeamNode(destTeamSubTree);
                                        }
                                        setVisible(false);
				}
			}
   			

    	}
}
