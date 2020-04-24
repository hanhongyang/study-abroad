
//记录申请学校数量
var schoolNum=1;

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
                        }).append("<input  type='checkbox'value='"+this.userId+"'>").append(this.name))
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
