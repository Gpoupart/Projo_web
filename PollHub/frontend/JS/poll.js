document.addEventListener('DOMContentLoaded', () => {
    const role = localStorage.getItem('role');
    console.log("Role:", role);
    if (role === "1") { // 1 pour Professeur
        document.getElementById('createPollLink').style.display = 'block';
        document.getElementById('showCreateGroupForm').style.display = 'block';
    }

    document.getElementById('createPollLink').addEventListener('click', () => {
        document.getElementById('welcomeMessage').style.display = 'none';
        document.getElementById('createPollContainer').style.display = 'block';
        document.getElementById('createPollLink').classList.add('disabled-link'); // Disable the create poll link
        document.getElementById('createGroupContainer').style.display = 'none';
        document.getElementById('showCreateGroupForm').classList.remove('disabled-link'); // Enable the create group link
    });

    /*
    document.getElementById('createPollForm').addEventListener('submit', async function (event) {
        event.preventDefault();

        let form = document.getElementById('createPollForm');
        let pollTitle = form.elements["pollTitle"].value;
        let questions = [];

        document.querySelectorAll('.question').forEach(questionDiv => {
            let questionText = questionDiv.querySelector('label').textContent;
            let questionType = questionDiv.querySelector('input[type="radio"]') ? 'multiple-choice' : 'open-ended';
            let options = [];
            if (questionType === 'multiple-choice') {
                questionDiv.querySelectorAll('input[type="radio"]').forEach(radio => {
                    options.push(radio.value);
                });
            }
            questions.push({ questionText, questionType, options });
        });

        let data = {
            pollTitle: pollTitle,
            questions: questions.map(q => ({
                question: q.questionText,
                questionType: q.questionType,
                nb_rep: 0,
                options: q.options
            }))
        };

        console.log("Sending data to server:", data);

        try {
            let response = await fetch('http://localhost:8080/PollHub/rest/polls/create', {
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
                document.getElementById('createPollForm').reset();
            } else {
                const errorMessage = await response.text();
                displayMessage(errorMessage, 'error');
            }
        } catch (error) {
            console.error("Error during fetch:", error);
            displayMessage('Une erreur s\'est produite. Veuillez réessayer.', 'error');
        }
    });*/

    async function addQuestion() {
        var questionContainer = document.getElementById('questions');
        if (!questionContainer) {
            console.error("Element with ID 'questions' not found");
            return;
        }

        var questionType = document.getElementById('question-type').value;
        var questionText = document.getElementById('question-text').value;

        let data = {
            question: questionText,
            questionType: questionType,
            nb_rep: 0,
            options: questionType === 'multiple-choice' ? document.getElementById('options').value.split('\n') : []
        };

        console.log("Sending question data to server:", data);

        try {
            let response = await fetch('http://localhost:8080/PollHub/rest/question/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(data)
            });

            console.log("Response from server:", response);

            if (response.ok) {
                let responseData = await response.json();

                // Afficher la question seulement après avoir reçu une réponse positive du serveur
                var questionDiv = document.createElement('div');
                questionDiv.className = 'question';

                var label = document.createElement('label');
                label.textContent = questionText;
                questionDiv.appendChild(label);

                if (questionType === 'multiple-choice') {
                    data.options.forEach(function(option) {
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
                } else {
                    var textarea = document.createElement('textarea');
                    questionDiv.appendChild(textarea);
                }

                questionContainer.appendChild(questionDiv);

                document.getElementById('question-text').value = '';
                document.getElementById('options').value = '';
            } else {
                const errorMessage = await response.text();
                displayMessage(errorMessage, 'error');
            }
        } catch (error) {
            console.error("Error during fetch:", error);
            displayMessage('Une erreur s\'est produite. Veuillez réessayer.', 'error');
        }
    }

    document.getElementById('addQuestionBtn').addEventListener('click', addQuestion);

    function displayMessage(message, type) {
        let alertElement = document.getElementById('alert');
        if (!alertElement) {
            alertElement = document.createElement('div');
            alertElement.id = 'alert';
            document.body.appendChild(alertElement);
        }
        alertElement.classList.remove('success', 'error');
        alertElement.innerHTML = message;
        alertElement.classList.add(type);
    }
});
