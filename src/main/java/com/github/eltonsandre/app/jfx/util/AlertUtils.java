package com.github.eltonsandre.app.jfx.util;

import com.github.eltonsandre.app.jfx.enums.AlertTypeEnum;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * @author eltonsandre
 * date: Jul 3, 2017 9:08:26 PM
 */
@Slf4j
@UtilityClass
public class AlertUtils {

    public void sucess(final String header, final String content) {
        info(AlertTypeEnum.SUCESSO.getDescricao(), header, content);
    }

    public void info(final String title, final String header, final String content) {
        generic(AlertType.INFORMATION, title, header, content);
    }

    public void info(final String header, final String content) {
        generic(AlertType.INFORMATION, AlertTypeEnum.INFO.getDescricao(), header, content);
    }

    public void warn(final String header) {
        generic(AlertType.WARNING, AlertTypeEnum.AVISO.getDescricao(), header, "");
    }

    public void warn(final String header, final String content) {
        generic(AlertType.WARNING, AlertTypeEnum.AVISO.getDescricao(), header, content);
    }

    public void error(final String header, final String content) {
        generic(AlertType.ERROR, AlertTypeEnum.ERRO.getDescricao(), header, content);
    }

    public void generic(final AlertTypeEnum type, final String title, final String header, final String content) {
        Alert alert = new Alert(type.getType());
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    public void generic(final AlertType type, final String title, final String header, final String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

}
