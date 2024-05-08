    let logout = document.querySelector('.logout');
    console.log(logout)
    logout.onclick= function (){
        sessionStorage.clear();
        window.location.href="login.html";
    }