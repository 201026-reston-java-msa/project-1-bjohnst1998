function fillTable()
{
    let viewType = sessionStorage.getItem('viewType');
    let xhr = new XMLHttpRequest();
  
xhr.onreadystatechange = function(){
    if(this.readyState ===4 && this.status===200)
    {
        console.log(this.responseText);
        let tbleArry =JSON.parse(this.responseText);
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
            if(currentUser.userRole === "Employee")
            {
                tr.innerHTML = '<td>'+id+'</td>' + '<td>'+employee+'</td>'+'<td>'+amount+'</td>'+'<td>'+type+'</td>'+'<td>'+status+'</td>'+'<td>'+desc+'</td>'+'<td>'+resolver + '</td>'+ '<td>'+date+'</td>';
                myTable.append(tr);
            }
            else
            {
                tr.innerHTML = '<td>'+id+'</td>' + '<td>'+employee+'</td>'+'<td>'+amount+'</td>'+'<td>'+type+'</td>'+'<td>'+status+'</td>'+'<td>'+desc+'</td>'+'<td>'+resolver + '</td>'+ '<td>'+date+'</td>' +'<td><form onsubmit="approveTransaction(event.preventDefault())"><input type="submit" value="Approve"></td>' +'<td><form onsubmit="denyTransaction(event.preventDefault())"id="1"><input type="submit" value="Deny"></form></td>';
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
function goBack()
{
    window.location = "http://localhost:9001/Reimbursement/home.html";
}
fillTable();