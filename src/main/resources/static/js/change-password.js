document.getElementById('password-change-btn').addEventListener('click', changePassword);

async function changePassword() {
    const userId = new URLSearchParams(window.location.search).get('userId');
    const newPw = document.getElementById('new-pw').value;
    const newPwCheck = document.getElementById('new-pw-check').value;

    try {
        const response = await fetch('/auth/password', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({userId: userId, password: newPw})
        });

        if (response.ok) {
            window.location.href = '/change-password-success';
        } else {
            document.getElementById('error').textContent = 'Password change failed. Please try again.';
        }
    } catch (e) {
        console.log("Error: ", e.message);
    }

}