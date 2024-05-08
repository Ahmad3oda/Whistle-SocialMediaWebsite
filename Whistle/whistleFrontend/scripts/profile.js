

// function handleProfilePage(url){
	if(sessionStorage.getItem('searchedUser')==null){
	fetch(`http://localhost:8060/post/self/${parseJwt().id}`)
	.then(response => response.json())
	.then((data)=> {data.forEach(element => {
		const template = getFeedTemplate(element);
		const elementHTML = document.querySelector(".feeds");
		elementHTML.innerHTML += template;
		likeHandling(element);
	});
	likeButtonInput();
	bookmarkHandling();
	}
);
}

else{
	let addFriendBtn = document.createElement("button")
	addFriendBtn.innerHTML='ADD Friend';
	addFriendBtn.id=sessionStorage.getItem('searchedUser')
	addFriendBtn.style.cssText="padding:6px; border-radius:5px;background-color:#7358de;color:white"
	let logoutBtn = document.querySelector('.logout')
	let container = document.querySelector('.container');
	
	addFriendBtn.onclick = function (){
		fetch(`http://localhost:8060/friends/request/${parseJwt().id}/${addFriendBtn.id}`, {
			method: "POST",
			redirect: "follow"
		}).then(response => 	addFriendBtn.innerHTML='Sent')
		.catch(error =>console.log(error));
	}
	container.insertBefore(addFriendBtn,logoutBtn);
	fetch(`http://localhost:8060/post/self/${sessionStorage.getItem('searchedUser')}`)
	.then(response => response.json())
	.then((data)=> {data.forEach(element => {
		const template = getFeedTemplate(element);
		const elementHTML = document.querySelector(".feeds");
		elementHTML.innerHTML += template;
		likeHandling(element);
	});
	likeButtonInput();
	bookmarkHandling();
	sessionStorage.removeItem('searchedUser');
	}
);
}