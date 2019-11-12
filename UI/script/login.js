class User {
    constructor(username, fullname, password) {
        this.username = username;
        this.fullname = fullname;
        this.password = password;
    }
}
function login(event) {
    let user = getLoginInfo();
    event.preventDefault();
    if (!user.username || !user.password) {
        showError('Error: Username and password cannot be blank');
        return;
    }
    authenticateUser(user);
}
function getLoginInfo() {
    let username = document.getElementById("username-input").value;
    let password = document.getElementById("pass-input").value;
    return new User(username, '', password);
}
function showError(msg) {
    let text = document.getElementById("status");
    text.innerHTML = msg;
    text.style.backgroundColor = 'red';
    text.style.fontSize = '12pt';
    text.style.textDecoration = 'none';
    setTimeout(clearError, 1500);
}
function clearError() {
    let text = document.getElementById("status");
    text.innerHTML = 'Login';
    text.style.backgroundColor = null;
    text.style.fontSize = '18pt';
    text.style.textDecoration = 'underline';
}
async function authenticateUser(user) {
    let response;
    try {
        response = await fetch('http://localhost:8080/ReimbursementSystem/auth/login', {
            method: 'POST',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });
        console.log(response.status);
        if (response.status !== 201) {
            console.log('Invalid login');
            return;
        }
    }
    catch (error) {
        console.error(error);
    }
}
