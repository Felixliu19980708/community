function post() {
    var questionId = $("#question_id").val();
    var content = $("#comment_content").val();
    if(!content){
        alert("回复不能为空");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType:'application/json',
        data: JSON.stringify({
            "parentId":questionId,
            "content":content,
            "type":1
        }),
        success: function (response) {
            if(response.code == 200){
                window.location.reload();
            }else{
                if(response.code==2003){
                   var isAccept =  confirm(response.message);
                   if(isAccept){
                   window.open("https://github.com/login/oauth/authorize?client_id=67e4193e4771b651399e&redirect_uri=http://localhost:8887/callback&scope=user&state=1");
                   window.localStorage.setItem("closable",true);
                   }
                }else {
                    alert(response.message);
                }
            }
            console.log(response);
        },
        dataType: "json"
    });
}