const notificationsUrl = "http://localhost:8060/notification/user/1";


fetch(notificationsUrl)
	.then(response => response.json())
	.then((data)=> data.reverse()
	.forEach(element => {
		const template = getNotificationTemplate(element);
		const elementHTML = document.querySelector(".notifications");
		elementHTML.innerHTML += template;
	}));


	// fetch("http://localhost:8085/like/getAll/20")
	// .then(response => response.json())
	// .then((data) => console.log(data));

fetch("http://localhost:8060/post/self/1")
	.then(response => response.json())
	.then((data) => console.log(data));




// Now, call the function to fetch data and store the Promise in the array
// const url = 'http://localhost:8181/getUser';
// 
// let data = {
// 	name: 'Sammy'
// }
//
// let request = new Request(url, 
// 	method: 'POST',
// 	body: JSON.stringify(data),
// 	headers: new Headers({
// 		'Content-Type': 'application/json; charset=UTF-8'
// 	})
// });
//
// fetch(request)
// 	.then((response) =>{
// 		return response.text();
// 	}).then((text) => {
// 	console.log(text); // access the text data here
// })



// const feeds = [
// 	{
// 		user: "Emily Smith",
// 		avatarURL: "./images/profile-4.jpg",
// 		caption:
// 			"Lorem ipsum dolor sit amet consectetur adipisicing elit. #lifestyle",
// 		photoURL: "./images/feed-3.jpg",
// 		likes: 235,
// 		location: "Seattle, WA",
// 		post_time: "2024-01-11T13:15:00",
// 		tags: ["lifestyle"],
// 	},
// 	{
// 		user: "John Doe",
// 		avatarURL: "./images/profile-5.jpg",
// 		caption: "Sunny day in the park! 🌳☀️ #nature #sunnyday",
// 		photoURL: "./images/feed-1.jpg",
// 		likes: 123,
// 		location: "New York, NY",
// 		post_time: "2024-01-12T10:30:00",
// 		tags: ["nature", "sunnyday"],
// 	},
// 	{
// 		user: "Sarah Davis",
// 		avatarURL: "./images/profile-8.jpg",
// 		caption: "Enjoying a cup of coffee at my favorite spot. #coffee #relax",
// 		photoURL: "./images/feed-2.jpg",
// 		likes: 156,
// 		location: "San Francisco, CA",
// 		post_time: "2024-01-13T08:45:00",
// 		tags: ["coffee", "relax"],
// 	},
// 	{
// 		user: "Michael Johnson",
// 		avatarURL: "./images/profile-3.jpg",
// 		caption: "Exciting weekend getaway! 🏞️ #travel #adventure",
// 		photoURL: "./images/feed-4.jpg",
// 		likes: 189,
// 		location: "Los Angeles, CA",
// 		post_time: "2024-01-14T15:20:00",
// 		tags: ["travel", "adventure"],
// 	},
// 	{
// 		user: "Olivia Taylor",
// 		avatarURL: "./images/profile-7.jpg",
// 		caption: "Movie night with friends! 🍿🎬 #movies #friends",
// 		photoURL: "./images/feed-5.jpg",
// 		likes: 200,
// 		location: "Chicago, IL",
// 		post_time: "2024-01-15T19:00:00",
// 		tags: ["movies", "friends"],
// 	},
// 	{
// 		user: "Christopher Brown",
// 		avatarURL: "./images/profile-2.jpg",
// 		caption: "New workout routine, feeling energized! 💪 #fitness #health",
// 		photoURL: "./images/feed-6.jpg",
// 		likes: 145,
// 		location: "Dallas, TX",
// 		post_time: "2024-01-16T07:00:00",
// 		tags: ["fitness", "health"],
// 	},
// 	{
// 		user: "Emma Miller",
// 		avatarURL: "./images/profile-9.jpg",
// 		caption: "Cooking up a storm in the kitchen! 🍳🔥 #cooking #foodie",
// 		photoURL: "./images/feed-7.jpg",
// 		likes: 178,
// 		location: "Miami, FL",
// 		post_time: "2024-01-17T12:45:00",
// 		tags: ["cooking", "foodie"],
// 	},
// 	{
// 		user: "William Anderson",
// 		avatarURL: "./images/profile-4.jpg",
// 		caption:
// 			"Art gallery visit, mesmerized by the paintings. #art #gallery",
// 		photoURL: "./images/feed-1.jpg",
// 		likes: 210,
// 		location: "Denver, CO",
// 		post_time: "2024-01-18T16:30:00",
// 		tags: ["art", "gallery"],
// 	},
// 	{
// 		user: "Ava Wilson",
// 		avatarURL: "./images/profile-6.jpg",
// 		caption: "Sunday brunch with a view! 🍹🌆 #brunch #cityscape",
// 		photoURL: "./images/feed-2.jpg",
// 		likes: 167,
// 		location: "Austin, TX",
// 		post_time: "2024-01-19T11:00:00",
// 		tags: ["brunch", "cityscape"],
// 	},
// 	{
// 		user: "Benjamin Martinez",
// 		avatarURL: "./images/profile-1.jpg",
// 		caption: "Tech conference vibes! 🚀💻 #tech #conference",
// 		photoURL: "./images/feed-3.jpg",
// 		likes: 198,
// 		location: "San Jose, CA",
// 		post_time: "2024-01-20T14:15:00",
// 		tags: ["tech", "conference"],
// 	},
// 	{
// 		user: "Sophia Garcia",
// 		avatarURL: "./images/profile-11.jpg",
// 		caption: "Chill evening with good music! 🎶🍷 #music #chill",
// 		photoURL: "./images/feed-4.jpg",
// 		likes: 180,
// 		location: "Portland, OR",
// 		post_time: "2024-01-21T18:45:00",
// 		tags: ["music", "chill"],
// 	},
// 	{
// 		user: "Mason White",
// 		avatarURL: "./images/profile-12.jpg",
// 		caption: "Exploring hidden gems in the city. #exploration #citylife",
// 		photoURL: "./images/feed-5.jpg",
// 		likes: 220,
// 		location: "Phoenix, AZ",
// 		post_time: "2024-01-22T09:30:00",
// 		tags: ["exploration", "citylife"],
// 	},
// ];

const messages = [
	{
		avatarURL: "./images/profile-4.jpg",
		user: "Edem Quist",
		message: "Just woke up bruh",
	},
	{
		avatarURL: "./images/profile-5.jpg",
		user: "Luna Rose",
		message: "Got it! Thanks!",
	},
	{
		avatarURL: "./images/profile-6.jpg",
		user: "Linda Sulei",
		message: "Wdym",
	},
	{
		avatarURL: "./images/profile-7.jpg",
		user: "Ema Thomus",
		message: "2 new messages",
	},
	{
		avatarURL: "./images/profile-8.jpg",
		user: "Jone Tom",
		message: "ok",
	},
	{
		avatarURL: "./images/profile-9.jpg",
		user: "Adam Tomas",
		message: "Congratulation!",
	},
];

// const requests = [
// 	{
// 		avatarURL: "./images/profile-17.jpg",
// 		user: "Hajia Bintu",
// 		mutual_friends: "8",
// 	},
// 	{
// 		avatarURL: "./images/profile-18.jpg",
// 		user: "Jackline Mensah",
// 		mutual_friends: "2",
// 	},
// 	{
// 		avatarURL: "./images/profile-19.jpg",
// 		user: "Jennifer Lawrence",
// 		mutual_friends: "0",
// 	},
// ];