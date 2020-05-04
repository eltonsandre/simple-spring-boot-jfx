package com.github.eltonsandre.app.jfx.util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * @author eltonsandre
 * date: 6 de nov de 2017 22:03:24
 */
@Slf4j
@UtilityClass
public class JFXDialogUtils {

    private final JFXDialogLayout content = new JFXDialogLayout();

    /**
     * @param stackpane void
     */
    public void generic(final StackPane stackpane) {
        generic(stackpane, "Parametros não preenchidos", "Preencha todos os campos de parametros");
    }

    /**
     * @param stackPane
     * @param heading
     * @param body
     */
    public void generic(final StackPane stackPane, final String heading, final String body) {
        content.setHeading(new Text(heading));
        content.setBody(new Text(body));

        var dialog = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.CENTER);

        var button = new JFXButton("OK!");
        button.setStyle("-fx-background-color: #246321;");
        button.setTextFill(Paint.valueOf("WHITE"));
        button.setOnAction(event -> dialog.close());
        content.setActions(button);

        dialog.show();
    }

}
