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

    $.ajax({
        url:"/login",
        type:"POST",
        data:$("#loginModal form").serialize(),
        success:function (result) {
            if(result.code==100){
                //关闭模态框
                $("#loginModal").modal("hide");
                //重定向到当前页面
                window.location.reload();
            }else {
                new PNotify({
                    title: 'Log in failed',
                    text: 'Something terrible happened.!',
                    type: 'error',
                    styling: 'bootstrap3'
                });
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
//点击注销
$("#logout").click(function () {
    $.ajax({
        url:"/logout",
        type:"GET",
        success:function (result) {
            if(result.code==100){

                window.location.reload();
            }else {
                new PNotify({
                    title: 'Log out failed',
                    text: 'Something terrible happened.!',
                    type: 'error',
                    styling: 'bootstrap3'
                });
            }

        }
    })
})