export interface IMembershipType{
    id?: number;
    type?: string;
    fee?: number;
    duration?: string;
}

export class MembershipType{
    constructor(
        public id?: number,
        public type?: string,
        public fee?: number,
        public duration?: string
    ){}
}