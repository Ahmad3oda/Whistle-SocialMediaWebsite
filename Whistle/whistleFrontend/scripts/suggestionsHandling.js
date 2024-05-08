fetch(`http://localhost:8060/friends/suggestions/${parseJwt().id}`)
    .then(response => response.json())
    .then((data) => {
        data.forEach(element => {
            const template = getSuggestionsTemplate(element);
            const elementHTML = document.querySelector(".suggestion");
            elementHTML.innerHTML += template;
        });

        const requestOptions = {
                method: 'POST',
                redirect: "follow"
            };
        // Find all accept buttons after rendering requests
        const addSuggestionButtons = document.querySelectorAll(".acceptSuggestion");


        // Add event listener to each accept button
        addSuggestionButtons.forEach(button => {
            button.addEventListener("click", function() {
                fetch(`http://localhost:8060/friends/request/${parseJwt().id}/${button.getAttribute('acceptBtn')}`, requestOptions)
                    .then(window.location.reload())
            });
        });

    });