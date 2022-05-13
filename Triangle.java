import java.awt.*;
public class Triangle extends Item {
  private Point point1;
  private Point point2;
  private Point point3;
  public Triangle(Point point1, Point point2, Point point3) {
    this.point1 = point1;
    this.point2 = point2;
    this.point3 = point3;
  }
  public Triangle(Point point1, Point point2) {
    this.point1 = point1;
    this.point2 = point2;
	point3 = null;
  }
  public Triangle(Point point1) {
    this.point1 = point1;
    point2 = null;
	point3 = null;
  }
  public Triangle() {
	  point1 = null;
	  point2 = null;
      point3 = null;
  }
  public boolean includes(Point point) {
    return ((distance(point, point1 ) < 10.0) || (distance(point, point2)< 10.0));
  }
  public void render() {
    uiContext.drawLine(point1, point2);
    uiContext.drawLine(point2, point3);
    uiContext.drawLine(point3, point1);
  }
  public void rotate90clockwise(){
    int point1x = (int) point1.getX();
    int point1y = (int) point1.getY();
    int point2x = (int) point2.getX();
    int point2y = (int) point2.getY();
    int point3x = (int) point3.getX();
    int point3y = (int) point3.getY();

    int midpointx = ((point1x + point2x + point3x) /3);
    int midpointy = ((point1y + point2y + point3y) /3);
    
    point1.move(midpointx + (midpointy - point1y), midpointy + (point1x - midpointx));
    point2.move(midpointx + (midpointy - point2y), midpointy + (point2x - midpointx));
    point3.move(midpointx + (midpointy - point3y), midpointy + (point3x - midpointx));
    
  }
  public void rotate90counterclockwise(){
    int point1x = (int) point1.getX();
    int point1y = (int) point1.getY();
    int point2x = (int) point2.getX();
    int point2y = (int) point2.getY();
    int point3x = (int) point3.getX();
    int point3y = (int) point3.getY();

    int midpointx = ((point1x + point2x + point3x) /3);
    int midpointy = ((point1y + point2y + point3y) /3);

    point1.move(midpointx + (point1y - midpointy), midpointy + (midpointx - point1x));
    point2.move(midpointx + (point2y - midpointy), midpointy + (midpointx - point2x));
    point3.move(midpointx + (point3y - midpointy), midpointy + (midpointx - point3x));
  }
  public void setPoint1(Point point) {
    point1 = point;
  }
  public void setPoint2(Point point) {
    point2 = point;
  }
  public void setPoint3(Point point) {
    point3 = point;
  }
  public Point getPoint1() {
    return point1;
  }
  public Point getPoint2() {
    return point2;
  }
  public Point getPoint3() {
    return point3;
  }
  public String toString() {
    return "Triangle  from " + point1 + " to " + point2 + " to " + point3;
  }
}

