import { BaseEntity } from './../../shared';

export class SalesReport implements BaseEntity {
    constructor(
        public id?: number,
        public bookId?: number,
        public bookName?: string,
        public quantity?: number,
    ) {
    }
}
