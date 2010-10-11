package phidgets;

import com.phidgets.*;

public class Main {

    Phidget phidget;
    static GUI guiobj;
    static InterfaceKitPhidget interf;
    static String str;
    static VLC rc;
    static long x;
    long sensor1;
    long sensor2;
    long sensor3;
    long sensor4;
    boolean IsPlaying = false;
    int Count;

    public Main() {
        guiobj = new GUI();
        guiobj.setVisible(true);
        guiobj.setExtendedState(guiobj.MAXIMIZED_BOTH);
        rc = new VLC("localhost", 1234);
        rc.start();
        try {
            interf = new InterfaceKitPhidget();
        } catch (PhidgetException e) {
            e.printStackTrace();
        }
        MakeConnection();
    }

    private void PlayVLC()
    {
        if (IsPlaying == false)
        {
            IsPlaying = true;
            rc.control.play();
        }
    }
    private void StopVLC()
    {
        if (IsPlaying == true)
        {
            IsPlaying = false;
            rc.control.play();
        }
    }
    public void ReadSensors() {
        SlideShow SlideSensors = new SlideShow();
        while (true) {
            try {
                sensor1 = readSensorValue(1);
                sensor2 = readSensorValue(2);
                sensor3 = readSensorValue(3);
                sensor4 = readSensorValue(4);
                String output = null;
                SlideSensors.HandleContent(guiobj.ImageLabel, guiobj.TextArea);
                InputKeyListener Keylistener = new InputKeyListener(guiobj);
                rc.control.play();
                if(sensor4 > 80)
                {
                    PlayVLC();
                    Count = 0;
                }
                else
                {
                    Count += 1;
                    if (Count == 60)
                        StopVLC();
                }
                if(sensor1 == 0)
                {
                    output = SlideSensors.HandlePrevSlide();
                }
                if(sensor2 == 0)
                {
                    SlideSensors.HandleSelect();
                }
                if(sensor3 == 0)
                {
                    output = SlideSensors.HandleNextSlide();
                }
                if (output != null)
                    System.out.println(output);
                Thread.sleep(250);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.ReadSensors();
    }

    /**
     * Make connection with the phidget webservice
     */
    private void MakeConnection() {

        try {
            interf.openAny("localhost", 5001);
            interf.waitForAttachment(10000);  // 1 second
        } catch (PhidgetException e) {
            e.printStackTrace();
        }
    }// end of phidgetmakeConnection()

    public long readSensorValue(int nr) {
        long value = -1;
        try {
            value = interf.getSensorValue(nr);
        } catch (PhidgetException e) {
            e.printStackTrace();
        }
        return value;
    }

    public void setOutputValue(int nr, Boolean state) {
        try {
            interf.setOutputState(nr, state);
        } catch (PhidgetException e) {
            e.printStackTrace();
        }
    }
}