package org.firstinspires.ftc.teamcode.common.nikorunner;

import android.util.JsonReader;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.hardware.RobotHardware;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AutonomousManager {
    private final RobotHardware robot = RobotHardware.getInstance();

    private static AutonomousManager instance = null;

    public static AutonomousManager getInstance(){
        if(instance == null) {
            instance = new AutonomousManager();
        }
        return instance;
    }

    public void loadAuto(String autoFile) {

        File auto = new File("../../pathing/autos/" + autoFile);

        SequentialCommandGroup commands = new SequentialCommandGroup();

        // needs json parser or import json simple.

        FileReader readFile = null;
        try {
            readFile = new FileReader(auto);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

//        JSONObject auto = (JSONObject) parser.parse(readFile);

//        JSONArray cmdArray = (JSONArray) auto.get("commands");

//        if(cmdArray != null) {
//            getCommandTreeAuto(commands, cmdArray);
//        }

        // if type = Pathing Command -> loadPath
    }

    public void loadPath(String pathFile) {
        // Make Follower Command
    }

    public static ArrayList<Object> convertFile(File pathFile) {
        // should be 1 large array list

        // paths with commands
//        ArrayList<Path> paths = new ArrayList();


        // auto only commands
//        ArrayList<Command> commands = new ArrayList<Command>();

        return new ArrayList<>();
    }
}
