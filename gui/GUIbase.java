package gui;
import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;

public abstract class GUIbase extends JPanel{
    //GUIを準備するためのメソッド
    public final void guiSet(){
        GuiInit();
        GuiLayout();
        addListeners();
    }
    //Guiの初期化を行うメソッド
    public void GuiInit(){}
    //Guiのレイアウトを作成するメソッド
    public abstract void GuiLayout();
    //Guiのリスナーをセットするメソッド
    public abstract void addListeners();
}
