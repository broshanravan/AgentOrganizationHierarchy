
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;

/*
 * Created on 23-Feb-2005
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
public class AgentNode extends DefaultMutableTreeNode implements TreeNode,Transferable,Serializable
{
	private String  userName;
	private String password;
	private int employeeId;
	private int agentId;
	private int applicationRoleId;
	private boolean areChildrenDefined = false;
	private int outlineNum;
	private int teamId;
	
	final public static DataFlavor INFO_FLAVOR =new DataFlavor(AgentNode.class,"agent");
	static DataFlavor flavors[]={INFO_FLAVOR};
	
	/**
	 * 
	 */
	public AgentNode() 
	{
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param arg0
	 */
	public AgentNode(Object arg0) 
	{
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param arg0
	 * @param arg1
	 */
	public AgentNode(Object arg0, boolean arg1) 
	{
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	public boolean isDataFlavorSupported(DataFlavor df)
	{
		return df.equals(INFO_FLAVOR);
	}
	
	public synchronized Object getTransferData(DataFlavor df)
		throws UnsupportedFlavorException,IOException
	{
		if (df.equals(INFO_FLAVOR))
		{
			return this;
		}
		else throw new UnsupportedFlavorException(df);
	}
	
	public DataFlavor[] getTransferDataFlavors()
	{
		return flavors;
	}
	
	private void writeObject(ObjectOutputStream out)
		throws IOException,ClassNotFoundException
	{
		out.defaultWriteObject();
	}

	private void readObject(ObjectInputStream in)
		throws IOException,ClassNotFoundException
	{
		in.defaultReadObject();
	}

	
	//Mutators
	
	public void setAgentId(int agentIdIn)
	{
		agentId = agentIdIn;
	}
	
	public void setUserName(String unIn)
	{
		userName = unIn;
	}
	public void setPassword(String pwIn)
	{
		password = pwIn;
	}
	
	public void setEmployeeId(int empIdIn)
	{
		employeeId = empIdIn;
	}
	
	public void setAppRpleId(int appRoleIdIn)
	{
		applicationRoleId = appRoleIdIn;
	}
	
	public void setTeamId(int teamIdIn)
	{
		teamId = teamIdIn;
	}
	
	//Accessors
	public int getTeamId()
	{
		return teamId;
	}
	public int getAgentId()
	{
		return agentId;
	}
	public String getUserName()
	{
		return userName;
	}
	public String getPassword()
	{
		return password ;
	}
	
	public int getEmployeeId()
	{
		return employeeId ;
	}
	
	public int getAppRpleId()
	{
		return applicationRoleId ;
	}
	
	public String toString()
	 { 
	 	TreeNode parent = getParent();
	    if (parent == null)
	      return(String.valueOf(outlineNum));
	    else
	 	return(userName);
	 }
	 
	
	 
	 	
}
