import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.border.EmptyBorder;
class View extends JFrame {
  private UIContext uiContext;
  private JPanel drawingPanel;
  private JPanel buttonPanel;
  private JPanel buttonPanel2;
  private JPanel filePanel;
  private JButton lineButton;
  private JButton triangleButton;
  private JButton rectangleButton;
  private JButton deleteButton;
  private JButton labelButton;
  private JButton selectButton;
  private JButton clockwiseButton;
  private JButton cclockwiseButton;
  private JButton saveButton;
  private JButton openButton;
  private JButton undoButton;
  private JButton redoButton;
  private static UndoManager undoManager;
    private String fileName;
  // other buttons to be added as needed;
  private static Model model;
  public UIContext getUI() {
    return uiContext;
  }
  private void setUI(UIContext uiContext) {
    this.uiContext = uiContext;
  }
  public static void setModel(Model model) {
    View.model = model;
  }
  public static void setUndoManager(UndoManager undoManager) {
    View.undoManager = undoManager;
  }
  private class DrawingPanel extends JPanel {
    private MouseListener currentMouseListener;
    private KeyListener currentKeyListener;
    private FocusListener currentFocusListener;
    public DrawingPanel() {
      setLayout(null);
    }
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      (NewSwingUI.getInstance()).setGraphics(g);
      g.setColor(Color.BLUE);
      Enumeration enumeration = model.getItems();
      while (enumeration.hasMoreElements()) {
        ((Item) enumeration.nextElement()).render();
      }
      g.setColor(Color.RED);
      enumeration = model.getSelectedItems();
      while (enumeration.hasMoreElements()) {
        ((Item) enumeration.nextElement()).render();
      }
    }
    public void addMouseListener(MouseListener newListener) {
      removeMouseListener(currentMouseListener);
      currentMouseListener =  newListener;
      super.addMouseListener(newListener);
    }
    public void addKeyListener(KeyListener newListener) {
      removeKeyListener(currentKeyListener);
      currentKeyListener =  newListener;
      super.addKeyListener(newListener);
    }
    public void addFocusListener(FocusListener newListener) {
      removeFocusListener(currentFocusListener);
      currentFocusListener =  newListener;
      super.addFocusListener(newListener);
    }
  }
  public void setFileName(String fileName) {
    this.fileName = fileName;
    setTitle("Drawing Program 1.1  " + fileName);
  }
  public String getFileName() {
    return fileName;
  }

  public View() {
    super("Drawing Program 1.1  Untitled");
    fileName = null;
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent event) {
        System.exit(0);
      }
    });
    model.setUI(NewSwingUI.getInstance());
    drawingPanel = new DrawingPanel();
    drawingPanel.setBorder(BorderFactory.createLineBorder(Color.black));

    JPanel panel = new JPanel(new BorderLayout());
    panel.setBorder(new EmptyBorder(2, 3, 2, 3));
    JPanel layout = new JPanel(new GridBagLayout());
    layout.setBorder(new EmptyBorder(5, 5, 5, 5));
    JPanel buttonPanel = new JPanel(new GridLayout(10, 1, 10, 5));
    buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));
    layout.add(buttonPanel);
    panel.add(layout, BorderLayout.WEST);

    buttonPanel2 = new JPanel(new GridLayout(1,0, 10,10));
    buttonPanel2.setBorder(BorderFactory.createLineBorder(Color.black));
    Container contentpane = getContentPane();

    contentpane.add(buttonPanel, "West");
    contentpane.add(buttonPanel2, "South");
    contentpane.add(drawingPanel);
    lineButton= new LineButton(undoManager, this, drawingPanel);
    triangleButton = new TriangleButton(undoManager, this, drawingPanel);
    rectangleButton = new RectangleButton(undoManager, this, drawingPanel);
    labelButton = new LabelButton(undoManager, this, drawingPanel);
    selectButton= new SelectButton(undoManager, this, drawingPanel);
    clockwiseButton= new ClockwiseButton(undoManager, this, drawingPanel);
    cclockwiseButton= new CounterClockwiseButton(undoManager, this, drawingPanel);
    deleteButton= new DeleteButton(undoManager);
    saveButton= new SaveButton(undoManager, this);
    openButton= new OpenButton(undoManager, this);
    undoButton = new UndoButton(undoManager);
    redoButton = new RedoButton(undoManager);
    buttonPanel.add(lineButton);
    buttonPanel.add(triangleButton);
    buttonPanel.add(rectangleButton);
    buttonPanel.add(labelButton);
    buttonPanel2.add(selectButton);
    buttonPanel2.add(clockwiseButton);
    buttonPanel2.add(cclockwiseButton);
    buttonPanel2.add(deleteButton);
    buttonPanel2.add(undoButton);
    buttonPanel2.add(redoButton);
    buttonPanel2.add(saveButton);
    buttonPanel2.add(openButton);
    this.setSize(1000, 600);
  }
  public void refresh() {
    // code to access the Model update the contents of the drawing panel.
    drawingPanel.repaint();
  }
  public static Point mapPoint(Point point){
    // maps a point on the drawing panel to a point
    // on the figure being created. Perhaps this
    // should be in drawing panel
    return point;
  }
}