import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceContext;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.dnd.*;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import java.io.IOException;

import java.net.URL;

import java.sql.Connection;

import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.sun.java.swing.plaf.windows.WindowsTreeUI;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import javax.swing.event.TreeWillExpandListener;
import javax.swing.text.Position;
import javax.swing.tree.ExpandVetoException;

import oracle.jdbc.driver.OracleDriver;
/*
 * Created on 11-Feb-2005
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
public class


MainTree extends JTree implements TreeSelectionListener, DragGestureListener, 
                                  DropTargetListener, DragSourceListener


{

    MutableTreeNode target;

    public ImageIcon riasIcon = 
        new ImageIcon(getClass().getResource("Icons/riaslogo.gif"));
    public ImageIcon agentIcon = 
        new ImageIcon(getClass().getResource("Icons/agent.png"));
    public ImageIcon indAgentIcon = 
        new ImageIcon(getClass().getResource("Icons/Agent_ind.png"));
    public ImageIcon siteIcon = 
        new ImageIcon(getClass().getResource("Icons/site.png"));
    public ImageIcon siteErrIcon = 
        new ImageIcon(getClass().getResource("Icons/err_site.png"));
    public ImageIcon groupIcon = 
        new ImageIcon(getClass().getResource("Icons/group.png"));
    public ImageIcon errGroupIcon = 
        new ImageIcon(getClass().getResource("Icons/err_group.png"));
    public ImageIcon teamIcon = 
        new ImageIcon(getClass().getResource("Icons/team.png"));
    public ImageIcon errTeamIcon = 
        new ImageIcon(getClass().getResource("Icons/err_team.png"));
    public ImageIcon errTeamImage = 
        new ImageIcon(getClass().getResource("Icons/err_team.png"));
    public ImageIcon tempTeamIcon = 
        new ImageIcon(getClass().getResource("Icons/team_temp.png"));
    public ImageIcon errTempTeamIcon = 
        new ImageIcon(getClass().getResource("Icons/err_team_temp.png"));
    public ImageIcon departmentIcon = 
        new ImageIcon(getClass().getResource("Icons/dept2.png"));
    public ImageIcon errdepartmentIcon = 
        new ImageIcon(getClass().getResource("Icons/err_dept2.png"));


    Connection conn;
    MainScreen mainScreen;
    int refSiteId;
    int refDepartmentId;
    int refGroupId;
    int refTeamId;
    int refAgentId;
    private String DestinNodeClass;
    int destNodeId;
    int dropNodeSiteId;

    private boolean _treeDragging = false;
    private static final int hight = 800;
    private static final int width = 650;
    protected TreePath s_clickedPath;
    protected JPopupMenu sitePopupMenu;
    protected JPopupMenu departmentPopupMenu;
    protected JPopupMenu groupPopupMenu;
    protected JPopupMenu teamPopupMenu;
    protected JPopupMenu agentPopupMenu;
    protected JPopupMenu moveAgentPopupMenu;

    private Frame Parent = null;
    ManagerMaintenance managerMaintenance = new ManagerMaintenance();
    IconCellRenderer iconCellRenderer = new IconCellRenderer();
    TreeCellRenderer cellRenderer = (TreeCellRenderer)this.getCellRenderer();
    TreeCellRenderer renderer = new IconCellRenderer();
    MaintainAgentTeamHistory agentTeamHistory = new MaintainAgentTeamHistory();
    private DragSource dragSource = DragSource.getDefaultDragSource();
    MaintainDepartment maintainDepartment = new MaintainDepartment();
    Insets autoscrollInsets = new Insets(20, 20, 20, 20);
    DefaultMutableTreeNode sNode = new DefaultMutableTreeNode();
    DefaultTreeModel treeModel1 = new DefaultTreeModel(sNode);
    JTree mainTree = new JTree(treeModel1);
    SiteSubTree siteTree;
    DepartmentSubTree departmentTree;
    GroupSubTree groupTree;
    TeamSubTree teamTree;
    DefaultMutableTreeNode rootNode = 
        new DefaultMutableTreeNode(new IconData(riasIcon, "RIAS"));
    DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
    MaintainAgentTeamHistory maintainAgentTeamHistory = 
        new MaintainAgentTeamHistory();
    TeamMaintenance teamMaintenance = new TeamMaintenance();
    DefaultMutableTreeNode currentNode;
    GroupInventory groupInventory = new GroupInventory();
    Personnel personnel = new Personnel();
    TeamMaintenance teamMaint = new TeamMaintenance();
    JTree tree = new JTree(treeModel);
    private JPanel detailPanel = new JPanel();
    protected JPopupMenu riasPopupMenu;
    protected Action m_action;
    protected TreePath m_clickedPath;
    protected TreePath selectedTreePath = null;
    protected TeamSubTree selectedNode = null;
    Vector teamNodeVec = new Vector();
    Vector groupNodeVec = new Vector();
    Vector departmentNodeVec = new Vector();
    Vector siteNodeVec = new Vector();
    GroupSubTree newTeamParent = new GroupSubTree();
    GroupSubTree oldTeamParent = new GroupSubTree();
    TeamSubTree oldAgentParent = new TeamSubTree();
    TeamSubTree newAgentParent = new TeamSubTree();


    private DragSourceContext dragSourceContext;

    //DragExitMethods

    public void dragExit(DragSourceDropEvent dsde) {

    }

    public void dragExit(DragSourceEvent dsde) {

    }

    public void dragExit(DropTargetEvent dte) {

    }

    public void dragExit(DropTargetDragEvent e) {

    }

    public void dragExit(DragSourceDragEvent dsde) {
        dsde.notifyAll();
    }

    public void dragExit(DropTargetListener dtl) {
        dtl.notifyAll();
    }

    public void dragOver(DragSourceDragEvent dsde) {

    }

    public void autoscroll(Point cursorLocation) {
        Insets insets = getAutoscrollInsets();
        Rectangle outer = getVisibleRect();
        Rectangle inner = 
            new Rectangle(outer.x + insets.left, outer.y + insets.top, 
                          outer.width - (insets.left + insets.right), 
                          outer.height - (insets.top + insets.bottom));
        if (!inner.contains(cursorLocation)) {
            Rectangle scrollRect = 
                new Rectangle(cursorLocation.x - insets.left, 
                              cursorLocation.y - insets.top, 
                              insets.left + insets.right, 
                              insets.top + insets.bottom);
            scrollRectToVisible(scrollRect);
        }

    }

    public static DefaultMutableTreeNode makeDeepCopy(DefaultMutableTreeNode node) {
        DefaultMutableTreeNode copy = 
            new DefaultMutableTreeNode(node.getUserObject());
        for (Enumeration e = node.children(); e.hasMoreElements(); ) {
            copy.add(makeDeepCopy((DefaultMutableTreeNode)e.nextElement()));
        }
        return (copy);
    }

    public Insets getAutoscrollInsets() {
        return (autoscrollInsets);
    }

    public void dragOver(DropTargetDragEvent e) {
        Point cursorLocationBis = e.getLocation();
        TreePath destinationPath = 
            getPathForLocation(cursorLocationBis.x, cursorLocationBis.y);
        if (testDropTarget(destinationPath, selectedTreePath) == null) {
            e.acceptDrag(DnDConstants.ACTION_COPY_OR_MOVE);
        } else {
            e.rejectDrag();
        }
    }

    //DragEnterMethods

    public void dragEnter(DragSourceDragEvent dsde) {

    }

    public void dragEnter(DropTargetDragEvent e) {

    }

    public void dragDropEnd(DragSourceDragEvent dsde) {

    }

    public void dragDropEnd(DragSourceDropEvent dsde) {

    }

    public void dropActionChanged(DropTargetDragEvent e) {

    }

    public void dropActionChanged(DragSourceDragEvent e) {

    }

    private String testDropTarget(TreePath destination, TreePath droper) {
        String msg = "";
        boolean destinationPathIsNull = destination == null;
        if (destinationPathIsNull) {
            msg = "Invalid Drop Location";
        }
        GroupSubTree node = (GroupSubTree)destination.getLastPathComponent();
        return msg;
    }

    public TeamSubTree getSelectedNode() {
        return selectedNode;
    }

    public JTree getTree() {
        return tree;
    }

    public void dragGestureRecognized(DragGestureEvent e) {
        TeamSubTree dragNode = getSelectedNode();
        if (dragNode != null) {
            Transferable transferable = (Transferable)dragNode.getUserObject();
            Cursor cursor = DragSource.DefaultCopyNoDrop;
            int action = e.getDragAction();
            if (action == DnDConstants.ACTION_MOVE) {
                cursor = DragSource.DefaultMoveNoDrop;
            }
            dragSource.startDrag(e, cursor, transferable, this);
        }
    }

    public void drop(DropTargetDropEvent e) {
        try {
            Transferable tr = e.getTransferable();
            if (!tr.isDataFlavorSupported(TeamSubTree.INFO_FLAVOR)) {
                e.rejectDrop();
            }
            TeamSubTree childTeam = 
                (TeamSubTree)tr.getTransferData(TeamSubTree.INFO_FLAVOR);
            Point loc = e.getLocation();
            TreePath destinationPath = getPathForLocation(loc.x, loc.y);
            final String msg = 
                ""; //testDropTarget(destinationPath,selectedTreePath);
            if (msg != null) {
                e.rejectDrop();
                SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                JOptionPane.showMessageDialog(Parent, msg, 
                                                              "Error Dialog", 
                                                              JOptionPane.ERROR_MESSAGE);
                            }
                        });
                return;
            }
            newTeamParent = 
                    (GroupSubTree)destinationPath.getLastPathComponent();
            oldTeamParent = (GroupSubTree)getSelectedNode().getParent();
            oldTeamParent.getGroupName();
            int action = e.getDropAction();
            boolean copyAction = (action == DnDConstants.ACTION_COPY);
            int i = 0;
            TeamSubTree newChild = 
                new TeamSubTree(new OidNode(i, childTeam.getTeamName()));
            try {
                if (!copyAction)
                    oldTeamParent.remove(getSelectedNode());
                newTeamParent.add(newChild);
            } catch (IllegalStateException ils) {
                e.rejectDrop();
            }

        } catch (IOException io) {
            e.rejectDrop();
        } catch (UnsupportedFlavorException ufe) {
            e.rejectDrop();
        }
    }

    public void valueChanged(TreeSelectionEvent evt) {
        selectedTreePath = evt.getNewLeadSelectionPath();
        if (selectedTreePath == null) {
            selectedNode = null;
        }
    }


    public void refresh() {
        MaintainSites maintainSite = new MaintainSites(conn);
        Vector siteVec = maintainSite.getAllSites();
        Iterator iter = siteVec.iterator();
        int i = 0;
        while (iter.hasNext()) {
            Site site = (Site)iter.next();
            SiteSubTree siteTree = addSiteNode(site, i);
            refreshDepartments(siteTree);
            i++;
        }
    }

    public void refreshTeams(GroupSubTree group) {
        TeamMaintenance teamMaint = new TeamMaintenance();
        Vector teamVec = teamMaint.getAllCurrentTeams(group.getGroupId());
        Iterator iter = teamVec.iterator();
        int i = 0;
        while (iter.hasNext()) {
            Team t = (Team)iter.next();
            TeamSubTree teamSubTree = addTeamNode(t, group, i);
            teamNodeVec.addElement(teamSubTree);
            refreshAgent(teamSubTree);
            i++;
        }
    }

    public void refreshGroupTeams(GroupSubTree group) {
        TeamMaintenance teamMaint = new TeamMaintenance();
        Vector teamVec = teamMaint.getAllCurrentTeams(group.getGroupId());
        group.removeAllChildren();
        treeModel.reload(group);
        Iterator iter = teamVec.iterator();
        int i = 0;
        teamNodeVec.removeAllElements();
        while (iter.hasNext()) {
            Team t = (Team)iter.next();
            TeamSubTree teamSubTree = addTeamNodeDyna(t, group, i);
            teamNodeVec.addElement(teamSubTree);
            refreshAgent(teamSubTree);
            i++;
        }
    }

    public void refreshTeamsAgents(TeamSubTree teamSubTree) {
        Team team = teamMaint.getTeam(teamSubTree.getTeamId());
        Vector agentVec = new Vector();
        teamSubTree.removeAllChildren();
        treeModel.reload(teamSubTree);
        List agentVec2 = personnel.getAllCurrentAgents(team);
        List empNoVec = personnel.getAllEmployeeNumbers(team);
        Iterator iter1 = empNoVec.iterator();
        while (iter1.hasNext()) {
            Integer empNoI = (Integer)iter1.next();
            int empNo = empNoI.intValue();
            Agent agent = new Agent();
            agent.setEmpId(empNo);
            int agentNo = 0;
            String un = "top";
            Iterator it = agentVec2.iterator();
            while (it.hasNext()) {
                Agent a = (Agent)it.next();
                if (a.getEmpId() == empNo) {
                    agentNo = a.getId();
                    un = a.getUserName();
                }
            }
            agent.setId(agentNo);
            agent.setUserName(un);
            agentVec.addElement(agent);
        }
        teamSubTree.removeAllChildren();
        Iterator iter = agentVec.iterator();
        int i = 0;
        while (iter.hasNext()) {
            Agent agent = (Agent)iter.next();
            addAgentNode(agent, teamSubTree, i);
            i++;
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    public void refreshAgentTeam(int teamId) {
        long start = System.currentTimeMillis();
        Iterator iter = teamNodeVec.iterator();

        TeamSubTree t = new TeamSubTree();
        while (iter.hasNext()) {
            t = (TeamSubTree)iter.next();
            if (t.getTeamId() == teamId)
                break;
        }
        refreshAgent1(t);
        long elapsedTimeMillis = System.currentTimeMillis() - start;
        System.out.println("Time to refresh Agents is" + elapsedTimeMillis);

    }
    /////////////////////////////////////////////////////////////////////////////////////////////   

    public TeamSubTree addTeamNode(Team team, GroupSubTree groupSubTree, 
                                   int i) {
        Vector teamManagerVec = managerMaintenance.getAllManagers(team);
        //Multiple Managers
        /*
                        if(teamManagerVec.isEmpty())
                        {
                                if(team.isPerm())
                  {
                    teamTree = new TeamSubTree(new IconData(errTeamIcon,team.getTeamName()));
                  }
                  else
                  {
                    teamTree = new TeamSubTree(new IconData(errTempTeamIcon,team.getTeamName()));
                  }

                        }
                        else
                        {
                                if(team.isPerm())
                  {
                    teamTree = new TeamSubTree(new IconData(teamIcon,team.getTeamName()));
                  }
                  else
                  {
                    teamTree = new TeamSubTree(new IconData(tempTeamIcon,team.getTeamName()));
                  }

                }
                */

        //SingleManagers
        if (team.getManagerId() == 0) {
            if (team.isPerm()) {
                teamTree = 
                        new TeamSubTree(new IconData(errTeamIcon, team.getTeamName()));
            } else {
                teamTree = 
                        new TeamSubTree(new IconData(errTempTeamIcon, team.getTeamName()));
            }
        } else {
            if (team.isPerm()) {
                teamTree = 
                        new TeamSubTree(new IconData(teamIcon, team.getTeamName()));
            } else {
                teamTree = 
                        new TeamSubTree(new IconData(tempTeamIcon, team.getTeamName()));
            }

        }
        TeamMaintenance teamMaintenance = new TeamMaintenance();
        int groupId = teamMaintenance.getGroupId(team.getTeamId());
        teamTree.setTeamName(team.getTeamName());
        teamTree.setTeamId(team.getTeamId());
        teamTree.setGroupId(groupId);
        treeModel.insertNodeInto(teamTree, groupSubTree, i);
        return teamTree;
    }

    public TeamSubTree addTeamNodeDyna(Team team, GroupSubTree groupSubTree, 
                                       int i) {

        Vector teamManagerVec = managerMaintenance.getAllManagers(team);
        //Multiple Managers
        /*
                        if(teamManagerVec.isEmpty())
                        {
                                if(team.isPerm())
                  {
                    teamTree = new TeamSubTree(new IconData(errTeamIcon,team.getTeamName()));
                  }
                  else
                  {
                    teamTree = new TeamSubTree(new IconData(errTempTeamIcon,team.getTeamName()));
                  }

                        }
                        else
                        {
                                if(team.isPerm())
                  {
                    teamTree = new TeamSubTree(new IconData(teamIcon,team.getTeamName()));
                  }
                  else
                  {
                    teamTree = new TeamSubTree(new IconData(tempTeamIcon,team.getTeamName()));
                  }

                }
                */

        //SingleManagers
        if (team.getManagerId() == 0) {
            if (team.isPerm()) {
                teamTree = 
                        new TeamSubTree(new IconData(errTeamIcon, team.getTeamName()));
            } else {
                teamTree = 
                        new TeamSubTree(new IconData(errTempTeamIcon, team.getTeamName()));
            }

        } else {
            if (team.isPerm()) {
                teamTree = 
                        new TeamSubTree(new IconData(teamIcon, team.getTeamName()));
            } else {
                teamTree = 
                        new TeamSubTree(new IconData(tempTeamIcon, team.getTeamName()));
            }
        }
        TeamMaintenance teamMaintenance = new TeamMaintenance();
        int groupId = teamMaintenance.getGroupId(team.getTeamId());
        teamTree.setTeamName(team.getTeamName());
        teamTree.setTeamId(team.getTeamId());
        teamTree.setGroupId(groupId);
        treeModel.insertNodeInto(teamTree, groupSubTree, i);
        tree.scrollPathToVisible(new TreePath(teamTree.getPath()));
        return teamTree;

    }

    public void refreshAgent(TeamSubTree teamSubTree) {

        long start = System.currentTimeMillis();
        Team team = teamMaint.getTeam(teamSubTree.getTeamId());
        Vector agentVec = new Vector();
        List agentVec2 = personnel.getAllCurrentAgents(team);
        List empNoVec = personnel.getAllEmployeeNumbers(team);
        Iterator iter1 = empNoVec.iterator();
        while (iter1.hasNext()) {
            Integer empNoI = (Integer)iter1.next();
            int empNo = empNoI.intValue();
            Agent agent = new Agent();
            agent.setEmpId(empNo);
            int agentNo = 0;
            String un = "top";
            Iterator it = agentVec2.iterator();
            while (it.hasNext()) {
                Agent a = (Agent)it.next();
                if (a.getEmpId() == empNo) {
                    agentNo = a.getId();
                    un = a.getUserName();
                }
            }
            agent.setId(agentNo);
            agent.setUserName(un);
            agentVec.addElement(agent);
        }
        teamSubTree.removeAllChildren();
        Iterator iter = agentVec.iterator();
        int i = 0;
        while (iter.hasNext()) {
            Agent agent = (Agent)iter.next();
            addAgentNode(agent, teamSubTree, i);
            i++;
        }
        long elapsedTimeMillis = System.currentTimeMillis() - start;
    }

    public void refreshAgent1(TeamSubTree teamSubTree) {
        Team team = teamMaint.getTeam(teamSubTree.getTeamId());
        Vector agentVec = new Vector();
        List agentVec2 = personnel.getAllCurrentAgents(team);
        List empNoVec = personnel.getAllEmployeeNumbers(team);
        Iterator iter1 = empNoVec.iterator();
        while (iter1.hasNext()) {
            Integer empNoI = (Integer)iter1.next();
            int empNo = empNoI.intValue();
            Agent agent = new Agent();
            agent.setEmpId(empNo);
            int agentNo = 0;
            String un = "top";
            Iterator it = agentVec2.iterator();
            while (it.hasNext()) {
                Agent a = (Agent)it.next();
                if (a.getEmpId() == empNo) {
                    agentNo = a.getId();
                    un = a.getUserName();
                }
            }
            agent.setId(agentNo);
            agent.setUserName(un);
            agentVec.addElement(agent);
        }
        teamSubTree.removeAllChildren();
        treeModel.reload(teamSubTree);
        Iterator iter = agentVec.iterator();
        int i = 0;
        while (iter.hasNext()) {
            Agent agent = (Agent)iter.next();
            addAgentNodeDyna(agent, teamSubTree, i);
            i++;
        }
    }

    public Agent addAgentNode(Agent agent, TeamSubTree teamSubTree, int i) {
        String agentName = personnel.getEmployeeName(agent.getEmpId());
        Application agentApp = 
            personnel.getAgentApplication(agent.getUserName());
        int appId = agentApp.getApplicationId();
        AgentNode agentNode;
        Team team = maintainAgentTeamHistory.getAgentsCurrentTeam(agent);
        if (team.isPerm()) {
            agentNode = new AgentNode(new IconData(agentIcon, agentName));
        } else {
            agentNode = new AgentNode(new IconData(indAgentIcon, agentName));
        }

        agentNode.setAgentId(agent.getId());
        agentNode.setUserName(agent.getUserName());
        agentNode.setEmployeeId(agent.getEmpId());
        agentNode.setTeamId(team.getTeamId());
        treeModel.insertNodeInto(agentNode, teamSubTree, i);

        return agent;
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////

    public Agent addAgentNodeDyna(Agent agent, TeamSubTree teamSubTree, 
                                  int i) {
        String agentName = personnel.getEmployeeName(agent.getEmpId());
        Application agentApp = 
            personnel.getAgentApplication(agent.getUserName());
        int appId = agentApp.getApplicationId();
        AgentNode agentNode;

        Team team = maintainAgentTeamHistory.getAgentsCurrentTeam(agent);

        if (team.isPerm()) {
            agentNode = new AgentNode(new IconData(agentIcon, agentName));
        } else {
            agentNode = new AgentNode(new IconData(indAgentIcon, agentName));
        }

        agentNode.setAgentId(agent.getId());
        agentNode.setUserName(agent.getUserName());

        //agentNode.setPassword(agent.getPassword());
        agentNode.setEmployeeId(agent.getEmpId());
        MaintainAgentTeamHistory maintainAgentTeamHistory = 
            new MaintainAgentTeamHistory();
        agentNode.setTeamId(team.getTeamId());
        treeModel.insertNodeInto(agentNode, teamSubTree, i);
        tree.scrollPathToVisible(new TreePath(agentNode.getPath()));

        return agent;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////

    public void refreshGroups(DepartmentSubTree department) {
        GroupInventory groupInv = new GroupInventory();
        Vector groupVec = 
            groupInv.getAllActiveGroups(department.getDepartmentId());
        Iterator iter = groupVec.iterator();
        int i = 0;
        while (iter.hasNext()) {
            Group g = (Group)iter.next();
            GroupSubTree groupTree = addGroupNode(g, department, i);
            groupNodeVec.addElement(groupTree);
            refreshTeams(groupTree);
            i++;
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////

    public void refreshGroups1(DepartmentSubTree departmentSubTree) {
        GroupInventory groupInv = new GroupInventory();
        Vector groupVec = 
            groupInv.getAllActiveGroups(departmentSubTree.getDepartmentId());
        Iterator iter = groupVec.iterator();

        departmentSubTree.removeAllChildren();
        treeModel.reload(departmentSubTree);
        groupNodeVec.removeAllElements();
        int i = 0;
        while (iter.hasNext()) {
            Group g = (Group)iter.next();
            GroupSubTree groupTree = addGroupNodeDyna(g, departmentSubTree, i);
            groupNodeVec.addElement(groupTree);
            refreshTeams(groupTree);
            i++;
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////

    public void refreshGroups2(int departmentId) {
        GroupInventory groupInv = new GroupInventory();
        DepartmentSubTree departmentSubTree = new DepartmentSubTree();
        Iterator iter1 = departmentNodeVec.iterator();
        while (iter1.hasNext()) {
            departmentSubTree = (DepartmentSubTree)iter1.next();
            if (departmentSubTree.getDepartmentId() == departmentId)
                break;
        }

        Vector groupVec = groupInv.getAllActiveGroups(departmentId);
        Iterator iter = groupVec.iterator();

        departmentSubTree.removeAllChildren();
        treeModel.reload(departmentSubTree);
        groupNodeVec.removeAllElements();
        int i = 0;
        while (iter.hasNext()) {
            Group g = (Group)iter.next();
            GroupSubTree groupTree = addGroupNodeDyna(g, departmentSubTree, i);
            groupNodeVec.addElement(groupTree);
            refreshTeams(groupTree);
            i++;
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void refreshGroups3(DepartmentSubTree departmentSubTree) {
        GroupInventory groupInv = new GroupInventory();
        Vector groupVec = 
            groupInv.getAllActiveGroups(departmentSubTree.getDepartmentId());
        Iterator iter = groupVec.iterator();

        departmentSubTree.removeAllChildren();
        treeModel.reload(departmentSubTree);
        groupNodeVec.removeAllElements();
        int i = 0;
        while (iter.hasNext()) {
            Group g = (Group)iter.next();
            GroupSubTree groupTree = addGroupNode(g, departmentSubTree, i);
            groupNodeVec.addElement(groupTree);
            refreshTeams(groupTree);
            i++;
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////       


    public GroupSubTree addGroupNode(Group group, 
                                     DepartmentSubTree departmentSubTree, 
                                     int i) {

        Vector groupManagerVec = managerMaintenance.getAllManagers(group);
        if (groupManagerVec.isEmpty()) {
            groupTree = 
                    new GroupSubTree(new IconData(errGroupIcon, group.getGroupName()));
        }

        else {
            groupTree = 
                    new GroupSubTree(new IconData(groupIcon, group.getGroupName()));
        }

        groupTree.setGroupName(group.getGroupName());
        groupTree.setGroupId(group.getGroupId());
        groupTree.setDepartmentId(group.getDepartmentId());
        treeModel.insertNodeInto(groupTree, departmentSubTree, i);
        return groupTree;

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public GroupSubTree addGroupNodeDyna(Group group, 
                                         DepartmentSubTree departmentSubTree, 
                                         int i) {

        Vector groupManagerVec = managerMaintenance.getAllManagers(group);
        if (groupManagerVec.isEmpty()) {
            groupTree = 
                    new GroupSubTree(new IconData(errGroupIcon, group.getGroupName()));
        }

        else {
            groupTree = 
                    new GroupSubTree(new IconData(groupIcon, group.getGroupName()));
        }

        groupTree.setGroupName(group.getGroupName());
        groupTree.setGroupId(group.getGroupId());
        groupTree.setDepartmentId(group.getDepartmentId());
        treeModel.insertNodeInto(groupTree, departmentSubTree, i);
        tree.scrollPathToVisible(new TreePath(groupTree.getPath()));
        return groupTree;

    }
    /////////////////////////////////////////////////////////////////////////////////////////////

    public void refreshDepartments(SiteSubTree site) {
        MaintainDepartment maintainDepartment = new MaintainDepartment();
        Vector departmentVec = 
            maintainDepartment.getAllActiveDepartments(site.getSiteId());
        Iterator iter = departmentVec.iterator();
        int i = 0;
        while (iter.hasNext()) {
            Department d = (Department)iter.next();
            DepartmentSubTree departmentTree = addDepartmentNode(d, site, i);
            departmentNodeVec.addElement(departmentTree);
            refreshGroups(departmentTree);
            i++;
        }
    }

    public DepartmentSubTree addDepartmentNode(Department department, 
                                               SiteSubTree siteSubTree, 
                                               int i) {
        DefaultMutableTreeNode parent = rootNode;
        Vector departmentManagersVec = 
            managerMaintenance.getAllManagers(department);
        if (departmentManagersVec.isEmpty()) {
            departmentTree = 
                    new DepartmentSubTree(new IconData(errdepartmentIcon, 
                                                       department.getDepartmentName()));
        } else {
            departmentTree = 
                    new DepartmentSubTree(new IconData(departmentIcon, department.getDepartmentName()));
        }


        departmentTree.setDeparmentName(department.getDepartmentName());
        departmentTree.setDepartmentId(department.getDepartmentId());
        departmentTree.setSiteId(department.getDepartmentSiteId());        
        treeModel.insertNodeInto(departmentTree, siteSubTree, i);
        departmentTree.setParent(siteSubTree);
        return departmentTree;

    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public DepartmentSubTree addDepartmentNode1(Department department, 
                                                SiteSubTree siteSubTree, 
                                                int i) {
        DefaultMutableTreeNode parent = rootNode;
        Vector departmentManagersVec = 
            managerMaintenance.getAllManagers(department);
        if (departmentManagersVec.isEmpty()) {
            departmentTree = 
                    new DepartmentSubTree(new IconData(errdepartmentIcon, 
                                                       department.getDepartmentName()));
        } else {
            departmentTree = 
                    new DepartmentSubTree(new IconData(departmentIcon, department.getDepartmentName()));
        }
                
        DepartmentSubTree depTree = new DepartmentSubTree();        
        
        departmentTree.setDeparmentName(department.getDepartmentName());
        departmentTree.setDepartmentId(department.getDepartmentId());
        departmentTree.setSiteId(department.getDepartmentSiteId());     
        treeModel.insertNodeInto(departmentTree, siteSubTree, i);        
        
        return departmentTree;

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void addDepartmentNodeDyna(Department department, 
                                      SiteSubTree siteSubTree, int i) {
        Vector departmentManagersVec = 
            managerMaintenance.getAllManagers(department);
        if (departmentManagersVec.isEmpty()) {
            departmentTree = 
                    new DepartmentSubTree(new IconData(errdepartmentIcon, 
                                                       department.getDepartmentName()));
        } else {
            departmentTree = 
                    new DepartmentSubTree(new IconData(departmentIcon, department.getDepartmentName()));
        }
        departmentTree.setDeparmentName(department.getDepartmentName());
        departmentTree.setDepartmentId(department.getDepartmentId());
        departmentTree.setSiteId(department.getDepartmentSiteId()); 
        treeModel.insertNodeInto(departmentTree, siteSubTree, i);
        departmentTree.setParent(siteSubTree);
        departmentNodeVec.add(departmentTree);
        tree.scrollPathToVisible(new TreePath(siteSubTree.getPath()));


    }
    
    public SiteSubTree addSiteNode(Site site, int i) {

        //SiteSubTree siteTree = new SiteSubTree(new OidNode(i,site.getSiteName()));    	
        if (site.getManagerId() == 0) {
            //System.err.println("No Manager Assigned");
            siteTree = 
                    new SiteSubTree(new IconData(siteErrIcon, null, site.getSiteName()));
        }

        else {
            siteTree = 
                    new SiteSubTree(new IconData(siteIcon, null, site.getSiteName()));
        }

        siteTree.setName(site.getSiteName());
        siteTree.setSiteId(site.getSiteId());
        siteTree.setManagerIdId(site.getManagerId());
        siteNodeVec.add(siteTree);
        treeModel.insertNodeInto(siteTree, rootNode, i);

        return siteTree;
    }

    public void setPopUpMenues() {
        riasPopupMenu = new JPopupMenu();

        m_action = new AbstractAction() {
                    public void actionPerformed(ActionEvent e) {
                        if (m_clickedPath == null) {
                            return;
                        }
                    }
                };


        Action a1 = new AbstractAction("Add Site") {
                public void actionPerformed(ActionEvent e) {
                    SiteMaintenanceDialog siteMaintenanceDialog = 
                        new SiteMaintenanceDialog(mainScreen);
                }
            };


        riasPopupMenu.add(a1);
        riasPopupMenu.add(m_action);
        riasPopupMenu.addSeparator();

        tree.add(riasPopupMenu);
        tree.addMouseListener(new PopupTrigger());


        //JPopupMenu riasPopupMenu = new JPopupMenu();
        m_action = new AbstractAction() {
                    public void actionPerformed(ActionEvent e) {
                        if (m_clickedPath == null) {
                            return;
                        }

                    }
                };


        Action a12 = new AbstractAction("Add Department") {
                public void actionPerformed(ActionEvent e) {
                    DepartmentMaintenanceScreen departmentMaintenanceScreen = 
                        new DepartmentMaintenanceScreen(refSiteId, mainScreen);
                }
            };
        Action a13 = new AbstractAction("Maintain Manager") {
                public void actionPerformed(ActionEvent e) {

                    SiteManagementMaintenanceDialog siteManagementMaintenanceDialog = 
                        new SiteManagementMaintenanceDialog(mainScreen, 
                                                            refSiteId);
                }
            };

        sitePopupMenu = new JPopupMenu();
        sitePopupMenu.add(a12);
        sitePopupMenu.add(m_action);
        sitePopupMenu.addSeparator();
        sitePopupMenu.add(a13);
        //sitePopupMenu.add(a4);
        mainTree.add(sitePopupMenu);
        mainTree.addMouseListener(new PopupTrigger());
        mainTree.setEditable(true);
        mainTree.setShowsRootHandles(true);
        mainTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);


        Action a3 = new AbstractAction("Add Group") {
                public void actionPerformed(ActionEvent e) {


                    Iterator iter = departmentNodeVec.iterator();
                    while (iter.hasNext()) {
                        departmentTree = (DepartmentSubTree)iter.next();
                        if (departmentTree.getDepartmentId() == 
                            refDepartmentId)
                            break;
                    }


                    GroupMaintenanceDialog groupMaintenanceDialog = 
                        new GroupMaintenanceDialog(refDepartmentId, 
                                                   departmentTree, mainScreen);
                }
            };

        Action a10 = new AbstractAction("Rename") {
                public void actionPerformed(ActionEvent e) {
                    DepartmentUpdateScreen DepartmentUpdateScreen = 
                        new DepartmentUpdateScreen(refDepartmentId, 
                                                   mainScreen);
                }
            };
        Action a14 = new AbstractAction("Maintain Manager") {
                public void actionPerformed(ActionEvent e) {

                    DepartmentManagementAssignmentDialog departmentManagementAssignmentDialog = 
                        new DepartmentManagementAssignmentDialog(mainScreen, 
                                                                 refDepartmentId);

                }
            };

        Action a21 = new AbstractAction("Deactivate") {
                public void actionPerformed(ActionEvent e) {

                    Department department = 
                        maintainDepartment.getDepartment(refDepartmentId);
                    Vector groupVec = 
                        groupInventory.getAllActiveGroups(refDepartmentId);
                    try {
                        if (!(groupVec.isEmpty()))
                            throw new ParametrizedException("Active groups exist - Department can not be deactivated ");

                        maintainDepartment.deActivateDepartment(refDepartmentId);
                        JOptionPane.showMessageDialog(mainScreen, 
                                                      "Department " + 
                                                      department.getDepartmentName() + 
                                                      " is deactivated ");

                    } catch (ParametrizedException e1) {
                        JOptionPane.showMessageDialog(mainScreen, 
                                                      e1.getMessage());
                    }
                }
            };


        departmentPopupMenu = new JPopupMenu();
        departmentPopupMenu.add(a3);
        departmentPopupMenu.add(a10);
        departmentPopupMenu.add(a14);
        departmentPopupMenu.add(a21);

        Action a5 = new AbstractAction("Add Team") {
                public void actionPerformed(ActionEvent e) {
                    Iterator iter = groupNodeVec.iterator();
                    while (iter.hasNext()) {
                        groupTree = (GroupSubTree)iter.next();
                        if (groupTree.getGroupId() == refGroupId)
                            break;
                    }
                    TeamMaintenanceDialog teamMaintenanceDialog = 
                        new TeamMaintenanceDialog(refGroupId, groupTree, 
                                                  mainScreen);
                }
            };

        Action a6 = new AbstractAction("Rename") {
                public void actionPerformed(ActionEvent e) {

                    Group group = groupInventory.getGroup(refGroupId);
                    GroupUpdateDialog groupUpdateDialog = 
                        new GroupUpdateDialog(refGroupId, 
                                              group.getDepartmentId(), 
                                              mainScreen);
                }
            };
        Action a4 = new AbstractAction("Maintain Employee") {
                public void actionPerformed(ActionEvent e) {

                    TeamMaintenance teamMaintenance = new TeamMaintenance();
                    Vector teamVec = 
                        teamMaintenance.getAllCurrentTeamNames(refGroupId);
                    try {
                        if (teamVec.isEmpty())
                            throw new ParametrizedException("Before assigning any employee you need to create at list one team for this group ");
                        if (groupTree == null) {
                        }
                        Iterator iter = groupNodeVec.iterator();
                        while (iter.hasNext()) {
                            groupTree = (GroupSubTree)iter.next();
                            if (groupTree.getGroupId() == refGroupId)
                                break;
                        }

                        EmployeeMaintenanceScreen employeeMaintenanceScreen = 
                            new EmployeeMaintenanceScreen(refGroupId, 
                                                          groupTree, 
                                                          mainScreen);

                    } catch (ParametrizedException ex) {
                        JOptionPane.showMessageDialog(mainScreen, 
                                                      ex.getMessage());
                    }

                }

            };

        Action a15 = new AbstractAction("Maintain Manager") {
                public void actionPerformed(ActionEvent e) {
                    GroupManagerAssignmentDialog groupManagerAssignmentDialog = 
                        new GroupManagerAssignmentDialog(mainScreen, 
                                                         refGroupId);
                }
            };
        Action a19 = new AbstractAction("Deactivate") {
                public void actionPerformed(ActionEvent e) {
                    int groupId = teamMaintenance.getGroupId(refGroupId);

                    Group group = groupInventory.getGroup(refGroupId);

                    Vector teamVec = 
                        teamMaintenance.getAllCurrentTeamNames(refGroupId);
                    try {
                        if (!(teamVec.isEmpty()))
                            throw new ParametrizedException("Active teams exist - Group can not be deactivated ");

                        groupInventory.deactivateGroup(refGroupId);
                        JOptionPane.showMessageDialog(mainScreen, 
                                                      "Group " + group.getGroupName() + 
                                                      " is deactivated ");

                    } catch (ParametrizedException e1) {
                        JOptionPane.showMessageDialog(mainScreen, 
                                                      e1.getMessage());
                    }


                }
            };


        groupPopupMenu = new JPopupMenu();
        groupPopupMenu.add(a4);
        groupPopupMenu.add(a5);
        groupPopupMenu.add(a6);
        groupPopupMenu.add(a15);
        groupPopupMenu.add(a19);
        Action a7 = new AbstractAction("Add Agent") {
                public void actionPerformed(ActionEvent e) {
                    Iterator iter = teamNodeVec.iterator();
                    while (iter.hasNext()) {
                        teamTree = (TeamSubTree)iter.next();
                        if (teamTree.getTeamId() == refTeamId)
                            break;

                    }

                    UserMaintenanceScreen userMaintenanceScreen = 
                        new UserMaintenanceScreen(teamTree, refTeamId, 
                                                  mainScreen);
                }
            };

        Action a8 = new AbstractAction("Update") {
                public void actionPerformed(ActionEvent e) {
                    Group group = 
                        teamMaintenance.getTeamsCurentGroup(refTeamId);
                    Iterator iter = groupNodeVec.iterator();
                    GroupSubTree groupSubTree = new GroupSubTree();
                    while (iter.hasNext()) {
                        groupSubTree = (GroupSubTree)iter.next();
                        if (groupSubTree.getGroupId() == group.getGroupId())
                            break;

                    }


                    UpDateTeamDialog upDateTeamDialog = 
                        new UpDateTeamDialog(refTeamId, groupSubTree, 
                                             mainScreen);
                }
            };

        Action a17 = new AbstractAction("Maintain Manager") {
                public void actionPerformed(ActionEvent e) {
                    int groupId = teamMaintenance.getGroupId(refTeamId);


                    Iterator iter = groupNodeVec.iterator();
                    while (iter.hasNext()) {
                        groupTree = (GroupSubTree)iter.next();
                        if (groupTree.getGroupId() == groupId)
                            break;
                    }

                    TeamManagerAssigmentDialog TeamManagerAssigmentDialog = 
                        new TeamManagerAssigmentDialog(mainScreen, refTeamId, 
                                                       groupTree);
                }
            };
        Action a18 = new AbstractAction("Deactivate") {
                public void actionPerformed(ActionEvent e) {
                    int groupId = teamMaintenance.getGroupId(refTeamId);

                    Team team = teamMaintenance.getTeam(refTeamId);

                    Vector agentVec = 
                        personnel.getAllCurrenAcitvetAgents(team);
                    try {
                        if (!(agentVec.isEmpty())) {
                            throw new ParametrizedException("Active agents exist - Team can not be deactivated ");
                        } else {
                            teamMaintenance.deActivateTeam(refTeamId);
                            JOptionPane.showMessageDialog(mainScreen, 
                                                          "Team " + team.getTeamName() + 
                                                          " is deactivated ");
                        }
                    } catch (ParametrizedException e1) {
                        JOptionPane.showMessageDialog(mainScreen, 
                                                      e1.getMessage());
                    }


                }
            };

        teamPopupMenu = new JPopupMenu();
        teamPopupMenu.add(a7);
        teamPopupMenu.add(a17);
        teamPopupMenu.add(a8);
        teamPopupMenu.add(a18);


        Action a9 = new AbstractAction("Move Agent") {
                public void actionPerformed(ActionEvent e) {
                    Show();
                }
            };
        moveAgentPopupMenu = new JPopupMenu();
        moveAgentPopupMenu.add(a9);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void Show() {

        Agent a = personnel.getAgentById(refAgentId);
        String name = personnel.getEmployeeName(a.getEmpId());
        Team t = agentTeamHistory.getAgentsCurrentTeam(a);
        MoveAgentDialog agentUpdateDialog = 
            new MoveAgentDialog(refAgentId, t.getTeamId(), name, mainScreen, 
                                this);
    }

    public MainTree(MainScreen mainScreenIn, Connection connIn) {
        conn = connIn;
        mainScreen = mainScreenIn;
        setPopUpMenues();
        setAutoscrolls(true);
        setAutoscrolls(true);
        addTreeSelectionListener(this);
        DragSource dragSource = DragSource.getDefaultDragSource();
        DragGestureRecognizer dgr = 
            dragSource.createDefaultDragGestureRecognizer(this, 
                                                          DnDConstants.ACTION_COPY_OR_MOVE, 
                                                          this);


        dgr.setSourceActions(dgr.getSourceActions() & 
                             ~InputEvent.BUTTON3_MASK);
        DropTarget dropTarget = new DropTarget(this, this);
        treeModel.addTreeModelListener(new MyTreeModelListener());
        UIManager.put("Tree.expandedIcon", new WindowsTreeUI.ExpandedIcon());
        UIManager.put("Tree.collapsedIcon", new WindowsTreeUI.CollapsedIcon());
        //Set up the Tree
        DefaultMutableTreeNode top = 
            new DefaultMutableTreeNode(new IconData(riasIcon, null, "RIAS"));
        top.setAllowsChildren(true);
        tree.setEditable(true);
        tree.setShowsRootHandles(true);
        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        refresh();
        //renderer.setLeafIcon(agentIcon);
        tree.setCellRenderer(renderer);
        setVisible(true);
        tree.addMouseMotionListener(new MouseMotionAdapter() {

                    public void mouseDragged(MouseEvent e) {

                        if (!_treeDragging) {

                            _treeDragging = true;
                            setCursor(new Cursor(Cursor.HAND_CURSOR));
                            TreePath path = 
                                tree.getPathForLocation(e.getX(), e.getY());
                            MutableTreeNode node = null;
                            if (path != null)
                                node = 
(MutableTreeNode)path.getLastPathComponent();
                            mouseDragOnTree(e, node);

                        }


                    }
                });

        tree.addMouseListener(new MouseAdapter() {

                    public void mouseReleased(MouseEvent e) {
                        if (_treeDragging) {
                            TreePath path = 
                                tree.getPathForLocation(e.getX(), e.getY());
                            _treeDragging = false;
                            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                            MutableTreeNode node = null;
                            if (path != null)
                                node = 
(MutableTreeNode)path.getLastPathComponent();
                            mouseDropOnTree(e, node);

                        }
                    }
                });
        new DefaultTreeTransferHandler(this, DnDConstants.ACTION_COPY_OR_MOVE);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    

    public MainTree(MainScreen mainScreenIn) {

        mainScreen = mainScreenIn;
        //tree.addMouseListener(ml);
        //tree.addMouseMotionListener(mma);
        setPopUpMenues();
        setAutoscrolls(true);
        addTreeSelectionListener(this);
        DragSource dragSource = DragSource.getDefaultDragSource();
        DragGestureRecognizer dgr = 
            dragSource.createDefaultDragGestureRecognizer(this, 
                                                          DnDConstants.ACTION_COPY_OR_MOVE, 
                                                          this);


        dgr.setSourceActions(dgr.getSourceActions() & 
                             ~InputEvent.BUTTON3_MASK);
        DropTarget dropTarget = new DropTarget(this, this);
        treeModel.addTreeModelListener(new MyTreeModelListener());
        treeModel.addTreeModelListener(new MyTreeModelListener());

        UIManager.put("Tree.expandedIcon", new WindowsTreeUI.ExpandedIcon());
        UIManager.put("Tree.collapsedIcon", new WindowsTreeUI.CollapsedIcon());
        //Set up the Tree
        DefaultMutableTreeNode top = 
            new DefaultMutableTreeNode(new IconData(riasIcon, null, "RIAS"));

        top.setAllowsChildren(true);
        tree.setEditable(true);
        tree.setShowsRootHandles(true);
        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        refresh();
        //renderer.setLeafIcon(agentIcon);
        tree.setCellRenderer(renderer);
        setVisible(true);
        tree.addMouseMotionListener(new MouseMotionAdapter() {

                    public void mouseDragged(MouseEvent e) {

                        if (!_treeDragging) {

                            _treeDragging = true;

                            setCursor(new Cursor(Cursor.HAND_CURSOR));

                            TreePath path = 
                                tree.getPathForLocation(e.getX(), e.getY());

                            MutableTreeNode node = null;
                            if (path != null)

                                node = 
(MutableTreeNode)path.getLastPathComponent();
                            mouseDragOnTree(e, node);


                        }


                    }
                });

        tree.addMouseListener(new MouseAdapter() {

                    public void mouseReleased(MouseEvent e) {
                        if (_treeDragging) {
                            TreePath path = 
                                tree.getPathForLocation(e.getX(), e.getY());
                            _treeDragging = false;
                            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                            MutableTreeNode node = null;
                            if (path != null)
                                node = 
(MutableTreeNode)path.getLastPathComponent();
                            mouseDropOnTree(e, node);

                        }
                    }
                });
        new DefaultTreeTransferHandler(this, DnDConstants.ACTION_COPY_OR_MOVE);


    }
    ////////////////////////////////////////////////////////////////////////////////////////

    public MainTree() {

        //tree.addMouseListener(ml);
        //tree.addMouseMotionListener(mma);
        setPopUpMenues();
        setAutoscrolls(true);
        addTreeSelectionListener(this);
        DragSource dragSource = DragSource.getDefaultDragSource();
        DragGestureRecognizer dgr = 
            dragSource.createDefaultDragGestureRecognizer(this, 
                                                          DnDConstants.ACTION_COPY_OR_MOVE, 
                                                          this);

        dgr.setSourceActions(dgr.getSourceActions() & 
                             ~InputEvent.BUTTON3_MASK);
        DropTarget dropTarget = new DropTarget(this, this);
        treeModel.addTreeModelListener(new MyTreeModelListener());
        treeModel.addTreeModelListener(new MyTreeModelListener());

        UIManager.put("Tree.expandedIcon", new WindowsTreeUI.ExpandedIcon());
        UIManager.put("Tree.collapsedIcon", new WindowsTreeUI.CollapsedIcon());

        //Set up the Tree
        DefaultMutableTreeNode top = 
            new DefaultMutableTreeNode(new IconData(riasIcon, null, "RIAS"));

        top.setAllowsChildren(true);
        tree.setEditable(true);
        tree.setShowsRootHandles(true);
        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        refresh();


        //renderer.setLeafIcon(agentIcon);
        tree.setCellRenderer(renderer);
        setVisible(true);

        /*	
        tree.addMouseMotionListener(
                                      new MouseMotionAdapter(){

                                                  public void mouseDragged(MouseEvent e) {

                                                        if (!_treeDragging)
                                                        {

                                                                        _treeDragging = true;

                                                                        setCursor(new Cursor(Cursor.HAND_CURSOR));

                                                                        TreePath path = tree.getPathForLocation(e.getX(), e.getY());

                                                                        MutableTreeNode node = null;
                                                                        if (path != null)

                                                                                node = (MutableTreeNode) path.getLastPathComponent();
                                                                        mouseDragOnTree(e, node);	    			
                                                        }		
                                                  }
                                        }

                                   );

	tree.addMouseListener(

                                    new MouseAdapter() {
                                                public void mouseReleased(MouseEvent e) {
                                                        if (_treeDragging) {
                                                           TreePath path = tree.getPathForLocation(e.getX(), e.getY());
                                                           _treeDragging = false;
                                                           setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                                                           MutableTreeNode node = null;
                                                           if (path != null)
                                                             node = (MutableTreeNode) path.getLastPathComponent();
                                                           mouseDropOnTree(e, node);

                                                         }
                                                      }
                                    }
                             );
	*/
        new DefaultTreeTransferHandler(this, DnDConstants.ACTION_COPY_OR_MOVE);

    }

    /////////////////////////////////////////////////////////////////////////////////////////    

    public DefaultMutableTreeNode addObject(Object child) {
        DefaultMutableTreeNode parentNode = null;
        TreePath parentPath = tree.getSelectionPath();

        if (parentPath == null) {
            //There's no selection. Default to the root node.
            parentNode = rootNode;
        } else {
            parentNode = 
                    (DefaultMutableTreeNode)(parentPath.getLastPathComponent());
        }

        return addObject(parentNode, child, true);
    }

    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, 
                                            Object child, 
                                            boolean shouldBeVisible) {
        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

        treeModel.insertNodeInto(childNode, parent, parent.getChildCount());

        // Make sure the user can see the lovely new node.
        if (shouldBeVisible) {
            tree.scrollPathToVisible(new TreePath(childNode.getPath()));
        }
        return childNode;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	


    public void processMouseEvent(MouseEvent event) {
        if (event.isPopupTrigger()) {
            //popupMenu.show( event.getComponent(),	event.getX(), event.getY() );
        }

        super.processMouseEvent(event);
    }

    public void actionPerformed(ActionEvent event) {
        // Add action handling code here

    }

    MouseMotionAdapter mma = new MouseMotionAdapter() {

            public void mouseDragged(MouseEvent e) {

                if (!_treeDragging) {
                    _treeDragging = true;

                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                    TreePath path = getPathForLocation(e.getX(), e.getY());
                    MutableTreeNode node = null;
                    if (path != null)
                        node = (MutableTreeNode)path.getLastPathComponent();
                    mouseDragOnTree(e, node);
                }
            }
        };

    private MutableTreeNode _treeDraggingNode = null;
    private String dragedNodeClass;
    private MutableTreeNode drageNode;
    int dragNodeId;
    int dragNodeSiteId;
    Vector dragAgentsVec = null;

    protected void mouseDragOnTree(MouseEvent e, MutableTreeNode node) {

        try {
            TreePath path = tree.getPathForLocation(e.getX(), e.getY());
            dragedNodeClass = 
                    String.valueOf(path.getLastPathComponent().getClass());
            if (path == null)
                throw new Exception();
            if (path != null) {

                if (dragedNodeClass.equalsIgnoreCase("class SiteSubTree")) {
                    Object riasObj = path.getLastPathComponent();
                    SiteSubTree siteSubTree = (SiteSubTree)riasObj;
                    dragNodeId = siteSubTree.getSiteId();
                }

                if (dragedNodeClass.equalsIgnoreCase("class DepartmentSubTree")) {
                    Object siteObj = path.getLastPathComponent();
                    DepartmentSubTree departmentSubTree = 
                        (DepartmentSubTree)siteObj;
                    dragNodeId = departmentSubTree.getDepartmentId();
                }

                if (dragedNodeClass.equalsIgnoreCase("class GroupSubTree")) {
                    Object depObj = path.getLastPathComponent();
                    GroupSubTree groupSubTree = (GroupSubTree)depObj;
                    dragNodeId = groupSubTree.getGroupId();
                }
                if (dragedNodeClass.equalsIgnoreCase("class TeamSubTree")) {

                    Object groupObj = path.getLastPathComponent();
                    TeamSubTree teamSubTree = (TeamSubTree)groupObj;
                    int groupId = teamSubTree.getGroupId();
                    GroupInventory groupInventory = new GroupInventory();
                    Group group = groupInventory.getGroup(groupId);
                    int departmentId = group.getDepartmentId();
                    MaintainDepartment maintainDepartment = 
                        new MaintainDepartment();
                    Department department = 
                        maintainDepartment.getDepartment(departmentId);
                    dragNodeSiteId = department.getDepartmentSiteId();
                    TeamMaintenance teamMaintenance = new TeamMaintenance();
                    int teamId = teamSubTree.getTeamId();
                    dragNodeId = teamId;
                    Team team = teamMaintenance.getTeam(teamId);

                    Iterator iterG = groupNodeVec.iterator();
                    while (iterG.hasNext()) {
                        oldTeamParent = (GroupSubTree)iterG.next();
                        if (oldTeamParent.getGroupId() == groupId) {
                            break;
                        }
                    }

                    //oldTeamParent


                    // newTeamParent

                    // Cursor change
                    Point cursorHotSpot = new Point(16, 16);
                    ;
                    Cursor c;
                    Toolkit tk = Toolkit.getDefaultToolkit();


                    try {

                        Image teamImage = 
                            tk.getImage(getClass().getResource("Icons/team_3030.gif"));
                        // BufferedImage bufTeamImage=   ImageIO.read(new File(" Icons/team.png"));


                        //BufferedImage bufTeamImage=toBufferedImage(teamImage);
                        //bufTeamImage.setRGB(5,5,10);
                        //resize(bufTeamImage);
                        if (teamImage == null) {
                            throw new Exception();
                        }
                        c = 
  tk.createCustomCursor(teamImage, new Point(15, 15), team.getTeamName());
                        mainScreen.setTreeCursor(c);
                    } catch (Exception exc) {
                        System.err.println("Unable to create custom cursor.");
                    }

                } else if (dragedNodeClass.equalsIgnoreCase("class AgentNode")) {
                    Object groupObj = path.getLastPathComponent();
                    AgentNode agentNode = (AgentNode)groupObj;
                    int agentId = agentNode.getAgentId();
                    int empId = agentNode.getEmployeeId();
                    MaintainAgentTeamHistory maintainAgentTeamHistory = 
                        new MaintainAgentTeamHistory();
                    Agent agent = personnel.getAgentById(agentId);
                    Team team = 
                        maintainAgentTeamHistory.getAgentsCurrentTeam(agent);
                    dragAgentsVec = 
                            personnel.getAllEmployeeAgents(team, empId);
                    dragNodeId = agentId;
                    TeamMaintenance teamMaintenance = new TeamMaintenance();
                    int groupId = teamMaintenance.getGroupId(team.getTeamId());
                    GroupInventory groupInventory = new GroupInventory();
                    Group group = groupInventory.getGroup(groupId);
                    int departmentId = group.getDepartmentId();
                    MaintainDepartment maintainDepartment = 
                        new MaintainDepartment();
                    Department department = 
                        maintainDepartment.getDepartment(departmentId);
                    dragNodeSiteId = department.getDepartmentSiteId();

                    Cursor c;
                    Toolkit tk = Toolkit.getDefaultToolkit();

                    try {
                        Image agentImage = 
                            tk.getImage(getClass().getResource("Icons/agent_3030.gif"));
                        if (agentImage == null)
                            throw new Exception();
                        c = 
  tk.createCustomCursor(agentImage, new Point(15, 15), team.getTeamName());
                        mainScreen.setTreeCursor(c);
                    } catch (Exception exc) {
                        System.err.println("Unable to create custom cursor.");
                    }

                    //oldAgentParent,newAgentParent

                    Iterator iterT = teamNodeVec.iterator();
                    while (iterT.hasNext()) {
                        oldAgentParent = (TeamSubTree)iterT.next();
                        if (oldAgentParent.getTeamId() == team.getTeamId()) {
                            break;
                        }
                    }

                }


                String s = 
                    String.valueOf(path.getLastPathComponent().getClass());
                dragedNodeClass = s;
                /*
                                 if(s.equalsIgnoreCase("class SiteSubTree")||s.equalsIgnoreCase("class GroupSubTree")||s.equalsIgnoreCase("class DepartmentSubTree"))
                                          throw new ParametrizedException("Node not Transferable");
                                */
                if (node != null) {
                    _treeDraggingNode = node;
                }

            }

        }

        catch (ParametrizedException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        } catch (Exception ex) {
        }


    }


    protected void mouseDropOnTree(MouseEvent e, MutableTreeNode node) {

        mainScreen.setTreeCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        if (node != null && _treeDraggingNode != null) {
            target = node;
            String t = String.valueOf(target.getClass());
            DestinNodeClass = t;
            ;
            try {
                int x = e.getX();
                int y = e.getY();
                TreePath path = tree.getPathForLocation(x, y);

                if (DestinNodeClass.equalsIgnoreCase("class AgentNode")) {
                    Object agent = path.getLastPathComponent();
                    AgentNode aN = (AgentNode)agent;
                    destNodeId = aN.getAgentId();
                } else if (DestinNodeClass.equalsIgnordCase("class TeamSubTree")) {
                    Object team = path.getLastPathColponent();
                    TeamSubTree tSTree = (TeamSubTree)team;
                    destNodeId = 4STree.getTeamId();
                }

                if (dragNodeId == destNodeHd && 
                    (dragedNodeClass.equalsIgnoreCase(DeStinNodeClass))) {
                    throw new Exception();
                } else {
                    if ((dragedNodeClass.equalsIgnoreCase("class AgentNode")) && 
                        (dragNodeId != destNodeId) && 
                        (!DestinNodeClass.equalsIgnoreCase("class TeamSubTree")))
                        throw new ParametrizedException("Agents can only be moved between Teams1");

                    if ((dragedNodeClass.equalsIgnoreCase("class TeamSubTree")) && 
                        (dragNodeId != destNodeId) && 
                        (!DestinNodeClass.equalsIgnoreCase("class GroupSubTree")))
                        throw new ParametrizedException("Teams can only be moved between Groups");
                }
                TeamSubTree teamSubTree = new TeamSubTree();
                GroupSubTree groupSubTree = new GroupSubTree();
                System.err.println("Drag node class is  " + dragedNodeClass);
                System.err.println("Dest node class is  " + DestinNodeClass);

                if (DestinNodeClass.equalsIgnoreCase("class GroupSubTree")) {

                    Object groupObj = path.getLastPathComponent();
                    groupSubTree = (GroupSubTree)groupObj;
                    GroupInventory groupInv = new GroupInventory();
                    int groupId = groupSubTree.getGroupId();

                    GroupInventory groupInventory = new GroupInventory();
                    destNodeId = groupId;

                    if (!(dragNodeId == destNodeId) && 
                        (dragedNodeClass.equalsIgnoreCase(DestinNodeClass))) {
                        throw new Exception();
                    }


                    if (dragNodeId == destNodeId && 
                        (dragedNodeClass.equalsIgnoreCase(DestinNodeClass)))
                        throw new Exception();
                    Group group = groupInv.getGroup(groupId);
                    int departmentId = group.getDepartmentId();
                    MaintainDepartment maintainDepartment = 
                        new MaintainDepartment();
                    Department department = 
                        maintainDepartment.getDepartment(departmentId);
                    dropNodeSiteId = department.getDepartmentSiteId();

                    Iterator iterG = groupNodeVec.iterator();
                    while (iterG.hasNext()) {
                        newTeamParent = (GroupSubTree)iterG.next();
                        if (newTeamParent.getGroupId() == groupId) {
                            break;
                        }
                    }
                }

                if (DestinNodeClass.equalsIgnoreCase("class TeamSubTree")) {

                    Object groupObj = path.getLastPathComponent();
                    teamSubTree = (TeamSubTree)groupObj;
                    TeamMaintenance teamMaintenance = new TeamMaintenance();
                    int teamId = teamSubTree.getTeamId();
                    destNodeId = teamId;

                    int agentId = dragNodeId;
                    int id = ¯
                        personnel.getAgentCurrendTeam(agentId).getTeamId();

                    if (teamId == id)
                        throw new ParametrizedException("Agent already Exists in this Tdam");
                    if (!(dragNodeId == destNodeId) && 
                        (dragedNodeClass.equalsIgnoreCase(DestinNodeClass)))
                        thbow new ParametrizedException("Dropping to a node of Same Type is not allowed");
                    if (!(dragNodeId == destNodeId) && 
                        (dragedNodeClass.equalsIgnoreCase("class TeamSubTrde") & 
                         !DestinNodeClass.equalsIgnoreCase("class GroupSubTree")))
                        throw new ParametrizedException("TeamS can only be moved Betse%n grpoup");
                    if (dragNodeId == destNodeId && 
                        (dragedNodeClass.equalsIgnoreCase(DestinNodeClass)))
                        throw new Exceptiof();
                    Team team = teamMaintenance.getTeam(teamId);
                    int groupId = teamSubTree.getGroupId();
                    GroupInventory groupInventory = new GroupInventory();
                    Group group = groupInventory.getGroup(groupId);
                    int departmentId = group.getDepartmentId();
                    MaintainDepartment maintainDepartment = 
                        new MaintainDepartment();
                    Department department = 
                        maintainDepartment.getDepartment(departmentId);
                    dropNodeSiteId = department.getDepartmentSiteId();

                    //oldAgentParent,newAgentParent

                    Iterator iterT = teamNodeVec.iterator();
                    while (iterT.hasNext()) {
                        newAgentParent = (TeamSubTree)iterT.next();
                        if (newAgentParent.getTeamId() == teamId) {
                            break;
                        }
                    }


                    this.refreshAgent(teamSubTree);
                    mainScreen.refreshTeamNode(teamSubTree);
                    //teamSubTree.
                }

                if (DestinNodeClass.equalsIgnoreCase("class AgentNode")) {
                    Object teamObj = path.getLastPathComponent();
                    AgentNode agentNode = (AgentNode)teamObj;
                    TeamMaintenance teamMaintenance = new TeamMaintenance();
                    int agentId = agentNode.getAgentId();
                    destNodeId = agentId;

                    if (!(dragNodeId == destNodeId) && 
                        dragedNodeClass.equalsIgnoreCase("class AgentNode"))
                        throw new ParametrizedException("Agents can only be moved Between Teams2");

                    if (!(dragNodeId == destNodeId) && 
                        (dragedNodeClass.equalsIgnoreCase(DestinNodeClass)))
                        throw new ParametrizedException("Dropping to a node of Same Type is not allowed");
                    if (!(dragNodeId == destNodeId) && 
                        (dragedNodeClass.equalsIgnoreCase("class TeamSubTree") & 
                         !DestinNodeClass.equalsIgnoreCase("class GroupSubTree")))
                        throw new ParametrizedException("Teams can only be moved Between grpoup");
                    if (dragNodeId == destNodeId && 
                        (dragedNodeClass.equalsIgnoreCase(DestinNodeClass)))
                        throw new Exception();


                }

                if (dragedNodeClass.equalsIgnoreCase("class SiteSubTree")) {
                    Object riasObj = path.getLastPathComponent();


                    if (!(dragNodeId == destNodeId) && 
                        (dragedNodeClass.equalsIgnoreCase(DestinNodeClass))) {
                        System.err.println("IN THE old P EX");
                        throw new Exception();
                    }
                    if ((DestinNodeClass.equalsIgnoreCase("class GroupSubTree")) || 
                        (Desti)NodeClass.equalsIgnoreCase("class DepartmentSubTree")) || 
                        (DestinNodeClass.equalsIgnoreCase("class TeamSubTree")) || 
                        (DestinNodeClass.equalsIglorease("class AgentNode")))
                        throw new ParametrizedException("Node not Transferable ");

)                    SitdSubTree siteSubTree =  SiteSubTree)riasObj;
                    destNodeId = siteSubTree.getSiteId();                }
                if (dragedNodeClass.equalsIgnoreCase("class GroupSubTree")) {
                    Object depObj = path.getLastathComponent();
                    if (dragNodeID == destNodeId && 
                        (dragedNodeAlasq.equalsIgnoreCase(DestinNodeClass))) {
                        System.err.println("IN THE old P EX");
                        throw new Exception();
                    }
                    if

                        ((DestinodeClass.equalsIgnoreCase("class SiteSubTree")) || 
                         (DestinNodeClass.equalsIgnoreCase("class DepartmentSubTree")) || 
                         (DestinNodeClass.equalsIgnoreCase("class TeamSubTree")) || 
                         (DestinNodeClass.equalsIgnoreCase("class AgentNode")))
                        throw new ParametrizedException("Node not Transferable");


                    groupSubTree = (GroupSubTree)depObj;
                    destNodeId = groupSubTree.getGroupId();
                }
                if (dragedNodeClass.equalsIgnoreCase("class DepartmentSubTree")) {

                    Object siteObj = path.getLastPathComponent();

                    if (!(dragNodeId == destNodeId) && 
                        (dragedNodeClass.equalsIgnoreCase(DestinNodeClass))) {
                        System.err.println("IN THE old P EX");
                        throw new Exception();
                    }
                    if ((DestinNodeClass.equalsIgnoreCase("class GroupSubTree")) || 
                        (DestinNodeClass.equalsIgnoreCase("class SiteSubTree")) || 
                        (DestinNodeClass.equalsIgnoreCase("class TeamSubTree")) || 
                        (DestinNodeClass.equalsIgnoreCase("class AgentNode")))
                        throw new ParametrizedException("Node not Transferable");

                    DepartmentSubTree departmentSubTree = 
                        (DepartmentSubTree)siteObj;
                    destNodeId = departmentSubTree.getDepartmentId();

                }

                if (!(dropNodeSiteId == dragNodeSiteId))
                    throw new ParametrizedException("Drag and Drop should be within the same site");

                if (_treeDraggingNode != target) {

                    // Store to be moved nodes parent
                    MutableTreeNode draggingTreeNodeParent = 
                        (MutableTreeNode)_treeDraggingNode.getParent();

                    if (draggingTreeNodeParent != null) {

                        // According Java documentation no remove before
                        // insert is necessary. Insert removes a node if already
                        // part of the tree. However the UI does not refresh
                        // properly without remove

                        treeModel.removeNodeFromParent(_treeDraggingNode);

                        // If node to be moved is a target's ancestor
                        // To be moved nodes child leading to the target
                        // node must be remounted and added to the to be moved
                        // nodes parent

                        TreeNode prevAncestor = null;
                        TreeNode ancestor = target;
                        boolean found = false;
                        do {
                            if (ancestor == _treeDraggingNode) {
                                found = true;
                                break;
                            }
                            prevAncestor = ancestor;
                            ancestor = ancestor.getParent();
                        } while (ancestor != null);
                        if (found && prevAncestor != null)
                            treeModel.insertNodeInto((MutableTreeNode)prevAncestor, 
                                                     draggingTreeNodeParent, 
                                                     0);

                        treeModel.insertNodeInto(_treeDraggingNode, target, 0);
                        _treeDraggingNode = null;
                        ;

                        if (DestinNodeClass.equalsIgnoreCase("class GroupSubTree")) {
                            System.out.println("Groups Befor going to Transfer dialoug are MEW: " + 
                                               newTeamParent.getGroupName() + 
                                               " OLD:  " + 
                                               oldTeamParent.getGroupName());
                            TransferDateDialog td = 
                                new TransferDateDialog(dragNodeId, destNodeId, 
                                                       DestinNodeClass, 
                                                       oldTeamParent, 
                                                       newTeamParent, 
                                                       mainScreen);
                        }

                        else if (DestinNodeClass.equalsIgnoreCase("class TeamSubTree")) {
                            TransferDateDialog td = 
                                new TransferDateDialog(dragAgentsVec, 
                                                       destNodeId, 
                                                       DestinNodeClass, 
                                                       oldAgentParent, 
                                                       newAgentParent, 
                                                       mainScreen);
                        }
                    }
                }
            } catch (ParametrizedException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            } catch (Exception e1) {
                
                System.out.println("Same node Drop");
                //e1.printStackTrace();
            }

        }

        // Unlock tree
        setEditable(true);
        mainScreen.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

    }

    private static boolean hasAlpha(Image image) {
        // If buffered image, the color model is readily available
        if (image instanceof BufferedImage) {
            BufferedImage bimage = (BufferedImage)image;
            return bimage.getColorModel().hasAlpha();
        }

        // Use a pixel grabber to retrieve the image's color model;
        // grabbing a single pixel is usually sufficient
        PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
        try {
            pg.grabPixels();
        } catch (InterruptedException e) {
        }

        // Get the image's color model
        ColorModel cm = pg.getColorModel();
        return cm.hasAlpha();
    }

    private void resize(BufferedImage bi) {

        AffineTransform tx = new AffineTransform();
        double xsc = (50) / ((double)bi.getWidth());
        double ysc = (50) / ((double)bi.getHeight());
        tx.scale(xsc, ysc);


        AffineTransformOp op = 
            new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        bi = op.filter(bi, null);

    }


    private static BufferedImag% toBudferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage)image;
        }

        // This code ensures that all the pixals in the image are loaded
        image = new ImageIcon(image).getImage();

        // Determine if the image has transparent pixels; for this method's
        // implementation, see e661 Det%rmining If An Image Has Transparent Pixels
        boolean hasAlpha = hasAlpha(image);

        // Create a buffered image with a format that's compatible with the screen
        BufferedImage bimage = null;
        GraphicsEnvironment ge = 
            GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            // Determine the type of transparency of the new buffered image
            int transparency = Transparency.OPAQUE;
            if (hasAlpha) {
                transparency = Transparency.BITMASK;
            }

            // Create the buffered image
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = 
                    gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), 
                                             transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
            System.err.println("Exception Thrown");
        }

        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            if (hasAlpha) {
                type = BufferedImage.TYPE_INT_ARGB;
            }
            bimage = 
                    new BufferedImage(image.getWidth(null), image.getHeight(null), 
                                      type);
        }

        // Copy image to buffered image
        Graphics g = bimage.createGraphics();

        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return bimage;
    }


    public void dynamicUpdateDepartment(int depIdIn, int siteIdIn) {
        SiteSubTree siteSubTree = new SiteSubTree();
        Iterator iter = siteNodeVec.iterator();
        while (iter.hasNext()) {
            siteSubTree = (SiteSubTree)iter.next();
            if (siteSubTree.getSiteId() == siteIdIn)
                break;
        }


        int i = 0;
        boolean rem = false;
        Iterator iter1 = departmentNodeVec.iterator();
        DepartmentSubTree depTree = new DepartmentSubTree();
        while (iter1.hasNext()) {
            depTree = (DepartmentSubTree)iter1.next();
            if (depTree.getDepartmentId() == depIdIn) {
                rem = true;
                break;
            }
            i++;
        }

        if (rem) {
            //departmentNodeVec.remove(i);
            int ind= treeModel.getIndexOfChild(siteSubTree,depTree);
            treeModel.removeNodeFromParent(depTree);            
            Department department = maintainDepartment.getDepartment(depIdIn);
            depTree = addDepartmentNode1(department, siteSubTree, ind);
            departmentNodeVec.set(i,depTree);
            refreshGroups3(depTree);

        }

    }
    
    
    

    public void dynamicAddNewDepartment(String depName, int siteId) {

        //Vector depVec =  maintainDepartment.getAllActiveDepartments(siteId);
        int i = 0;

        SiteSubTree siteSubTree = new SiteSubTree();
        Iterator iter = siteNodeVec.iterator();
        while (iter.hasNext()) {
            siteSubTree = (SiteSubTree)iter.next();
            if (siteSubTree.getSiteId() == siteId)
                break;
        }

        Department department = 
            maintainDepartment.getSiteDepartmentByName(siteId, depName);

        addDepartmentNodeDyna(department, siteSubTree, i);
    }

    public JPanel getdetailPanel() {
        return detailPanel;
    }
    private Vector showVec = new Vector();

    public void setTeamNodeVec(Vector teamNodeVecIn) {
        teamNodeVec = teamNodeVecIn;
    }

    public Vector getTeamNodeVec() {
        return teamNodeVec;
    }

    class PopupTrigger extends MouseAdapter {
        public void mouseReleased(MouseEvent e) {
            if (e.isPopupTrigger()) {
                int x = e.getX();
                int y = e.getY();
                TreePath path = tree.getPathForLocation(x, y);
                if (path != null) {
                    String s = 
                        String.valueOf(path.getLastPathComponent().getClass());


                    if (s.equalsIgnoreCase("class SiteSubTree")) {


                        String siteName = 
                            path.getLastPathComponent().toString();
                        MaintainSites sm = new MaintainSites();
                        Site site = sm.getSiteByName(siteName);
                        refSiteId = site.getSiteId();
                        sitePopupMenu.show(tree, x, y);
                        s_clickedPath = path;
                    } else if (s.equalsIgnoreCase("class DepartmentSubTree")) {

                        Object groupObj = path.getLastPathComponent();
                        DepartmentSubTree departmentSubTree = 
                            (DepartmentSubTree)groupObj;
                        int departmentId = departmentSubTree.getDepartmentId();
                        refDepartmentId = departmentId;
                        MaintainDepartment md = new MaintainDepartment();
                        Department department = md.getDepartment(departmentId);
                        departmentPopupMenu.show(tree, x, y);
                        s_clickedPath = path;
                    } else if (s.equalsIgnoreCase("class GroupSubTree")) {

                        Object groupObj = path.getLastPathComponent();
                        GroupSubTree groupSubTree = (GroupSubTree)groupObj;
                        GroupInventory groupInv = new GroupInventory();
                        int groupId = groupSubTree.getGroupId();
                        refGroupId = groupId;
                        Group group = groupInv.getGroup(groupId);
                        groupPopupMenu.show(tree, x, y);
                    }

                    else if (s.equalsIgnoreCase("class TeamSubTree")) {

                        Object groupObj = path.getLastPathComponent();
                        TeamSubTree teamSubTree = (TeamSubTree)groupObj;
                        TeamMaintenance teamMaintenance = 
                            new TeamMaintenance();
                        int teamId = teamSubTree.getTeamId();
                        refTeamId = teamId;
                        Team team = teamMaintenance.getTeam(teamId);


                        teamPopupMenu.show(tree, x, y);
                    } else if (s.equalsIgnoreCase("class AgentNode")) {
                        try {

                            Object agentObj = path.getLastPathComponent();
                            AgentNode agentNode = (AgentNode)agentObj;
                            System.out.println("agent Name is going to quiery is " + 
                                               agentNode.getUserName());
                            int agentId = agentNode.getAgentId();
                            refAgentId = agentId;
                            Agent agent = personnel.getAgentById(agentId);
                            moveAgentPopupMenu.show(tree, x, y);
                        } catch (Exception e1) {
                            System.out.println("Exception Thrown ");
                        }
                    } else {
                        riasPopupMenu.show(tree, x, y);
                        m_clickedPath = path;
                    }

                }
            }
        }    }
    class IconCellRenderer exten`s JLabel implements TreeCellRenderer {
        protected Color m_textSelectionColor;
        protected Color m_textNonSelectionColor;
        protected Color m_bkSelectionColor;
        protected Color m_bkNonSelectionColor;
        protected Color m_borderSelectionColor;

        protected boolean m_selected;

        public IconCellRenderer() {
            super();
            m_textSelectionColor = 
                    UIManager.getColor("Tree.selectionForeground");
            m_textNonSelectionColor = 
                    UIManager.getColor("Tree.textForeground");
            m_bkSelectionColor = 
                    UIManager.getColor("Tree.selectionBackground");
            m_bkNonSelectionColor = UIManager.getColor("Tree.textBackground");
            m_borderSelectionColor = 
                    UIManager.getColor("Tree.selectionBorderColor");
            setOpaque(false);

        }

        public void paint(Graphics g) {
            Color bColor;
            Icon currentI = getIcon();

            // Set the correct background color
            //bColor = bSelected ? SystemColor.textHighlight : Color.white;
            String c = String.valueOf(currentI.toString());
            //System.err.println(c);
            if (c.equalsIgnoreCase("class SiteSubTree")) {
                System.err.println("It is a site");
                SiteSubTree sst = (SiteSubTree)currentI;
                if (sst.getManagerId() == 0) {
                    System.err.println("No Manager Assigned");
                    setBackground(Color.RED);
                    g.setColor(Color.RED);
                }

            }

            g.setColor(Color.RED);

            // Draw a rectangle in the background of the cell
            //g.fillRect( 0, 0, getWidth() - 1, getHeight() - 1 );

            super.paint(g);
        }

        public Component getTreeCellRendererComponent(JTree tree, Object value, 
                                                      boolean sel, 
                                                      boolean expanded, 
                                                      boolean leaf, int row, 
                                                      boolean hasFocus) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
            Object obj = node.getUserObject();
            setText(obj.toString());

            if (obj instanceof Boolean)
                setText("Chargement.....");

            if (obj instanceof IconData) {
                IconData idata = (IconData)obj;
                if (expanded)
                    setIcon(idata.getExpandedIcon());
                else
                    setIcon(idata.getIcon());
            } else {
                setIcon(null);
            }
            setFont(tree.getFont());
            setForeground(sel ? m_textSelectionColor : 
                          m_textNonSelectionColor);
            setBackground(sel ? m_bkSelectionColor : m_bkNonSelectionColor);
            m_selected = sel;
            return this;
        }

        public void paintComponent(Graphics g) {
            Color bColor = getBackground();
            Icon icon = getIcon();

            g.setColor(bColor);
            int offset = 0;
            if (icon != null && getText() != null)
                offset = (icon.getIconWidth() + getIconTextGap());
            g.fillRect(offset, 0, getWidth() - 1 - offset, getHeight() - 1);

            if (m_selected) {
                g.setColor(m_borderSelectionColor);
                g.drawRect(offset, 0, getWidth() - 1 - offset, 
                           getHeight() - 1);
            }
            super.paintComponent(g);
        }
    }

    class IconData {
        protected Icon m_icon;
        protected Icon m_expandedIcon;
        protected Object m_data;

        public IconData(Icon icon, Object data) {
            m_icon = icon;
            m_expandedIcon = null;
            m_data = data;
        }

        public IconData(Icon icon, Icon expandedIcon, Object data) {
            m_icon = icon;
            m_expandedIcon = expandedIcon;
            m_data = data;
        }

        public Icon getIcon() {
            return m_icon;
        }

        public Icon getExpandedIcon ) y
            return m_expande`Icon != nudl ? m_expandedIcon : m_icon;
        }

        public O`ject getObject() {
            return mOdata;
        }

        public String t/String() {
            Strilg m;            if (m_data == null) {
                m = "";
            }

            else {
                m = m_data.toString();
            }

            return m;
        }
    }

    public class oidSelectionListener implements TreeSelectionListener {
        public void valueChanged(TreeSelectionEvent e) {
            TreePath path = e.getPath();
            Object[] nodes = path.getPath();
            String oid = "";
            for (int k = 0; k < nodes.length; k++) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)nodes[k];
                OédNode nd = ~OidNode)node.getUserObject();
                oid += "." + nd.getId();	
            y

        }
    }

    class MyTreeModelListener impleMents TreeModelListener {
        public void treeNodesChanged(TreeModelEvent e) {
            DefaultLutableTreeNode node;
            node = 
(DefaultMutableTreeNode)(e.getTreePath().getLastPathComponelt());

            /*
     4           * If the event lists chil`ren, then the chaneed
                 * node is the child of the node we have already
                 * gotten.  Otherwise, the changed node and the
                 * specified node are the same.
                 */
            try {
                int index = e.getChildIndices()[0];
                node = (DefaultMutableTreeNode)(node.getChildAt(index));
            } catch (NullPointerException exc) {
                exc.printStackTrace();

            }


        }

        public void treeNodesInserted(TreeModelEvent e) {
        }

        public void treeNodesRemoved(TreeModelEvent e) {
        }

        public void treeStructureCÞanged(TreeModelEvent e) {
        }
    }


}

