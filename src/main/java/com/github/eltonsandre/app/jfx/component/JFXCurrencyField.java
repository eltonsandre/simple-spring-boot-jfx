package com.github.eltonsandre.app.jfx.component;

import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import javafx.application.Platform;
import javafx.geometry.NodeOrientation;

/**
 * @author eltonsandre
 * date 03/05/2020 22:14
 */
public class JFXCurrencyField extends JFXTextField {

    private static BigDecimal ZERO = BigDecimal.valueOf(0.00);

    private NumberFormat format;
    private BigDecimal amount;

    public JFXCurrencyField() {
        this(Locale.getDefault(), ZERO);
    }

    public JFXCurrencyField(Locale locale) {
        this(locale, ZERO);
    }

    public JFXCurrencyField(Locale locale, BigDecimal initialAmount) {
        this.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        //        this.amount = new SimpleDoubleProperty(this, "amount", initialAmount);
        this.amount = initialAmount;
        this.format = NumberFormat.getCurrencyInstance(locale);
        this.setText(this.format.format(initialAmount));

        this.focusedProperty().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> {
                int lenght = this.getText().length();
                this.selectRange(lenght, lenght);
                this.positionCaret(lenght);
            });
        });

        this.textProperty().addListener((observable, oldValue, newValue) -> JFXCurrencyField.this.formatText(newValue));
    }

    /**
     * Get the current amount value
     * @return Total amount
     */
    public BigDecimal getAmount() {
        return this.amount;
    }

    /**
     * Change the current amount value
     * @param newAmount
     */
    public void setAmount(BigDecimal newAmount) {
        if (newAmount.doubleValue() >= 0.0) {
            this.amount = newAmount;
            this.formatText(this.format.format(newAmount));
        }
    }

    /**
     * Set Currency format
     * @param locale
     */
    public void setCurrencyFormat(Locale locale) {
        this.format = NumberFormat.getCurrencyInstance(locale);
        this.formatText(this.format.format(this.getAmount()));
    }

    @Override
    public void deleteText(int start, int end) {
        final int caretPosition = this.getCaretPosition();
        StringBuilder builder = new StringBuilder(this.getText());
        builder.delete(start, end);
        this.formatText(builder.toString());
        this.selectRange(start, start);
        this.positionCaret(caretPosition);
    }

    /**
     * Property getter
     * @return SimpleDoubleProperty
     */
    public BigDecimal amountProperty() {
        return this.amount;
    }

    private void formatText(String text) {
        if (text != null && !text.isEmpty()) {
            StringBuilder plainText = new StringBuilder(text.replaceAll("[^0-9]", ""));

            while (plainText.length() < 3) {
                plainText.insert(0, "0");
            }

            StringBuilder builder = new StringBuilder(plainText.toString());
            builder.insert(plainText.length() - 2, ".");

            BigDecimal newValue = new BigDecimal(builder.toString());
            this.amount = newValue;
            this.setText(this.format.format(newValue));
        }
    }

}