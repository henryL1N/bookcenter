import { BaseEntity } from './../../shared';
import {OrderItem} from '../../entities/order-item/order-item.model';

export class Purchase implements BaseEntity {
    constructor(
        public id?: number,
        public date?: any,
        public supplier?: string,
        public totalAmount?: number,
        public buyerId?: number,
        public buyerName?: string,
        public orderItems?: OrderItem[],
        public warehouseId?: number,
        public warehouseName?: string,
    ) {
    }
}
