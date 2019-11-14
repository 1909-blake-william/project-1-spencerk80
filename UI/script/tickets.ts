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

function showForm() {

    const form      = document.createElement('form')
    const lblName   = document.createElement('label')
    const lblAmount = document.createElement('label')
    const lblType   = document.createElement('label')
    const lblDesc   = document.createElement('label')
    const name      = document.createElement('input')
    const amount    = document.createElement('input')
    const desc      = document.createElement('textarea')
    const type      = document.createElement('select')
    const car       = document.createElement('option')
    const fly       = document.createElement('option')
    const food      = document.createElement('option')
    const sleep     = document.createElement('option')
    const owie      = document.createElement('option')
    const bus       = document.createElement('option')
    const other     = document.createElement('option')
    const submit    = document.createElement('button')

    form.setAttribute('onsubmit', 'newTicket(event)')
    name.setAttribute('type', 'text')
    name.setAttribute('disabled', 'true')
    name.setAttribute('value', user.fullname)
    amount.setAttribute('id', 'money')
    amount.setAttribute('type', 'number')
    amount.setAttribute('step', '0.01')
    type.setAttribute('id', 'type')
    car.setAttribute('value', 'car_rental')
    fly.setAttribute('value', 'flight')
    food.setAttribute('value', 'food')
    sleep.setAttribute('value', 'lodging')
    owie.setAttribute('value', 'medical')
    bus.setAttribute('value', 'pub_trans')
    other.setAttribute('value', 'other')
    desc.setAttribute('id', 'desc')
    desc.setAttribute('placeholder', 'Optional description...')
    submit.setAttribute('type', 'submit')

    lblName.innerHTML = 'Name'
    lblAmount.innerHTML = 'Amount'
    lblType.innerHTML = 'Type'
    lblDesc.innerHTML = 'Description'

    car.innerHTML   = 'Car Rental'
    fly.innerHTML   = 'Flight'
    food.innerHTML  = 'Meal' 
    sleep.innerHTML = 'Lodging'
    owie.innerHTML  = 'Medical'
    bus.innerHTML   = 'Public Transit'
    other.innerHTML = 'Other'

    submit.innerHTML = 'Submit Ticket'

    type.appendChild(car)
    type.appendChild(fly)
    type.appendChild(food)
    type.appendChild(sleep)
    type.appendChild(owie)
    type.appendChild(bus)
    type.appendChild(other)

    form.appendChild(lblName)
    form.appendChild(name)
    form.appendChild(lblAmount)
    form.appendChild(amount)
    form.appendChild(lblType)
    form.appendChild(type)
    form.appendChild(lblDesc)
    form.appendChild(desc)
    form.appendChild(submit)

    clearPage()

    document.getElementById('mid-section').appendChild(form)

}

function newTicket(event: Event) {

    const amount    = parseInt((<HTMLInputElement> document.getElementById('money')).value)
    const desc      = (<HTMLInputElement> document.getElementById('desc')).value
    const type      = (<HTMLInputElement> document.getElementById('type')).value
    
    let reimbursement: Reimbursement

    event.preventDefault()

    if(isNaN(amount)) {
        
        showError('Error: Please enter the amount for which you wish to be reimbursed')
        return

    }

    if(type === 'other' && desc === '') {

        showError('Error: When selecting "other", a description is manditory')
        return

    }

    reimbursement = new Reimbursement(amount, desc, user.fullname, user.username, null, null, null, type, 'PENDING')
    submitTicket(reimbursement)

}

function showError(msg: string) {

    const error = document.createElement('h1')

    error.setAttribute('id', 'error')
    error.innerHTML = msg
    document.getElementById('body').appendChild(error)

    setTimeout(clearError, 3000);

}

function showSuccess(msg: string) {

    const success = document.createElement('h1')

    success.setAttribute('id', 'success')
    success.innerHTML = msg
    document.getElementById('body').appendChild(success)

    setTimeout(clearSuccess, 3000);

}

function clearError() {

    document.getElementById('error').remove()

}

function clearSuccess() {

    document.getElementById('success').remove()

}

async function submitTicket(reimbursement: Reimbursement) {

    try {

        const response = await fetch('http://localhost:8080/ReimbursementSystem/reimbursements', {

            method: 'POST',
            credentials: 'include',
            headers: {

                'Content-Type': 'application/json'

            },
            body: JSON.stringify(reimbursement)

        })

        if(response.status !== 201)

            showError('Error: Could not submit ticket')

        else {

            clearForm()
            showSuccess('Ticket submitted!')

        }

    } catch(err) {console.error(err)}

}

function clearForm() {

    (<HTMLInputElement> document.getElementById('money')).value = ''; //Weirdly it REQUIRED the ;
    (<HTMLInputElement> document.getElementById('desc')).value = ''

}

async function get(status: string) {

    let response: Response

    clearPage()

    try {

        switch(status) {

            case 'pending':

                document.getElementById('indicator').innerHTML = 'Pending'

                response = await fetch(`http://localhost:8080/ReimbursementSystem/reimbursements?name=${user.username}&status=pending`, {
        
                    method: 'GET',
                    credentials: 'include',
                    headers: {
        
                        'Content-Type': 'application/json'
        
                }
        
                }); break
            
            case 'approved':
                   
                document.getElementById('indicator').innerHTML = 'Approved'

                response = await fetch(`http://localhost:8080/ReimbursementSystem/reimbursements?name=${user.username}&status=approved`, {
        
                    method: 'GET',
                    credentials: 'include',
                    headers: {
        
                        'Content-Type': 'application/json'
        
                }
        
                }); break

            case 'denied':

                document.getElementById('indicator').innerHTML = 'Denied'

                response = await fetch(`http://localhost:8080/ReimbursementSystem/reimbursements?name=${user.username}&status=denied`, {
        
                    method: 'GET',
                    credentials: 'include',
                    headers: {
        
                        'Content-Type': 'application/json'
        
                }
        
                }); break

            default:

              document.getElementById('indicator').innerHTML = 'All'

                response = await fetch(`http://localhost:8080/ReimbursementSystem/reimbursements?name=${user.username}`, {
        
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

    document.getElementById('indicator').innerHTML = ''

}

function makeInfoCard(reimbursement: Reimbursement) {

    const submittedDate = new Date(reimbursement.submitted)
    const resolvedDate = new Date(reimbursement.resolved)

    const mid       = document.getElementById('mid-section')
    const card      = document.createElement('div')
    const head      = document.createElement('div')
    const body      = document.createElement('div')
    const resolved  = reimbursement.resolved ? document.createElement('div') : null
    const name      = document.createElement('h3')
    const amount    = document.createElement('h4')
    const submitted = document.createElement('p')
    const status    = document.createElement('p')
    const type      = document.createElement('p')
    const desc      = document.createElement('p')
    const resolver  = reimbursement.resolved ? document.createElement('p') : null
    const resDate   = reimbursement.resolved ? document.createElement('p') : null

    card.setAttribute('class', 'card')
    head.setAttribute('class', 'head')
    body.setAttribute('class', 'body')
    if(resolved) resolved.setAttribute('class', 'resolved')

    name.innerHTML                  = `${reimbursement.author}`
    amount.innerHTML                = `$${reimbursement.amount}`
    submitted.innerHTML             = `${submittedDate.getMonth() + 1}/${submittedDate.getDay()}/${submittedDate.getFullYear()} ${submittedDate.getHours()}:${submittedDate.getMinutes()}:${submittedDate.getSeconds()}`
    status.innerHTML                = `${reimbursement.status}` 
    type.innerHTML                  = `${reimbursement.type}`
    desc.innerHTML                  = `${reimbursement.description}`
    if(resolver) resolver.innerHTML = `${reimbursement.resolver}` 
    if(resDate)  resDate.innerHTML  = `${resolvedDate.getMonth() + 1}/${resolvedDate.getDay()}/${resolvedDate.getFullYear()} ${resolvedDate.getHours()}:${resolvedDate.getMinutes()}:${resolvedDate.getSeconds()}`

    head.appendChild(name)
    head.appendChild(amount)

    body.appendChild(submitted)
    body.appendChild(status)
    body.appendChild(type)
    if(reimbursement.description) body.appendChild(desc)

    if(resolved) resolved.appendChild(resolver)
    if(resolved) resolved.appendChild(resDate)

    card.appendChild(head)
    card.appendChild(body)
    if(resolved) card.appendChild(resolved)

    document.getElementById('mid-section').appendChild(card)

}
