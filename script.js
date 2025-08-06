const API_BASE = 'http://localhost:8080/api'; // Change to your backend URL when deployed

async function fetchQuestions() {
    const response = await fetch(`${API_BASE}/questions`);
    if (!response.ok) throw new Error('Failed to fetch questions');
    return response.json();
}

function createQuestionElement(question) {
    const div = document.createElement('div');
    div.className = 'question';
    div.innerHTML = `
        <p>${question.id}. ${question.question}</p>
        <label><input type="radio" name="q${question.id}" value="yes" required /> Yes</label>
        <label><input type="radio" name="q${question.id}" value="no" /> No</label>
    `;
    return div;
}

async function setupQuiz() {
    try {
        const container = document.getElementById('questions-container');
        const questions = await fetchQuestions();
        questions.forEach(q => container.appendChild(createQuestionElement(q)));
    } catch (error) {
        alert('Error loading quiz questions.');
    }
}

document.getElementById('quizForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const answers = {};
    for (const [key, value] of formData.entries()) {
        const id = parseInt(key.slice(1));
        answers[id] = value;
    }
    try {
        const response = await fetch(`${API_BASE}/submit`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ answers })
        });
        if (!response.ok) throw new Error('Submission failed');
        const result = await response.json();
        const resultDiv = document.getElementById('result');
        resultDiv.innerHTML = `<strong>Your Score: ${result.score} / ${result.total}</strong><br>${result.feedback}`;
    } catch {
        alert('Failed to submit answers. Try again later.');
    }
});

setupQuiz();
