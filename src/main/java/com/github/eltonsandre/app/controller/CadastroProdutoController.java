package com.github.eltonsandre.app.controller;

import com.github.eltonsandre.app.core.domain.dto.ProdutoDTO;
import com.github.eltonsandre.app.core.domain.service.ProdutoService;
import com.github.eltonsandre.app.jfx.component.JFXCurrencyField;
import com.github.eltonsandre.app.jfx.util.AlertUtils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.ColumnConstraints;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

/**
 * @author eltonsandre
 * date 24/02/2020 18:36
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CadastroProdutoController {

    final ProdutoService service;

    @FXML ResourceBundle resources;
    @FXML URL location;
    @FXML ColumnConstraints gPaneFormProduto;

    @FXML Label lblTitulo;
    @FXML JFXTextField fieldCodigo;
    @FXML JFXTextField fieldDescricao;
    @FXML JFXCurrencyField fieldValor;
    @FXML JFXButton btnSalvarProduto;

    @FXML TableView<ProdutoDTO> tableProdutos;
    @FXML TableColumn<ProdutoDTO, String> colCodigo;
    @FXML TableColumn<ProdutoDTO, String> colDescricao;
    @FXML TableColumn<ProdutoDTO, String> colValor;

    private ObservableList<ProdutoDTO> items;

    @FXML
    void initialize() {
        final var requiredFieldValidator = new RequiredFieldValidator("Campo obrigatório.");

        this.fieldCodigo.getValidators().add(requiredFieldValidator);
        this.fieldDescricao.getValidators().add(requiredFieldValidator);
        this.fieldValor.getValidators().add(requiredFieldValidator);

        this.initTableView();
        this.items = FXCollections.observableArrayList();
        this.items.addAll(this.service.findAll());
        this.tableProdutos.setItems(this.items);
    }

    private void initTableView() {
        this.colCodigo.setCellValueFactory(column -> new SimpleStringProperty(column.getValue().getCodigo()));
        this.colDescricao.setCellValueFactory(column -> new SimpleStringProperty(column.getValue().getDescricao()));
        this.colValor.setCellValueFactory(column -> new SimpleStringProperty(
                NumberFormat.getCurrencyInstance().format(column.getValue().getValor())));

    }

    @FXML
    private void salvarProduto(final ActionEvent event) {
        if (this.isCamposValido()) {

            final var produto = ProdutoDTO.builder()
                    .codigo(this.fieldCodigo.getText())
                    .descricao(this.fieldDescricao.getText())
                    .valor(this.fieldValor.getAmount())
                    .build();

            log.debug("save produto={}", produto);
            this.service.save(produto);
            this.items.add(produto);
            AlertUtils.sucess("Produto cadastrado", "produto " + produto.getCodigo() + " cadastrado com sucesso!");
        }
    }

    private boolean isCamposValido() {
        final boolean codigo = this.fieldCodigo.validate();
        final boolean desc = this.fieldDescricao.validate();
        final boolean valor = this.fieldValor.getAmount().doubleValue() > 0.0;
        return (codigo && desc && valor);

    }
}
