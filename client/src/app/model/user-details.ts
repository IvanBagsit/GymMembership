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
