import java.awt.*;
import java.awt.event.*;

public class CalculatorAWT extends Frame implements ActionListener {

    private TextField display;
    private double currentResult = 0;
    private String currentOperator = "";

    public CalculatorAWT() {
        setTitle("Calculator");
        setSize(300, 400);
        setLayout(new GridLayout(5, 4));

        display = new TextField();
        display.setEditable(false);
        add(display);

        String[] buttons = {
            "7", "8", "9", "/", 
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "0", ".", "=", "+"
        };

        for (String text : buttons) {
            Button button = new Button(text);
            button.addActionListener(this);
            add(button);
        }

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.matches("[0-9.]")) {
            display.setText(display.getText() + command);
        } else if (command.matches("[+\\-*/]")) {
            currentResult = Double.parseDouble(display.getText());
            currentOperator = command;
            display.setText("");
        } else if (command.equals("=")) {
            calculate(Double.parseDouble(display.getText()));
            display.setText("" + currentResult);
        }
    }

    private void calculate(double number) {
        switch (currentOperator) {
            case "+":
                currentResult += number;
                break;
            case "-":
                currentResult -= number;
                break;
            case "*":
                currentResult *= number;
                break;
            case "/":
                currentResult /= number;
                break;
        }
    }

    public static void main(String[] args) {
        new CalculatorAWT();
    }
}
