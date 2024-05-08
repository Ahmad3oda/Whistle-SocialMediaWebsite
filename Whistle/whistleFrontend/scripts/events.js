


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


	getAll("label[for='create-post']").forEach((label) => {
		label.addEventListener("click", () => {
			get(".input-container").style.boxShadow =
				"0 0 1rem var(--color-primary)";
			setTimeout(() => {
				get(".input-container").style.boxShadow = "none";
			}, 2000);
		});
	});



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