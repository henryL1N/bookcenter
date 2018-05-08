import { BaseEntity } from './../../shared';

export class OrderItem implements BaseEntity {
    constructor(
        public id?: number,
        public price?: number,
        public quantity?: number,
        public purchaseOrder?: BaseEntity,
        public salesOrder?: BaseEntity,
        public book?: BaseEntity,
    ) {
    }
}
