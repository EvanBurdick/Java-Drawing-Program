import java.awt.*;
public class Line extends Item {
  private Point point1;
  private Point point2;
  public Line(Point point1, Point point2) {
    this.point1 = point1;
    this.point2 = point2;
  }
  public Line(Point point1) {
    this.point1 = point1;
	point2 = null;
  }
  public Line() {
	  point1 = null;
	  point2 = null;
  }
  public boolean includes(Point point) {
    return ((distance(point, point1 ) < 10.0) || (distance(point, point2)< 10.0));
  }
  public void rotate90clockwise(){
    int point1x = (int) point1.getX();
    int point1y = (int) point1.getY();
    int point2x = (int) point2.getX();
    int point2y = (int) point2.getY();
    int midpointx = ((point1x + point2x) /2);
    int midpointy = ((point1y + point2y) /2);
    
    point1.move(midpointx + (midpointy - point1y), midpointy + (point1x - midpointx));
    point2.move(midpointx + (midpointy - point2y), midpointy + (point2x - midpointx));
  }
  public void rotate90counterclockwise(){
    int point1x = (int) point1.getX();
    int point1y = (int) point1.getY();
    int point2x = (int) point2.getX();
    int point2y = (int) point2.getY();
    int midpointx = ((point1x + point2x) /2);
    int midpointy = ((point1y + point2y) /2);
    

    point1.move(midpointx + (point1y - midpointy), midpointy + (midpointx - point1x));
    point2.move(midpointx + (point2y - midpointy), midpointy + (midpointx - point2x));
  }
  public void render() {
    uiContext.drawLine(point1, point2);
  }
  public void setPoint1(Point point) {
    point1 = point;
  }
  public void setPoint2(Point point) {
    point2 = point;
  }
  public Point getPoint1() {
    return point1;
  }
  public Point getPoint2() {
    return point2;
  }
  public String toString() {
    return "Line  from " + point1 + " to " + point2;
  }
}

