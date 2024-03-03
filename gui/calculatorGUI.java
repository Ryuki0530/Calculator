package gui;

import javax.swing.*; 

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class calculatorGUI extends GUIbase{

    String[] numKeysLabels = {"0","1","2","3","4","5","6","7","8","9","00","."};
    String[] symbolKeysLabels = {
        "+",//0
        "-",//1
        "×",//2
        "÷",//3
        "=",//4
        "C",//5
    };
    char[] symbolBackGroundMoji = {'+','-','*','/'};

    //算術演算子の式(Expressions using arithmetic operators)
    StringBuilder expUsingAO= new StringBuilder();

    //private String[] numKeysLabels;
    //private String[] symbolKeysLabels;


    private Panel ViewerArea;
    private JLabel Viewer;
    
    private Panel numKeysArea;
    private JButton[] numKeys;
    
    private Panel symbolKeysArea;
    private Panel symbolKeysAreaSub1;
    private Panel symbolKeysAreaSub2;
    private JButton[] symbolKeys;

    

    @Override
    public void GuiInitSub(){
       // numKeysLabels = numKeysMoji.clone();
     //   symbolKeysLabels = symbolKeysMoji.clone();
    }

    @Override
    public void GuiLayout(){
        //レイアウトの外殻
        BorderLayout backLayout = new BorderLayout();
        setLayout(backLayout);

        //表示領域
        ViewerArea = new Panel();
            Viewer = new JLabel("");
                Viewer.setPreferredSize(new Dimension(300,30));
            ViewerArea.add(Viewer);
        add(Viewer,BorderLayout.NORTH);
        
        //数字用ボタンエリア
        numKeysArea = new Panel();
            GridLayout numKeysAreaLayout = new GridLayout(4,3);
            numKeysArea.setLayout(numKeysAreaLayout);
            numKeys = new JButton[12];
            //1-9のボタン
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
            //0 00 . ボタン
            numKeys[0] = new JButton(numKeysLabels[0]);
            numKeysArea.add(numKeys[0]);
            numKeys[10] = new JButton(numKeysLabels[10]);
            numKeysArea.add(numKeys[10]);
            numKeys[11] = new JButton(numKeysLabels[11]);
            numKeysArea.add(numKeys[11]);

        add(numKeysArea,BorderLayout.CENTER);
        
        symbolKeysArea = new Panel();

            GridLayout symbolKeysLayout =  new GridLayout(2,1);
            symbolKeysArea.setLayout(symbolKeysLayout);
            symbolKeys = new JButton[8];
            int k = 0;

            //演算記号入力ボタン
            symbolKeysAreaSub1 = new Panel();
                GridLayout symbolKeysAreSub1Layout = new GridLayout(2,4);
                symbolKeysAreaSub1.setLayout(symbolKeysAreSub1Layout);
                for(;k<4;k++){
                    symbolKeys[k] = new JButton(symbolKeysLabels[k]);
                    symbolKeysAreaSub1.add(symbolKeys[k]);
                }
            symbolKeysArea.add(symbolKeysAreaSub1);
            
            //演算実行ボタン
            symbolKeysAreaSub2 = new Panel();
                GridLayout symbolKeysAreSub2Layout = new GridLayout(1,4);
                symbolKeysAreaSub2.setLayout(symbolKeysAreSub2Layout);
                for(;k<6;k++){
                    symbolKeys[k] = new JButton(symbolKeysLabels[k]);
                    symbolKeysAreaSub2.add(symbolKeys[k]);
                }
            symbolKeysArea.add(symbolKeysAreaSub2);

        add(symbolKeysArea,BorderLayout.EAST);

    }



    //リスナーのセット
    @Override
    public void addListeners(){
        initNumKeysListener();
        initSymbolKeysListener1();
        initSymbolKeysListener2();
    }

    private void initNumKeysListener(){
        for(int k= 0;k<12;k++){
            final int kCopy = k;
            numKeys[k].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    String currentString = Viewer.getText();
                    Viewer.setText(currentString+numKeysLabels[kCopy]);
                    expUsingAO.append(numKeysLabels[kCopy]);
                    System.out.println("expUsingAO:"+expUsingAO);
                }
            });
        }
    }


    private void initSymbolKeysListener1(){
        // TODO Auto-generated method stub
        for(int k= 0;k<4;k++){
            final int kCopy = k;
            symbolKeys[k].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    String currentString = Viewer.getText();
                    Viewer.setText(currentString+symbolKeysLabels[kCopy]);
                    expUsingAO.append(symbolBackGroundMoji[kCopy]);
                    System.out.println("expUsingAO:"+expUsingAO);
                }
            });
        }

       

    }

    private void initSymbolKeysListener2(){
        // TODO Auto-generated method stub
        symbolKeys[4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                doCalculation();   
                } catch (Exception e2) {
                    // TODO: handle exception
                    Viewer.setText("");
                    expUsingAO.setLength(0);
                }
            }
        });
    }

    //expUsingAO内の式を処理。その後Viewerに出力します。
   private void doCalculation() {
    String expression = expUsingAO.toString();
    StringBuilder postfix = infixToPostfix(expression);
    String result = evaluatePostfix(postfix.toString());
    Viewer.setText(result);
    expUsingAO.setLength(0);
    expUsingAO.append(result);
    System.out.println("expUsingAO:"+expUsingAO);
}

private StringBuilder infixToPostfix(String infix) {
    StringBuilder postfix = new StringBuilder();
    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < infix.length(); i++) {
        char c = infix.charAt(i);
        if (Character.isDigit(c) || c == '.') {
            postfix.append(c);
        } else if (c == '(') {
            stack.push(c);
        } else if (c == ')') {
            while (!stack.isEmpty() && stack.peek() != '(') {
                postfix.append(stack.pop());
            }
            stack.pop(); // Pop '('
        } else {
            while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
                postfix.append(stack.pop());
            }
            stack.push(c);
        }
    }

    while (!stack.isEmpty()) {
        postfix.append(stack.pop());
    }

    return postfix;
}

private int precedence(char c) {
    if (c == '+' || c == '-') {
        return 1;
    } else if (c == '*' || c == '/') {
        return 2;
    } else {
        return 0;
    }
}

private String evaluatePostfix(String postfix) {
    Stack<Double> stack = new Stack<>();
    for (int i = 0; i < postfix.length(); i++) {
        char c = postfix.charAt(i);
        if (Character.isDigit(c) || c == '.') {
            stack.push(Double.parseDouble(String.valueOf(c)));
        } else {
            double operand2 = stack.pop();
            double operand1 = stack.pop();
            double result = performOperation(c, operand1, operand2);
            stack.push(result);
        }
    }
    return String.valueOf(stack.pop());
}

private double performOperation(char operator, double operand1, double operand2) {
    switch (operator) {
        case '+':
            return operand1 + operand2;
        case '-':
            return operand1 - operand2;
        case '*':
            return operand1 * operand2;
        case '/':
            if (operand2 == 0) {
                throw new ArithmeticException("Division by zero");
            }
            return operand1 / operand2;
        default:
            throw new IllegalArgumentException("Invalid operator");
    }
}

    
    
}