package com.example.it.toolsutils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.it.toolsutils.data.OrderInfo;
import com.example.it.toolsutils.utils.PrintUtil;
import com.landicorp.android.eptapi.DeviceService;
import com.landicorp.android.eptapi.exception.ReloginException;
import com.landicorp.android.eptapi.exception.RequestException;
import com.landicorp.android.eptapi.exception.ServiceOccupiedException;
import com.landicorp.android.eptapi.exception.UnsupportMultiProcess;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_print)
    Button btnPrint;
    @BindView(R.id.btn_read_card)
    Button btnReadCard;
    @BindView(R.id.btn_id_card)
    Button btnIdCard;
    @BindView(R.id.btn_sao_ma)
    Button btnSaoMa;
    @BindView(R.id.btn_nei_sao_ma)
    Button btnNeiSaoMa;
    private boolean isDeviceServiceLogined = false;
    PrintUtil printUtil = new PrintUtil(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_print, R.id.btn_read_card, R.id.btn_id_card, R.id.btn_nei_sao_ma})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_print:

                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setAge(11);
                orderInfo.setHobby("测试打印机测打印机");
                orderInfo.setId(1234897489);
                orderInfo.setSex("测试打印机测打印机");
                orderInfo.setName("测试打印机印");
                String s = printUtil.getPrintInfoAndPrint(orderInfo);
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_read_card:



                break;
            case R.id.btn_id_card:


                break;
            case R.id.btn_nei_sao_ma:


                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindDeviceService();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindDeviceService();
    }

    public void bindDeviceService() {
        try {
            isDeviceServiceLogined = false;
            DeviceService.login(this);
            isDeviceServiceLogined = true;
        } catch (RequestException e) {
            e.printStackTrace();
        } catch (ServiceOccupiedException e) {
            e.printStackTrace();
        } catch (ReloginException e) {
            e.printStackTrace();
        } catch (UnsupportMultiProcess e) {
            e.printStackTrace();
        }
    }

    /**
     * Release the right of using the device.
     */
    public void unbindDeviceService() {
        DeviceService.logout();
        isDeviceServiceLogined = false;
    }

}
