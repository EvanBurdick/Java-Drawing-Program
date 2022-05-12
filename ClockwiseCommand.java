import java.awt.*;
import java.util.*;
public class ClockwiseCommand extends Command {
  private Item item;
  public ClockwiseCommand() {
  }
  public boolean setPoint(Point point) {
	boolean found = false;
    Enumeration enumeration = model.getItems();
    while (enumeration.hasMoreElements()) {
      item = (Item)(enumeration.nextElement());
      if (item.includes(point)) {
          if(item instanceof Line){
              //is a line
              Line line = (Line) item;
              line.rotate90clockwise();
          }
          else if(item instanceof Triangle){
            //is a triangle
            Triangle triangle = (Triangle) item;
            triangle.rotate90clockwise();
          }
        //model.markSelected(item);
		found = true;
        break;
      }
    }
	return found;
  }
  public boolean undo() {
    model.unSelect(item);
    return true;
  }
  public boolean  redo() {
    execute();
    return true;
  }
  public void execute() {
    model.markSelected(item);
  }
}
