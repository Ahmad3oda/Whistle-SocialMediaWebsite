fetch("http://localhost:8060/friends/requests/1")
    .then(response => response.json())
    .then((data) => {
        data.reverse().forEach(element => {
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
                fetch(`http://localhost:8060/friends/request/${button.id}/1`, requestOptions)
                    .then(response => response.json())
                    .then(window.location.reload());
            });
        });


		declineRequestButtons.forEach(button => {
            button.addEventListener("click", function() {
                fetch(`http://localhost:8060/friends/request/delete/${button.id}/1`, requestOptions)
                    .then(response => response.json())
                    .then(window.location.reload());
            });
        });
    });