fetch(`http://localhost:8060/post/bookmarks/${parseJwt().id}`)
	.then(response => response.json())
	.then((data)=> {data.forEach(element => {
		const template = getFeedTemplate(element);
		const elementHTML = document.querySelector(".feeds");
		elementHTML.innerHTML += template;
    likeHandling(element);
    likeButtonInput();
    bookmarkHandling();
	});

  let bookmarkAction;
  bookmarkAction = document.querySelectorAll(".bookmarkAction");


  bookmarkAction.forEach(bookmarkedPost =>{
    bookmarkedPost.addEventListener('click', function() {
      fetch(`http://localhost:8060/bookmark/deleteBookmark/${parseJwt().id}/${bookmarkedPost.id}`)
          .then(response => console.log(response))
          .then(window.location.reload());
  })
  })
  }
);