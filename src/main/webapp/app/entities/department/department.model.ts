import { BaseEntity } from './../../shared';

export class Department implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public address?: string,
        public bookCenterId?: number,
        public bookCenterName?: string,
        public managerId?: number,
        public managerName?: string,
        public employees?: BaseEntity[],
    ) {
    }
}
