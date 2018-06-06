import { BaseEntity } from './../../shared';

export class StockReport implements BaseEntity {
    constructor(
        public id?: number,
        public bookId?: number,
        public bookName?: string,
        public quantity?: number,
    ) {
    }
}
