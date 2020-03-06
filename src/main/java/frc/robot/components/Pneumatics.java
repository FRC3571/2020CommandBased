package frc.robot.components;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import java.util.ArrayList;

public class Pneumatics {

    public static final class Constants {
        private static final int kCompressorID = 0;
    }

    private final Compressor compressor;

    ArrayList<DoubleSolenoid> solenoidList;

    boolean SHIFTSTATE;

    public Pneumatics() {
        compressor = new Compressor(Constants.kCompressorID);
        compressor.setClosedLoopControl(true);

        solenoidList = new ArrayList<>();
    }

    public void start() {
        compressor.setClosedLoopControl(true);
    }

    public void stop() {
        compressor.setClosedLoopControl(false);
    }

    public void createSolenoid(final int solenoidId, final int id1, final int id2) {

        solenoidList.add(solenoidId, new DoubleSolenoid(id1, id2));

        solenoidList.get(solenoidId).set(DoubleSolenoid.Value.kReverse);

        SHIFTSTATE = false;
    }

    public void solenoidOff(final int solenoidId) {
        try {
            final DoubleSolenoid ds = solenoidList.get(solenoidId);

            if (ds != null) {
                ds.set(DoubleSolenoid.Value.kOff);
            }

        } catch (final IndexOutOfBoundsException e) {
            System.out.println(e);
            return;
        }
    }

    public void solenoidForward(final int solenoidId) {
        try {

            final DoubleSolenoid ds = solenoidList.get(solenoidId);

            if (ds != null) {
                ds.set(DoubleSolenoid.Value.kForward);
                SHIFTSTATE = true;
            }

        } catch (final IndexOutOfBoundsException e) {
            System.out.println(e);
            return;
        }
    }

    public void solenoidReverse(final int solenoidId) {
        try {

            final DoubleSolenoid ds = solenoidList.get(solenoidId);

            if (ds != null) {

                ds.set(DoubleSolenoid.Value.kReverse);

                SHIFTSTATE = false;
            }
        } catch (final IndexOutOfBoundsException e) {
            System.out.println(e);
            return;
        }
    }

    public boolean getShiftState() {
        return SHIFTSTATE;
    }
}