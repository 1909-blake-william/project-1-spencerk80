class User {

    constructor(public username: string, public fullname: string, public password: string, public role: string) {

    }
}

let user: User

async function getSessionUser() {

    let response: Response

    try {

        const response = await fetch('http://localhost:8080/ReimbursementSystem/auth/session-user', {

            method: 'GET',
            credentials: 'include',
            headers: {

                'Content-Type': 'application/json'

            }

        })

        user = <User> await response.json()

    } catch(err) {

        console.error(err)
        user = null

    }

}

async function managerCheck() {

    await getSessionUser()

    if(user === null || user.role !== 'Manager')

        window.location.href = '/unautherized.html'
    
}

async function employeeCheck() {

    await getSessionUser()

    if(user === null || user.role !== 'Employee')

        window.location.href = '/unautherized.html'

}