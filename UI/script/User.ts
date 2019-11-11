export class User {

    private _username:string
    private _fullname:string
    private _password:string

    constructor(username: string, fullname: string, password: string) {

        this._username = username
        this._fullname = fullname
        this._password = password

    }

    get username() {
        return this._username
    }

    get fullname() {
        return this._fullname
    }

    get password() {
        return this._password
    }

}