let searchBtn = document.querySelector('.searchBtn');
let searchInput = document.querySelector("#navbar-search");
const elementHTML = document.querySelector(".feeds");
searchBtn.onclick = function (){
    elementHTML.innerHTML="";
    let  searchInputText = searchInput.value;
    fetch(`http://localhost:8060/user-management/searchByName/${searchInputText}`)
    .then(response => response.json())
    .then((data) => {
        data.forEach(element => {
            const template = getSearchTemplate(element);
            elementHTML.innerHTML += template;
        })
        let searchResults = document.querySelectorAll('.searchRes');
        searchResults.forEach(button => {
            button.addEventListener("click", function() {
                let searchedUser = button.getAttribute('userId');
                sessionStorage.setItem('searchedUser',searchedUser);
                window.location.href="profile.html";
            });
        });


    });
}

