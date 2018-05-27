import { BaseEntity } from './../../shared';

export class StockItem implements BaseEntity {
    constructor(
        public id?: number,
        public quantity?: number,
        public bookId?: number,
        public bookName?: string,
        public warehouseId?: number,
        public warehouseName?: string,
    ) {
    }
}
