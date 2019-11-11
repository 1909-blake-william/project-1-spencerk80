import { User } from "./User"

function login(): void {

    let user:User = getLoginInfo()

    if(!user.username || !user.password) {



    }

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

    setTimeout(clearError, 800)

}

function clearError(): void {

    let text = document.getElementById("status")

    text.innerHTML = 'Login'
    text.style.backgroundColor = null

}