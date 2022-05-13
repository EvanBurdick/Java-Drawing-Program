import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class RectangleButton  extends JButton implements ActionListener {
  protected JPanel drawingPanel;
  protected View view;
  private MouseHandler mouseHandler;
  private RectangleCommand rectangleCommand;
  private UndoManager undoManager;
  public RectangleButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
    super("Rectangle");
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
        rectangleCommand = new RectangleCommand(View.mapPoint(event.getPoint()));
        undoManager.beginCommand(rectangleCommand);
    } 
    else if (pointCount == 2){
        rectangleCommand.setLinePoint(View.mapPoint(event.getPoint()));
        undoManager.beginCommand(rectangleCommand);
    }
    else if (pointCount == 3){
        rectangleCommand.setLinePoint(View.mapPoint(event.getPoint()));
        //undoManager.beginCommand(rectangleCommand);
    }
    else if (pointCount == 4) {
        pointCount = 0;
        rectangleCommand.setLinePoint(View.mapPoint(event.getPoint()));
        rectangleCommand.finalize();
        drawingPanel.removeMouseListener(this);
        view.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        undoManager.endCommand(rectangleCommand);
      }
    }
    public void mouseMoved(MouseEvent event){
        if(pointCount == 1){ //User has plotted first point
            rectangleCommand.drawRectangle(View.mapPoint(event.getPoint()));
        }
      if(pointCount == 2){ //User has plotted second point
        rectangleCommand.drawRectangle(View.mapPoint(event.getPoint()));
      }
      if(pointCount == 3){ //User has plotted second point
        rectangleCommand.drawRectangle(View.mapPoint(event.getPoint()));
      }
    }
  }
}