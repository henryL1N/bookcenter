import { BaseEntity } from './../../shared';

export class BookCenter implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public address?: string,
        public generalManagerId?: number,
        public departments?: BaseEntity[],
        public employees?: BaseEntity[],
    ) {
    }
}
