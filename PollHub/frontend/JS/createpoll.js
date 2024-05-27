async function addQuestion() {
    console.log("addQuestion function called");
    var questionContainer = document.getElementById('questions');
    var questionType = document.getElementById('question-type').value;
    var questionText = document.getElementById('question-text').value;
    var optionsContainer = document.getElementById('options-container');

    console.log("Question Type:", questionType);
    console.log("Question Text:", questionText);
    
    if (!questionContainer || !questionType || !questionText) {
        console.error("Required element or value is missing");
        return;
    }

    var questionDiv = document.createElement('div');
    questionDiv.className = 'question';

    var label = document.createElement('label');
    label.textContent = questionText;
    questionDiv.appendChild(label);

    let data = {
        question: questionText,
        nb_rep: 0
    };

    if (questionType === 'multiple-choice') {
        var options = document.getElementById('options').value.split('\n');
        options.forEach(function(option) {
            var optionDiv = document.createElement('div');
            var radio = document.createElement('input');
            radio.type = 'radio';
            radio.name = questionText;
            radio.value = option;
            optionDiv.appendChild(radio);
            var optionLabel = document.createElement('label');
            optionLabel.textContent = option;
            optionDiv.appendChild(optionLabel);
            questionDiv.appendChild(optionDiv);
        });

        //data.options = options;
    } else {
        var textarea = document.createElement('textarea');
        questionDiv.appendChild(textarea);
        //data.replibre = textarea.value;
    }

    console.log("Data to be sent:", data);

    questionContainer.appendChild(questionDiv);

    document.getElementById('question-text').value = '';
    if (optionsContainer) {
        document.getElementById('options').value = '';
    }

    try {
        let response = await post('http://localhost:8080/PollHub/rest/question/create', data);
        console.log("Response from server:", response);

        displayMessage(response.message, response.success ? 'success' : 'error');
        if (response.success) {
            window.location.href = 'home.html';
        }
    } catch (error) {
        console.error("Error during fetch:", error);
        displayMessage('Une erreur s\'est produite. Veuillez réessayer.', 'error');
    }
}

async function addResponse(questionId, responses) {
    if (responses.length === 0) {
        console.log("No responses to add for question ID:", questionId);
        return;
    }

    let data = {
        questionId: questionId,
        responses: responses
    };

    console.log("Sending responses to server:", data);

    try {
        let response = await fetch('http://localhost:8080/PollHub/rest/response/create', {
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

    function displayMessage(message, type) {
        let alertElement = document.getElementById('alert');
        alertElement.classList.remove('success', 'error');
        alertElement.innerHTML = message;
        alertElement.classList.add(type);
    }
}