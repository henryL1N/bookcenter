import { BaseEntity } from './../../shared';

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
        public userId?: number,
        public userLogin?: string,
        public bookCenterId?: number,
        public bookCenterName?: string,
        public departmentId?: number,
        public departmentName?: string,
    ) {
    }
}
