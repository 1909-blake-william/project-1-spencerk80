class Reimbursement{
    
    constructor(public amount:number, public description: string, public author:string, 
                public username: string, public resolver:string, public submitted: any,
                public resolved: any, public type: any, public status: any) {}

}

class User {

    constructor(public username: string, public fullname: string, public password: string, public role: string) {

    }
}

let user: User
let reimbursements: Reimbursement[]

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

async function get(status: string) {

    let response: Response

    clearPage()

    try {

        switch(status) {

            case 'pending':

                response = await fetch('http://localhost:8080/ReimbursementSystem/reimbursements?status=pending', {
        
                    method: 'GET',
                    credentials: 'include',
                    headers: {
        
                        'Content-Type': 'application/json'
        
                }
        
                }); break
            
            case 'approved':

                response = await fetch('http://localhost:8080/ReimbursementSystem/reimbursements?status=approved', {
        
                    method: 'GET',
                    credentials: 'include',
                    headers: {
        
                        'Content-Type': 'application/json'
        
                }
        
                }); break

            case 'denied':

                response = await fetch('http://localhost:8080/ReimbursementSystem/reimbursements?status=denied', {
        
                    method: 'GET',
                    credentials: 'include',
                    headers: {
        
                        'Content-Type': 'application/json'
        
                }
        
                }); break

            default:

                response = await fetch('http://localhost:8080/ReimbursementSystem/reimbursements', {
        
                    method: 'GET',
                    credentials: 'include',
                    headers: {
        
                        'Content-Type': 'application/json'
        
                }
        
                })

        } //End of switch

    } catch(err) {console.error(err)}

    reimbursements = <Reimbursement[]> await response.json()

    console.log(reimbursements)

    if(reimbursements.length === 0)

        noInfo()

    else 

        reimbursements.forEach(reimbursement => {

            makeInfoCard(reimbursement)

        })

}

function noInfo() {

    const info = document.createElement('h3')
    info.innerHTML = 'No reimbursements exist'

    document.getElementById("mid-section").appendChild(info)

}

function clearPage() {

    let midSection = document.getElementById('mid-section')

    while(midSection.firstChild)

        midSection.firstChild.remove()

}

async function updateRecord(action: boolean, author: string, submitted: any) {

    const status = action ? 'approved' : 'denied'

    try {

        const response = await fetch(`http://localhost:8080/ReimbursementSystem/reimbursements?set=${status}&resolver=${user.username}`, {

            method: 'PATCH',
            credentials: 'include',
            headers: {

                'Content-Type': 'application/json'

            },
            body: JSON.stringify(new Reimbursement(0, '', '', author, '', submitted, null, null, null))

        })

    } catch(err) {

        console.error(err)

    }

    get('pending')

}

function makeInfoCard(reimbursement: Reimbursement) {

    const submittedDate = new Date(reimbursement.submitted)
    const resolvedDate = new Date(reimbursement.resolved)

    const card      = document.createElement('div')
    const head      = document.createElement('div')
    const body      = document.createElement('div')
    const resolved  = reimbursement.resolved ? document.createElement('div') : null
    const foot      = reimbursement.status === 'PENDING' ? document.createElement('div') : null
    const name      = document.createElement('h3')
    const amount    = document.createElement('h4')
    const submitted = document.createElement('p')
    const status    = document.createElement('p')
    const type      = document.createElement('p')
    const desc      = document.createElement('p')
    const resolver  = reimbursement.resolved ? document.createElement('p') : null
    const resDate   = reimbursement.resolved ? document.createElement('p') : null
    const approve   = reimbursement.status === 'PENDING' ? document.createElement('button') : null
    const deny      = reimbursement.status === 'PENDING' ? document.createElement('button') : null

    card.setAttribute('class', 'card')
    head.setAttribute('class', 'head')
    body.setAttribute('class', 'body')
    if(resolved) resolved.setAttribute('class', 'resolved')
    if(foot) foot.setAttribute('class', 'foot')
    if(foot) approve.setAttribute('onclick', `updateRecord(true, '${reimbursement.username}', '${reimbursement.submitted}')`)
    if(foot) deny.setAttribute('onclick', `updateRecord(false, '${reimbursement.username}', '${reimbursement.submitted}')`)

    name.innerHTML                  = `${reimbursement.author}`
    amount.innerHTML                = `$${reimbursement.amount}`
    submitted.innerHTML             = `${submittedDate.getMonth()}/${submittedDate.getDay()}/${submittedDate.getFullYear()} ${submittedDate.getHours()}:${submittedDate.getMinutes()}:${submittedDate.getSeconds()}`
    status.innerHTML                = `${reimbursement.status}` 
    type.innerHTML                  = `${reimbursement.type}`
    desc.innerHTML                  = `${reimbursement.description}`
    if(resolver) resolver.innerHTML = `${reimbursement.resolver}` 
    if(resDate)  resDate.innerHTML  = `${resolvedDate.getMonth()}/${resolvedDate.getDay()}/${resolvedDate.getFullYear()} ${resolvedDate.getHours()}:${resolvedDate.getMinutes()}:${resolvedDate.getSeconds()}`
    if(approve)  approve.innerHTML  = 'Approve'
    if(deny)     deny.innerHTML     = 'Deny'

    head.appendChild(name)
    head.appendChild(amount)

    body.appendChild(submitted)
    body.appendChild(status)
    body.appendChild(type)
    body.appendChild(desc)

    if(resolved) resolved.appendChild(resolver)
    if(resolved) resolved.appendChild(resDate)

    if(foot) foot.appendChild(approve)
    if(foot) foot.appendChild(deny)

    card.appendChild(head)
    card.appendChild(body)
    if(resolved) card.appendChild(resolved)
    if(foot) card.appendChild(foot)

    document.getElementById('mid-section').appendChild(card)

}
