export interface IAccountType{
    id?: number;
    role?: string;
}

export class AccountType {
    constructor(
        public id?: number,
        public role?: string
    ){}
}