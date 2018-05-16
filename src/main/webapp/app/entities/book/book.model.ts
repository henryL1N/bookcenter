import { BaseEntity } from './../../shared';

export class Book implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public specification?: string,
        public cost?: number,
        public retailPrice?: number,
        public wholesalePrice?: number,
        public publisherId?: number,
        public publisherName?: number,
        public categoryId?: number,
        public categoryName?: number,
    ) {
    }
}
