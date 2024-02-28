package gui;
import javax.swing.*; 
import javax.swing.border.LineBorder; 
import java.awt.*;

public class calculatorGUI extends GUIbase{

    String[] numKeysMoji = {"0","1","2","3","4","5","6","7","8","9","00","."};
    String[] symbolKeysMoji = {
        "+",//0
        "-",//1
        "×",//2
        "÷",//3
        "(",//4
        ")",//5
        "^",//6
        "π",//7
        "=",//8
        "√",//9
        "|χ|",//10
        "!"//11
    };
    String[] symbolBackGroundMoji = {"+","-","*","/","(",")","^","3.141592"};

    private String[] numKeysLabels;
    private String[] symbolKeysLabels;
    private JButton[] numButtons; 

    private Panel ViewerArea;
    private JLabel Viewer;
    
    private Panel numKeysArea;
    private JButton[] numKeys;
    private Panel symbolKeysArea;
    private JButton[] symbolKeys;

    @Override
    public void GuiInitSub(){
        numKeysLabels = numKeysMoji.clone();
        symbolKeysLabels = symbolKeysMoji.clone();
    }

    @Override
    public void GuiLayout(){
        //レイアウトの外殻
        BorderLayout backLayout = new BorderLayout();
        setLayout(backLayout);

        ViewerArea = new Panel();
            Viewer = new JLabel("HAPPY HAPPY HAPPY!!");
                Viewer.setPreferredSize(new Dimension(300,30));
            ViewerArea.add(Viewer);
        add(Viewer,BorderLayout.NORTH);

        numKeysArea = new Panel();
            numKeysArea.setBackground(Color.BLUE);
            GridLayout numKeysAreaLayout = new GridLayout(4,3);
            numKeysArea.setLayout(numKeysAreaLayout);
            numKeys = new JButton[12];
            for(int k=7;k<10;k++) {
                numKeys[k] = new JButton(numKeysLabels[k]);
                numKeysArea.add(numKeys[k]);
            }		
            for(int k=4;k<7;k++) {
                numKeys[k] = new JButton(numKeysLabels[k]);
                numKeysArea.add(numKeys[k]);
            }
            for(int k=1;k<4;k++) {
                numKeys[k] = new JButton(numKeysLabels[k]);
                numKeysArea.add(numKeys[k]);
            }
            
            
            numKeys[0] = new JButton(numKeysLabels[0]);
            numKeysArea.add(numKeys[0]);
            numKeys[10] = new JButton(numKeysLabels[10]);
            numKeysArea.add(numKeys[10]);
            numKeys[11] = new JButton(numKeysLabels[11]);
            numKeysArea.add(numKeys[11]);

        add(numKeysArea,BorderLayout.CENTER);

        

        
        //数字盤
        numButtons = new JButton[numKeysLabels.length];
    }

    @Override
    public void addListeners(){
    
    }
}