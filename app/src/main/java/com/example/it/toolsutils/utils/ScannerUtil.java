package com.example.it.toolsutils.utils;

import android.app.Activity;
import android.content.Context;

import com.example.it.toolsutils.data.CameraScannerError;
import com.example.it.toolsutils.data.Constants;
import com.landicorp.android.scan.scanDecoder.ScanDecoder;

public class ScannerUtil {
    private ScanDecoder scanDecoder;
    private Context context;

    public ScannerUtil(Context context) {
        this.context = context;
        scanDecoder = new ScanDecoder(context);
    }

    private ScanDecoder.ResultCallback callback = new ScanDecoder.ResultCallback() {
        @Override
        public void onResult(String s) {
            //displayInfo("scan success, code = " + s);

        }

        @Override
        public void onCancel() {
            // displayInfo("scan cancel");

        }

        @Override
        public void onTimeout() {
            //  displayInfo("scan timeout");

        }
    };


    private int openCamera(int cameraId) {
        int id = ScanDecoder.CAMERA_ID_FRONT;
        if (cameraId == Constants.Scanner.CAMERA_BACK) {
            id = ScanDecoder.CAMERA_ID_BACK;
        }
        return scanDecoder.Create(id, callback);
    }

    public void startScan(Activity activity, int cameraId) {
        int ret = openCamera(cameraId);
        if (ret != CameraScannerError.SUCCESS) {
            //displayInfo("open camera fail: " + getDescribe(ret));
        } else {
            ret = scanDecoder.startScanDecode(activity, null);
            if (ret != CameraScannerError.SUCCESS) {
                String errorDes = getDescribe(ret);
                //  displayInfo("start scan fail: " + errorDes);
            }
        }
    }

    public void close() {
        scanDecoder.Destroy();
    }

    private String getDescribe(int error) {
        switch (error) {
            case CameraScannerError.INIT_DECODER_FAIL:
                return "初始化底层解码库失败";
            case CameraScannerError.HAS_CREATED:
                return "已经创建过";
            case CameraScannerError.OPEN_CAMERA_FAIL:
                return "打开扫码器失败";
            case CameraScannerError.LICENSE_FAIL:
                return "License 认证失败";
            case CameraScannerError.NOT_FOUND_DECODRE:
                return "找不到正确的底层解码库";
            default:
                return "未知错误";
        }
    }
}
