import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.GridPane;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.util.Arrays;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;
import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.ModbusClient;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;


public class ControllerModbus extends  Application{
    public static void main(String[] args) throws IOException {

        Application.launch(args);





        // Writing Single Coil


        //modbusClient.WriteSingleCoil(1024,true); //Stop
        //modbusClient.WriteSingleCoil(1024,false);
        //modbusClient.WriteSingleCoil(1025,true); //Start
        //modbusClient.WriteSingleCoil(1025,false);
        //modbusClient.WriteSingleCoil(1026,true); //Alarm
        //modbusClient.WriteSingleCoil(1026,false);

        // Writing Holding Reg float
        //modbusClient.WriteMultipleRegisters(1500,ModbusClient.ConvertFloatToTwoRegisters((float)(1323.55))); //sp T


        // Read Input Status
        //System.out.println(Arrays.toString(modbusClient.ReadDiscreteInputs(1024, 1))); // Статус работы






        //Read Float Value from Input Reg
        // System.out.println(ModbusClient.ConvertRegistersToFloat(modbusClient.ReadInputRegisters(1572, 2))); // ТОС

        // for (int i = 0; i < 24; i++) {

        // Writer.write(ModbusClient.ConvertRegistersToFloat(modbusClient.ReadInputRegisters(1572, 2)));
        // i++;
        //TimeUnit.SECONDS.sleep(1);
        // }



    }

    @Override
    public void start(Stage stage) throws Exception {

        //Image img = new Image("C:/Users/sin/IdeaProjects/ReocamUI/on.png");
       // ImageView view1 = new ImageView();

        Label parameter = new Label("Parameter");
        parameter.setFont(Font.font("Lucida Grande", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 22));
        parameter.setAlignment(Pos.CENTER);
        parameter.setTextAlignment(TextAlignment.CENTER);
        Label Pout = new Label("Value");
        Pout.setFont(Font.font("Lucida Grande", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 22));
        Pout.setAlignment(Pos.CENTER);
        Pout.setTextAlignment(TextAlignment.CENTER);
        Label Pin = new Label("Set Point");
        Pin.setFont(Font.font("Lucida Grande", FontWeight.EXTRA_BOLD, FontPosture.REGULAR, 22));
        Pin.setAlignment(Pos.CENTER);
        Pin.setTextAlignment(TextAlignment.CENTER);

        Label Temperature = new Label("Temperature");
        Temperature.setFont(Font.font("Lucida Grande", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 15));
        Temperature.setAlignment(Pos.CENTER);
        Temperature.setTextAlignment(TextAlignment.CENTER);
        TextField spout = new TextField();
        spout.setEditable(false);
        TextField spin = new TextField();
        TextField timenow = new TextField();
        Date date = new Date();

        timenow.setText(date.toString());



        Button ok = new Button();
        ok.setText("  OK  ");
        ok.setTextAlignment(TextAlignment.CENTER);
        ok.setStyle("#iphone {\n" +
                "    -fx-background-color: \n" +
                "        #516175,\n" +
                "        linear-gradient(#303842 0%, #3e5577 20%, #375074 100%),\n" +
                "        linear-gradient(#768aa5 0%, #849cbb 5%, #5877a2 50%, #486a9a 51%, #4a6c9b 100%);\n" +
                "    -fx-background-insets: 0 0 -1 0,0,1;\n" +
                "    -fx-background-radius: 5,5,4;\n" +
                "    -fx-padding: 7 30 7 30;\n" +
                "    -fx-text-fill: #202224;\n" +
                "    -fx-font-family: \"Lucida Grande\";\n" +
                "    -fx-font-size: 16px;\n" +
                "    -fx-text-fill: gray;\n" +
                "}");

        Button go = new Button();
        go.setText("START");
       // go.setGraphic(view1);
        Button stop = new Button();
        stop.setText("STOP");

        GridPane gridpane = new GridPane();
        gridpane.setAlignment(Pos.CENTER);
        gridpane.setBlendMode(BlendMode.MULTIPLY);
        gridpane.setHgap(10);
        gridpane.setVgap(3);

        gridpane.add(parameter,2,0);
        gridpane.add(Pout,3,0);
        gridpane.add(Pin,4,0);
        gridpane.add(Temperature,2,1);
        gridpane.add(spout,3,1);
        gridpane.add(spin,4,1);
        gridpane.add(ok,4,3);
        gridpane.add(timenow,0,1);
        gridpane.add(go,0,2);
        gridpane.add(stop,0,3);


        Scene scene = new Scene(gridpane);


        stage.setScene(scene);


        stage.setWidth(700);
        stage.setHeight(440);
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);


        stage.show();

        ModbusClient modbusClient = new ModbusClient("192.168.0.218", 502);
        modbusClient.Connect();


            float TRO = ModbusClient.ConvertRegistersToFloat(modbusClient.ReadInputRegisters(1500, 2));
            spout.setText(String.valueOf(TRO));


        ok.setOnAction(event -> {
           String SPT = spin.getText();
           float setpoint = Float.parseFloat(SPT);
            try {
                modbusClient.WriteMultipleRegisters(1500,ModbusClient.ConvertFloatToTwoRegisters((float)(setpoint)));
            } catch (ModbusException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        });


        if (Arrays.toString(modbusClient.ReadDiscreteInputs(1024, 1)) == String.valueOf(1)) {

            go.setVisible(false);
            stop.setVisible(true);

        }
        else if (Arrays.toString(modbusClient.ReadDiscreteInputs(1024, 1)) == String.valueOf(0)) {

            go.setVisible(true);
            stop.setVisible(false);

        }

       go.setOnAction(event -> {
           try {
               modbusClient.WriteSingleCoil(1025,true); //Start
           } catch (ModbusException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }
           try {
               modbusClient.WriteSingleCoil(1025,false);
           } catch (ModbusException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }


       });
        stop.setOnAction(event -> {
            try {
                modbusClient.WriteSingleCoil(1024,true); //Stop
            } catch (ModbusException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                modbusClient.WriteSingleCoil(1024,false);
            } catch (ModbusException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        });


}
}

