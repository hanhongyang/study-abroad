
//防止csrf拦截post请求
var header = $("meta[name='_csrf_header']").attr("content");
var token =$("meta[name='_csrf']").attr("content");
//点击user添加按钮
$("#userAddBtn").click(function () {
    //清楚表单数据（表单重置），防止重复提交
    resetFrom("#userAddModal form");
    //发送ajax请求查询国家信息，显示在下拉列表
    getRegions("#userAddModal select");
    //弹出模态框
    $("#userAddModal").modal({
        backdrop:"static"
    })
})
//清空表单样式，表单数据，提示信息.param:表单元素
function resetFrom(ele) {
    $(ele)[0].reset();
    $(ele).find("*").removeClass("has-success has-error");
    $(ele).find(".help-block").text("");
}
//获取国家列表.param:国家列表select元素
function getRegions(ele) {
    //先情况之前下拉啊列表的值
    $(ele).empty();
    $.ajax({
        url:"/countries",
        type:"GET",
        success:function(result){
            $.each(result.extend.countries,function () {
                var optionElement=$("<option ></option>").append("<span>"+this.name+"</sapn>").attr("value",this.countryId);
                optionElement.appendTo(ele);
            })
        }
    })
}
//模态框中填写的表单数据提交保存
$("#userSavaBtn").click(function () {
    //校验表单数据
    if(!validate_add_form()){
        return false;
    };
    //判断checkEmail校验是否成功。成功才往下进行
    if($(this).attr("ajax_validate")=="error"){
        return false;
    }
    //发送ajax请求保存user
    $.ajax({
        url:"/user",
        type:"POST",
        data:$("#userAddModal form").serialize(),
        success:function (result) {
            //员工保存成功
            //1、关闭模态框
            $("#userAddModal").hide();
            //2、重定向到最后一页
            window.location.replace("/users?pageNum=99999");
        }
    })
})
//校验添加表单
function validate_add_form() {

    //校验密码
    var password=$("#passwordAdd").val();
    var regPassword=/^[\w_-]{4,16}$/;
    if(!regPassword.test(password)){
        show_validate_msg("#passwordAdd","error","请输入4-16位密码！(特殊符号只允许‘-’、‘_’)");
        return false;
    }else {
        show_validate_msg("#passwordAdd","success","");
    }
    //校验邮箱
    var email=$("#emailAdd").val();
    var regEmail=/^[A-Za-z0-9]+([_\.][A-Za-z0-9]+)*@([A-Za-z0-9\-]+\.)+[A-Za-z]{2,6}$/;
    if(!regEmail.test(email)){
        show_validate_msg("#emailAdd","error","邮箱格式不正确");
        return false;
    }else {
        show_validate_msg("#emailAdd","success","");
    }
    //校验手机号码
    var mobile=$("#mobileAdd").val();
    var regMobile= /^\d+$|^\d+[.]?\d+$/;
    if(!regMobile.test(mobile)){
        show_validate_msg("#mobileAdd","error","只允许输入数字");
        return false;
    }else {
        show_validate_msg("#mobileAdd","success","");
    }
    //校验用户名
    var name=$("#nameAdd").val();
    var regName=/(^[a-zA-Z0-9_-]{3,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
    if(!regName.test(name)){
        show_validate_msg("#nameAdd","error","名字必须是2-5位中文或者3-16位英文和数字的组合");
        return false;
    }else {
        show_validate_msg("#nameAdd","success","");
    }
    return true;
}
//校验更新表单
function validate_update_form() {

    //校验密码
    var password=$("#passwordUpdate").val();
    var regPassword=/^[\w_-]{4,16}$/;
    if(!regPassword.test(password)){
        show_validate_msg("#passwordUpdate","error","请输入4-16位密码！(特殊符号只允许‘-’、‘_’)");
        return false;
    }else {
        show_validate_msg("#passwordUpdate","success","");
    }
    //校验邮箱
    var email=$("#emailUpdate").val();
    var regEmail=/^[A-Za-z0-9]+([_\.][A-Za-z0-9]+)*@([A-Za-z0-9\-]+\.)+[A-Za-z]{2,6}$/;
    if(!regEmail.test(email)){
        show_validate_msg("#emailUpdate","error","邮箱格式不正确");
        return false;
    }else {
        show_validate_msg("#emailUpdate","success","");
    }
    //校验手机号码
    var mobile=$("#mobileUpdate").val();
    var regMobile= /^\d+$|^\d+[.]?\d+$/;
    if(!regMobile.test(mobile)){
        show_validate_msg("#mobileUpdate","error","只允许输入数字");
        return false;
    }else {
        show_validate_msg("#mobileUpdate","success","");
    }
    //校验用户名
    var name=$("#nameUpdate").val();
    var regName=/(^[a-zA-Z0-9_-]{3,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
    if(!regName.test(name)){
        show_validate_msg("#nameUpdate","error","名字必须是2-5位中文或者3-16位英文和数字的组合");
        return false;
    }else {
        show_validate_msg("#nameUpdate","success","");
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
//检验邮箱是否可用
$("#emailAdd").change(function () {
    //获取输入框的email
    var email=this.value;
    //发送ajax检验邮箱是否可用
    $.ajax({
        url:"/checkEmail",
        data:"email="+email,
        type:"POST",
        beforeSend : function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        success:function (result) {
            if(result.code==100){
                show_validate_msg("#emailAdd","success","email可用");
                $("#userSavaBtn").attr("ajax_validate","success");
            }else{
                show_validate_msg("#emailAdd","error",result.extend.va_msg);
                $("#userSavaBtn").attr("ajax_validate","error");
            }
        }
    })

})
//检验邮箱是否可用
$("#emailUpdate").change(function () {
    //获取输入框的email
    var email=this.value;
    //发送ajax检验邮箱是否可用
    $.ajax({
        url:"/checkEmail",
        data:"email="+email,
        type:"POST",
        beforeSend : function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        success:function (result) {
            if(result.code==100){
                show_validate_msg("#emailUpdate","success","email可用");
                $("#userUpdateBtn").attr("ajax_validate","success");
            }else{
                show_validate_msg("#emailUpdate","error",result.extend.va_msg);
                $("#userUpdateBtn").attr("ajax_validate","error");
            }
        }
    })

})
//点击user编辑按钮
$(".edit_btn").click(function () {
    //获取userId
    var userId=$(this).parent().parent().children("#userId").text();
    //查询user信息，显示在模态框中
    getUser(userId);
    //发送ajax请求查询国家信息，显示在下拉列表
    getRegions("#userUpdateModal select");
    //把userId传递到模态框的更新按钮
    $("#userUpdateBtn").attr("userId",userId);

    //弹出模态框
    $("#userUpdateModal").modal({
        backdrop:"static"
    })
})
//查询User信息
function getUser(userId) {
    $.ajax({
        url:"/user/"+userId,
        type:"GET",
        success:function (result) {
            var userData=result.extend.user;
            $("#userUpdateModal input[name=userId]").val(userId);
            $("#userUpdateModal input[name=rule]").val([userData.rule]);
            $("#passwordUpdate").val(userData.password);
            $("#userUpdateModal select").val([userData.countryId]);
            $("#emailUpdate").val(userData.email);
            $("#mobileUpdate").val(userData.mobile);
            if(userData.birthday!=null){
                var birthday=userData.birthday.substr(0,10);
                $("#birthdayUpdate").val(birthday);
            }
            $("#nameUpdate").val(userData.name);
            $("#iconImg").attr("src",userData.icon);
        }
    })
}
//点击更新按钮
$("#userUpdateBtn").click(function () {
    //校验表单数据
    if(!validate_update_form()){
        return false;
    };
    //判断checkEmail校验是否成功。成功才往下进行
    if($(this).attr("ajax_validate")=="error"){
        return false;
    }
    //获取当前页码
    var pageNum=parseInt($(this).attr("data-pageNum"));
    //formData封装提交的数据

    var formData = new FormData();
    formData.append('rule',$("#userUpdateModal input[name=rule]").val());
    formData.append('password',$("#passwordUpdate").val());
    formData.append('countryId',$("#userUpdateModal select").val());
    formData.append('email',$("#emailUpdate").val());
    formData.append('mobile',$("#mobileUpdate").val());
    formData.append('birthday',$("#birthdayUpdate").val());
    formData.append('name',$("#nameUpdate").val());
    if(!judgeEmpty($("#iconImg").attr('src'))){
        formData.append('icon',$("#iconImg").attr('src'));
    }else {
        formData.append('icon',null);
    }

    //发送ajax保存user更新的数据
    $.ajax({
        url:"/user/"+$(this).attr("userId"),
        type:"PUT",
        beforeSend : function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        data:formData,
        processData: false,   // jQuery不要去处理发送的数据
        contentType: false,   // jQuery不要去设置Content-Type请求头
        success:function (result) {
            //关闭模态框
            $("#userUpdateModal").modal("hide");
            //回到页面
            window.location.replace("/users?pageNum="+pageNum);
        }
    })
})
//点击user删除按钮
$(".delete_btn").click(function () {
    //获取userId
    var userId=$(this).parent().parent().children("#userId").text();
    //弹出是否确认对话框
    if(confirm("确认删除【"+userId+"】吗？")){
        //获取当前页码
        var pageNum=parseInt($(this).attr("data-pageNum"));
        //确认，发送ajax请求删除即可
        $.ajax({
            url:"/user/"+userId,
            type:"DELETE",
            beforeSend : function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            success:function (result) {
                window.location.replace("/users?pageNum="+pageNum);
            }
        })
    }
})
//全选框
$("#checkAll").click(function () {
    //获取选中状态checked
    $(".check-menu-item").prop("checked",$(this).prop("checked"));
})
//单选
$(document).on("click",".check-menu-item",function () {
    //flag保存当前选择的行数是否为全选
    var flag=$(".check-menu-item:checked").length==$(".check-menu-item").length;
    $("#checkAll").prop("checked",flag);
})
//点击批量删除
$("#userDeleteBtn").click(function () {
    //userId保存选中的列userId
    var userIds=""
    $.each($(".check-menu-item:checked"),function () {
        userIds+=$(this).parents("tr").find("td:eq(1)").text()+",";
    })
    //去除userId最后的逗号
    userIds=userIds.substring(0,userIds.length-1);
    if(userIds!=""){
        if(confirm("确认删除【"+userIds+"】吗？")){
            //发送Ajax请求批量删除
            $.ajax({
                url:"/user/"+userIds,
                type:"DELETE",
                beforeSend : function(xhr) {
                    xhr.setRequestHeader(header, token);
                },
                success:function (result) {
                    alert(result.msg);
                    //回到当前页面
                }
            })
        }
    }else {
        alert("请先选择用户！");
    }

})

//鼠标放在上传头像上时改变css
$("#iconImg").hover(function () {
    $(this).css("-webkit-filter"," blur(2px)");
},function(){
    $(this).css("-webkit-filter"," blur(0px)")
});
//气泡提示框
$("#iconImg").popover({
    placement:'right',
    trigger: 'hover',
    content:'点击头像即可更换',
})
// 上传数量控制，给出默认值
var iconNum = 1;
// 上传数量控制，判断是否定义并赋值
iconNum = typeof iconNum != "undefined" && iconNum ? iconNum : 1;
// 上传大小控制，当前为1M
var iconSize = 5*1024*1024;
/**
 * 判断文件是否为空
 */
function judgeEmpty(data) {
    return (Array.isArray(data) && data.length === 0) || (Object.prototype.isPrototypeOf(data) && Object.keys(data).length === 0);
}
/**
 * 文件选择触发
 */
$("#iconUpdate").change(function (){
    var file = this.files[0];
    if( file.size >  iconSize){
        alert("你选择的文件太大了！");
        return false;
    }
    if(!/image\/\w+/.test(file.type)){
        alert("文件必须为图片！");
        return false;
    }
    var reader = new FileReader();
    //读取文件过程方法
    reader.onerror = function (e) {
        console.log("文件读取异常....");alert('文件上传异常请关闭重试....');
    }
    reader.onabort = function(e) {
        console.log("文件读取异常....");alert('文件上传异常请关闭重试....');
    };
    reader.onload = function (e) {
        var url=e.target.result;
        document.getElementById("iconImg").src=url;
    }
    //readAsDataURL方法会使用base-64进行编码，编码的资料由data字串开始，
    // 后面跟随的是MIME type，然后再加上base64字串，逗号之后就是编码过的图像文件的内容。
    reader.readAsDataURL(file);
});

