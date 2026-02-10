package org.example.proyecto_conjunto.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.proyecto_conjunto.model.Tarea;
import org.example.proyecto_conjunto.dao.TareaDAO;

import java.time.LocalDate;
import java.sql.Date;

public class TaskController {
    @FXML
    private TextField tituloTareaField;

    @FXML
    private DatePicker fechaLimitePicker;

    @FXML
    private TableView<Tarea> tareasTableView;

    @FXML
    private TableColumn<Tarea, String> tituloCol;

    @FXML
    private TableColumn<Tarea, String> completadoCol;

    @FXML
    private TableColumn<Tarea, Date> fechaCol;

    private ObservableList<Tarea> tareas = FXCollections.observableArrayList();

    private TareaDAO tareaDAO = new TareaDAO();

    @FXML
    public void initialize() {
        // Inicializa las columnas con los nombres de las propiedades de la clase Tarea
        tituloCol.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        completadoCol.setCellValueFactory(new PropertyValueFactory<>("estado"));
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fechaLimite"));

        tareasTableView.setItems(tareas);
    }

    @FXML
    private void agregarTarea(ActionEvent event) {
        String titulo = tituloTareaField.getText();
        LocalDate localDate = fechaLimitePicker.getValue();

        if (titulo == null || titulo.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "El título no puede estar vacío.", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        Date fechaSql = null;
        if (localDate != null) {
            fechaSql = Date.valueOf(localDate);
        }

        // Crear tarea con proyectoId 0 (ajustar si tienes proyectos)
        Tarea tarea = new Tarea(0, titulo, "Pendiente", fechaSql);

        boolean ok = tareaDAO.insertTarea(tarea);
        if (ok) {
            tareas.add(tarea);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Tarea guardada correctamente.", ButtonType.OK);
            alert.showAndWait();

            // Limpia los campos de entrada
            tituloTareaField.clear();
            fechaLimitePicker.setValue(null);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error al guardar la tarea en la base de datos.", ButtonType.OK);
            alert.showAndWait();
        }
    }
}
