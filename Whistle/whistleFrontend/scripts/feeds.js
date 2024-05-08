const myHeaders = new Headers();

// myHeaders.append('Authorization', 'Bearer ' + sessionStorage.getItem('token'));



const requestOptions = {
  method: "GET",
  redirect: "follow"
};

// the same on profile page but get only self posts with this link "http://localhost:8060/post/self/1"
fetch(`http://localhost:8060/post/friends/${parseJwt().id}`)
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


createPostButton = document.getElementById("createPost");
postText= document.getElementById("create-post");
createPostButton.onclick = function () {

    var settings = {
        "url": `http://localhost:8060/post/post/${parseJwt().id}/${postText.value}/a`,
        "method": "GET",
        "timeout": 0,
      };
      
      $.ajax(settings).done(function (response) {
        console.log(window.location.reload());
      });

}



