fetch(`http://localhost:8060/friends/requests/${parseJwt().id}`)
    .then(response => response.json())
    .then((data) => {
        data.forEach(element => {
            const template = getRequestTemplate(element);
            const elementHTML = document.querySelector(".requests");
            elementHTML.innerHTML += template;
        });

        const requestOptions = {
                method: "GET",
                redirect: "follow"
            };
        // Find all accept buttons after rendering requests
        const acceptRequestButtons = document.querySelectorAll(".acceptRequest");
        const declineRequestButtons = document.querySelectorAll(".declineRequest");

        // Add event listener to each accept button
        acceptRequestButtons.forEach(button => {
            button.addEventListener("click", function() {
                fetch(`http://localhost:8060/friends/request/${button.getAttribute('acceptBtn')}/${parseJwt().id}`, requestOptions)
                    .then(window.location.reload())
            });
        });

		declineRequestButtons.forEach(button => {
            button.addEventListener("click", function() {
                fetch(`http://localhost:8060/friends/request/delete/${button.getAttribute('declineBtn')}/${parseJwt().id}`, requestOptions)
                    .then(window.location.reload())
            });
        });
    });