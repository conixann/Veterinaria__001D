package cl.duoc.veterinaria.controller;

import cl.duoc.veterinaria.model.Mascota;
import cl.duoc.veterinaria.repository.MascotaRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class MascotaController {
    @FXML
    private Label labelChip;
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelFechaNacimiento;
    @FXML
    private TextField textFieldChip;
    @FXML
    private TextField textFieldNombre;
    @FXML
    private DatePicker datePickerFechaNacimiento;
    @FXML
    private Button buttonAgregar;

    ArrayList<Mascota> mascotas = new ArrayList<>();

    @FXML
    protected void agregarMascota() {
        try{
            int chip = Integer.parseInt(textFieldChip.getText());
            String nombre = textFieldNombre.getText();
            LocalDate fechaNacimiento = datePickerFechaNacimiento.getValue();

            Mascota m = new Mascota(chip,nombre,fechaNacimiento);
            //mascotas.add(m);
            boolean res = new MascotaRepository().agregarMascota(m);
            if(res){
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setContentText("Mascota registrada");
                alerta.show();
            }else{
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setContentText("Mascota no registrada");
                alerta.show();
            }


        }catch(Exception ex){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText( ex.getMessage() );
            alerta.show();
        }
    }


}



