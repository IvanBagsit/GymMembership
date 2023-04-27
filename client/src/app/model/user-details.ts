import { IMembershipType } from "./membership-type";

export interface IUserDetails{
    id: number;
    firstName?: string;
    lastName?: string;
    age?: number;
    birthday?: Date;
    expirationDate?: Date;
    joinDate?: Date;
    termsAndCondition?: Boolean;
    disable?: Boolean;
    membershipType: IMembershipType;
}

export class UserDetails {
    constructor(
        public id: number,
        public firstName?: string,
        public lastName?: string,
        public age?: number,
        public birthday?: Date,
        public expirationDate?: Date,
        public joinDate?: Date,
        public termsAndCondition?: Boolean,
        public disable?: Boolean,
        public membershipType?: IMembershipType,
    ) {}
}