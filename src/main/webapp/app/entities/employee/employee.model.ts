import { BaseEntity, User } from './../../shared';

export const enum Gender {
    'MALE',
    'FEMALE'
}

export class Employee implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public age?: number,
        public gender?: Gender,
        public position?: string,
        public salary?: number,
        public user?: User,
    ) {
    }
}
