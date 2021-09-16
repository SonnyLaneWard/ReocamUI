import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;
import de.re.easymodbus.exceptions.ModbusException;
import de.re.easymodbus.modbusclient.ModbusClient;
import java.io.IOException;



public class ControllerModbus extends  Application{
    public static void main(String[] args) throws IOException {

        Application.launch(args);
        ModbusClient modbusClient = new ModbusClient("192.168.0.218", 502);
        modbusClient.Connect();




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

    }

}
