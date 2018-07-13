package com.example.it.toolsutils.base;

import com.landicorp.android.eptapi.device.Printer;
import com.landicorp.android.eptapi.exception.RequestException;

import java.util.List;

public  abstract class PrintBase {

    public static int getPrinterStatus() {
        int status = 0;
        try {
            status = Printer.getInstance().getStatus();
            return status;
        } catch (RequestException e) {
            e.printStackTrace();
        }
        return status;
    }

    public String startPrint(final List<Printer.Step> stepList) {
        final String s = null;
        if (stepList == null) {
            return null;
        }
        com.landicorp.android.eptapi.device.Printer.Progress progress = new com.landicorp.android.eptapi.device.Printer.Progress() {
            @Override
            public void doPrint(com.landicorp.android.eptapi.device.Printer printer) throws Exception {

            }

            @Override
            public void onFinish(int error) {
                stepList.clear();
                if (error == com.landicorp.android.eptapi.device.Printer.ERROR_NONE) {

                } else {
                  //  String errorDes = getDescribe(error);
                }
            }

            @Override
            public void onCrash() {
                stepList.clear();
            }
        };
        for (com.landicorp.android.eptapi.device.Printer.Step step : stepList) {
            progress.addStep(step);
        }
        try {
            progress.start();
        } catch (RequestException e) {
            e.printStackTrace();
        }
        return s;
    }
}
