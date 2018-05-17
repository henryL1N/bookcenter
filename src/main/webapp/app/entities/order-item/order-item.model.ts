import { BaseEntity } from './../../shared';

export class OrderItem implements BaseEntity {
    constructor(
        public id?: number,
        public price?: number,
        public quantity?: number,
        public purchaseOrderId?: number,
        public salesOrderId?: number,
        public bookId?: number,
        public bookName?: string,
    ) {
    }
}
