fetch("http://localhost:8060/post/bookmarks/1")
	.then(response => response.json())
	.then((data)=> {data.forEach(element => {
		const template = getFeedTemplate(element);
		const elementHTML = document.querySelector(".feeds");
		elementHTML.innerHTML += template;

	});

  let bookmarkAction;
  bookmarkAction = document.querySelectorAll(".bookmarkAction");



  bookmarkAction.forEach(bookmarkedPost =>{
    bookmarkedPost.addEventListener('click', function() {
      fetch(`http://localhost:8060/bookmark/deleteBookmark/1/${bookmarkedPost.id}`)
          .then(response => console.log(response))
          .then(window.location.reload());
  })
  })
  }
);