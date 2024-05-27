
async function signup(event) {
    event.preventDefault();

    let form = document.querySelector('form');
    let nom = form.elements["nom"].value;
    let prenom = form.elements["prenom"].value;
    let email = form.elements["email"].value;
    let password = form.elements["password"].value;
    let confirmPassword = form.elements["confirm-password"].value;

    // Check if passwords match
    if (password !== confirmPassword) {
        displayMessage('Les mots de passe ne correspondent pas.', 'error');
        return;
    }

    // Convert role to boolean
    let userType = form.elements["user-type"].value;
    let role = (userType === 'teacher'); // true for teacher and false for student

    let data = {
        nom: nom,
        prenom: prenom,
        mail: email,
        mdp: password,
        role: role,
        year: form.elements["year"] ? form.elements["year"].value : null,
        major: form.elements["major"] ? form.elements["major"].value : null,
        specialty: form.elements["specialty"] ? form.elements["specialty"].value : null
    };

    console.log("Sending data to server:", data);

    try {
        let response = await post('http://localhost:8080/PollHub/rest/login/creation', data);
        console.log("Response from server:", response);
        localStorage.setItem('role', response.role); // verifier si mettre dans signup.js

        console.log("Role stored in localStorage:", localStorage.getItem('role'))
        //displayMessage(response.message, response.succes ? 'success' : 'error');
        window.location.href = 'home.html';
    } catch (error) {
        console.error("Error during fetch:", error);
        displayMessage('Une erreur s\'est produite. Veuillez réessayer.', 'error');
    }
}

function displayMessage(message, type) {
    let alertElement = document.getElementById('alert');
    alertElement.classList.remove('success', 'error');
    alertElement.innerHTML = message;
    alertElement.classList.add(type);
}

function post(url, data) {
    return fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(response => response.json());
}

function toggleStudentFields() {
    var userType = document.querySelector('input[name="user-type"]:checked').value;
    var studentFields = document.getElementById('student-fields');
    if (userType === 'student') {
        studentFields.classList.add('active');
    } else {
        studentFields.classList.remove('active');
    }
}

function updateSpecialtyOptions() {
    var userType = document.querySelector('input[name="user-type"]:checked').value;
    var studentFields = document.getElementById('student-fields');
    if (userType === 'student') {
        var year = document.getElementById('year').value;
        var major = document.getElementById('major').value;
        var specialtyContainer = document.getElementById('specialty-container');
        var specialtySelect = document.getElementById('specialty');
        var specialties = {
            "SN": ["IMM", "HPC BG", "Télécom", "Réseaux", "ASR", "Logiciel", "ModIA"],
            "MF2E": {
                "2": ["Eau et environnement", "Energie"],
                "3": ["Fluide énergie et procédé", "Science de l'eau et de l'environnement", "modélisation et simulation numérique"]
            },
            "3EA": ["InSYS", "SysCOM", "PN", "IATI", "SATR", "SEF", "SM"]
        };

        specialtySelect.innerHTML = '';

        if (year === "1") {
            specialtyContainer.style.display = 'none';
        } else {
            specialtyContainer.style.display = 'block';

            if (specialties[major]) {
                var options = Array.isArray(specialties[major]) ? specialties[major] : specialties[major][year] || [];
                options.forEach(function(specialty) {
                    var option = document.createElement('option');
                    option.value = specialty;
                    option.text = specialty;
                    specialtySelect.appendChild(option);
                });
            }
        }
    } else {
        studentFields.classList.remove('active');
    }
}





