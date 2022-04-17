package com.example.simplegui;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;


public class ToDoListController implements Initializable {


@Override
public void initialize(URL location, ResourceBundle resources){

    datePicker.setValue(LocalDate.now());
    taskList.setPlaceholder(new Label("Список пуст"));
}
    @FXML
    Button addButton;
    @FXML
    TextField descriptionTextField;
    @FXML
    DatePicker datePicker;
    @FXML
    ListView<LocalTask> taskList;
    @FXML
    ObservableList<LocalTask> list = FXCollections.observableArrayList();

    @FXML
    CheckBox filterCheckbox;


    Boolean clickEdit=false;



    @FXML
    //Добавление и редактирование задачи
    private void addTask(Event e) {

        String textOfTask = descriptionTextField.getText();

        if (!textOfTask.isBlank()) {
            //Если было выбрано редактирование задачи, то меняем выбранную задачу, если нет, то создаем новую
            if (!clickEdit) {
                list.add(new LocalTask(datePicker.getValue(), textOfTask));

            } else {
                list.set(taskList.getSelectionModel().getSelectedIndex(), new LocalTask(datePicker.getValue(), textOfTask));
                clickEdit = false;
            }
            taskList.setItems(list);
            refresh();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION, "Введите описание задачи");
            alert.setHeaderText("");
            alert.show();
        }
    }

    @FXML
    //Создание задачи по нажатию Enter
    public void onEnter(Event e) {
        addTask(e);
    }

    @FXML
    //удаляем задачу из списка
    private void delTask(Event e) {

        if ((taskList.getSelectionModel().getSelectedItem() != null)) {
            int indexElementToRemove = taskList.getSelectionModel().getSelectedIndex();
            System.out.println(indexElementToRemove);
            Alert alert = new Alert(AlertType.CONFIRMATION, "Действительно удалить?", ButtonType.YES, ButtonType.NO);
            alert.setHeaderText("Подтвердите действие");
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                list.remove(indexElementToRemove);
                taskList.setItems(list);
                refresh();
            }
        } else {
            Alert alert = new Alert(AlertType.INFORMATION, "Не выбрана задача");
            alert.setHeaderText("");
            alert.show();
        }
    }

    @FXML
    //Редактируем задачку
    private void editTask(Event e) {

        if ((taskList.getSelectionModel().getSelectedItem() != null)) {
            clickEdit = true;
            datePicker.setValue(taskList.getSelectionModel().getSelectedItem().getDate());
            descriptionTextField.setText(taskList.getSelectionModel().getSelectedItem().getDescription());

        } else {
            Alert alert = new Alert(AlertType.INFORMATION, "Не выбрана задача для редактирования");
            alert.setHeaderText("");//чтобы не показывать лишний стандартный текст
            alert.show();
        }
    }

    @FXML
    //Фильтр списка задач по дате
    private void filterCheckbox(Event e) {
        FilteredList<LocalTask> filteredData = new FilteredList<>(list, item -> item.getDate().equals(datePicker.getValue()));
        taskList.setItems(filteredData);
        if (filterCheckbox.isSelected()) {
            taskList.setItems(filteredData);
        } else
            taskList.setItems(list);
    }

    @FXML
    //Сбросим в начальное состояние дату и очистим описание задачи
    protected void refresh() {
        datePicker.setValue(LocalDate.now());
        descriptionTextField.setText("");
    }

}