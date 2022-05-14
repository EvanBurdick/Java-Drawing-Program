import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class TriangleButton  extends JButton implements ActionListener {
  protected JPanel drawingPanel;
  protected View view;
  private MouseHandler mouseHandler;
  private TriangleCommand triangleCommand;
  private UndoManager undoManager;
  public TriangleButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
    super("Triangle");
    this.undoManager = undoManager;
    addActionListener(this);
    view = jFrame;
    drawingPanel = jPanel;
    mouseHandler = new MouseHandler();
  }
  public void actionPerformed(ActionEvent event) {
    view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    // Change cursor when button is clicked
    drawingPanel.addMouseListener(mouseHandler);
    drawingPanel.addMouseMotionListener(mouseHandler);
  // Start listening for mouseclicks on the drawing panel
  }
  private class MouseHandler extends MouseAdapter {
    private int pointCount = 0;
    public void mouseClicked(MouseEvent event) {
    if (++pointCount == 1) {
        triangleCommand = new TriangleCommand(View.mapPoint(event.getPoint()));
        undoManager.beginCommand(triangleCommand);
    } 
    else if (pointCount == 2){
        triangleCommand.setLinePoint(View.mapPoint(event.getPoint()));
        //undoManager.beginCommand(triangleCommand);
    }
    else if (pointCount == 3) {
        pointCount = 0;
        triangleCommand.setLinePoint(View.mapPoint(event.getPoint()));
        //triangleCommand.finalize();
        drawingPanel.removeMouseListener(this);
        view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        undoManager.endCommand(triangleCommand);
      }
    }
    public void mouseMoved(MouseEvent event){
        if(pointCount == 1){ //User has plotted first point
            triangleCommand.drawTriangle(View.mapPoint(event.getPoint()));
        }
      if(pointCount == 2){ //User has plotted second point
        triangleCommand.drawTriangle(View.mapPoint(event.getPoint()));
      }
    }
  }
}