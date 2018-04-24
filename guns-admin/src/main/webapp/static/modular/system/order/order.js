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
            {title: '主键id', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '对应的用户id', field: 'userId', visible: true, align: 'center', valign: 'middle'},
            {title: '订单的衣服图片', field: 'pic', visible: true, align: 'center', valign: 'middle'},
            {title: '对应app的订单号', field: 'orderNum', visible: true, align: 'center', valign: 'middle'},
            {title: '衣服货号', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: '原价', field: 'price', visible: true, align: 'center', valign: 'middle'},
            {title: '代理价', field: 'disPrice', visible: true, align: 'center', valign: 'middle'},
            {title: '关联的客户id', field: 'customId', visible: true, align: 'center', valign: 'middle'},
            {title: '来源（1.特价 、2.微商城、3.普通）', field: 'source', visible: true, align: 'center', valign: 'middle'},
            {title: '收款情况（1.利润已收、2.利润未收、3.已收款、4.未收款、5.垫款）', field: 'gathering', visible: true, align: 'center', valign: 'middle'},
            {title: '状态（1.待货、2.已发货、3.断货、4.取消订单、5.成交、6.退货中、7.已退货、8.特殊处理、9.删除）', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '待货时间', field: 'waiting', visible: true, align: 'center', valign: 'middle'},
            {title: '快递费', field: 'post', visible: true, align: 'center', valign: 'middle'},
            {title: '上级意见', field: 'opinion', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {title: '退款情况（1.无，2.已收退款，3.未收退款，4.退款完成）', field: 'refund', visible: true, align: 'center', valign: 'middle'},
            {title: '进度（0-100）', field: 'progress', visible: true, align: 'center', valign: 'middle'},
            {title: '提醒，用于后期扩展（微信提醒，短信提醒等）', field: 'remind', visible: true, align: 'center', valign: 'middle'},
            {title: '创建时间', field: 'createTime', visible: true, align: 'center', valign: 'middle'},
            {title: '更新时间', field: 'updateTime', visible: true, align: 'center', valign: 'middle'}
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
        area: ['800px', '420px'], //宽高
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
            area: ['800px', '420px'], //宽高
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

/**
 * 查询列表
 */
Order.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    Order.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Order.initColumn();
    var table = new BSTable(Order.id, "/order/list", defaultColunms);
    table.setPaginationType("client");
    Order.table = table.init();
});
