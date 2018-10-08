
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;

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
public class TeamSubTree extends DefaultMutableTreeNode implements TreeNode,Transferable,Serializable
{
	
	
	public TeamSubTree() 
	{
		super();
		// TODO Auto-generated constructor stub
	
	}
	
	public TeamSubTree(Object arg0) 
	{
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	
	public TeamSubTree(Object arg0, boolean arg1) 
	{
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	private String teamName;
	private int teamId;
	private int managerId;
	private boolean areChildrenDefined = false;
	private int outlineNum;
	private int numChildren;
	private int groupId;

	final public static DataFlavor INFO_FLAVOR =new DataFlavor(TeamSubTree.class,"team");
        public static final DataFlavor NODE_FLAVOR = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType, "Node");
  
	static DataFlavor flavors[]={INFO_FLAVOR};
	
	public boolean isDataFlavorSupported(DataFlavor df)
	{
		return df.equals(INFO_FLAVOR);
	}
	
	public synchronized  Object getTransferData(DataFlavor df)
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

	
	public void setTeamName(String nameIn)
	{
		teamName=nameIn;
	}
	
	public void setTeamId(int idIn)
	{
		teamId = idIn;
	}
	
	public void setManagerId(int managerIdIn)
	{
		managerId = managerIdIn;
	}
	public int getManagerId()
	{
		return managerId;
	}
	
	public String getTeamName()
	{
		return teamName;
	}
	
	public int getTeamId()
	{
		return teamId;
	}
	
	
	public void setGroupId(int groupIdIn)
	{
		groupId= groupIdIn;
	}
	
	public int getGroupId()
	{
		return groupId;
	}


	 public String toString()
	 { 
	 	TreeNode parent = getParent();
	    if (parent == null)
	      return(String.valueOf(outlineNum));
	    else
	 	return(teamName);
	 }
	 
	

	 			
}

