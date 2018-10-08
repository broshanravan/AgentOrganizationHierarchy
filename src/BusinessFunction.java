
public class BusinessFunction 
{
  private int bFId;
  private String bFName;
  
  public BusinessFunction(int bFIdIn,String bFNameIn)
  {
      bFId =bFIdIn;
      bFName = bFNameIn;
  }
  
  public BusinessFunction()
  {
  
  }
  public int getBusFunId()
  {
    return bFId;
  }
  
  public String getBusFunName()
  {
    return bFName;
  }
  
  public void setBusFunId(int bFIdIn)
  {
     bFId= bFIdIn;
  }
  
  public void setBusFunName(String bFNameIn)
  {
     bFName =bFNameIn;
  }
  
  public void show()
  {
    //System.out.println("bf Name is:  "+ bFName);
    //System.out.println("bf Id is: "+ bFId);
  }
  
}