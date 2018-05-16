import { BaseEntity } from './../../shared';

export class PurchaseOrder implements BaseEntity {
    constructor(
        public id?: number,
        public date?: any,
        public supplier?: string,
        public totalAmount?: number,
        public buyerId?: number,
        public orderItems?: BaseEntity[],
    ) {
    }
}
