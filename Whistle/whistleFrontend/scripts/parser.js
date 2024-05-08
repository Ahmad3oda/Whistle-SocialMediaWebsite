function parseJwt() {
    token = sessionStorage.getItem('token');
    if(!token)
        window.location.href="login";
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    let data = JSON.parse(jsonPayload).sub;
    let splitedData = data.split(' ');
    let tokenParts = {
        id:splitedData[0],
        email:splitedData[1],
    }
    return tokenParts;
}