/*
async function login(event) {
    event.preventDefault();

    let form = document.querySelector('form');
    let email = form.elements["email"].value;
    let password = form.elements["password"].value;

    let data = {
        mail: email,
        mdp: password
    };

    console.log("Sending data to server:", data);

    try {
        let response = await post('http://localhost:8080/PollHub/rest/login/connexion', data);
        console.log("Response from server:", response);
        if (response.ok) {
            let responseData = await response.json();
            displayMessage(`Bonjour ${responseData.prenom} ${responseData.nom}`, 'success');
        } else {
            displayMessage('Identifiants incorrects ou compte inexistant.', 'error');
        }
    } catch (error) {
        console.error("Error during fetch:", error);
        displayMessage('Une erreur s\'est produite. Veuillez réessayer.', 'error');
    }
}

function displayMessage(message, type) {
    let alertElement = document.getElementById('alert');
    //alertElement.classList.remove('success', 'error');
    alertElement.innerHTML = message;
    //alertElement.classList.add(type);
}

function post(url, data) {
    console.log("Posting data to", url);
    console.log("Data:", data);
    return fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
}
*/
async function login(event) {
    event.preventDefault();
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    const data = {
        mail: email,
        mdp: password
    };

    console.log("Sending data to server:", data);

    try {
        const response = await fetch('http://localhost:8080/PollHub/rest/login/connexion', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        console.log("Response from server:", response);

        if (response.ok) {
            const responseData = await response.json();
            console.log("Response data:", responseData);
            localStorage.setItem('role', responseData.role); // verifier si mettre dans signup.js

            console.log("Role stored in localStorage:", localStorage.getItem('role'))
            alert('Connexion réussie. Bienvenue ' + responseData.prenom + ' ' + responseData.nom);
            window.location.href = 'home.html';
        } else if (response.status === 401 || response.status === 404) {
            const errorMessage = await response.text();
            alert(errorMessage);
        } else {
            throw new Error('Erreur de connexion');
        }
    } catch (error) {
        console.error('Erreur lors de la connexion :', error);
        alert('Une erreur s\'est produite. Veuillez réessayer.');
    }
}

document.addEventListener('DOMContentLoaded', () => {
    const loginForm = document.querySelector('form');
    loginForm.addEventListener('submit', login);
});
