
const getNotificationTemplate = (notification) => {
	
	const avatarURL="";
	return `
	<div>
		
		<div class="notification-body" style="width:100%;padding:10px">
			<b>${notification.content}</b>
			<hr style="height: 1px;background: #8d41e852;margin-top:10px">
		</div>
		
	</div>
	`;
};



const getFeedTemplate = (feed) => {
	const time = new Date(feed.post_time).toLocaleString();
	return `
		<div class="feed">
			<div class="head">
				<div class="user">
					<div class="profile-photo">
						<img src="images/image.jpeg">
					</div>
					<div class="ingo">
						<h3>${feed.username}</h3>
						<small> ${feed.postDate.split("T")[0]}</small>
					</div>
				</div>
				<span class="more">
					<i class="uil uil-ellipsis-h"></i>
				</span>
			</div>	
			<div class="caption">
				<p>
					${feed.content} 
				</p>
			</div>
			<div class="action-buttons">
				<div class="interaction-buttons">
					<span class="like like-${feed.postId}" likeBtn=${feed.postId}><i class="uil uil-heart"></i></span>
					<span><i class="uil uil-comment-dots"></i></span>		
				</div>
				<div class="bookmark">
					<span><i class="uil bookmarkAction uil-bookmark pressed bookmark-${feed.postId}" bookmarkBtn=${feed.postId}></i></span>
				</div>
			</div>
			<div class="liked-by">
				<p>Liked by <b class="number-of-likes-${feed.postId}">${feed.likesNumber}</b><b> Users</p>
			</div>
			<div class="show-comments text-muted">View all ${feed.commentsNumber} comments</div>
		</div>
	`;
};


const getRequestTemplate = (request) => {
	// const { user, avatarURL, mutual_friends } = request;
	const avatarURL="adg/afd";
	return `
	<div class="request">
		<div class="info">
			<div class="profile-photo">
			<img src="images/image.jpeg">
			</div>
			<div>
				<h5>${request.name}</h5>
			</div>
		</div>
		<div class="action">
			<button class="btn btn-primary acceptRequest" acceptBtn=${request.id}>
				Accept
			</button>
			<button class="btn declineRequest" declineBtn=${request.id}>
				Decline
			</button>
		</div>
	</div>
	`;
};


const getSuggestionsTemplate = (suggestion) => {
	console.log(suggestion)
	// const { user, avatarURL, mutual_friends } = request;
	const avatarURL="adg/afd";
	return `
	<div class="request">
		<div class="info">
			<div class="profile-photo">
				<img src="images/image.jpeg">
			</div>
			<div>
				<h5>${suggestion.name}</h5>
			</div>
		</div>
		<div class="action">
			<button class="btn btn-primary acceptSuggestion" acceptBtn=${suggestion.id}>
				Add Friend
			</button>
			
		</div>
	</div>
	`;
};


const getSearchTemplate = (search) =>{
	return `
	<div class="request searchRes" style="width:80%" userId=${search.id}>
			<div style="width:100% ;text-align:center" >
				<div  style=" margin: auto; width: 80px; border-radius: 50%; overflow: hidden; margin-bottom: 10px;">
					<img src="images/image.jpeg" width="100%">
				</div>
				<div>
					<h5>${search.firstName + " " + search.lastName}</h5>
				</div>
			</div>
		</div>

	`
}