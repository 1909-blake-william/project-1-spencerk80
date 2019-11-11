import { User } from "./User"

async function authenticateUser(user: User) {

    let responseJson

    try {

        const response = fetch('http://localhost:8080/ReimbursementSystem/auth/login', {
            method: 'POST',
            mode: 'no-cors',
            credentials: 'include',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        })

        responseJson = await response

        console.log(responseJson)

    } catch(error) {

        console.error("Could not log in")

    }

}