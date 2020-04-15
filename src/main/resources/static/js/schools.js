//选择国家
$(".countryBtn").click(function () {
    var countryId=$(this).attr("data-id");
        $.ajax({
            url:"/schools?countryId="+countryId,
            type:"GET",
            success: function (result) {

                if (result.code == 100) {
                    console.log(result.extend)
                }else {
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
