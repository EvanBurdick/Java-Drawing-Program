import javax.swing.*;
import java.awt.event.*;
public class CounterClockwiseButton  extends JButton implements ActionListener {
  protected JPanel drawingPanel;
  protected View view;
  private MouseHandler mouseHandler;
  private CounterClockwiseCommand cclockwiseCommand;
  private UndoManager undoManager;
  public CounterClockwiseButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
    super("R90 CounterClockwise");
    addActionListener(this);
    view = jFrame;
    drawingPanel = jPanel;
    this.undoManager = undoManager;
    mouseHandler = new MouseHandler();
  }
  public void actionPerformed(ActionEvent event) {
    cclockwiseCommand = new CounterClockwiseCommand();
    drawingPanel.addMouseListener(mouseHandler);
    undoManager.beginCommand(cclockwiseCommand);
  }
  private class MouseHandler extends MouseAdapter {
    public void mouseClicked(MouseEvent event) {
       if (cclockwiseCommand.setPoint(View.mapPoint(event.getPoint())))
	   {
		drawingPanel.removeMouseListener(this);
		undoManager.endCommand(cclockwiseCommand);
	   }
	   else {
		   undoManager.cancelCommand();
	   }
    }
  }
}
