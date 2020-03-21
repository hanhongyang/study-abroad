//防止csrf拦截post请求
var header = $("meta[name='_csrf_header']").attr("content");
var token =$("meta[name='_csrf']").attr("content");
//点击publish按钮
$("#publish").click(function () {
    //判断用户是否登录
    $.ajax({
        url: "/loginStatus",
        type: "GET",
        success: function (result) {
            if (result.code == 100) {
                //清楚表单数据（表单重置），防止重复提交
                resetFrom("#publishModal form");
                $("#publishModal").modal({
                    backdrop: "static"
                })
            } else {
                alert("Please login first.");
            }
        }
    })
})
//清空表单样式，表单数据，提示信息.param:表单元素
function resetFrom(ele) {
    $(ele)[0].reset();
    $(ele).find("*").removeClass("has-success has-error");
    $(ele).find(".help-block").text("");
}
//点击确认发布
$("#publishBtn").click(function () {
    //获取sectionId
    var sectionId=parseInt($(this).attr("data-sectionId"));
    //发送ajax请求保存user
    $.ajax({
        url:"/question",
        type:"POST",
        data:$("#publishModal form").serialize(),
        success:function (result) {
            if(result.code==100){
                //关闭模态框
                $("#publishModal").modal("hide");
                //重定向到首页
                window.location.href="/section/"+sectionId+"?pageNum=1"
            }else {
                alert("Publish failed,Please login again！")
            }
        }
    })
})