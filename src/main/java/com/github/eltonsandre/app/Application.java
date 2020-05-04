package com.github.eltonsandre.app;

import com.github.eltonsandre.app.jfx.util.AlertUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application extends javafx.application.Application {

    @Value("${spring.application.name}")
    private String appName;

    private ConfigurableApplicationContext springContext;
    private Parent rootNode;

    public static void main(final String[] args) {
        javafx.application.Application.launch(args);
    }

    @Override
    public void init() throws Exception {
        Thread.setDefaultUncaughtExceptionHandler(Application::showErrorDialog);

        this.springContext = SpringApplication.run(Application.class);
        final var fxmlLoader = new FXMLLoader(this.getClass().getResource("/templates/Main.fxml"));
        fxmlLoader.setControllerFactory(this.springContext::getBean);
        this.rootNode = fxmlLoader.load();
    }

    private static void showErrorDialog(final Thread thread, final Throwable throwable) {
        AlertUtils.error("Erro não tratado", throwable.getMessage());
    }

    @Override
    public void start(final Stage stage) throws Exception {
        stage.setScene(new Scene(this.rootNode));
        stage.setTitle(this.appName);
        Image applicationIcon = new Image(this.getClass().getResourceAsStream("icon-app.png"));
        stage.getIcons().add(applicationIcon);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        this.springContext.close();
    }

}
