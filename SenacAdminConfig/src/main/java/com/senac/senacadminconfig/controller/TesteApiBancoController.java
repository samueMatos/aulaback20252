package com.senac.senacadminconfig.controller;

import com.senac.senacadminconfig.Utils.JPAUtils;
import com.senac.senacadminconfig.model.DAO.EnderecoDAO;
import com.senac.senacadminconfig.model.Endereco;
import jakarta.persistence.EntityManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TesteApiBancoController {


    @FXML
    private TextArea txtEndereco;

    @FXML
    private TextField txtCep;


    public void consultarCep(ActionEvent event){

        try {

            var urlEndereco = "https://viacep.com.br/ws/"+txtCep.getText()+"/json/";
            URL url = new  URL(urlEndereco);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type","application/json");

//            String json = String.format("{\"email\":\"%s\",\"senha\":\"%s\"}",txtCep.getText(),txtEndereco.getText());
//
//            try (OutputStream os = conn.getOutputStream()){
//                os.write(json.getBytes());
//            }

            int status = conn.getResponseCode();
            if(status == 200){
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                txtEndereco.setText(response.toString());

                salvarEnderco(response.toString(),txtCep.getText());

            }
            conn.disconnect();

        }catch (Exception e){

        }





        Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Dados Diditados!");
        alert.setHeaderText(null);
        alert.setContentText("Cep" + txtCep.getText());



        alert.showAndWait();
    }

    public void voltar(ActionEvent event) throws  Exception {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/com/senac/senacadminconfig/menu-view.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }


    private boolean salvarEnderco(String endereco, String cep){

        try {
            EntityManager entityManager = JPAUtils.getEntityManager();
            EnderecoDAO enderecoDAO = new EnderecoDAO(entityManager);

            Endereco enderecoBanco = new Endereco();
            enderecoBanco.setEndereco(endereco);
            enderecoBanco.setCep(cep);

            enderecoDAO.salvar(enderecoBanco);

            return true;
        }catch (Exception e){
            return false;
        }

    }


}
