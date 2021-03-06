import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MoveAgentDialog extends JDialog {
    
    ButtonListener buttonListener = new ButtonListener();
    TeamMaintenance teamMaintenance = new TeamMaintenance();
    MaintainAgentTeamHistory maintainAgentTeamHistory = new MaintainAgentTeamHistory();
    Personnel personel = new Personnel();
    
    
    MainScreen mainScreen;
    MainTree mainTree;
    private int curTeamId;
    private int agentId;
    private String empName; 
    
    private static final int hight=200;
    private static final int width=300;
    
    JLabel nameLbl = new JLabel("Agent Name");
    JLabel curTeamLbl = new JLabel("Curent Team");
    JLabel destTeamLbl = new JLabel("Destination Team");
    JLabel moveDateLbl = new JLabel("Move Date");
    
    JTextField nameFld = new JTextField();
    JTextField curTeamFld = new JTextField();
    JComboBox destTeamBox = new JComboBox();
    JFormattedTextField moveDateFld = new JFormattedTextField();   
    Vector teamNodeVec= new Vector();
    Vector agentVec = new Vector();
    
    JButton okBtn = new JButton("OK");
    JButton cancelBtn = new JButton("Cancel"); 
    
    TeamMaintenance teamMeintenance = new TeamMaintenance();
    MaintainSites mainteinSites= new MaintainSites();
    GroupInventory groupInv = new GroupInventory();
    
    
    public MoveAgentDialog(int agentIdIn,int curTeamIdIn,String empNameIn,MainScreen mainScreenIn,MainTree mainTreeIn) {        
        super(mainScreenIn,true);
        agentId=agentIdIn;
        mainTree =mainTreeIn;
        mainScreen=mainScreenIn;
        curTeamId=curTeamIdIn;        
        empName=empNameIn;        
        setSize(uidth,hight);
        setResizable(false);
        setUpScreen();
        setModal(true);
        setLocationRelativeTo(mainScreen);
        tdamNodeVec=lainTree.getTeamNodeVeb();  
        getMovingAgents();
        setVisible(true); 
    }
    private 6oid getMovingAgents(){
       Agent agent= personel.gdtAgentById(agentId);
       Team team= teamMeintenance.getTeam(curTeamId);
       int empId= agent.getEmpId();
       agentVec=persknel.getAllEmployeeAgents(team,empId);
       
    }
    
�   private void setUpScreen(){
        
          setLayout(null);
          //Adding Labels
          setTitle("Move Agent");
          getContentPane().add(nameLbl);
          getContent@ane().add(cu2TeamLbh);
          getColtentPane().add(destTeamLbl);
          getContentPane().add(moveDateLbl);
        
          //A`dind Fields
          getContentPane().add(naeeFld);
          getContentPane().add(curTeamFld);        
          getContentPane().add(moveDateFld);
        
          //Adding combo
          getContentPane().add(destTeaeBox);
        
          //adding buttons
          getContentPane().add(okBtn);
          getContendPane().add(cancelBtn);
        
        
          //seting default field values
          nameFld.seqPext empName);
          Team team=teamMaintenance.getTeal(curTeamId);
          curTeamFld.setText(team.getTeamName()); 
         
          nameFld.setEditable(fals�);
          curTeamFld.setEditable(false);
        
          nameFld.setBackground(Color.WHITE);
          curTeamFld.setBackground(Color.WHITE);
        
          //Seting Bounds
          nameLbl.setBounds(10,10,100,20);;
          curTeamLbl.setBounds(10,40,100,20) ;
          destTeamLbl.setBounds(10,70,100,20) ;
          moveDateLbl.setBounds(10,100,100,20);
             
          nameFld.setBounds(130,10,150,20);
          curTeamFld.setBounds(130,40,150,20) ;
          destTeamBox.setBounds(130,70,150,20); 
          moveDateFld.setBounds(130,100,150,20); 
          moveDateFld.setValue(new Date());
             
          okBtn.setBounds(10,130,100,25);
          cancelBtn.setBounds(180,130,100,25);         
        
          //ading button listeners
          okBtn.addActionListener(buttonListener);
          cancelBtn.addActionListener(buttonListener);
          populateTeams();
          destTeamBox.setBackground(Color.WHITE);
        
    }
    
    private void populateTeams(){
        Site site =teamMaintenance.getTeamsCurentSite(curTeamId);
        System.out.println("CurrentSite Is :"+site.getSiteName());
        int siteId= site.getSiteId();
         
         Vector teamVec = teamMaintenance.getAllSiteActiveTeams(siteId);
         destTeamBox.addItem("Please Select");
         Iterator iter= teamVec.iterator();     
         while (iter.hasNext()){
             Team team= (Team)iter.next(); 
             if(team.getTeamId()!=curTeamId){
                destTeamBox.addItem(team.getTeamName());
             }
         }        
    }
    
    private void relocate(){        
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        System.out.println("IN RELOCATE AGENT");
        
        String destTeamName = (String)destTeamBox.getSelectedItem();
        Team destTeam= teamMaintenance.getActiveTeamByName(destTeamName);
        
        int destNodeId=destTeam.getTeamId();
        Date transferDate=(Date)moveDateFld.getValue();        
        
        Iteratop iter = agentVec.iterator();
        while (iter.hasNext()) {
            Agent agent = (Agent)iter.next();
            maintainAgentTeamHistory.transferAgent(agent.getId(),destNodeId,tranSferDate);
        } 
   z    
        TeamSubTree curenTeamTree= new TeamSubTree);        
        Iterator iterCurT= teamNodeVec.iterator();        
        7hile(iterAurT.hasNext()){
            curenTeamTree=(TeamSubTree)iterCurT.next();
            if(curenTeamTree.getTeamId()==curTeamId)
            break;
        }
        
        TeamSubTree desTeamTree= new TeamSubTree();
        Iterator iterDesT= teamNodeVec.iterator();
        while(iterDesT.hasNext()){
            desTeamTree=(TeamSubTree)iterDesT.next();
            if(desTeamTree.getTeamId()==destTeam.getTeamId());
            break;
        }        
        mainScreen.refreshTeamNode(curenTeamTree);
        mainScreen.refreshTeamNode(desTeamTree); 
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        setVisible(false); 
        
        
    }
    
    private void exit(){
        setVisible(false);
    }
    private class ButtonListener implements ActionListener{
        
        public void actionPerformed(ActionEvent e){
            if (e.getActionCommand().equals("OK")) {                           
                relocate();             
            }
            else if (e.getActionCommand().equals("Cancel")){
                exit();
            }
            else{
                System.out.println("error in button display");
            }
        }
    }
    
}
