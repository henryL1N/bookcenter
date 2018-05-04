import { BaseEntity } from './../../shared';

export class Warehouse implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public phone?: string,
        public stockItems?: BaseEntity[],
    ) {
    }
}
