//防止csrf拦截post请求
var header = $("meta[name='_csrf_header']").attr("content");
var token =$("meta[name='_csrf']").attr("content");
//记录申请学校数量
var schoolNum=1;
//注册事件
$(document).ready(function(){
    //将国家信息加载到模态框
    getRegions("#country");
});
//获取国家列表.param:国家列表select元素
function getRegions(ele) {
    //先清空之前下拉列表的值
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
//添加一个申请
$("#addSchool").click(function () {
    schoolNum+=1;
    console.log(schoolNum);
    //添加学校
    var school=$("<div/>", {
        "class": "row addSchool",
        "id":"school"+schoolNum,
        "style":"margin-top: 5px;margin-bottom: 5px"
    });
    //创建删除按钮
    var deleteBtn = $("<div/>", {
        "class": "bs-glyphicons-list col-md-1"
    }).append($("<a/>", {
        "href":"#"
    }).append($("<li/>",{

    }).append($("<span/>",{
        "class":"glyphicon glyphicon-minus"
    }))));
    //创建院校名称
    var schoolName=$("<div/>", {
        "class": "col-md-3"
    }).append($("<select/>", {
        "class": "form-control"
    }).append($("<option/>",{
        "value": "1"
    }).append($("<span/>",{
        "text": "哈佛"
    }))));
    //创建专业名称
    var specialities=$("<div/>", {
        "class": "col-md-3"
    }).append($("<select/>", {
        "class": "form-control"
    }).append($("<option/>",{
        "value": "1"
    }).append($("<span/>",{
        "text": "软件工程"
    }))));
    //创建官方网站
    var website=$("<div/>", {
        "class": "col-md-2"
    }).append($("<a/>", {
        "style":"padding-top: 6px",
        "text": "点击前往"
    }));
    //创建批次
    var batch=$("<div/>", {
        "class": "col-md-3"
    }).append($("<select/>", {
        "class": "form-control"
    }).append($("<option/>",{
        "value": "1"
    }).append($("<span/>",{
        "text": "秋季11月01日"
    }))));
    //组合
    school.append(deleteBtn).append(schoolName).append(specialities).append(website).append(batch);
    $(this).parent("div").parent("div").parent("div").append(school);
})
//删除某个申请
$(function(){
    $("#schoolList").on('click',".addSchool a",function () {
        $(this).parent("div").parent("div").slideToggle();
    })
})
//显示推荐人
$(function(){
    $.ajax({
        url:"/Recommenders",
        type:"GET",
        success:function (result) {
            if(result.code==100){
                if(result.extend.recommenders!==null){
                    $.each(result.extend.recommenders,function () {
                        $(".checkbox").append($("<label/>", {
                            "class":"checkbox-inline"
                        }).append("<input  name='recommender' type='checkbox'value='"+this.userId+"'>").append(this.name))
                    })
                }else{
                    $(".checkbox").append($("<label/>", {

                    }).append("<span>"+你还没有添加推荐人+"</sapn>"))
                }

            }else {
                new PNotify({
                    title: 'Please login first！',
                    text: 'Just to let you know.',
                    type: 'warn',
                    styling: 'bootstrap3'
                });
            }
        }
    })

})
//文件上传
$(function () {
    initFileInput("input-id");
})

function initFileInput(ctrlName) {
    var id=parseInt($("#step-2").attr("data-id"));
    var control = $('#' + ctrlName);
    control.fileinput({
        language: 'zh', //设置语言
        uploadUrl: "/upload?id="+this.id, //上传的地址
        //allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
        uploadExtraData:{"id": 1, "fileName":'123.mp3'},
        uploadAsync: true, //默认异步上传
        showUpload: true, //是否显示上传按钮
        showRemove : true, //显示移除按钮
        showPreview : true, //是否显示预览
        showCaption: true,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式
        dropZoneEnabled: true,//是否显示拖拽区域
        //minImageWidth: 50, //图片的最小宽度
        //minImageHeight: 50,//图片的最小高度
        //maxImageWidth: 1000,//图片的最大宽度
        //maxImageHeight: 1000,//图片的最大高度
        //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
        //minFileCount: 0,
        maxFileCount: 10, //表示允许同时上传的最大文件个数
        showBrowse: true,
        browseOnZoneClick: true,
        enctype: 'multipart/form-data',
        validateInitialCount:true,
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        fileActionSettings: {                               // 在预览窗口中为新选择的文件缩略图设置文件操作的对象配置
            showRemove: true,                                   // 显示删除按钮
            showUpload: true,                                   // 显示上传按钮
            showDownload: false,                            // 显示下载按钮
            showZoom: false,                                    // 显示预览按钮
            showDrag: false,                                        // 显示拖拽
            removeIcon: '<i class="fa fa-trash"></i>',   // 删除图标
            uploadIcon: '<i class="fa fa-upload"></i>',     // 上传图标
            uploadRetryIcon: '<i class="fa fa-repeat"></i>'  // 重试图标
        },
    }).on('filepreupload', function(event, data, previewId, index) {     //上传中
        var form = data.form, files = data.files, extra = data.extra,
            response = data.response, reader = data.reader;
        console.log('文件正在上传');
    }).on("fileuploaded", function (event, data, previewId, index) {    //一个文件上传成功
        console.log('文件上传成功！'+data.id);

    }).on('fileerror', function(event, data, msg) {  //一个文件上传失败
        console.log('文件上传失败！'+data.id);


    })
}
//点击下一步按钮

$(function(){
    $(".buttonNext").on('click',function () {
        //获取当前步骤
        var step=$(this).attr('id');
        //发送ajax保存步骤内容
        if(step=='1'){
            $.ajax({
                url:"/ApplyStep"+step,
                type:"POST",
                data:$("#form1").serialize(),
                success:function (result) {
                    if(result.code==100){
                        var id=result.extend.id;
                        $("#step-2").attr("data-id",id);
                        //把申请表id返回给每个步骤的form
                        $("#form input[name=id]").val(id);
                        $("#form2 input[name=id]").val(id);
                        $("#form3 input[name=id]").val(id);
                        $("#form4 input[name=id]").val(id);
                        new PNotify({
                            title: 'Saved successfully！',
                            text: 'Just to let you know.',
                            type:'info',
                            styling: 'bootstrap3'
                        });
                    }else {
                        new PNotify({
                            title: 'Please login first！',
                            text: 'Just to let you know.',
                            type: 'warn',
                            styling: 'bootstrap3'
                        });
                    }
                }
            })
        }else if(step=='2'){
            $.ajax({
                url:"/ApplyStep"+step,
                type:"PUT",
                data:$("#form2").serialize(),
                beforeSend : function(xhr) {
                    xhr.setRequestHeader(header, token);
                },
                success:function (result) {
                    if(result.code==100){
                        new PNotify({
                            title: 'Saved successfully！',
                            text: 'Just to let you know.',
                            type:'info',
                            styling: 'bootstrap3'
                        });
                    }else {
                        new PNotify({
                            title: 'Please login first！',
                            text: 'Just to let you know.',
                            type: 'warn',
                            styling: 'bootstrap3'
                        });
                    }
                }
            })
        }else if(step=='3'){
            // Create an FormData object
            var data = new FormData();
            var files=Array.from($("#input-id")[0].files);
            for (var i = 0 ; i < files.length ; i++) {
                data.append("temp" + i, files[i]);
            }
            data.append("id",$("#form input[name=id]").val());
            $.ajax({
                url:"/ApplyStep"+step,
                type:"PUT",
                data:data,
                beforeSend : function(xhr) {
                    xhr.setRequestHeader(header, token);
                },
                cache: false,
                processData: false,
                contentType: false,
                success:function (result) {
                    if(result.code==100){
                        new PNotify({
                            title: 'Saved successfully！',
                            text: 'Just to let you know.',
                            type:'info',
                            styling: 'bootstrap3'
                        });
                    }else {
                        new PNotify({
                            title: 'Please login first！',
                            text: 'Just to let you know.',
                            type: 'warn',
                            styling: 'bootstrap3'
                        });
                    }
                }
            })
        }else if(step=='4'){
            console.log($("#form3").serialize())
            $.ajax({
                url:"/ApplyStep"+step,
                type:"PUT",
                data:$("#form3").serialize(),
                success:function (result) {
                    if(result.code==100){
                        new PNotify({
                            title: 'Saved successfully！',
                            text: 'Just to let you know.',
                            type:'info',
                            styling: 'bootstrap3'
                        });
                    }else {
                        new PNotify({
                            title: 'Please login first！',
                            text: 'Just to let you know.',
                            type: 'warn',
                            styling: 'bootstrap3'
                        });
                    }
                }
            })
        }else if(step=='5'){
            $.ajax({
                url:"/ApplyStep"+step,
                type:"POST",
                data:$("#form4").serialize(),
                success:function (result) {
                    if(result.code==100){


                    }else {
                        new PNotify({
                            title: 'Please login first！',
                            text: 'Just to let you know.',
                            type: 'warn',
                            styling: 'bootstrap3'
                        });
                    }
                }
            })
        }
    })
})
