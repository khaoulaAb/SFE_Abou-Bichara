const url = 'http://localhost:3030';
let stompClient;
let selectedUser;
let newMessages = new Map();

function connectToChat(userName) {
    console.log("connecting to chat...")
    let socket = new SockJS(url + '/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to: " + frame);
        stompClient.subscribe("/topic/messages/" + userName, function (response) {
            let data = JSON.parse(response.body);
            if(selectedUser==data.fronLogin){
                render(data.message,data.fronLogin);
            }else{
                newMessages.set(data.fronLogin,data.message);
                $('#userNameAppender_'+data.fronLogin).append('<span id="newMessage_' + data.fronLogin + '"  style="color:red">+1</span>')
            }
        });
    });
}
function  sendMsg(fron,text) {
    stompClient.send("/app/chat/"+ selectedUser,{},JSON.stringify({
        fronLogin:fron,
        message:text
    }));

}


function registration() {
   let userName=document.getElementById('userName').value;
    $.get(url+"/registration/"+ userName,function (response){
            connectToChat(userName);
        }).fail(function (error) {
            if(error.status===400){
                alert("Login is already busy!")

            }

    })
}


function selectUser(userName){
    console.log("selecting users: "+userName);
    selectedUser=userName;
    let isNew = document.getElementById("newMessage_"+userName)!==null;
    if(isNew){
        let element= document.getElementById("newMessage_" + userName);
        element.parentNode.removeChild(element);
        render(newMessages.get(userName),userName)
    }
    $('#selectedUserId').html('');
    $('#selectedUserId').append('Chat avec '+userName);
}





function fetchAll() {
    $.get(url + "/fetchAllUsers", function (response) {
        let users = response;
        let usersTemplateHTML = "";
        for (let i = 0; i < users.length; i++) {
            usersTemplateHTML = usersTemplateHTML + '<a href="#" onclick="selectUser(\'' + users[i] + '\')"><li class="clearfix">\n' +
                '                <img src="https://rtfm.co.ua/wp-content/plugins/all-in-one-seo-pack/images/default-user-image.png" width="55px" height="55px" alt="avatar" />\n' +
                '                <div class="about">\n' +
                '                    <div id="userNameAppender_' + users[i] + '" class="name">' + users[i] + '</div>\n' +
                '                    <div class="status">\n' +
                '                        <i class="fa fa-circle offline"></i>\n' +
                '                    </div>\n' +
                '                </div>\n' +
                '            </li></a>';
        }
        $('#usersList').html(usersTemplateHTML);
    });
}

