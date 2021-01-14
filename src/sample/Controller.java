package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Controller {

    public static String formula = "";
    public int strCnt=0;
    public Button b;

    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("js");

    @FXML
    public TextField textField;

    @FXML
    void Clear(ActionEvent event) {
        formula ="";
        textField.setText(formula);
        strCnt=0;
    }

    @FXML
    void Digit(ActionEvent event) {

        Button b = (Button)event.getSource();
        if(strCnt==0&& b.getText().equals("0"));
        else{
            formula += b.getText();
            textField.setText(formula);
            strCnt++;
        }
    }

    @FXML
    void Equal(ActionEvent event) throws ScriptException {
        if(!(formula.endsWith("+")||formula.endsWith("-")||formula.endsWith("×")||formula.endsWith("÷")||formula.endsWith("."))) {

            formula = formula.replace("×", "*");
            formula = formula.replace("÷", "/");
            formula = engine.eval(formula).toString();
            strCnt = formula.length();
            textField.setText(formula);

        }
    }

    @FXML
    void Op(ActionEvent event) {
        b = (Button)event.getSource();
        if(formula.endsWith("+")||formula.endsWith("-")||formula.endsWith("×")||formula.endsWith("÷")) {
            formula = formula.substring(0, formula.length() - 1) + b.getText();
        }else if(!(formula.length()==0)) {
            formula += b.getText();
        }
        strCnt=0;
        textField.setText(formula);
    }

    @FXML
    void addFormula(KeyEvent event) {
        formula=textField.getText();
    }

    @FXML
    void delete(ActionEvent event){
        if(formula.endsWith("+")||formula.endsWith("-")||formula.endsWith("×")||formula.endsWith("÷"));
        else if(!(formula.length()==0)&&strCnt!=0) {
            formula = formula.substring(0, formula.length() - 1);
            strCnt--;
        }
        textField.setText(formula);
    }
    @FXML
    void Percent(ActionEvent event) {
        if(!(formula.endsWith("+")||formula.endsWith("-")||formula.endsWith("×")||formula.endsWith("÷")||formula.endsWith("."))&&formula.length()!=0&&strCnt!=0){
            String str=formula.substring(formula.length()-strCnt, formula.length());
            float num =Float.parseFloat(str);
            float ans = num/100;
            formula = formula.substring(0, formula.length() - strCnt)+ans;
            textField.setText(formula);
        }
    }
    @FXML
    void Dot(ActionEvent event){
        b = (Button)event.getSource();
        if(!(formula.endsWith("+")||formula.endsWith("-")||formula.endsWith("×")||formula.endsWith("÷"))&&!(formula.length()==0)&&!(formula.endsWith("."))) {
            formula += b.getText();
            strCnt++;
        }
        textField.setText(formula);
    }

}
