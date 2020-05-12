
//chabot
//防止csrf拦截post请求
var header = $("meta[name='_csrf_header']").attr("content");
var token =$("meta[name='_csrf']").attr("content");
var doc = document;
// 模拟一些后端传输数据
var serviceData = {
    'robot': {
        'name': 'robot001',
        'dialogue': ['模拟回复1', '模拟回复2', '模拟回复3'],
        'welcome': '您好，robot001为您服务'
    }
};

var dialogueInput = doc.getElementById('dialogue_input'),
    dialogueContain = doc.getElementById('dialogue_contain'),
    dialogueHint = doc.getElementById('dialogue_hint'),
    btnOpen = doc.getElementById('btn_open'),
    btnClose = doc.getElementById('btn_close'),
    timer,
    timerId,
    shiftKeyOn = false;  // 辅助判断shift键是否按住

btnOpen.addEventListener('click', function(e) {
    $('.dialogue-support-btn').css({'display': 'none'});
    $('.dialogue-main').css({'display': 'inline-block', 'height': '0'});
    $('.dialogue-main').animate({'height': '600px'})
})

btnClose.addEventListener('click', function(e) {
    $('.dialogue-main').animate({'height': '0'}, function() {
        $('.dialogue-main').css({'display': 'none'});
        $('.dialogue-support-btn').css({'display': 'inline-block'});
    });
})

dialogueInput.addEventListener('keydown', function(e) {
    var e = e || window.event;
    if (e.keyCode == 16) {
        shiftKeyOn = true;
    }
    if (shiftKeyOn) {
        return true;
    } else if (e.keyCode == 13 && dialogueInput.value == '') {
        // console.log('发送内容不能为空');
        // 多次触发只执行最后一次渐隐
        setTimeout(function() {
            fadeIn(dialogueHint);
            clearTimeout(timerId)
            timer = setTimeout(function() {
                fadeOut(dialogueHint)
            }, 2000);
        }, 10);
        timerId = timer;
        return true;
    } else if (e.keyCode == 13) {
        var nodeP = doc.createElement('p'),
            nodeSpan = doc.createElement('span');
        nodeP.classList.add('dialogue-customer-contain');
        nodeSpan.classList.add('dialogue-text', 'dialogue-customer-text');
        nodeSpan.innerHTML = dialogueInput.value;
        nodeP.appendChild(nodeSpan);
        dialogueContain.appendChild(nodeP);
        dialogueContain.scrollTop = dialogueContain.scrollHeight;
        submitCustomerText(dialogueInput.value);
    }
});

dialogueInput.addEventListener('keyup', function(e) {
    var e = e || window.event;
    if (e.keyCode == 16) {
        shiftKeyOn = false;
        return true;
    }
    if (!shiftKeyOn && e.keyCode == 13) {
        dialogueInput.value = null;
    }
});

function submitCustomerText(question) {

    // code here 向后端发送text内容
    $.ajax({
        url:"/chatbot",
        type:"POST",
        data:"question="+question,
        beforeSend : function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        success:function (result) {
            var answer = {
                'robot': {
                    'name': 'robot001',
                    'dialogue': '',
                    'welcome': '您好，robot001为您服务'
                }
            };
            answer.robot.dialogue=result.extend.answer;
            getServiceText(answer);

        }
    })

}

function getServiceText(data) {
    var serviceText = data.robot.dialogue;
    var nodeP = doc.createElement('p'),
        nodeSpan = doc.createElement('span');
    nodeP.classList.add('dialogue-service-contain');
    nodeSpan.classList.add('dialogue-text', 'dialogue-service-text');
    nodeSpan.innerHTML = serviceText;
    nodeP.appendChild(nodeSpan);
    dialogueContain.appendChild(nodeP);
    dialogueContain.scrollTop = dialogueContain.scrollHeight;
}

// 渐隐
function fadeOut(obj) {
    var n = 100;
    var time = setInterval(function() {
        if (n > 0) {
            n -= 10;
            obj.style.opacity = '0.' + n;
        } else if (n <= 30) {
            obj.style.opacity = '0';
            clearInterval(time);
        }
    }, 10);
    return true;
}

// 渐显
function fadeIn(obj) {
    var n = 30;
    var time = setInterval(function() {
        if (n < 90) {
            n += 10;
            obj.style.opacity = '0.' + n;
        } else if (n >= 80) {

            obj.style.opacity = '1';
            clearInterval(time);
        }
    }, 100);
    return true;
}
