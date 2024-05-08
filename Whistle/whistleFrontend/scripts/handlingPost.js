const noAction = '<i class="uil uil-heart"></i>';
const liked = '<img src="./images/heart.png"/>';
function likeHandling(element){
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
}

function likeButtonInput(){
    let likes = document.querySelectorAll('.like');
likes.forEach(heart=>{
  heart.addEventListener("click", () => {
    likesNumberDoc=document.querySelector(`.number-of-likes-${heart.getAttribute('likeBtn')}`);
  if (heart.innerHTML === noAction) {
    heart.innerHTML = liked;
    likesNumber = parseInt(likesNumberDoc.innerText);
    likesNumberDoc.innerText=++likesNumber;
    fetch(`http://localhost:8060/like/add/${parseJwt().id}/${heart.getAttribute('likeBtn')}`)
  } else {
    fetch(`http://localhost:8060/like/delete/${parseJwt().id}/${heart.getAttribute('likeBtn')}`)

    likesNumber = parseInt(likesNumberDoc.innerText);
    likesNumberDoc.innerText=--likesNumber;
    heart.innerHTML = noAction;
  }
  // window.location.reload();
});
})
}


function bookmarkHandling(){
    let bookmarkAction;
    bookmarkAction = document.querySelectorAll(".bookmarkAction");
const myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");
    bookmarkAction.forEach(bookmarkedPost =>{
        const raw = {
        postId: parseInt(bookmarkedPost.getAttribute('bookmarkBtn')),
        userId: parseJwt().id
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
            fetch(`http://localhost:8060/bookmark/deleteBookmark/${parseJwt().id}/${bookmarkedPost.getAttribute(`bookmarkBtn`)}`);
        }
    
    })
    })
}