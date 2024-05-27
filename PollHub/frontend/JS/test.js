document.addEventListener("DOMContentLoaded", function() {
    function register(event) {
        event.preventDefault();
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirm-password').value;

        if (password !== confirmPassword) {
            alert('Les mots de passe ne correspondent pas.');
            return;
        }

        // Extraire le prénom et le nom à partir de l'adresse e-mail
        const [localPart] = email.split('@');
        const [firstName, lastName] = localPart.split('.');

        fetch('http://localhost:8080/PollHub2/rest/login/creation', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                firstName: firstName,
                lastName: lastName,
                email: email,
                password: password
            })
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Erreur lors de la création du compte');
            }
        })
        .then(data => {
            alert('Compte créé avec succès');
        })
        .catch(error => {
            console.error('Erreur lors de la création du compte :', error);
            alert('Erreur lors de la création du compte : ' + error.message);
        });
    }

    document.getElementById('signup-form').addEventListener('submit', register);
});
