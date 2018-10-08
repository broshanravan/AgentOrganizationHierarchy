
import javax.swing.tree.DefaultMutableTreeNode;
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
public class GroupSubTree extends DefaultMutableTreeNode implements TreeNode
{
	
	
	private String groupName;
	private int groupId;
	private boolean areChildrenDefined = false;
	private int outlineNum;
	private int numChildren;
	private int departmentId;
	
	public void setGroupName(String nameIn)
	{
		groupName=nameIn;
	}
	
	public void setGroupId(int idIn)
	{
		groupId = idIn;
	}
	
	public String getGroupName()
	{
		return groupName;
	}
	
	public int getGroupId()
	{
		return groupId;
	}
	public int getDepartmentId()
	{
		return departmentId;
	}
	
	public void setDepartmentId(int departmentIdIn)
	{
		departmentId = departmentIdIn;
	}

	public String toString()
	{ 
	 	TreeNode parent = getParent();
                if (parent == null)
                    return(String.valueOf(outlineNum));
                else
                    return(groupName);
	}
	 
        public GroupSubTree() 
        {
                  super();
        }
        public GroupSubTree(Object arg0) 
        {
                  super(arg0);
        }
          
        public GroupSubTree(Object arg0, boolean arg1) 
        {
                  super(arg0, arg1);
        }
	
	 			
}
