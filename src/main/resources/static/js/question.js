
//输入框聚焦时样式
$("#content").focus(function () {
    $(this).css({"box-shadow":" 0 0 8px rgba(82,168,236,.6)","border-color": "rgba(82,168,236,.8)"});
})
$("#content").blur(function () {
    $(this).css({"box-shadow":" 0 0 0 0","border-color": "#ccc"});
})

//点击reply按钮(问题）
$("#replyQuestion").click(function () {
    var content=$("#content").val();
    var id=$("#replyForm input[name=parentId]").val();
    //校验输入
    if(!content){
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

//点击reply按钮(评论）
$(".replyBtn").click(function () {
    var id=$(this).attr("data-parentId");
    var content=$("#form-"+id+' input[name=content]').val();
    //校验输入
    if(!content){
        $(".valid_msg").text("请输入内容！");
        return false;
    }else {
        $(".valid_msg").text("");
    }
    $.ajax({
        url:"/comment",
        type:"POST",
        data:$("#form-"+id).serialize(),
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
//展开二级评论
$(".childComment").click(function () {
    //target标记是否获取过评论数据,防止重复生成
    var target=$(this).attr("data-flag");
    if(target=="false"){
        var id=$(this).attr("data-id");
        $(this).attr("data-flag","true");
        $.ajax({
            url: "/childComments?id=" + id,
            type: "GET",
            success: function (result) {
                if (result.code == 100) {
                    $.each(result.extend.commentList,function () {

                        //创建二级评论左边栏
                        var mediaLeftElement = $("<div/>", {
                            "class": "col-md-2"
                        }).append($("<img/>", {
                            "class": "avatar",
                            "src": this.user.icon,
                            "alt":"Avatar"
                        })).append($("<h4/>",{
                            "text":this.user.name,
                            "style":"float: left;width: 80px"
                        }));
                        //创建二级评论右边栏
                        var mediaBodyElement = $("<div/>", {
                            "class": "col-md-10"
                        }).append($("<h3/>", {
                            "class": "heading",
                            "text": this.content
                        })).append($("<br/>"));
                        //插入二级评论
                        var mediaElement=$("<li/>", {
                            "style": "height: 80px"
                        }).append($("<div/>", {
                            "class": "row"
                        }));
                        mediaElement.append(mediaLeftElement).append(mediaBodyElement)
                        $(".child_comment").children("ul").prepend(mediaElement);
                    })
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
    }

})
