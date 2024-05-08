package lk.ijse.shaafashions.controller.util;

import javafx.scene.paint.Paint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean isTextFieldValid(TextField textField, String text){
        String field = "";

        switch (textField){
            case ID :
                field = "^\\d+$";
                break;
            case EMAIL:
                field = "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";
                break;
            case NAME:
                field = "^[A-z|\\s]{3,}$";
                break;
            case DATE:
                field = "";
                break;
            case PRICE:
                field = "";
                break;
            case LENGTH:
                field = "";
                break;
            case WIDTH:
                field = "";
                break;
            case ADDRESS:
                field = "^([A-z0-9]|[-,.@+]|\\s){4,}$";
                break;
            case CONTACT:
                field = "^(0[1-9][0-9]{8})$";
                break;
            case PASSWORD:
                field = "";
                break;
            case QUANTITY:
                field = "";
                break;
            case USERNAME :
                field = "";
                break;
            case NUM_OF_DAYS:
                field = "";
                break;
        }
        Pattern pattern= Pattern.compile(field);
        if(text !=null){
            if(text.trim().isEmpty()){
                return false;
            }
        }else{
            return false;
        }
        Matcher matcher= pattern.matcher(text);

        if(matcher.matches()){
            return true;
        }
        return false;
    }

    public static boolean setTextColour(TextField location, javafx.scene.control.TextField field){
        if (Regex.isTextFieldValid(location, field.getText())){
            field.setStyle("-fx-focus-colour: #00FF00");
            return true;
        } else {
            field.setStyle("-fx-border-colour: red; -fx-border-radius: 5px; -fx-border-width : 3px");
            return false;
        }
    }
}
