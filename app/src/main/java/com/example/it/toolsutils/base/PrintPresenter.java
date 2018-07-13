package com.example.it.toolsutils.base;

import android.content.Context;

import com.example.it.toolsutils.data.OrderInfo;
import com.example.it.toolsutils.utils.StringUtils;
import com.landicorp.android.eptapi.device.Printer;
import com.landicorp.android.eptapi.exception.RequestException;
import com.landicorp.android.eptapi.utils.QrCode;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.landicorp.android.eptapi.device.Printer.Format.HZ_DOT24x24;
import static com.landicorp.android.eptapi.utils.QrCode.ECLEVEL_Q;

public class PrintPresenter {
    private com.landicorp.android.eptapi.device.Printer.Progress progress;
    private List<Printer.Step> stepList = new ArrayList<>();
    private Context context;

    public PrintPresenter(Context context) {
        this.context = context;
    }

    /**
     * 获取打印机的状态
     *
     * @return
     */
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

    /**
     * 添加打印小票的头信息
     *
     * @return
     */
    public boolean addBitmapInfo() {
        if (stepList == null) {

            return false;
        }
        stepList.add(new com.landicorp.android.eptapi.device.Printer.Step() {
            @Override
            public void doPrint(com.landicorp.android.eptapi.device.Printer printer) throws Exception {
                InputStream inputStream = context.getAssets().open("pay.bmp");
                printer.printImage(com.landicorp.android.eptapi.device.Printer.Alignment.LEFT, inputStream);
            }
        });
        return true;
    }

    /**
     * 添加正常的文字显示
     *
     * @param s
     * @return
     */
    public boolean addTextNormal(final String s, final boolean b) {
        if (stepList == null) {
            return false;
        }
        stepList.add(new com.landicorp.android.eptapi.device.Printer.Step() {
            @Override
            public void doPrint(com.landicorp.android.eptapi.device.Printer printer) throws Exception {
                printer.setAutoTrunc(false);
                Printer.Format format = new Printer.Format();
                format.setAscScale(Printer.Format.ASC_SC1x1);
                format.setAscSize(Printer.Format.ASC_DOT24x12);
                format.setHzScale(Printer.Format.HZ_SC1x1);
                format.setHzSize(Printer.Format.HZ_DOT24x24);
                printer.setFormat(format);
                if (b) {
                    printer.printMid(StringUtils.addNewLine(s));
                } else {
                    printer.printMid(s);
                }
            }
        });
        return true;
    }

    /**
     * 添加正常的文字显示
     *
     * @param orderInfo
     * @return
     */
    public boolean addTextNormal(final OrderInfo orderInfo, final boolean b) {
        if (stepList == null) {
            return false;
        }
        stepList.add(new com.landicorp.android.eptapi.device.Printer.Step() {
            @Override
            public void doPrint(com.landicorp.android.eptapi.device.Printer printer) throws Exception {
                printer.setAutoTrunc(false);
                Printer.Format format = new Printer.Format();
                format.setAscScale(Printer.Format.ASC_SC1x1);
                format.setAscSize(Printer.Format.ASC_DOT24x12);
                format.setHzScale(Printer.Format.HZ_SC1x1);
                format.setHzSize(Printer.Format.HZ_DOT24x24);
                printer.setFormat(format);
                if (b) {
                    printer.printMid(StringUtils.addNewLine(orderInfo.getName()));
                } else {
                    printer.printMid(orderInfo.getName());

                }
            }
        });
        return true;
    }

    /**
     * 添加向左对齐的小号字体
     *
     * @param s
     * @return
     */
    public boolean addTextLeftLittle(final String s, final boolean b) {
        if (stepList == null) {
            return false;
        }
        stepList.add(new com.landicorp.android.eptapi.device.Printer.Step() {
            @Override
            public void doPrint(com.landicorp.android.eptapi.device.Printer printer) throws Exception {
                printer.setAutoTrunc(false);
                Printer.Format format = new Printer.Format();
                format.setAscScale(Printer.Format.ASC_SC1x1);
                format.setAscSize(Printer.Format.ASC_DOT16x8);
                format.setHzScale(Printer.Format.HZ_SC1x1);
                format.setHzSize(Printer.Format.HZ_DOT16x16);
                printer.setFormat(format);
                Printer.Alignment alignment = Printer.Alignment.LEFT;
                if (b) {
                    printer.printText(alignment, StringUtils.addNewLine(s));
                    printer.printText(alignment, StringUtils.addNewLine(s));
                } else {
                    printer.printText(alignment, s);
                    printer.printText(alignment, s);
                }
            }
        });
        return true;
    }

    /**
     * 添加向左对齐的小号字体
     *
     * @param orderInfo
     * @return
     */
    public boolean addTextLeftLittle(final OrderInfo orderInfo, final boolean b) {
        if (stepList == null) {
            return false;
        }
        stepList.add(new com.landicorp.android.eptapi.device.Printer.Step() {
            @Override
            public void doPrint(com.landicorp.android.eptapi.device.Printer printer) throws Exception {
                printer.setAutoTrunc(false);
                Printer.Format format = new Printer.Format();
                format.setAscScale(Printer.Format.ASC_SC1x1);
                format.setAscSize(Printer.Format.ASC_DOT16x8);
                format.setHzScale(Printer.Format.HZ_SC1x1);
                format.setHzSize(Printer.Format.HZ_DOT16x16);
                printer.setFormat(format);
                Printer.Alignment alignment = Printer.Alignment.LEFT;
                if (b) {
                    printer.printText(alignment, StringUtils.addNewLine(orderInfo.getHobby()));
                    printer.printText(alignment, StringUtils.addNewLine(orderInfo.getSex()));
                } else {
                    printer.printText(alignment, orderInfo.getHobby());
                    printer.printText(alignment, orderInfo.getSex());
                }
            }
        });
        return true;
    }

    /**
     * 添加正常显示的居中文字
     *
     * @param s
     * @return
     */
    public boolean addTextCenterNormal(final String s, final boolean b) {
        if (stepList == null) {
            return false;
        }
        stepList.add(new com.landicorp.android.eptapi.device.Printer.Step() {
            @Override
            public void doPrint(com.landicorp.android.eptapi.device.Printer printer) throws Exception {
                printer.setAutoTrunc(false);
                Printer.Format format = new Printer.Format();
                format.setAscScale(Printer.Format.ASC_SC1x1);
                format.setAscSize(Printer.Format.ASC_DOT24x12);
                format.setHzScale(Printer.Format.HZ_SC1x1);
                format.setHzSize(HZ_DOT24x24);
                printer.setFormat(format);
                Printer.Alignment alignment = Printer.Alignment.CENTER;
                if (b) {
                    printer.printText(alignment, StringUtils.addNewLine(s));
                    printer.printText(alignment, StringUtils.addNewLine(s));
                } else {
                    printer.printText(alignment, s);
                    printer.printText(alignment, s);
                }
            }
        });
        return true;
    }

    /**
     * 设置正常显示得居中文字
     *
     * @param orderInfo
     * @return
     */
    public boolean addTextCenterNormal(final OrderInfo orderInfo, final boolean b) {
        if (stepList == null) {

            return false;
        }
        stepList.add(new com.landicorp.android.eptapi.device.Printer.Step() {
            @Override
            public void doPrint(com.landicorp.android.eptapi.device.Printer printer) throws Exception {
                printer.setAutoTrunc(false);
                Printer.Format format = new Printer.Format();
                format.setAscScale(Printer.Format.ASC_SC1x1);
                format.setAscSize(Printer.Format.ASC_DOT24x12);
                format.setHzScale(Printer.Format.HZ_SC1x1);
                format.setHzSize(HZ_DOT24x24);
                printer.setFormat(format);
                Printer.Alignment alignment = Printer.Alignment.CENTER;
                if (b) {
                    printer.printText(alignment, StringUtils.addNewLine(orderInfo.getSex()));
                    printer.printText(alignment, StringUtils.addNewLine(orderInfo.getHobby()));
                } else {

                    printer.printText(alignment, orderInfo.getSex());
                    printer.printText(alignment, orderInfo.getHobby());
                }
            }
        });
        return true;
    }

    /**
     * 设置向右对齐的加粗字体
     *
     * @param s
     * @return
     */
    public boolean addTextRightBlod(final String s, final boolean b) {
        if (stepList == null) {
            return false;
        }
        stepList.add(new com.landicorp.android.eptapi.device.Printer.Step() {
            @Override
            public void doPrint(com.landicorp.android.eptapi.device.Printer printer) throws Exception {
                printer.setAutoTrunc(false);
                Printer.Format format = new Printer.Format();
                format.setAscScale(Printer.Format.ASC_SC2x2);
                format.setAscSize(Printer.Format.ASC_DOT24x12);
                format.setHzScale(Printer.Format.HZ_SC2x2);
                format.setHzSize(Printer.Format.HZ_DOT24x24);
                printer.setFormat(format);
                Printer.Alignment alignment = Printer.Alignment.RIGHT;
                if (b) {
                    printer.printText(alignment, StringUtils.addNewLine(s));
                    printer.printText(alignment, StringUtils.addNewLine(s));
                } else {
                    printer.printText(alignment, s);
                    printer.printText(alignment, s);
                }
            }
        });
        return true;
    }

    /**
     * 设置向右对齐的加粗字体
     *
     * @param orderInfo
     * @return
     */
    public boolean addTextRightBlod(final OrderInfo orderInfo, final boolean b) {
        if (stepList == null) {
            return false;
        }
        stepList.add(new com.landicorp.android.eptapi.device.Printer.Step() {
            @Override
            public void doPrint(com.landicorp.android.eptapi.device.Printer printer) throws Exception {
                printer.setAutoTrunc(false);
                Printer.Format format = new Printer.Format();
                format.setAscScale(Printer.Format.ASC_SC2x2);
                format.setAscSize(Printer.Format.ASC_DOT24x12);
                format.setHzScale(Printer.Format.HZ_SC2x2);
                format.setHzSize(Printer.Format.HZ_DOT24x24);
                printer.setFormat(format);
                Printer.Alignment alignment = Printer.Alignment.RIGHT;
                if (b) {
                    printer.printText(alignment, StringUtils.addNewLine(orderInfo.getHobby()));
                    printer.printText(alignment, StringUtils.addNewLine(orderInfo.getName()));
                } else {
                    printer.printText(alignment, orderInfo.getHobby());
                    printer.printText(alignment, orderInfo.getName());
                }
            }
        });
        return true;
    }

    /**
     * 添加条形码
     *
     * @param s
     * @return
     */
    public boolean addBarcode(final String s) {
        if (stepList == null) {
            return false;
        }
        stepList.add(new com.landicorp.android.eptapi.device.Printer.Step() {
            @Override
            public void doPrint(com.landicorp.android.eptapi.device.Printer printer) throws Exception {
                printer.printBarCode(s);
            }
        });
        return true;
    }

    /**
     * 添加条形码
     *
     * @param orderInfo
     * @return
     */
    public boolean addBarcode(final OrderInfo orderInfo) {
        if (stepList == null) {
            return false;
        }
        stepList.add(new com.landicorp.android.eptapi.device.Printer.Step() {
            @Override
            public void doPrint(com.landicorp.android.eptapi.device.Printer printer) throws Exception {
                printer.printBarCode(com.landicorp.android.eptapi.device.Printer.Alignment.CENTER, 200, 100,
                        orderInfo.getId() + "");

            }
        });
        return true;
    }

    /**
     * 设置空行
     *
     * @param line
     * @return
     */
    public boolean addFeedLine(final int line) {
        if (stepList == null) {

            return false;
        }
        stepList.add(new com.landicorp.android.eptapi.device.Printer.Step() {
            @Override
            public void doPrint(com.landicorp.android.eptapi.device.Printer printer) throws Exception {
                printer.feedLine(line);
            }
        });
        return true;
    }

    /**
     * 设置截止
     *
     * @return
     */
    public boolean cutPage() {
        if (stepList == null) {

            return false;
        }
        stepList.add(new com.landicorp.android.eptapi.device.Printer.Step() {
            @Override
            public void doPrint(com.landicorp.android.eptapi.device.Printer printer) throws Exception {
                printer.cutPaper();
            }
        });
        return true;
    }

    /**
     * 开始打印
     *
     * @return
     */
    public String startPrint() {
        final String s = null;
        if (stepList == null) {
            return null;
        }
        progress = new com.landicorp.android.eptapi.device.Printer.Progress() {
            @Override
            public void doPrint(com.landicorp.android.eptapi.device.Printer printer) throws Exception {

            }

            @Override
            public void onFinish(int error) {
                stepList.clear();
                if (error == com.landicorp.android.eptapi.device.Printer.ERROR_NONE) {

                } else {
                    String errorDes = getDescribe(error);
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


    /**
     * 添加打印二维码
     *
     * @param s
     * @return
     */
    public boolean addQRcode(final String s) {
        if (stepList == null) {
            return false;
        }
        stepList.add(new com.landicorp.android.eptapi.device.Printer.Step() {
            @Override
            public void doPrint(com.landicorp.android.eptapi.device.Printer printer) throws Exception {
                printer.printQrCode(com.landicorp.android.eptapi.device.Printer.Alignment.CENTER,
                        new QrCode(s, ECLEVEL_Q),
                        200);
            }
        });
        return true;
    }

    /**
     * 获取打印失败原因
     *
     * @param error
     * @return
     */
    public String getDescribe(int error) {
        switch (error) {
            case com.landicorp.android.eptapi.device.Printer.ERROR_BMBLACK:
                return "黑标探测器检测到黑色信号";
            case com.landicorp.android.eptapi.device.Printer.ERROR_BUFOVERFLOW:
                return "缓冲模式下所操作的位置超出范围";
            case com.landicorp.android.eptapi.device.Printer.ERROR_BUSY:
                return "打印机处于忙状态";
            case com.landicorp.android.eptapi.device.Printer.ERROR_COMMERR:
                return "手座机状态正常，但通讯失败 (520针打特有返回值)";
            case com.landicorp.android.eptapi.device.Printer.ERROR_CUTPOSITIONERR:
                return "切纸刀不在原位 (自助热敏打印机特有返回值)";
            case com.landicorp.android.eptapi.device.Printer.ERROR_HARDERR:
                return "硬件错误";
            case com.landicorp.android.eptapi.device.Printer.ERROR_LIFTHEAD:
                return "打印头抬起 (自助热敏打印机特有返回值)";
            case com.landicorp.android.eptapi.device.Printer.ERROR_LOWTEMP:
                return "低温保护或AD出错 (自助热敏打印机特有返回值)";
            case com.landicorp.android.eptapi.device.Printer.ERROR_LOWVOL:
                return "低压保护";
            case com.landicorp.android.eptapi.device.Printer.ERROR_MOTORERR:
                return "打印机芯故障(过快或者过慢)";
            case com.landicorp.android.eptapi.device.Printer.ERROR_NOBM:
                return "没有找到黑标";
            case com.landicorp.android.eptapi.device.Printer.ERROR_NONE:
                return "正常状态，无错误";
            case com.landicorp.android.eptapi.device.Printer.ERROR_OVERHEAT:
                return "打印头过热";
            case com.landicorp.android.eptapi.device.Printer.ERROR_PAPERENDED:
                return "缺纸，不能打印";
            case com.landicorp.android.eptapi.device.Printer.ERROR_PAPERENDING:
                return "纸张将要用尽，还允许打印 (单步进针打特有返回值)";
            case com.landicorp.android.eptapi.device.Printer.ERROR_PAPERJAM:
                return "卡纸";
            case com.landicorp.android.eptapi.device.Printer.ERROR_PENOFOUND:
                return "自动定位没有找到对齐位置,纸张回到原来位置";
            case com.landicorp.android.eptapi.device.Printer.ERROR_WORKON:
                return "打印机电源处于打开状态";
            default:
                return "未知错误";
        }
    }
}
