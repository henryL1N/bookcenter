import { BaseEntity } from './../../shared';

export class Department implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public address?: string,
        public bookCenter?: BaseEntity,
        public manager?: BaseEntity,
    ) {
    }
}
