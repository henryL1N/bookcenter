import { BaseEntity } from './../../shared';

export class BookCenter implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public address?: string,
        public generalManager?: BaseEntity,
        public departments?: BaseEntity[],
    ) {
    }
}
