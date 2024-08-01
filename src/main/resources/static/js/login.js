const URL = `http://localhost:8080`

document.getElementById('login-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const email = document.getElementById('login-email').value;
    const password = document.getElementById('login-password').value;

    const data = {
        email: email,
        password: password
    };

    fetch(URL + '/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            if(data.userDto !== null){
                window.location.href = URL + '/home' // home 화면으로 이동
            }
        })
        .catch((error) => {
            console.error('Error:', error);
        });
});