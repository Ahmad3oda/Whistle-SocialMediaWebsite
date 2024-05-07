{/* <div class="profile-photo">
			<img src="${avatarURL}">
		</div>

		 */}
const getNotificationTemplate = (notification) => {
	// const { user, avatarURL, action, time } = notification;
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

// const getMiddleTemplte = () => {
// 	return `
		
// 	`;
// };


const getFeedTemplate = (feed) => {
	const time = new Date(feed.post_time).toLocaleString();
	return `
		<div class="feed">
			<div class="head">
				<div class="user">
					<div class="profile-photo">
						<img src="${feed.avatarURL}">
					</div>
					<div class="ingo">
						<h3>${feed.username}</h3>
						<small> ${feed.postDate}</small>
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

// const getMessageTemplate = (msg) => {
// 	const { user, avatarURL, message } = msg;
// 	return `
// 	<div class="message">
// 		<div class="profile-photo">
// 			<img src="${avatarURL}">
// 		</div>
// 		<div class="message-body">
// 			<h5>${user}</h5>
// 			<p class="text-muted">${message}</p>
// 		</div>
// 	</div>
// 	`;
// };

const getRequestTemplate = (request) => {
	// const { user, avatarURL, mutual_friends } = request;
	const avatarURL="adg/afd";
	return `
	<div class="request">
		<div class="info">
			<div class="profile-photo">
				<img src="${avatarURL}">
			</div>
			<div>
				<h5>${request}</h5>
			</div>
		</div>
		<div class="action">
			<button class="btn btn-primary acceptRequest" id=${request}>
				Accept
			</button>
			<button class="btn declineRequest" id=${request}>
				Decline
			</button>
		</div>
	</div>
	`;
};