package com.fxproject.unidashboard.validator;

import javafx.css.PseudoClass;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.util.List;
import java.util.function.UnaryOperator;

public class Validator {

    private static boolean isEmpty(TextField tf) {
        String input = tf.getText();
        return input.trim().isEmpty() || input.length() == 0;
    }

    private static void showAlert(Alert.AlertType type, String msg) {
        Alert a = new Alert(type, msg);
        a.show();
    }

    public static boolean checkIfEmpty(List<TextField> tfs) {
        for (TextField tf : tfs) {
            if(isEmpty(tf)) {
                showAlert(Alert.AlertType.WARNING, tf.getId().toUpperCase() + " is Empty");
                return false;
            }
        }
        return true;
    }

    public static UnaryOperator<TextFormatter.Change> integerValidator() {
        return change -> {
            String input = change.getText();
            if (!input.matches("\\d+")) {
                change.setText("");
            }
            return change;
        };
    }

    public static UnaryOperator<TextFormatter.Change> stringValidator() {
        return change -> {
            String input = change.getText();
            if (!input.matches("[a-zA-Z]+")) {
                change.setText("");
            }
            return change;
        };
    }

    public static void lengthValidator(TextField tf, int length) {
        tf.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue.length() > length) {
                tf.setText(tf.getText().substring(0, length));
            }
        });
    }

    public static void emailValidator(TextField tf) {
        tf.textProperty().addListener(event -> tf.pseudoClassStateChanged(
                PseudoClass.getPseudoClass("error"),
                !tf.getText().isEmpty() &&
                        !tf.getText().matches("(?:[a-z0-9!#$%&'*+=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
        ));
    }

    public static void setValidator(TextField tf, UnaryOperator<TextFormatter.Change> change) {
        tf.setTextFormatter(new TextFormatter<>(change));
    }
}
