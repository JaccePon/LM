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
            {title: '图片', field: 'pic', visible: true, align: 'center', valign: 'middle'},
            {title: '订单号', field: 'orderNum', visible: true, align: 'center', valign: 'middle'},
            {title: '货号', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: '原价', field: 'price', visible: true, align: 'center', valign: 'middle'},
            {title: '代理价', field: 'disPrice', visible: true, align: 'center', valign: 'middle'},
            {title: '客户', field: 'name', visible: true, align: 'center', valign: 'middle'},
            {title: '来源', field: 'source', visible: true, align: 'center', valign: 'middle'},
            {title: '收款', field: 'gathering', visible: true, align: 'center', valign: 'middle'},
            {title: '状态', field: 'status', visible: true, align: 'center', valign: 'middle'},
            {title: '待货', field: 'waiting', visible: true, align: 'center', valign: 'middle'},
            {title: '退款', field: 'refund', visible: true, align: 'center', valign: 'middle'},
            {title: '快递费', field: 'post', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'remark', visible: true, align: 'center', valign: 'middle'},
            {title: '上级意见', field: 'opinion', visible: true, align: 'center', valign: 'middle'},
            {title: '进度', field: 'progress', visible: true, align: 'center', valign: 'middle',sortable: true},
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
    $("#name").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    $('#progress').val("");

    Order.search();
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
