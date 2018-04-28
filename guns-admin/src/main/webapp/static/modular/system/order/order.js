/**
 * 管理初始化
 */
var Order = {
    id: "OrderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Order.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '图片', field: 'pic', visible: true, align: 'center', valign: 'middle'},
            {title: '来源', field: 'sourceName', visible: true, align: 'center', valign: 'middle'},
            {title: '原价', field: 'price', visible: true, align: 'center', valign: 'middle'},
            {title: '代理价', field: 'disPrice', visible: true, align: 'center', valign: 'middle'},
            {title: '客户', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '收款', field: 'gatheringName', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'statusName', visible: true, align: 'center', valign: 'middle'},
            {title: '退款', field: 'refundName', visible: true, align: 'center', valign: 'middle'},
            {title: '待货', field: 'waiting', visible: true, align: 'center', valign: 'middle'},
            {title: '快递费', field: 'post', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {title: '进度', field: 'progress', visible: true, align: 'center', valign: 'middle',sortable: true},
            {title: '订单号', field: 'orderNum', visible: true, align: 'center', valign: 'middle'},
            {title: '货号', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: '提醒', field: 'remind', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle' ,sortable: true}
    ];
};

/**
 * 检查是否选中
 */
Order.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Order.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加
 */
Order.openAddOrder = function () {
    var index = layer.open({
        type: 2,
        title: '添加',
        area: ['800px', '705px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/order/order_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看详情
 */
Order.openOrderDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            area: ['800px', '705px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/order/order_update/' + Order.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Order.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/order/delete", function (data) {
            Feng.success("删除成功!");
            Order.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("orderId",this.seItem.id);
        ajax.start();
    }
};

openMsg = function (msgString) {

    layer.alert("<div>"+msgString+"</div>", {
        skin: 'layui-layer-molv'
        ,closeBtn: 0
        ,title:"详细信息"
        ,anim: 4 //动画类型
    });

};


/**
 * 查询列表
 */
Order.search = function () {
    var queryData = {};
    queryData['orderNum'] = $("#orderNum").val();
    queryData['code'] = $("#code").val();
    queryData['customPhone'] = $("#customPhone").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['source'] = $("#source").val();
    queryData['endTime'] = $("#endTime").val();
    queryData['gathering'] = $("#gathering").val();
    queryData['refund'] = $("#refund").val();
    queryData['status'] = $("#status").val();
    queryData['progress'] = $("#progress").val();
    Order.table.refresh({query: queryData});
};


function openPic(obj){
    layer.open({
        type: 1,
        title: false,
        closeBtn: 0,
        area: '516px',
        skin: 'layui-layer-nobg', //没有背景色
        shadeClose: true,
        content: "<img alt=\"image\" src=\""+obj+"\"  width=\"515px\" height=\"615px\" > "
    });
}

Order.resetSearch = function () {
    $("#beginTime").val("");
    $("#endTime").val("");
     $("#orderNum").val("");
     $("#code").val("");
    $("#customPhone").val("");
    $("#source").val("");
    $("#gathering").val("");
    $("#refund").val("");
    $("#status").val("");
    $("#progress").val("");

    Order.search();
}


Order.showEdit=function(type){

    if (this.check()) {

        var ajax = new $ax(Feng.ctxPath + "/order/updateOrder", function (data) {
            layer.msg('操作成功', {icon: 1});
            Order.table.refresh();
        }, function (data) {
            Feng.error("操作失败!" + data.responseJSON.message + "!");
        });

        ajax.set("id",this.seItem.id);
        var obj=this.seItem;
        var str="";
        var title="";
        if(type==1){

            var gathering=this.seItem.gathering;
            title="收款情况 - 修改";
            //1.利润已收、2.利润未收、3.已收款、4.未收款、5.垫款
            str+="<input data-index=\"0\" name=\"shoukuan\" type=\"radio\" value='1' ";
            if(gathering==1){
                str+="checked='checked'";
            }
            str+=" > 利润已收 &emsp;&emsp;";
            str+="<input data-index=\"1\" name=\"shoukuan\" type=\"radio\" value='2'";
            if(gathering==2){
                str+="checked='checked'";
            }
            str+=" > 利润未收 <hr>";
            str+="<input data-index=\"2\" name=\"shoukuan\" type=\"radio\" value='3'";
            if(gathering==3){
                str+="checked='checked'";
            }
            str+=" > 已收款 &emsp;";
            str+="<input data-index=\"3\" name=\"shoukuan\" type=\"radio\" value='4'";
            if(gathering==4){
                str+="checked='checked'";
            }
            str+=" > 未收款 &emsp;";
            str+="<input data-index=\"4\" name=\"shoukuan\" type=\"radio\" value='5'";
            if(gathering==5){
                str+="checked='checked'";
            }
            str+=" > 垫款 &emsp;";
        }else{

            if(type==2){

                var status=this.seItem.status;
                title="订单状态 - 修改";
                //1.待货、2.已发货、3.断货、4.取消订单、5.成交、6.退货中、7.已退货、8.特殊处理
                str+="<input data-index=\"0\" name=\"zhuangtai\" type=\"radio\" value='1' ";
                if(status==1){
                    str+="checked='checked'";
                }
                str+=" > 待货 &emsp;&emsp;&emsp;&emsp;";
                str+="<input data-index=\"0\" name=\"zhuangtai\" type=\"radio\" value='2' ";
                if(status==2){
                    str+="checked='checked'";
                }
                str+=" > 已发货 <hr>";
                str+="<input data-index=\"0\" name=\"zhuangtai\" type=\"radio\" value='3' ";
                if(status==3){
                    str+="checked='checked'";
                }
                str+=" > 断货 &emsp;&emsp;&emsp;&emsp;";
                str+="<input data-index=\"0\" name=\"zhuangtai\" type=\"radio\" value='4' ";
                if(status==4){
                    str+="checked='checked'";
                }
                str+=" > 取消订单 <hr>";
                str+="<input data-index=\"0\" name=\"zhuangtai\" type=\"radio\" value='6' ";
                if(status==6){
                    str+="checked='checked'";
                }
                str+=" > 退货中 &emsp;&emsp;&emsp;";
                str+="<input data-index=\"0\" name=\"zhuangtai\" type=\"radio\" value='7' ";
                if(status==7){
                    str+="checked='checked'";
                }
                str+=" > 已退货 <hr>";
                str+="<input data-index=\"0\" name=\"zhuangtai\" type=\"radio\" value='5' ";
                if(status==5){
                    str+="checked='checked'";
                }
                str+=" > 成交 &emsp;&emsp;&emsp;&emsp;";
                str+="<input data-index=\"0\" name=\"zhuangtai\" type=\"radio\" value='8' ";
                if(status==8){
                    str+="checked='checked'";
                }
                str+=" > 特殊处理 ";

            }else{
                if(type==3){
                    var waiting=this.seItem.waiting;
                    title="待货时间 - 修改";
                    str+="<span>待货时间：</span><input id=\"daihuo\"  value=\""+waiting+"\" />";
                }else{
                    if(type==4){

                        //1.无，2.已收退款，3.未收退款，4.退款完成

                        var refund=this.seItem.refund;
                        title="退款情况 - 修改";
                        str+="<input data-index=\"0\" name=\"tuikuan\" type=\"radio\" value='1' ";
                        if(refund==1){
                            str+="checked='checked'";
                        }
                        str+=" > 无 &emsp;&emsp;";
                        str+="<input data-index=\"0\" name=\"tuikuan\" type=\"radio\" value='4' ";
                        if(refund==4){
                            str+="checked='checked'";
                        }
                        str+=" > 退款完成 <hr>";
                        str+="<input data-index=\"0\" name=\"tuikuan\" type=\"radio\" value='2' ";
                        if(refund==2){
                            str+="checked='checked'";
                        }
                        str+=" > 已收退款 &emsp;&emsp;";
                        str+="<input data-index=\"0\" name=\"tuikuan\" type=\"radio\" value='3' ";
                        if(refund==3){
                            str+="checked='checked'";
                        }
                        str+=" > 未收退款 &emsp;&emsp;";
                    }else{
                        if(type==5){
                            var post=this.seItem.post;
                            title="快递费 - 修改";
                            str+="<span>快递费：</span><input id=\"kuaidi\"  value=\""+post+"\" />";
                        }else{
                            if(type==6){
                                var remark=this.seItem.remark;
                                title="备注 - 修改";
                                str+="<span>备注：</span><textarea rows=\"4\" cols=\"42\"  id=\"beizhu\" onKeyDown='if (this.value.length>=100){event.returnValue=false}'>"+remark+"</textarea>";
                            }else{

                                var name=this.seItem.name;
                                title="删除";
                                str+="<div >确定删除客户："+name+" 的这个订单吗？</div>";

                            }
                        }
                    }
                }
            }

        }

        layer.confirm(str, {
            title:title,
            btn: ['确认'] //按钮
        }, function(){
            var flag=true;
            if(type==1){
                var shoukuan= $("input[name='shoukuan']:checked").val();


                    if(obj.source!=2){
                        if(shoukuan==1||shoukuan==2){
                        Feng.error("非微商城的订单，勿选择利润状况!");
                            flag=false;
                         }
                     }else{
                        if(shoukuan==3||shoukuan==4||shoukuan==5){
                            Feng.error("微商城的订单，勿选择收款状况!");
                            flag=false;
                        }
                    }

                ajax.set("gathering",shoukuan);
            }else{
                if(type==2){
                    //1.待货、2.已发货、3.断货、4.取消订单、5.成交、6.退货中、7.已退货、8.特殊处理
                    var zhuangtai= $("input[name='zhuangtai']:checked").val();

                    if(zhuangtai==1){
                        ajax.set("progress",10);
                        ajax.set("refund",1);
                    }else{
                        if(zhuangtai==2){
                            ajax.set("progress",50);
                            ajax.set("refund",1);
                        }else{
                            if(zhuangtai==3){
                                ajax.set("progress",90);
                                ajax.set("refund",3);
                            }else{
                                if(zhuangtai==4){
                                    ajax.set("progress",90);
                                    ajax.set("refund",3);
                                }else{
                                    if(zhuangtai==5){
                                        ajax.set("progress",100);
                                        if(obj.gathering==2||obj.gathering==4||obj.gathering==5){
                                            Feng.error("该订单收款情况尚未完成！！！");
                                            flag=false;
                                        }
                                        if(obj.refund==2||obj.refund==3){
                                            Feng.error("该订单退款情况尚未完成！！！");
                                            flag=false;
                                        }
                                    }else{
                                        if(zhuangtai==6){

                                            ajax.set("progress",80);
                                            ajax.set("refund",3);

                                        }else{
                                            if(zhuangtai==7){
                                                ajax.set("progress",100);
                                                if(obj.gathering==2||obj.gathering==4||obj.gathering==5){
                                                    Feng.error("该订单收款情况尚未完成！！！");
                                                    flag=false;
                                                }
                                                if(obj.refund!=4){
                                                    Feng.error("该订单退款情况尚未完成！！！");
                                                    flag=false;
                                                }
                                            }else{
                                                ajax.set("progress",90);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    ajax.set("status",zhuangtai);
                }else{
                    if(type==3){
                        var daihuo= $("#daihuo").val();
                        ajax.set("waiting",daihuo);
                    }else{
                        if(type==4){
                            var tuikuan= $("input[name='tuikuan']:checked").val();
                            ajax.set("refund",tuikuan);
                        }else{
                            if(type==5){
                                var kuaidi= $("#kuaidi").val();
                                if(kuaidi === "" || kuaidi ==null){
                                    Feng.error("请填写数值!");
                                    flag=false;
                                }
                                if(isNaN(kuaidi)){
                                    Feng.error("请填写数值!");
                                    flag=false;
                                }
                                ajax.set("post",kuaidi);
                            }else{
                                if(type==6){
                                    var beizhu= $("#beizhu").val();
                                    ajax.set("remark",beizhu);
                                }else{
                                    ajax.set("status",9);
                                }
                            }
                        }
                    }
                }
            }
            if(flag){
                ajax.start();
            }
        });

    }

}



$(function () {
    var defaultColunms = Order.initColumn();
    var table = new BSTable(Order.id, "/order/list", defaultColunms);
    table.setPaginationType("client");
    table.setPageSize(7);
    table.setPageList([7,15,20]);
    table.setHeight(700);
    Order.table = table.init();
});
