
function sendLogin()
{
let loginForm = document.loginForm;
let username = document.getElementById("username").value;
let password = document.getElementById("password").value;
let signIn = document.getElementById("signIn").value;
console.log("Username: " +username);
let loginTemplate = {
    username: username,
    password: password,
    signIn: signIn
}

//AJAX
let xhr = new XMLHttpRequest();
xhr.onreadystatechange = function(){
    if(this.readyState ===4 && this.status===200)
    {
        sessionStorage.setItem('currentUser',this.responseText);
        window.location = 'http://localhost:9001/Reimbursement/home.html';

    }
    if(this.readyState===4 && this.status===204)
    {
        console.log("Failed to log in");

        let childDiv = document.getElementById("warningText");
        childDiv.textContent ="Failed to log in! Username or Password is incorrect";

    }
    console.log("Processing")

}
xhr.open("POST","http://localhost:9001/Reimbursement/login");
xhr.send(JSON.stringify(loginTemplate));
}