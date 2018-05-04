import { BaseEntity } from './../../shared';

export class StockItem implements BaseEntity {
    constructor(
        public id?: number,
        public quantity?: number,
        public warehouse?: BaseEntity,
    ) {
    }
}
