function submitReimbursement()
{  if(userString === null)
    {
        window.location = "http://localhost:9001/Reimbursement/index.html"
    }
    else{
        let reimbursementForm = document.reimbursementForm;
        let type = document.getElementById("inlineFormCustomSelectPref").value;
        let amount = document.getElementById("amount").value;
        let description = document.getElementById("description").value;
    
    
        console.log("Submitting reimbursement!");
        let reimbursementTemplate = {
            type: type,
            amount: amount,
            description: description
        }
    
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function()
        {
            if(this.readyState===4 && this.status===200)
            {
                //alert("Your reimbursement has been submitted!")
                console.log("response: "+this.responseText);
                sessionStorage.setItem('viewType', 'current');   
                window.location.href = "http://localhost:9001/Reimbursement/view.html";
              }
            else if(this.readyState===4 && this.status ===204)
            {
                console.log("Failed to submit.")
                alert("Failed to submit reimbursement")
            }
    
            console.log("processing");
        }
    xhr.open("POST", "http://localhost:9001/Reimbursement/reimburse");
    xhr.send(JSON.stringify(reimbursementTemplate));
    
    }
  

}
function goBack()
{
    window.location = "http://localhost:9001/Reimbursement/home.html";
}
$(document).on('keyup', 'input[name=quantity]', function() {
    var _this = $(this);
    var min = parseInt(_this.attr('min')) || 1; // if min attribute is not defined, 1 is default
    var max = parseInt(_this.attr('max')) || 100; // if max attribute is not defined, 100 is default
    var val = parseInt(_this.val()) || (min - 1); // if input char is not a number the value will be (min - 1) so first condition will be true
    if (val < min)
      _this.val(min);
    if (val > max)
      _this.val(max);
  });
