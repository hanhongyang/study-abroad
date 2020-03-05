//防止csrf拦截post请求
var header = $("meta[name='_csrf_header']").attr("content");
var token =$("meta[name='_csrf']").attr("content");
//点击login按钮
$("#loginBtn").click(function () {
    //清楚表单数据（表单重置），防止重复提交
    resetFrom("#loginModal form");
    $("#loginModal").modal({
        backdrop:"static"
    })
})
//清空表单样式，表单数据，提示信息.param:表单元素
function resetFrom(ele) {
    $(ele)[0].reset();
    $(ele).find("*").removeClass("has-success has-error");
    $(ele).find(".help-block").text("");
}