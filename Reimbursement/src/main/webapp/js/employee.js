function fillTable()
{
    let viewType = sessionStorage.getItem('viewType');
    let xhr = new XMLHttpRequest();
  
xhr.onreadystatechange = function(){
    if(this.readyState ===4 && this.status===200)
    {
        console.log(this.responseText);
        let tbleArry =JSON.parse(this.responseText);
        sessionStorage.setItem('tableUser',this.responseText);
        var i = 0;
        for(i=0; i <tbleArry.length;i++)
        {
            var tr= document.createElement('tr');
            let id = tbleArry[i].id;
            let firstname = tbleArry[i].firstName;
            let lastname = tbleArry[i].lastName;
            let email = tbleArry[i].email;
            let userName = tbleArry[i].username;
            let password = tbleArry[i].password;
            let role = tbleArry[i].userRole;
            let myTable = document.getElementById("myTable");
            let currentUser = JSON.parse(sessionStorage.getItem("currentUser"));
                tr.innerHTML = '<td>'+id+'</td>' + '<td><form><input type ="text" id="firstN'+id+'" value="'+firstname+'"></form></td>'+'<td><form><input type ="text" id="lastN'+id+'" value="'+lastname+'"></form></td><td><form><input type ="text" id="email'+id+'" value="'+email+'"></form></td>'+'<td><form><input type ="text" id="userN'+id+'" value="'+userName+'"></form></td>'+'<td><form><input type ="password" id="pass'+id+'"value ="hidden" </form></td>'+'<td><form><input type ="text" id="role'+id+'" value="'+role+'" readonly></form></td>'+'<td><form onSubmit="updateProfile('+id+', event.preventDefault())"><input type="submit" value = "Update"></form></td>';
                myTable.append(tr);
        }
    }
    if(this.readyState===4 && this.status===204)
    {
        console.log("Failed to generate table");

    }
    console.log("Processing")

}
console.log("viewType: " +viewType);
    if(viewType === "current")
    {
        xhr.open("POST","http://localhost:9001/Reimbursement/view/currentEmp");
        xhr.send();

    }
    else if(viewType ==="all")
    {
        console.log("requesting All");
        xhr.open("POST","http://localhost:9001/Reimbursement/view/allEmp");
        xhr.send();

    }
    else if(viewType ==="user")
    {
        let userId = sessionStorage.getItem('viewUser');
        console.log(userId);
        xhr.open("POST","http://localhost:9001/Reimbursement/view/userEmp");
        let template = {
            userId: userId
        }
        xhr.send(JSON.stringify(template));
    }
}

function updateProfile(value)
{
    let id = value;
    let username;
    let password;
    let firstName;
    let lastName;
    let email;
    let userRole;
   
        username= document.getElementById("userN"+id).value;
    
        password =document.getElementById("pass"+id).value
        
    
        firstName = document.getElementById("firstN"+id).value
    
   
        lastName =document.getElementById("lastN"+id).value
    
   
        email =document.getElementById("email"+id).value
    
   
         userRole =document.getElementById("role"+id).value
    
    
    console.log("Updating Profile!")
    let userTemplate = {
        id:id,
        username:username,
        password: password,
        firstName: firstName,
        lastName: lastName,
        email: email,
        userRole:userRole
    }

    console.log(userTemplate);
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function()
    {
        if(this.readyState ===4 && this.status===200)
        {
            console.log("Updated profile!")
        }
        else if(this.readyState===4 && this.status===204)
        {
            console.log("Failed to update profule")
        }
        console.log("processing")
    }
    xhr.open("POST","http://localhost:9001/Reimbursement/view/update");
    xhr.send(JSON.stringify(userTemplate));

}
function goBack()
{
    window.location = "http://localhost:9001/Reimbursement/home.html";
}
fillTable();