
import java.util.Date;

/*
 * Created on 14-Feb-2005
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
public class Employee 
{
	private int employeeId;
	private String forName;
	private String surName;
	private int groupId;
	private Date dateStartPlaned;
	private Date dateEndPlaned;
	private Date dateStartAct;
	private Date dateEndAct;
	private Date dateOfBirth;
	private String riasEmpNo;
  private String email;

	//Defult Constructor
	public Employee()
	{
	
	}
	
	//Accessors
  public String getEmail()
  {
    return email;
  }
	public int getEmployeeId()
	{
		return employeeId;
	}
	public String getForName()
	{
		return forName;
	}
	public String getSurName()
	{
		return surName;
	}
	public Date getDateStartPlaned()
	{
		return dateStartPlaned;
	}
	public Date getDateEndPlaned()
	{
		return dateEndPlaned;
	}
	public Date getDateStartAct()
	{
		return dateStartAct;
	}
	public Date getDateEndAct()
	{
		return dateEndAct;
	}
	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}
	public String getRiasEmpNo()
	{
		return riasEmpNo;
	}
	
	public int getGroupId()
	{
		return groupId;
	}
	
	//mutarors
  
  public void setEmail(String emailIn)
  {
    email=emailIn;
  }
	
	public void setGroupId(int groupIdIn)
	{
		groupId = groupIdIn;
	}
	public void setEmployeeId(int employeeIdIn)
	{
		 employeeId = employeeIdIn;
	}
	public void setForName(String forNameIn)
	{
		forName= forNameIn;
	}
	public void setSurName(String surNameIn)
	{
		 surName=surNameIn;
	}
	public void setDateStartPlaned(Date dateStartPlanedIn)
	{
		dateStartPlaned= dateStartPlanedIn;
	}
	public void setDateEndPlaned(Date dateEndPlanedIn)
	{
		 dateEndPlaned=dateEndPlanedIn ;
	}
	public void setDateStartAct(Date dateStartActIn)
	{
		dateStartAct= dateStartActIn;
	}
	public void setDateEndAct(Date dateEndActIn)
	{
		 dateEndAct = dateEndActIn;
	}
	public void setDateOfBirth(Date dateOfBirthIn)
	{
		dateOfBirth = dateOfBirthIn;
	}
	public void setRiasEmpNo(String riasEmpNoIn)
	{
		riasEmpNo = riasEmpNoIn;
	}
	
}
