async function logout() {

    let response:Response

    try {

        const response = await fetch('http://localhost:8080/ReimbursementSystem/auth/logout', {

            method: 'PUT',
            credentials: 'include',
            headers: {

                'Content-Type': 'application/json'

            }

        })

        window.location.href = '/'

    } catch(err) {

        console.error(err)

    }

}