
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

/*
 * Created on 16-Mar-2005
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
public class DepartmentSubTree extends DefaultMutableTreeNode implements TreeNode
{
	
	private String departmentName;
	private int departmentId;
	private boolean areChildrenDefined = false;
	private int outlineNum;
	private int numChildren;
	private int siteId;

	
	  
	
	
	public void setDeparmentName(String nameIn)
	{
		departmentName=nameIn;
	}
	
	public void setSiteId(int siteIdIn)
	{
		siteId = siteIdIn;
	}
	
	public int getSiteId()
	{
		return siteId;
	}
	
	public void setDepartmentId(int idIn)
	{
		departmentId = idIn;
	}
	
	public String getDepartmentName()
	{
		return departmentName;
	}
	
	public int getDepartmentId()
	{
		return departmentId;
	}
	
	
	/**
	 * 
	 */
	public DepartmentSubTree() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param arg0
	 */
	public DepartmentSubTree(Object arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param arg0
	 * @param arg1
	 */
	public DepartmentSubTree(Object arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}


    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setAreChildrenDefined(boolean areChildrenDefined) {
        this.areChildrenDefined = areChildrenDefined;
    }

    public boolean isAreChildrenDefined() {
        return areChildrenDefined;
    }

    public void setOutlineNum(int outlineNum) {
        this.outlineNum = outlineNum;
    }

    public int getOutlineNum() {
        return outlineNum;
    }

    public void setNumChildren(int numChildren) {
        this.numChildren = numChildren;
    }

    public int getNumChildren() {
        return numChildren;
    }
}
