//防止csrf拦截post请求
var header = $("meta[name='_csrf_header']").attr("content");
var token =$("meta[name='_csrf']").attr("content");
//点击login按钮
$("#login").click(function () {
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
//点击确认登录
$("#loginBtn").click(function () {

    //发送ajax请求保存user
    $.ajax({
        url:"/login",
        type:"POST",
        data:$("#loginModal form").serialize(),
        success:function (result) {
            if(result.code==100){
                //关闭模态框
                $("#loginModal").modal("hide");
                //重定向到首页
                window.location.href="/"
            }else {
                alert("登陆失败")
            }

        }
    })
})
//点击register按钮，未完成
$("#register").click(function () {
    //关闭模态框
    $("#loginModal").modal("hide");
    //清楚表单数据（表单重置），防止重复提交
    resetFrom("#registerModal form");
    $("#registerModal").modal({
        backdrop:"static"
    })
})
