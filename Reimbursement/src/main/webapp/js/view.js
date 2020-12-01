function fillTable()
{
    let viewType = sessionStorage.getItem('viewType');
    let xhr = new XMLHttpRequest();
  
xhr.onreadystatechange = function(){
    if(this.readyState ===4 && this.status===200)
    {
        console.log(this.responseText);
        let tbleArry =JSON.parse(this.responseText);
        sessionStorage.setItem('table',this.responseText);
        var i = 0;
        for(i=0; i <tbleArry.length;i++)
        {
            var tr= document.createElement('tr');
            let id = tbleArry[i].id;
            let employee = tbleArry[i].user;
            let amount = tbleArry[i].amount;
            let type = tbleArry[i].type;
            let status = tbleArry[i].status;
            let desc = tbleArry[i].description;
            let resolver = tbleArry[i].resolver;
            let date = tbleArry[i].dateSubmitted;
            let myTable = document.getElementById("myTable");
            let currentUser = JSON.parse(sessionStorage.getItem("currentUser"));
            if(currentUser.userRole === "Employee" || status !="Pending")
            {
                tr.innerHTML = '<td>'+id+'</td>' + '<td>'+employee+'</td>'+'<td>'+amount+'</td>'+'<td>'+type+'</td>'+'<td>'+status+'</td>'+'<td>'+desc+'</td>'+'<td>'+resolver + '</td>'+ '<td>'+date+'</td>';
                myTable.append(tr);
            }
            else
            {
                
                tr.innerHTML = '<td>'+id+'</td>' + '<td>'+employee+'</td>'+'<td>'+amount+'</td>'+'<td>'+type+'</td>'+'<td>'+status+'</td>'+'<td>'+desc+'</td>'+'<td>'+resolver + '</td>'+ '<td>'+date+'</td>' +'<td><form  onSubmit="approveTransaction('+id+', event.preventDefault())"><input type="submit" value="Approve"></form></td>' +'<td><form  onSubmit="denyTransaction('+id+', event.preventDefault())"><input type="submit" value="Deny"></form></td>';
                myTable.append(tr);

            }
         

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
        xhr.open("POST","http://localhost:9001/Reimbursement/view/current");
        xhr.send();

    }
    else if(viewType ==="all")
    {
        console.log("requesting All");
        xhr.open("POST","http://localhost:9001/Reimbursement/view/all");
        xhr.send();

    }
    else if(viewType ==="user")
    {
        let userId = sessionStorage.getItem('viewUser');
        xhr.open("POST","http://localhost:9001/Reimbursement/view/user");
        let template = {
            userId: userId
        }
        xhr.send(JSON.stringify(template));
    }
}
function approveTransaction(value)
{
    console.log(value);
    let arry = JSON.parse(sessionStorage.getItem('table'));
    console.log(arry.length);
    let i=0;
    for(i=0; i <arry.length;i++)
    {
        if(arry[i].id ===value )
        {
            let reimObj = {
                userId: arry[i].id
            }
            console.log(reimObj);
            let xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function(){
                 if(this.readyState ===4 && this.status===200)
                 {
                    location.reload();
                    

                 }
                if(this.readyState===4 && this.status===204)
                {
                     console.log("Failed to update reimbursement");
                }
             console.log("Processing")

            }
            xhr.open("POST","http://localhost:9001/Reimbursement/view/approve");
            xhr.send(JSON.stringify(reimObj));
        }
    }
 }

function denyTransaction(value)
{
    console.log("In Deny")
    console.log(value)
    
    let arry = JSON.parse(sessionStorage.getItem('table'));
    console.log(arry.length)
    let i=0;
    for(i=0; i <arry.length;i++)
    {
        if(arry[i].id ===value )
        {
            let reimObj = {
                userId: arry[i].id
            }
            console.log(reimObj);

            let xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function(){
                 if(this.readyState ===4 && this.status===200)
                 {
                   location.reload();
                    
                    console.log("Updated!")
                 }
                if(this.readyState===4 && this.status===204)
                {
                     console.log("Failed to update reimbursement");
                }
             console.log("Processing")

            }
            xhr.open("POST","http://localhost:9001/Reimbursement/view/deny");
            xhr.send(JSON.stringify(reimObj));
        }
    }
}
function goBack()
{
    window.location = "http://localhost:9001/Reimbursement/home.html";
}
fillTable();