
/*
 * Created on 22-Feb-2005
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
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JComponent;




public class WindowDestroyer extends WindowAdapter
{
  public void WindowClosing(WindowEvent e)
  {
    System.exit(0);
  }
}


