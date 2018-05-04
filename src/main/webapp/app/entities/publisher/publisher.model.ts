import { BaseEntity } from './../../shared';

export class Publisher implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public phone?: string,
        public address?: string,
    ) {
    }
}
