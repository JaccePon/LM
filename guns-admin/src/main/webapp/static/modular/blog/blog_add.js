/**
 *
 * @author JaccePon
 * @create 2018年08月30日 3:40 PM
 **/


$(function () {
    // 初始化封面上传
    var avatarUp = new $WebUpload("avatar",2);
    avatarUp.initForblog("blogPicShow");
    $("#avatarPreId").parent().remove();
    $("#avatar").prev().attr("class","col-sm-9");

    //checkbox单选
    checkboxChooseOne("originalFlag");
    checkboxChooseOne("publicityFlag");
});

function checkboxChooseOne(name){
    $("input[name='"+name+"']").change(function(){

        if($(this).attr("checked")==true){
            $("input[name='"+name+"']").prop("checked","checked");
            $(this).removeAttr("checked");
        }else{
            $("input[name='"+name+"']").removeAttr("checked");
            $(this).prop("checked","checked");
        }
    })
}