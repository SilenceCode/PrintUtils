package com.example.it.toolsutils.utils;

import android.content.Context;

import com.example.it.toolsutils.base.PrintPresenter;
import com.example.it.toolsutils.data.OrderInfo;
import com.landicorp.android.eptapi.device.Printer;

public class PrintUtil {

    private PrintPresenter printPresenter;
    private Context context;

    public PrintUtil(Context context) {
        this.context = context;
        if(printPresenter==null){
            printPresenter = new PrintPresenter(context);
        }
    }

    /**
     * 获取打印信息并开始打印
     *
     * @param orderInfo
     * @return
     */
    public String getPrintInfoAndPrint(OrderInfo orderInfo) {
        String errorInfo = "";
        int ret = PrintPresenter.getPrinterStatus();
        if (ret != Printer.ERROR_NONE) {
            errorInfo = printPresenter.getDescribe(ret);
            return errorInfo;
        }
        if (!printPresenter.addBitmapInfo()) {
            errorInfo = "add bitmap fail";
            return errorInfo;
        }
        if (!printPresenter.addTextLeftLittle(orderInfo, true)) {
            errorInfo = "add text fail";
            return errorInfo;
        }
        if (!printPresenter.addTextCenterNormal(orderInfo, true)) {
            errorInfo = "add text fail";
            return errorInfo;
        }
        if (!printPresenter.addTextNormal(orderInfo, true)) {
            errorInfo = "add text fail";
            return errorInfo;
        }
        if (!printPresenter.addTextRightBlod(orderInfo, true)) {
            errorInfo = "add text fail";
            return errorInfo;
        }
        if (!printPresenter.addTextLeftLittle(orderInfo, false)) {
            errorInfo = "add text fail";
            return errorInfo;
        }
        if (!printPresenter.addTextRightBlod(orderInfo, true)) {
            errorInfo = "add text fail";
            return errorInfo;
        }
        if (!printPresenter.addBarcode(orderInfo)) {
            errorInfo = "add barcode fail";
            return errorInfo;
        }
        if (!printPresenter.addQRcode(orderInfo.getName())) {
            errorInfo = "add qrcode fail";
            return errorInfo;
        }
        if (!printPresenter.addFeedLine(10)) {
            errorInfo = "feed line fail";
            return errorInfo;
        }
        if (!printPresenter.cutPage()) {
            errorInfo = "cut page fail";
            return errorInfo;
        }
        errorInfo = printPresenter.startPrint();
        return errorInfo;
    }

}
