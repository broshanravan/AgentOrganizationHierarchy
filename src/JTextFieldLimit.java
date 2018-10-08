

import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class JTextFieldLimit extends PlainDocument 
{
  private int limit;
  // optional uppercase conversion
  private boolean toUppercase = false;
  // Optional numeric only.
  private boolean numericOnly = false;
  
  public JTextFieldLimit(int limit) 
  {
         super();
         this.limit = limit;
   }
   
  public JTextFieldLimit(int limit, boolean upper) 
  {
         super();
         this.limit = limit;
         toUppercase = upper;
   }
  
  public JTextFieldLimit(int limit, boolean upper, boolean numeric) 
  {
       super();
       this.limit = limit;
       toUppercase = upper;
       numericOnly = numeric;
   }
 
    public void insertString(int offset, String  str, AttributeSet attr)
        throws BadLocationException 
    {
        if (str == null) return;

        if ((getLength() + str.length()) <= limit) 
        {
            if (toUppercase)
                str = str.toUpperCase();

            if(numericOnly)
            {
                try 
                {
                    Float.valueOf(str).floatValue(); 
                }
                catch(java.lang.NumberFormatException e) 
                {
                    return; 
                }
            }

            super.insertString(offset, str, attr);
        }
    }
}