
import java.util.Vector;

/*
 * Created on 17-Feb-2005
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
public class Group 
{
	private int groupId;
	private String groupName;
	private int departmentId;
	
	private int managerId;
	
	public Group()
	{
		
	}
	public Group(int groupIdIn,
				 String groupNameIn,
				 int departmentIdIn)
	{
		groupId = groupIdIn;
		groupName=groupNameIn;
		departmentId = departmentIdIn;
		
	}
	
	public Group(String groupNameIn,
			 	 int departmentIdIn)
	{
	
	groupName=groupNameIn;
	departmentId = departmentIdIn;
	
	}
	//Accessors
	
	public int getGroupId()
	{
		return groupId;
	}
	public String getGroupName()
	{
		return groupName;
	}
	public int getDepartmentId()
	{
		return departmentId;
	}
	public int getManagerId()
	{
		return managerId;
	}
	
	
	//Mutators
	public void setGroupId(int groupIdIn)
	{
		 groupId= groupIdIn;
	}
	public void setGroupName(String groupNameIn)
	{
		 groupName= groupNameIn;
	}
	public void setDepartmentId(int departmentIdIn)
	{
		 departmentId= departmentIdIn;
	}
	
	public void setManagerId(int managerIdIn)
	{
		managerId=managerIdIn;
	}
	
	public Vector getAllAutorizedManagers()
	{
		MaintainDepartment maintainDepartment = new MaintainDepartment();
		Department department= maintainDepartment.getDepartment(getDepartmentId()) ;
		Personnel personnel = new Personnel();
		Vector managersVec = department.getAutorisedManagers();
		Employee guoupManager = personnel.findEmployeeByEmpId(managerId);		
		if (!(guoupManager==null))
		{
			managersVec.addElement(guoupManager);
		}
		return managersVec;
	}
}
