class User {
    constructor(username, fullname, password, role) {
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.role = role;
    }
}
let user;
async function getSessionUser() {
    let response;
    try {
        const response = await fetch('http://localhost:8080/ReimbursementSystem/auth/session-user', {
            method: 'GET',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        user = await response.json();
    }
    catch (err) {
        console.error(err);
        user = null;
    }
}
async function managerCheck() {
    await getSessionUser();
    if (user === null || user.role !== 'Manager')
        window.location.href = '/unautherized.html';
}
async function employeeCheck() {
    await getSessionUser();
    if (user === null || user.role !== 'Employee')
        window.location.href = '/unautherized.html';
}
