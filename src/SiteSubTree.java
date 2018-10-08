
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;





/*
 * Created on 15-Feb-2005
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
public class SiteSubTree extends DefaultMutableTreeNode implements TreeNode
{
	
	
	
	/**
	 * 
	 */
	public SiteSubTree() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param arg0
	 */
	public SiteSubTree(Object arg0) 
	{
		super(arg0);
		// TODO Auto-generated constructor stub
		 
	}
	/**
	 * @param arg0
	 * @param arg1
	 */
	public SiteSubTree(Object arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}
	private String siteName;
	private int siteId;
	private int managerId;
	private boolean areChildrenDefined = false;
	private int outlineNum;
	private int numChildren;

	public static final ImageIcon SITE_ICON = new ImageIcon("Icons/Site.gif") ;
	
	
	
	
	public void setName(String nameIn)
	{
		siteName=nameIn;
	}
	
	public void setSiteId(int idIn)
	{
		siteId = idIn;
	}
	
	public void setManagerIdId(int manIdIn)
	{
		managerId= manIdIn;
	}
	
	
	public String getSiteName()
	{
		return siteName;
	}
	
	public int getSiteId()
	{
		return siteId;
	}
	
	public int getManagerId()
	{
		return managerId;
	}

	 public String toString()
	 { 
	 	TreeNode parent = getParent();
	    if (parent == null)
	      return(String.valueOf(outlineNum));
	    else
	 	return(siteName);
	 }
	 
	 			
}
