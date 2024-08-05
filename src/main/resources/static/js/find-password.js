document.getElementById('find-password-btn').addEventListener('click', findPassword);

async function findPassword() {
    const email = document.getElementById('email-input').value;
    const passwordQuestionId = document.getElementById('dropdownMenu').value;
    const passwordAnswer = document.getElementById('pw-answer-input').value;

    const data = {
        email: email,
        passwordQuestionId: passwordQuestionId,
        passwordAnswer: passwordAnswer
    }

    try{
        const response = await fetch('http://localhost:8080/auth/password-certification',{
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })

        if (response.ok) {
            const data = await response.json();
            window.location.href = `/change-password?userId=${data.userDto.id}`;
        } else {
            document.getElementById('error').textContent = 'Authentication failed. Please try again.';
        }
    } catch (e) {
        console.log("Error: ", e.message);
    }
}