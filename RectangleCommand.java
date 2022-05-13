import java.awt.*;
import java.text.*;
public class RectangleCommand extends Command {
  private Rectangle rectangle;
  private int pointCount;
  public RectangleCommand() {
    this(null, null);
    pointCount = 0;
  }
  public RectangleCommand(Point point) {
    this(point, null);
    pointCount = 1;
  }
  public RectangleCommand(Point point1, Point point2) {
    rectangle = new Rectangle(point1, point2);
    pointCount = 2;
  }
  public RectangleCommand(Point point1, Point point2, Point point3) {
    rectangle = new Rectangle(point1, point2, point3);
    pointCount = 3;
  }
  public RectangleCommand(Point point1, Point point2, Point point3, Point point4) {
    rectangle = new Rectangle(point1, point2, point3, point4);
    pointCount = 4;
  }
  public void setLinePoint(Point point) {
    if (++pointCount == 1) {
      rectangle.setPoint1(point);
    } else if (pointCount == 2) {
      rectangle.setPoint2(point);
    } else if (pointCount == 3){
        rectangle.setPoint3(point);
    } else if (pointCount == 4){
        rectangle.setPoint4(point);
    }
  }
  public void drawRectangle(Point point){
    rectangle.setPoint4(point); //set the 4th point to moved mouse location
    execute(); //draw rectangle
    undo(); //remove rectangle 
  }
  public void finalize(){
      undo(); //remove temp rectangle
  }
  public void execute() {
    model.addItem(rectangle);
  }
  public boolean undo() {
    model.removeItem(rectangle);
    return true;
  }
  public boolean redo() {
    execute();
    return true;
  }
  public boolean end() {
    if (rectangle.getPoint1() == null) {
      undo();
      return false;
    }
    if (rectangle.getPoint2() == null) {
       rectangle.setPoint2(rectangle.getPoint1());
    }
    if (rectangle.getPoint3() == null){
        rectangle.setPoint3(rectangle.getPoint2());
    }
    if (rectangle.getPoint4() == null){
        rectangle.setPoint4(rectangle.getPoint3());
    }
    return true;
  }
}
