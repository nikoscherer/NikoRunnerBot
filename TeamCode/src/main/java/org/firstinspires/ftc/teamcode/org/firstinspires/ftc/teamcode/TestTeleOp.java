package org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.org.firstinspires.ftc.teamcode.Util;

public class TestTeleOp {

    public void something() {
       /* double value = 5;
        int result = 0;*/

        // is true if a value is inside a range
   /*     if(value <= 10 && value >= 0) { // inside 0-10
            result = 5;
        } else if(value <= 25 && value >= 15) { // inside 15-25
            result = 10;
        } else if(value <= 50 && value >= 30) { // inside 30-50
            result = 15;
        }*/


        double value = 5;
        int result = 0;

        // is true if a value is inside a range
        if(Util.inRange(value, 10, 0)) { // inside 0-10
            result = 5;
        } else if(Util.inRange(value, 25, 15)) { // inside 15-25
            result = 10;
        } else if(Util.inRange(value, 50, 30)) { // inside 30-50
            result = 15;
        }


        value = result;
    }
}
