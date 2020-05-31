//全部已读按钮
$("#read").click(function () {
    $.ajax({
        url:"/NotificationStatus",
        type:"PUT",
        beforeSend : function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        success:function (result) {
            if(result.code==100){
                alert("消息已读")
            }else {

            }
        }
    })
})