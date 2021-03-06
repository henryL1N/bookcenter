import { BaseEntity } from './../../shared';

export class PurchaseOrder implements BaseEntity {
    constructor(
        public id?: number,
        public date?: any,
        public supplier?: string,
        public totalAmount?: number,
        public orderItems?: BaseEntity[],
        public warehouseId?: number,
        public warehouseName?: string,
        public buyerId?: number,
        public buyerName?: string,
    ) {
    }
}
