class User {

    constructor(public username: string, public fullname: string, public password: string) {

    }
}

function login(event): void {

    let user:User = getLoginInfo()

    event.preventDefault()

    if(!user.username || !user.password) {

        showError('Error: Username and password cannot be blank')
        return

    }

    authenticateUser(user)

}

function getLoginInfo():User {

    let username = (<HTMLInputElement> document.getElementById("username-input")).value
    let password = (<HTMLInputElement> document.getElementById("pass-input")).value

    return new User(username, '', password)

}

function showError(msg: string): void {

    let text = document.getElementById("status")

    text.innerHTML = msg
    text.style.backgroundColor = 'red'
    text.style.fontSize = '12pt'
    text.style.textDecoration = 'none'

    setTimeout(clearError, 1500)

}

function clearError(): void {

    let text = document.getElementById("status")

    text.innerHTML = 'Login'
    text.style.backgroundColor = null
    text.style.fontSize = '18pt'
    text.style.textDecoration = 'underline'

}

async function authenticateUser(user: User) {

    let response: Response

    try {

            const response = await fetch('http://localhost:8080/ReimbursementSystem/auth/login', {

            method: 'POST',
            credentials: 'include',
            headers: {

                'Content-Type': 'application/json'

            },
            body: JSON.stringify(user)

        })

        console.log(response.status)

        if(response.status !== 201) {

            console.log('Invalid login')
            return

        }

    } catch(error) {

        console.error(error)

    }

}