document.getElementById('register-form').addEventListener('submit', function (event) {
    event.preventDefault();

    const email = document.getElementById('register-email').value;
    const password = document.getElementById('register-password').value;
    const passwordQuestionId = document.getElementById('dropdownMenu').value;
    const passwordQuestionAnswer = document.getElementById('register-pw-question-answer').value;
    const username = document.getElementById('register-username').value;
    const phone = document.getElementById('register-phone').value;

    const data = {
        email: email,
        userName: username,
        passwordQuestionId: passwordQuestionId,
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

// 아이디 중복 확인 버튼 클릭 시 호출되는 함수
async function checkEmailDuplicate() {
    // 이메일 입력 필드와 결과 메시지 요소를 가져옵니다
    const emailInput = document.getElementById('register-email');
    const resultMessage = document.getElementById('result-message');

    // 입력된 이메일을 가져옵니다
    const email = emailInput.value;
    console.log("email: ", email);

    if (!email) {
        resultMessage.textContent = '이메일 주소를 입력해주세요';
        return;
    }

    try {
        // 서버에 이메일 중복 확인 요청을 보냅니다
        const response = await fetch(`http://localhost:8080/email-duplication?email=${encodeURIComponent(email)}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        // 응답에 따라 결과 메시지를 설정합니다
        if (response.status === 204) {
            resultMessage.textContent = '사용 가능한 이메일입니다';
            resultMessage.style.color = 'green';
        } else if (response.status === 409) {
            // Email is duplicated
            resultMessage.textContent = '이미 사용중인 이메일입니다';
            resultMessage.style.color = 'red';
        } else {
            // Other error
            resultMessage.textContent = 'An error occurred. Please try again.';
            resultMessage.style.color = 'orange';
        }
    } catch (error) {
        console.error('Error checking email:', error);
        resultMessage.textContent = 'An error occurred. Please try again.';
        resultMessage.style.color = 'orange';
    }
}

// 중복 확인 버튼 클릭 시 checkEmailDuplicate 함수를 호출합니다
document.getElementById('check-email-btn').addEventListener('click', checkEmailDuplicate);

// 닉네임 중복 확인 버튼 클릭 시 호출되는 함수
async function checkUserNameDuplicate() {
    // 이메일 입력 필드와 결과 메시지 요소를 가져옵니다
    const usernameInput = document.getElementById('register-username');
    const resultMessage2 = document.getElementById('result-message2');

    // 입력된 이메일을 가져옵니다
    const username = usernameInput.value;
    console.log("username: ", username);

    if (!username) {
        resultMessage2.textContent = '닉네임을 입력해주세요';
        return;
    }

    try {
        // 서버에 이메일 중복 확인 요청을 보냅니다
        const response = await fetch(`http://localhost:8080/nickname-duplication?userName=${encodeURIComponent(username)}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        // 응답에 따라 결과 메시지를 설정합니다
        if (response.status === 204) {
            resultMessage2.textContent = '사용 가능한 닉네임입니다';
            resultMessage2.style.color = 'green';
        } else if (response.status === 409) {
            // Email is duplicated
            resultMessage2.textContent = '이미 사용중인 닉네임입니다';
            resultMessage2.style.color = 'red';
        } else {
            // Other error
            resultMessage2.textContent = 'An error occurred. Please try again.';
            resultMessage2.style.color = 'orange';
        }
    } catch (error) {
        console.error('Error checking email:', error);
        resultMessage2.textContent = 'An error occurred. Please try again.';
        resultMessage2.style.color = 'orange';
    }
}

// 중복 확인 버튼 클릭 시 checkUserNameDuplicate 함수를 호출합니다
document.getElementById('check-username-btn').addEventListener('click', checkUserNameDuplicate);