import javax.swing.*;
import java.awt.event.*;
public class ClockwiseButton  extends JButton implements ActionListener {
  protected JPanel drawingPanel;
  protected View view;
  private MouseHandler mouseHandler;
  private ClockwiseCommand clockwiseCommand;
  private UndoManager undoManager;
  public ClockwiseButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
    super("R90 Clockwise");
    addActionListener(this);
    view = jFrame;
    drawingPanel = jPanel;
    this.undoManager = undoManager;
    mouseHandler = new MouseHandler();
  }
  public void actionPerformed(ActionEvent event) {
    clockwiseCommand = new ClockwiseCommand();
    drawingPanel.addMouseListener(mouseHandler);
    undoManager.beginCommand(clockwiseCommand);
  }
  private class MouseHandler extends MouseAdapter {
    public void mouseClicked(MouseEvent event) {
       if (clockwiseCommand.setPoint(View.mapPoint(event.getPoint())))
	   {
		drawingPanel.removeMouseListener(this);
		undoManager.endCommand(clockwiseCommand);
	   }
	   else {
		   undoManager.cancelCommand();
	   }
    }
  }
}
