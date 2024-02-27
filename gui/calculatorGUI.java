package gui;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;

public class calculatorGUI extends GUIbase{

    private String[] numKeysLabels;
    private String[] symbolKeysLabels;
    private JButton[] numButtons; 

    @Override
    public void GuiInit(){
        String[] numKeysMoji = {"0","1","2","3","4","5","6","7","8","9","π"};
        numKeysLabels = numKeysMoji.clone();
        String[] symbolKeysMoji = {"+","-","×","÷","(",")","^","!","=","√","|χ|",""};
        symbolKeysLabels = symbolKeysMoji.clone();
    }

    @Override
    public void GuiLayout(){
        //レイアウトの外殻
        GridLayout shell = new GridLayout(2,1);
        setLayout(shell);
        
        //レイアウト
        numButtons = new JButton[numKeysLabels.length];
    }

    @Override
    public void addListeners(){
    
    }
}