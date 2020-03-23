//输入框聚焦时样式
$("#content").focus(function () {
    $(this).css({"box-shadow":" 0 0 8px rgba(82,168,236,.6)","border-color": "rgba(82,168,236,.8)"});
})
$("#content").blur(function () {
    $(this).css({"box-shadow":" 0 0 0 0","border-color": "#ccc"});
})
//点击reply按钮
$("#reply").click(function () {
    var content=$("#content").val();
    var id=$("#replyForm input[name=parentId]").val();
    //校验输入
    if(!content.length>0){
        $("#valid_msg").text("请输入内容！");
        return false;
    }else {
        $("#valid_msg").text("");
    }
    $.ajax({
        url:"/comment",
        type:"POST",
        data:$("#replyForm").serialize(),
        success:function (result) {
            if(result.code==100){
                window.location.href="/question/"+id+"?pageNum=1";
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
})
