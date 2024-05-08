let registerButton = document.querySelector("#registerButton");
registerButton.addEventListener("click", function(event) {
    event.preventDefault();
    let usernameInput = document.querySelector("#username").value;
    let passwordInput = document.querySelector("#password").value;
    let firstNameInput = document.querySelector("#firstName").value;
    let lastNameInput = document.querySelector("#lastName").value;
    if(checkInputs(usernameInput, passwordInput)){
        const data = {
            email:usernameInput,
            password:passwordInput,
            firstName:firstNameInput,
            lastName:lastNameInput
        }

        const myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");
        const raw = JSON.stringify(data);

        const requestOptions = {
            method: "POST",
            headers: myHeaders,
            body: raw,
            redirect: "follow"
        };

        fetch("http://localhost:8060/security/register", requestOptions)
        .then((response) => {
            if(response.status>=400 && response.status<=499 ){
                makeWarning("That email is already taken. please try another one")
                throw new Error("Authentication failed");
            }
            else 
                window.location.href="login.html";
        })
        .catch((error) => console.error(error));
    }
});


function checkInputs(email, password){
    const passwordRegex = new RegExp('.{8,}');
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    

    if(email.length==0 || password.length == 0){
        makeWarning("Please fill all the fields")
        return false;
    }
    else if(!emailRegex.test(email)){
        makeWarning("Please enter a valid email")
        return false;
    }
    else if(!passwordRegex.test(password)){
        makeWarning("Password should contain at least 8 characters")
        return false;
    }
    return true;
}

function makeWarning(message){
    let warning = document.querySelector(".warning");
    warning.innerHTML=message;
    warning.classList.add("visible")
}