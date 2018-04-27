/**
 * 系统管理--客户管理的单例对象
 */
var MgrUser = {
    id: "managerTable",//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    deptid:0
};

/**
 * 初始化表格的列
 */
MgrUser.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '姓名', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '微信号', field: 'vxAccount', align: 'center', valign: 'middle'},
        {title: '性别', field: 'sexName', align: 'center', valign: 'middle'},
        {title: '电话', field: 'phone', align: 'center', valign: 'middle'},
        {title: '地址', field: 'address', align: 'center', valign: 'middle'},
        {title: '身高', field: 'height', align: 'center', valign: 'middle'},
        {title: '体重', field: 'weight', align: 'center', valign: 'middle'},
        {title: '码数', field: 'size', align: 'center', valign: 'middle'},
        {title: '广告', field: 'advertisement', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true},
        {title: '备注', field: 'remark', align: 'center', valign: 'middle'}];
    return columns;
};

/**
 * 检查是否选中
 */
MgrUser.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        MgrUser.seItem = selected[0];
        return true;
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
 * 点击添加
 */
MgrUser.openAddMgr = function () {
    var index = layer.open({
        type: 2,
        title: '添加客户',
        area: ['800px', '600px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/cus/cus_add'
    });
    this.layerIndex = index;
};

/**
 * 点击修改按钮时
 * @param userId 管理员id
 */
MgrUser.openChangeUser = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑客户信息',
            area: ['800px', '600px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/cus/update/' + this.seItem.id
        });
        this.layerIndex = index;
    }
};


/**
 * 删除用户
 */
MgrUser.delMgrUser = function () {
    if (this.check()) {

        var operation = function(){
            var customId = MgrUser.seItem.id;
            var ajax = new $ax(Feng.ctxPath + "/cus/delete", function () {
                Feng.success("删除成功!");
                MgrUser.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("customId", customId);
            ajax.start();
        };

        Feng.confirm("是否删除客户 " + MgrUser.seItem.name + " ？",operation);
    }
};


MgrUser.resetSearch = function () {
    $("#name").val("");
    $("#beginTime").val("");
    $("#endTime").val("");

    MgrUser.search();
}

MgrUser.search = function () {
    var queryData = {};

    queryData['name'] = $("#name").val();
    queryData['beginTime'] = $("#beginTime").val();
    queryData['endTime'] = $("#endTime").val();

    MgrUser.table.refresh({query: queryData});
}

MgrUser.onClickDept = function (e, treeId, treeNode) {
    MgrUser.deptid = treeNode.id;
    MgrUser.search();
};

$(function () {
    var defaultColunms = MgrUser.initColumn();
    var table = new BSTable("managerTable", "/cus/list", defaultColunms);
    table.setPaginationType("client");
    MgrUser.table = table.init();
});
