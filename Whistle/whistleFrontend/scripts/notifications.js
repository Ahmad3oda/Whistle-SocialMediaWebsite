const notificationsUrl = `http://localhost:8060/notification/user/${parseJwt().id}`;

fetch(notificationsUrl)
	.then(response => response.json())
	.then((data)=> data.reverse()
	.forEach(element => {
		const template = getNotificationTemplate(element);
		const elementHTML = document.querySelector(".notifications");
		elementHTML.innerHTML += template;
	}));


fetch(`http://localhost:8060/post/self/${parseJwt().id}`)
	.then(response => response.json())
	.then((data) => console.log(data));
