package com.stylefeng.guns.common.constant.state;

/**
 * @描述:状态对应的进度
 * @创建人: JaccePon
 * @创建日期: 2018年04月26日 10:32 AM
 */
public enum StatusProcessEnum {

    // 1.待货、2.已发货、3.断货、4.取消订单、5.成交、6.退货中、7.已退货、8.特殊处理、9.删除
   DAIHUO(1,10),YIFAHUO(2,50),DUANHUO(3,90),QUXIAODINGDAN(4,90),CHENGJIAO(5,100),TUIHUOZHONG(6,80),YITUIHUO(7,100),TESHUCHULI(8,90),SHANCHU(9,100);

    int status;
    int process;

    StatusProcessEnum(int status, int process) {
        this.status = status;
        this.process = process;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    public static Integer valueOf(Integer status) {
        if (status == null) {
            return DAIHUO.getProcess();
        } else {
            for (StatusProcessEnum ms : StatusProcessEnum.values()) {
                if (ms.getStatus() == status) {
                    return ms.getProcess();
                }
            }
            return DAIHUO.getProcess();
        }
    }
}
