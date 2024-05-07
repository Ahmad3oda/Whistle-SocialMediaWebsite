const get = (target) => {
	return document.querySelector(target);
};
const getAll = (target) => {
	return document.querySelectorAll(target);
};



const insertTemplate = ({ data = undefined, templateFunc, className }) => {
	const template = data
		.reverse()
		.map((d) => templateFunc(d))
		.join("");
	const elementHTML = document.querySelector(className);
	elementHTML.innerHTML = template;
};

const createImageContainer = (img) => {
	const url = URL.createObjectURL(img);
	const image = document.createElement("img");
	image.src = url;
	const removeIcon = document.createElement("span");
	removeIcon.innerHTML = `<i class="uil uil-multiply"></i>`;
	const div = document.createElement("div");
	div.appendChild(image);
	div.appendChild(removeIcon);
	return { element: div, imgURL: url, removeIcon };
};