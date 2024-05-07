


// fetch("http://localhost:8060/post/self/4")
// 	.then(response => response.json())
// 	.then((data)=> data.reverse()
// 	.forEach(element => {
//         console.log(element)
// 		const template = getFeedTemplate(element);
// 		const elementHTML = document.querySelector(".feeds");
// 		elementHTML.innerHTML += template;
// 	}));

fetch("http://localhost:8060/post/self/4")
	.then(response => response.json())
	.then((data)=> data.reverse()
	.forEach(element => {
        console.log(element)
		const template = getFeedTemplate(element);
		const elementHTML = document.querySelector(".feeds");
		elementHTML.innerHTML += template;
	}));

	// fetch("http://localhost:8085/like/getAll/20")
	// .then(response => response.json())
	// .then((data) => console.log(data));

// fetch("http://localhost:8060/post/self/1")
// 	.then(response => response.json())
// 	.then((data) => console.log(data));

