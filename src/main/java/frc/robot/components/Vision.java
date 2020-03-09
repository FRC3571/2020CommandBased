package frc.robot.components;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Vision {
    private NetworkTableInstance inst;
    private NetworkTable table;
    private NetworkTableEntry highTarget;
    private double highTargetX;
    
    public Vision(){
        inst = NetworkTableInstance.getDefault();
        table = inst.getTable("OpenSight");
        highTarget = table.getEntry("highTarget-x");
    }

    public void refresh(){
        highTargetX = highTarget.getDouble(0);
    }

    public double getHighTargetX(){
        return highTargetX;
    }

    public void log (){
        SmartDashboard.putNumber("Vision/HighTargetX", highTargetX);
    }


}