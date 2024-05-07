const myHeaders = new Headers();

// myHeaders.append('Authorization', 'Bearer ' + sessionStorage.getItem('token'));



const requestOptions = {
  method: "GET",
  redirect: "follow"
};
const noAction = '<i class="uil uil-heart"></i>';
  const liked = '<img src="./images/heart.png"/>';
// the same on profile page but get only self posts with this link "http://localhost:8060/post/self/1"
fetch("http://localhost:8060/post/friends/1")
	.then(response => response.json())
	.then((data)=> {data.forEach(element => {
		const template = getFeedTemplate(element);
    const elementHTML = document.querySelector(".feeds");
		elementHTML.innerHTML += template;
    if(element.liked){
      likedPost=document.querySelector(`.like-${element.postId}`)/////////////////////
      likedPost.innerHTML=liked;  
      
    }
    else{
      likedPost=document.querySelector(`.like-${element.postId}`)/////////////////////
      likedPost.innerHTML=noAction;  
    }

    if(element.bookMarked){
      bookmarkedPost = document.querySelector(`.bookmark-${element.postId}`);
      bookmarkedPost.style.color="red";
    }
    else{
      bookmarkedPost = document.querySelector(`.bookmark-${element.postId}`);
      bookmarkedPost.style.color="black";
    }

	});

  let bookmarkAction;
  bookmarkAction = document.querySelectorAll(".bookmarkAction");

  const myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

let likes = document.querySelectorAll('.like');
likes.forEach(heart=>{
  heart.addEventListener("click", () => {
    likesNumberDoc=document.querySelector(`.number-of-likes-${heart.getAttribute('likeBtn')}`);
  if (heart.innerHTML === noAction) {
    heart.innerHTML = liked;
    likesNumber = parseInt(likesNumberDoc.innerText);
    likesNumberDoc.innerText=++likesNumber;
    fetch(`http://localhost:8060/like/add/1/${heart.getAttribute('likeBtn')}`)
  } else {
    fetch(`http://localhost:8060/like/delete/1/${heart.getAttribute('likeBtn')}`)

    likesNumber = parseInt(likesNumberDoc.innerText);
    likesNumberDoc.innerText=--likesNumber;
    heart.innerHTML = noAction;
  }
  // window.location.reload();
});
})



  bookmarkAction.forEach(bookmarkedPost =>{
    console.log(bookmarkedPost.style.color)
    const raw = {
      postId: parseInt(bookmarkedPost.getAttribute('bookmarkBtn')),
      userId: 1
    };
    // console.log(raw);
    const requestOptions = {
      method: "POST",
      headers: myHeaders,
      body:JSON.stringify(raw),
      redirect: "follow"
    };

    bookmarkedPost.addEventListener('click', function() {
      if(bookmarkedPost.style.color=="black"){
        bookmarkedPost.style.color="red";
      fetch(`http://localhost:8060/bookmark/addBookmark`,requestOptions)

      }
        
      else{
        bookmarkedPost.style.color="black";
        fetch(`http://localhost:8060/bookmark/deleteBookmark/1/${bookmarkedPost.getAttribute(`bookmarkBtn`)}`);
      }

  })
  })
  }
);


createPostButton = document.getElementById("createPost");

createPostButton.onclick = function () {

    var settings = {
        "url": "http://localhost:8060/post/post/1/asd/asfaf",
        "method": "GET",
        "timeout": 0,
      };
      
      $.ajax(settings).done(function (response) {
        console.log(response);
      });

    // requestOptions.method='POST';
    // //  myHeaders.append("Content-Type","application/json");
    // let postContent = document.getElementById("create-post");
    // // delete requestOptions.headers;
    // let data ={
    //     ownerId:4,
    //     content:postContent.value,
    //     imagePath:'agadg'
    // }
    // const raw = JSON.stringify(data);
    // requestOptions.body= raw;
    // console.log(data);
    
    // fetch('http://localhost:8060/post/post', requestOptions)
    // .then((response) => response)
    // .then((result) => console.log(result))
    // .catch((error) => console.error(error));
}



