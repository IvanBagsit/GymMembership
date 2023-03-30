import { IAccountType } from "./account-type";
import { IMembershipType } from "./membership-type";

export interface IUserDetails{
    id: number;
    username?: string;
    password?: number;
    firstName?: string;
    lastName?: string;
    age?: number;
    birthday?: Date;
    lastLogIn?: Date;
    lastLogOut?: Date;
    expirationDate?: Date;
    joinDate?: Date;
    termsAndCondition?: Boolean;
    disable?: Boolean;
    membershipType: IMembershipType;
    accountType: IAccountType;
}

export class UserDetails {
    constructor(
        public id: number,
        public username?: string,
        public password?: number,
        public firstName?: string,
        public lastName?: string,
        public age?: number,
        public birthday?: Date,
        public lastLogIn?: Date,
        public lastLogOut?: Date,
        public expirationDate?: Date,
        public joinDate?: Date,
        public termsAndCondition?: Boolean,
        public disable?: Boolean,
        public membershipType?: IMembershipType,
        public accountType?: IAccountType
    ) {}
}