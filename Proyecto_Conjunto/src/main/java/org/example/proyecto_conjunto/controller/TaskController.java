package org.example.proyecto_conjunto.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.proyecto_conjunto.model.Tarea;
import org.example.proyecto_conjunto.model.Proyecto;
import org.example.proyecto_conjunto.dao.TareaDAO;
import org.example.proyecto_conjunto.dao.ProyectoDAO;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

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
    private ProyectoDAO proyectoDAO = new ProyectoDAO();

    @FXML
    public void initialize() {
        // Inicializa las columnas con los nombres de las propiedades de la clase Tarea
        tituloCol.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        completadoCol.setCellValueFactory(new PropertyValueFactory<>("estado"));
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fechaLimite"));

        tareasTableView.setItems(tareas);

        // Cargar tareas desde la base de datos para el primer proyecto disponible
        int proyectoId = proyectoDAO.getPrimerProyectoId();
        if (proyectoId == -1) {
            // Si no hay proyectos, creamos uno por defecto para pruebas (usuario_id = 1)
            Proyecto proyectoDef = new Proyecto(1, "Proyecto por defecto", "Creado automáticamente para pruebas");
            int nuevoId = proyectoDAO.insertarProyectoConId(proyectoDef);
            if (nuevoId != -1) {
                proyectoId = nuevoId;
            } else {
                // Si no podemos crear un proyecto, no hay tareas que cargar
                System.out.println("No se pudo obtener ni crear un proyecto. No se cargarán tareas.");
                return;
            }
        }

        try {
            List<Tarea> desdeDb = tareaDAO.getTareasByProject(proyectoId);
            if (desdeDb != null && !desdeDb.isEmpty()) {
                tareas.addAll(desdeDb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        // Obtener un proyecto válido
        int proyectoId = proyectoDAO.getPrimerProyectoId();
        if (proyectoId == -1) {
            // No hay proyectos: crear uno por defecto (usuario_id = 1)
            Proyecto proyectoDef = new Proyecto(1, "Proyecto por defecto", "Creado automáticamente para pruebas");
            int nuevoId = proyectoDAO.insertarProyectoConId(proyectoDef);
            if (nuevoId == -1) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No se pudo crear un proyecto por defecto.", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            proyectoId = nuevoId;
        }

        // Crear tarea con proyectoId obtenido
        Tarea tarea = new Tarea(proyectoId, titulo, "Pendiente", fechaSql);

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
