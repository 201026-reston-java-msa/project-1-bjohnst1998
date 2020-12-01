function loadOptions()
{
    console.log("Loading options")
    let userS = sessionStorage.getItem('currentUser')
    let adminForm = document.getElementById('specForm')
    let currentU = JSON.parse(userS)
    if(currentU.userRole == 'FinanceManager')
    {
        console.log('giving admin option')
        adminForm.innerHTML = "<br><h3>Finance Manager Tools</h3> <form onSubmit='getViewPageAll(event.preventDefault())'><input type='submit' value='View All Reimbursements'></form> " +
        "<form onSubmit='getViewPageUser(event.preventDefault())'><input type ='number' id='userID' placeholder='Enter User ID'><br><input type='submit' value='View Reimbursements'></form>"

    }


}

function getReimbursePage()
{
    console.log("Redirection to reimbursement.html");
    window.location.href = "http://localhost:9001/Reimbursement/reimbursement.html";
}


function getViewPageCurrent()
{
    window.location.href = "http://localhost:9001/Reimbursement/view.html";
    sessionStorage.setItem('viewType', 'current');
}
function getViewPageAll()
{
    window.location.href = "http://localhost:9001/Reimbursement/view.html";
    sessionStorage.setItem('viewType', 'all');
}
function getViewPageUser()
{
    window.location.href = "http://localhost:9001/Reimbursement/view.html";
    sessionStorage.setItem('viewType', 'user');
    let viewUser = document.getElementById('userID').value;
    sessionStorage.setItem('viewUser', viewUser)
}
loadOptions();