package frc.robot.components;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Vision {
    private NetworkTableInstance inst;
    private NetworkTable table;
    private NetworkTableEntry highTargetX, highTargetY;
    
    public Vision(){
        inst = NetworkTableInstance.getDefault();
        table = inst.getTable("OpenSight");
        highTargetX = table.getEntry("highTarget-x");
        highTargetY = table.getEntry("highTarget-y");
    }

    public double getHighTargetX(){
        return highTargetX.getDouble(0);
    }

    public double getHighTargetY(){
        return highTargetY.getDouble(0);
    }

    public void log (){
        SmartDashboard.putNumber("Vision/HighTargetX", highTargetX.getDouble(0));
        SmartDashboard.putNumber("Vision/HighTargetY", highTargetY.getDouble(0));
    }
}