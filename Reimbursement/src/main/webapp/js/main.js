function loadOptions()
{
    console.log("Loading options")
    let userS = sessionStorage.getItem('currentUser')
    let adminForm = document.getElementById('specForm')
    let currentU = JSON.parse(userS)
    if(currentU.userRole == 'FinanceManager')
    {
        console.log('giving admin option')
        adminForm.innerHTML = "<br><h3>Manager Tools</h3> <form onSubmit='getViewPageAll(event.preventDefault())'><input type='submit' value='View All Reimbursements'></form> " +
        "<form onSubmit='getViewPageUser(event.preventDefault())'><input type ='number' id='userID' placeholder='Enter User ID'><br><input type='submit' value='View Reimbursements'></form><form onSubmit='viewAllEmployees(event.preventDefault())'><input type='submit' value = 'View all employees'></form><form onSubmit='viewEmployee(event.preventDefault())'><input type ='number' id='userID2' placeholder='Enter User ID'><br><input type='submit' value = 'View employee'></form>"

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

function viewAllEmployees()
{
    window.location.href = "http://localhost:9001/Reimbursement/employee.html";
    sessionStorage.setItem('viewType', 'all');
}


function viewEmployee()
{
    window.location.href = "http://localhost:9001/Reimbursement/employee.html";
    sessionStorage.setItem('viewType', 'user');
    let viewUser = document.getElementById('userID2').value;
    sessionStorage.setItem('viewUser', viewUser)
}

function viewCurrentEmployee()
{
    window.location.href = "http://localhost:9001/Reimbursement/employee.html";
    sessionStorage.setItem('viewType', 'current');
}
function logout()
{
    sessionStorage.clear();
    window.location.href = "http://localhost:9001/Reimbursement/"
}


loadOptions();