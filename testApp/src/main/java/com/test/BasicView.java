package com.test;

import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class BasicView extends View
{

    public BasicView()
    {
        getStylesheets().add(BasicView.class.getResource("style.css").toExternalForm());

        ComboBox<String> combo = new ComboBox<>(FXCollections.observableArrayList(Font.getFamilies()));

        final Button next = new Button("next");
        final Button pre = new Button("pre");
        next.setOnAction(e -> {
            combo.getSelectionModel().selectNext();
        });
        pre.setOnAction(e -> {
            combo.getSelectionModel().selectPrevious();
        });

        Button tahoma = new Button("use Tahoma");
        tahoma.setOnAction(e -> {
            combo.getSelectionModel().select("Tahoma");
        });

        final Label faText = new Label("سلام");
        final Label enText = new Label("Hello");
        combo.valueProperty().addListener((o, ov, nv) -> {
            if (nv != null) {
                faText.setStyle("-fx-font-family:" + nv + ";");
                faText.applyCss();
                enText.setStyle("-fx-font-family:" + nv + ";");
                enText.applyCss();
            }
        });

        final VBox content = new VBox(new HBox(pre, combo, next), tahoma, faText, enText);
        content.setSpacing(20);
        setCenter(content);
    }

    @Override
    protected void updateAppBar(AppBar appBar)
    {
        appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> System.out.println("Menu")));
        appBar.setTitleText("Basic View");
        appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e -> System.out.println("Search")));
    }

}
