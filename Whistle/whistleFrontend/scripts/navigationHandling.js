
let profile = document.querySelector(".profile");
profile.onclick = function(){
    window.location.href="profile.html";
}


const changeActiveObj = (items) => {
	if(window.location.href=="http://127.0.0.1:5500/bookmarks.html")
		items[2].classList.add("active");
	else if(window.location.href=="http://127.0.0.1:5500/index.html")
		items[0].classList.add("active");
	else if(window.location.href=="http://127.0.0.1:5500/search.html")
		items[4].classList.add("active");
	else if(window.location.href=="http://127.0.0.1:5500/settings.html")
		items[5].classList.add("active");
};

const changeWindow = (items) => {
	items[0].onclick=function(){
		window.location.href="index.html";
	}
	items[2].onclick=function(){
		window.location.href="bookmarks.html";
	}
	items[4].onclick=function(){
		window.location.href="search.html";
	}
	items[5].onclick=function(){
		window.location.href="settings.html";
	}
}






changeActiveObj(getAll(".menu-item"));
changeWindow(getAll(".menu-item"));