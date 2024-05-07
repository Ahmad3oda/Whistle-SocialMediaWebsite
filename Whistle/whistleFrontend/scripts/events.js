


const addEventListeners = () => {
	// --------------------Menu Items Activation Handling--------------------
	const menuItems = getAll(".menu-item");

	menuItems.forEach((menuItem, idx) => {
		menuItem.addEventListener("click", () => {
			// changeActiveObj(menuItems, idx);

			// --------------------Toggle Notification Pannel--------------------
			if (menuItem.id !== "notification-menu-item") {
				get(".notifications-popup").classList.remove("active");
			} else {
				get(".notifications-popup").classList.add("active");
			}

			if (menuItem.id === "home" || menuItem.id === "explore") {
				get(".middle").className = "middle active";
				get(".right").className = "right";
			}

			if (menuItem.id === "explore") {
				get("#explore-search").classList.add("active");
				if (window.innerWidth > 992) {
					get("#navbar-search").focus();
					get(".search-bar").style.boxShadow =
						"0 0 1rem var(--color-primary)";
					setTimeout(() => {
						get(".search-bar").style.boxShadow = "none";
					}, 2000);
				} else {
					get("#search").focus();
					get("#explore-search").style.boxShadow =
						"0 0 1rem var(--color-primary)";
					setTimeout(() => {
						get("#explore-search").style.boxShadow = "none";
					}, 2000);
				}
			} else {
				get("#explore-search").classList.remove("active");
			}
		});
	});

	// // ---------------------------Messages---------------------------
	// const msgNotif = get("#messages-notifications");
	// msgNotif.addEventListener("click", () => {
	// 	get(".middle").className = "middle";
	// 	get(".right").className = "right active";
	// 	msgNotif.querySelector(".notification-count").style.display = "none";
	// 	if (window.innerWidth > 992) {
	// 		get(".messages").style.boxShadow = "0 0 1rem var(--color-primary)";
	// 		setTimeout(() => {
	// 			get(".messages").style.boxShadow = "none";
	// 		}, 2000);
	// 	}
	// });

	// // search for messages
	// get("#messages").addEventListener("keyup", (evt) => {
	// 	getAll(".message").forEach((msg) => {
	// 		const user = msg.querySelector("h5").innerText.toLowerCase();
	// 		if (user.includes(evt.target.value.toLowerCase())) {
	// 			msg.style.display = "flex";
	// 		} else {
	// 			msg.style.display = "none";
	// 		}
	// 	});
	// });

	// --------------------Toggle Sidebar on Mobile Screen--------------------
	const menuToggler = get(".menu-toggle");
	menuToggler.addEventListener("click", () => {
		if (menuToggler.className === "menu-toggle") {
			get(".left").classList.add("active");
			menuToggler.classList.add("active");
		} else {
			get(".left").classList.remove("active");
			menuToggler.classList.remove("active");
			get(".notifications-popup").classList.remove("active");
		}
	});

	// --------------------Toggle Theme Customized Pannel--------------------
	get("#theme-menu-item").addEventListener("click", () => {
		get(".customize-theme").style.display = "grid";
	});

	get(".close-theme").addEventListener("click", () => {
		get(".customize-theme").style.display = "none";
	});

	get(".customize-theme").addEventListener("click", (evt) => {
		if (evt.target.className === "customize-theme") {
			get(".customize-theme").style.display = "none";
		}
	});

	// --------------------Handle Theme Customization--------------------
	const fontSizes = get(".choose-size").querySelectorAll("span");
	const colors = get("#choose-primary-color").querySelectorAll("span");
	const btnColors = get("#choose-btn-color").querySelectorAll("span");
	const bgs = get(".choose-bg").querySelectorAll("div");

	// -----Handle Font Size Change-----
	const initFontSize = 14;
	fontSizes.forEach((size, idx) => {
		size.addEventListener("click", () => {
			changeActiveObj(fontSizes, idx);
			get("html").style.fontSize = `${initFontSize + idx}px`;
		});
	});

	// -----Handle Color Change-----
	const hslHues = [252, 52, 352, 152, 202];
	colors.forEach((color, idx) => {
		color.addEventListener("click", () => {
			changeActiveObj(colors, idx);
			get(":root").style.setProperty("--primary-color-hue", hslHues[idx]);
		});
	});

	// -----Handle Button Color Change-----
	btnColors.forEach((color, idx) => {
		color.addEventListener("click", () => {
			changeActiveObj(btnColors, idx);
			get(":root").style.setProperty(
				"--btn-primary-color-hue",
				hslHues[idx]
			);
		});
	});

	// -----Handle Background Change-----
	const rootColorVars = ["--color-card", "--color-bg", "--color-font"];
	const bgColors = [
		["100%", "95%", "17%"],
		["20%", "15%", "90%"],
		["10%", "0%", "90%"],
	];

	const changeBackground = (rootVar, color) => {
		if (rootVar.length !== color.length) return;
		rootVar.forEach((vari, idx) => {
			get(":root").style.setProperty(vari, color[idx]);
		});
	};

	bgs.forEach((bg, idx) => {
		bg.addEventListener("click", () => {
			changeActiveObj(bgs, idx);
			changeBackground(rootColorVars, bgColors[idx]);
		});
	});

	// ----------------------Handle Like Event-----------------------
	// const addLikeEvyListener = () => {
	// 	console.log("ddddddd")
	// 	const numbers = getAll(".number-of-likes");
	// 	let s= getAll(".like")
	// 	console.log(s)
	// 	getAll(".like").forEach((heart, idx) => {
	// 		console.log("aaaaaaaaaa")
	// 		heart.addEventListener("click", () => {
	// 			const noAction = '<i class="uil uil-heart"></i>';
	// 			const liked = '<img src="./images/heart.png"/>';
	// 			if (heart.innerHTML === noAction) {
	// 				heart.innerHTML = liked;
	// 				numbers[idx].innerText = `${
	// 					parseInt(numbers[idx].innerText) + 1
	// 				} others`;
	// 			} else {
	// 				heart.innerHTML = noAction;
	// 				numbers[idx].innerText = `${
	// 					parseInt(numbers[idx].innerText) - 1
	// 				} others`;
	// 			}
	// 		});
	// 	});
	// };
	// addLikeEvyListener();

	// ----------------------Handle Search Event-----------------------
	// getAll(".feeds-search").forEach((searchBar) => {
	// 	const inputEl = searchBar.querySelector("input");
	// 	const selectEl = searchBar.querySelector("select");
	// 	inputEl.addEventListener("keyup", (evt) => {
	// 		get(".create-post").style.display =
	// 			evt.target.value === "" ? "flex" : "none";

	// 		getAll(".feed").forEach((feed) => {
	// 			const creator = feed
	// 				.querySelector("h3")
	// 				.innerText.toLowerCase();
	// 			let tags = "";
	// 			feed.querySelectorAll(".hash-tag").forEach((tag) => {
	// 				tags += tag.innerText.toLowerCase();
	// 			});
	// 			const target = selectEl.value === "creator" ? creator : tags;

	// 			if (target.includes(evt.target.value.toLowerCase())) {
	// 				feed.style.display = "block";
	// 			} else {
	// 				feed.style.display = "none";
	// 			}
	// 		});
	// 	});
	// });

	// ----------------------Handle Create Post-----------------------
	// const initFeeds = {
	// 	user: "Diana Ayi",
	// 	avatarURL: "./images/profile-1.jpg",
	// 	caption: "",
	// 	photoURL: "",
	// 	location: "New York, NY",
	// 	likes: 0,
	// 	post_time: "",
	// 	tags: [],
	// };

	// let feedData = { ...initFeeds };

	getAll("label[for='create-post']").forEach((label) => {
		label.addEventListener("click", () => {
			get(".input-container").style.boxShadow =
				"0 0 1rem var(--color-primary)";
			setTimeout(() => {
				get(".input-container").style.boxShadow = "none";
			}, 2000);
		});
	});

	// get("#add-image").addEventListener("input", (evt) => {
	// 	const { element, imgURL, removeIcon } = createImageContainer(
	// 		evt.target.files[0]
	// 	);
	// 	const wrapper = document.createElement("div");
	// 	wrapper.className = "img-wrapper";
	// 	wrapper.appendChild(element);
	// 	get(".img-thumbnail").innerHTML = wrapper.outerHTML;
	// 	feedData = { ...feedData, photoURL: imgURL };

	// 	removeIcon.addEventListener("click", () => {
	// 		get(".img-thumbnail").removeChild(wrapper);
	// 	});
	// });

	get("#submit-post").addEventListener("submit", (evt) => {
		evt.preventDefault();
		const caption = get("#create-post").value;
		// unable to use the rule of /(?=#).../g, cause issue in mobile
		let tags = caption.match(/#[\w\d\p{Script=Han}_-]+/gu) || [];
		tags = tags.map((tag) => tag.replace("#", ""));
		feedData = { ...feedData, caption, tags };

		if (feedData.caption === "" ) {
			alert("Please type something! ");
		}
	});
};

/*--------------------------------- go to bookmarks-------------------------- */
let allBookmarks = document.querySelector(".allBookmarks");
allBookmarks.addEventListener('click', () => {
	window.location.href="bookmarks.html"
})