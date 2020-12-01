function loadOptions()
{
    console.log("Loading options")
    let userS = sessionStorage.getItem('currentUser')
    let adminForm = document.getElementById('specForm')
    let currentU = JSON.parse(userS)
    if(currentU.userRole == 'FinanceManager')
    {
        console.log('giving admin option')
        adminForm.innerHTML = "<br><h3>Finance Manager Tools</h3><form action='view/all' method='post'><input type='submit' value='View All Reimbursements'></form>" +
        "<form action='view/user' method='get'><input type ='text' name='userID' placeholder='Enter User ID'><br><input type='submit' value='View Reimbursements'></form>"

    }


}

function getReimbursePage()
{
    window.location = 'http://localhost:9001/Reimburse/reimbursement.html';
}



loadOptions();