
let submitLogin = document.querySelector(".submitLogin");
submitLogin.addEventListener("click", function(event) {
    event.preventDefault();
    let usernameInput = document.querySelector("#username").value;
    let passwordInput = document.querySelector("#password").value;
    const data = {
        username:usernameInput,
        password:passwordInput
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

    fetch("http://localhost:8060/security/login", requestOptions)
    .then((response) => {
        if(response.status>=400 && response.status<=499 ){
            let warning = document.querySelector(".warning");
            if(usernameInput==""||passwordInput=="")
                warning.innerHTML="Please fill all the data";
            else
                warning.innerHTML="Wrong Email or Password";
            warning.classList.add("visible");
            throw new Error("wrong data");
        }
        return response.text()
    })
    .then((result) => {
        sessionStorage.setItem('token', result); // Store the token in local storage
        // Decode and extract data from the JWT token
        return decodedToken = parseJwt(result);
    }).then((decodedToken) =>{
        window.location.href="index.html";
} )
    .catch((error) => console.error(error));

});


