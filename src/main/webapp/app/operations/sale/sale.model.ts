import { BaseEntity } from './../../shared';
import {OrderItem} from '../../entities/order-item/order-item.model';

export class Sale implements BaseEntity {
    constructor(
        public id?: number,
        public date?: any,
        public customer?: string,
        public totalAmount?: number,
        public sellerId?: number,
        public sellerName?: string,
        public orderItems?: OrderItem[],
        public warehouseId?: number,
        public warehouseName?: string,
    ) {
    }
}
