



public class ClassOfBusiness 
{
  
  private String cobName;
  private String cobId;
  private float writeOff;
  
  public ClassOfBusiness()
  {
  }
  
  public String getCobName()
  {
    return cobName;
  }
  
  public float getWriteOff()
  {
    return writeOff;
  }
  
  public String getCobId()
  {
    return cobId;
  }
  
   public void setCobName(String cobNameIn)
  {
    cobName= cobNameIn;
  }
  
  public void setWriteOff(float writeOffIn)
  {
    writeOff= writeOffIn;
  }
  
  public void setCobId(String cobIdIn)
  {
     cobId= cobIdIn;
  }
  
  public void show()
  {
    //System.out.println("cob name is: " +cobName);
    //System.out.println("Cob id is: " + cobId);
  }
   
}