/**
 * 初始化详情对话框
 */
var OrderInfoDlg = {
    orderInfoData : {},
    validateFields: {
        orderNum: {
            validators: {
                notEmpty: {
                    message: '订单号不能为空'
                }
            }
        },
        custom: {
            validators: {
                notEmpty: {
                    message: '客户信息不能为空'
                }
            }
        },
        price:{
            validators: {
                numeric: {
                    message: '原价必须填数字'
                }
            }
        },
        post:{
            validators: {
                numeric: {
                    message: '邮费必须填数字'
                }
            }
        }
    }
};

/**
 * 验证数据是否为空
 */
OrderInfoDlg.validate = function () {

    var source=$("#source").val();
    var gathering=$("#gathering").val();
    var status=$("#status").val();
    var refund=$("#refund").val();
    if(source!=2){
        if(gathering==1||gathering==2){
            Feng.error("非微商城的订单，勿选择利润状况!");
            return false;
        }
    }else{
        if(gathering==3||gathering==4||gathering==5){
            Feng.error("微商城的订单，请选择利润状况!");
            return false;
        }
    }
    if(status==5){
        if(gathering==2||gathering==4||gathering==5){
            Feng.error("成交的订单，收款情况尚未完成！！！");
            return false;
        }
        if(refund==2||refund==3){
            Feng.error("成交的订单，退款情况尚未完成！！！");
            return false;
        }
    }
    if(status==7){

        if(gathering==2||gathering==4||gathering==5){
            Feng.error("已退货的订单，收款情况尚未完成！！！");
            return false;
        }
        if(refund!=4){
            Feng.error("已退货的订单，退款情况尚未完成！！！");
            return false;
        }
    }

    $('#orderInfoForm').data("bootstrapValidator").resetForm();
    $('#orderInfoForm').bootstrapValidator('validate');
    return $("#orderInfoForm").data('bootstrapValidator').isValid();
};


/**
 * 清除数据
 */
OrderInfoDlg.clearData = function() {
    this.orderInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrderInfoDlg.set = function(key, val) {
    this.orderInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
OrderInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
OrderInfoDlg.close = function() {
    parent.layer.close(window.parent.Order.layerIndex);
}

/**
 * 收集数据
 */
OrderInfoDlg.collectData = function() {
    this
    .set('pic')
    .set('orderNum')
    .set('code')
    .set('price')
    .set('custom')
    .set('source')
    .set('gathering')
    .set('status')
    .set('waiting')
    .set('post')
    .set('remark')
    .set('remind')
    .set('refund')
    ;
}

/**
 * 提交添加
 */
OrderInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/order/add", function(data){
        Feng.success("添加成功!");
        window.parent.Order.table.refresh();
        OrderInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.orderInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
OrderInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/order/update", function(data){
        Feng.success("修改成功!");
        window.parent.Order.table.refresh();
        OrderInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.orderInfoData);
    ajax.start();
}

OrderInfoDlg.showCustom = function() {

    var index = layer.open({
        type: 2,
        title: '选择客户',
        area: ['800px', '750px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/order/order_custom'

    });
    this.layerIndex = index;
}


$(function() {
    Feng.initValidator("orderInfoForm", OrderInfoDlg.validateFields);

    var idVal = $("#id").val();

    if(idVal!=null&&idVal!=undefined&&idVal!=""&&idVal!=0){
        $("#source").val($("#sourceValue").val());
        $("#gathering").val($("#gatheringValue").val());
        $("#status").val($("#statusValue").val());
        $("#refund").val($("#refundValue").val());
    }

    // 初始化图片上传
    var avatarUp = new $WebUpload("pic",1);
    avatarUp.setUploadBarId("progressBar");
    avatarUp.init();

});
