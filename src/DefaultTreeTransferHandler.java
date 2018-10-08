
/*
 * Created on 24-Feb-2005
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
import java.awt.*;
import javax.swing.*;
import javax.swing.tree.*;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
 
public class DefaultTreeTransferHandler extends AbstractTreeTransferHandler
{
 
	public DefaultTreeTransferHandler(MainTree tree, int action) 
	{
		super(tree, action, true);
	}
 
	public boolean canPerformAction(MainTree target, DefaultMutableTreeNode draggedNode, int action, Point location) 
	{
		TreePath pathTarget = target.getPathForLocation(location.x, location.y);
		if (pathTarget == null) 
		{
			target.setSelectionPath(null);
			return(false);
		}
		target.setSelectionPath(pathTarget);
		if(action == DnDConstants.ACTION_COPY)
		{
			return(true);
		}
		else
		if(action == DnDConstants.ACTION_MOVE) 
		{	
			DefaultMutableTreeNode parentNode =(DefaultMutableTreeNode)pathTarget.getLastPathComponent();				
			if (draggedNode.isRoot() || parentNode == draggedNode.getParent() || draggedNode.isNodeDescendant(parentNode))
			{					
				return(false);	
			}
			else 
			{
				return(true);
			}				 
		}
		else 
		{		
			return(false);	
		}
	}
 
	public boolean executeDrop(MainTree target, DefaultMutableTreeNode draggedNode, DefaultMutableTreeNode newParentNode, int action) { 
		if (action == DnDConstants.ACTION_COPY) {
			DefaultMutableTreeNode newNode = target.makeDeepCopy(draggedNode);
			((DefaultTreeModel)target.getModel()).insertNodeInto(newNode,newParentNode,newParentNode.getChildCount());
			TreePath treePath = new TreePath(newNode.getPath());
			target.scrollPathToVisible(treePath);
			target.setSelectionPath(treePath);	
			return(true);
		}
		if (action == DnDConstants.ACTION_MOVE) {
			draggedNode.removeFromParent();
			((DefaultTreeModel)target.getModel()).insertNodeInto(draggedNode,newParentNode,newParentNode.getChildCount());
			TreePath treePath = new TreePath(draggedNode.getPath());
			target.scrollPathToVisible(treePath);
			target.setSelectionPath(treePath);
			return(true);
		}
		return(false);
	}
}



