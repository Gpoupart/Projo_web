async function createGroup(event) {
    event.preventDefault();

    let form = document.getElementById('createGroupForm');
    let groupName = form.elements["groupName"].value;
    let students = form.elements["students"].value.split(',');

    let data = {
        groupName: groupName,
        students: students
    };

    console.log("Sending data to server:", data);

    try {
        let response = await fetch('http://localhost:8080/PollHub/rest/groups/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        });

        console.log("Response from server:", response);

        if (response.ok) {
            let responseData = await response.json();
            displayMessage(responseData.message, 'success');
        } else {
            const errorMessage = await response.text();
            displayMessage(errorMessage, 'error');
        }
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

document.addEventListener('DOMContentLoaded', () => {
    const createGroupForm = document.getElementById('createGroupForm');
    createGroupForm.addEventListener('submit', createGroup);
});
