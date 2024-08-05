document.getElementById('register-form').addEventListener('submit', function (event) {
    event.preventDefault();

    const email = document.getElementById('register-email').value;
    const password = document.getElementById('register-password').value;
    // 비밀번호 찾기 질문
    const passwordQuestionAnswer = document.getElementById('register-pw-question-answer').value;
    const username = document.getElementById('register-username').value;
    const phone = document.getElementById('register-phone').value;

    const data = {
        email: email,
        userName: username,
        passwordQuestionId: 1, // 임시
        password: password,
        passwordAnswer: passwordQuestionAnswer,
        phone: phone
    };

    fetch('http://localhost:8080/member', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(res => res.json())
        .then(data => {
            console.log("Success: ", data.id);
            if(data.id) {
                window.location.href = 'http://localhost:8080/register-success'
            }
        })
        .catch(e => {
            console.error('Error: ', e);
        })
})