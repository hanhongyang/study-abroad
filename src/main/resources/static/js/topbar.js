
//注册事件
//发送ajax请求查询未读消息数量
window.onload=function(){
    //判断用户是否登录
    $.ajax({
        url: "/loginStatus",
        type: "GET",
        success: function (result) {
            if (result.code == 100) {
                //如果已登录则查询未读消息数量
                $.ajax({
                    url:"/NotificationCount",
                    type:"GET",
                    success:function (result) {
                        if(result.code==100){
                            if(result.extend.unreadCount!=0){
                                $(".badge").text(result.extend.unreadCount)
                                console.log(result.extend.unreadCount)
                            }
                        }else {
                            console.log("查询消息数量失败")
                        }
                    }
                })
            }
        }
    })

};
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
//点击apply按钮
$("#apply").click(function () {
    //判断用户是否登录
    $.ajax({
        url: "/loginStatus",
        type: "GET",
        success: function (result) {
            if (result.code == 100) {
               //进入apply页面
                window.location.href='/applyWizard';
            } else {
                new PNotify({
                    title: 'Please login first！',
                    text: 'Just to let you know.',
                    type: 'info',
                    styling: 'bootstrap3'
                });
            }
        }
    })
})
window.onload=function() {
    $.ajax({
        type: "GET",
        url: "/news",
        success: function (result) {
            if(result.code==100){
                //获取div
                var div = document.getElementById("div2");
                for(var i=0;i<result.extend.lastNews.length;i++){
                    //h
                    var h=document.createElement("h4");
                    var title=result.extend.lastNews[i].title
                    h.innerHTML=title;
                    //a
                    var a = document.createElement("a");
                    a.appendChild(h)
                    var href='/newsDetail?id='
                    href=href+result.extend.lastNews[i].id
                    a.href = href;
                    div.appendChild(a)
                }
            }else {
                new PNotify({
                    title: 'Reply failed,Please login first！',
                    text: 'Just to let you know.',
                    type: 'warn',
                    styling: 'bootstrap3'
                });
            }
        }
    })
}