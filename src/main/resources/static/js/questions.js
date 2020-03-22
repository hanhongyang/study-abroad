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
                alert('Please login first！');
                $("#publish").blur();
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
    //校验表单数据
    if(!validate_form()){
        return false;
    };
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
                //提示发布失败，请重新登录
                alert('Publish failed,Please login again！');
            }
        }
    })
})
//校验添加表单
function validate_form() {
    //校验title
    var title=$("#title").val();
    if(!title.length>0){
        show_validate_msg("#title","error","Please enter a title!");
        return false;
    }else {
        show_validate_msg("#title","success","");
    }
    //校验description
    var description=$("#description").val();
    if(!description.length>0){
        show_validate_msg("#description","error","Please enter description!");
        return false;
    }else {
        show_validate_msg("#description","success","");
    }
    return true;
}
//显示校验信息。param：input元素，校验结果，校验信息
function show_validate_msg(ele,status,msg) {
    //先清空之前的样式，防止重复生成样式
    $(ele).parent().removeClass("has-success has-error");
    $(ele).next("span").text("");
    if("success"==status){
        $(ele).parent().addClass("has-success");
        $(ele).next("span").text(msg);
    }else  if("error"==status){
        $(ele).parent().addClass("has-error");
        $(ele).next("span").text(msg);
    }
}