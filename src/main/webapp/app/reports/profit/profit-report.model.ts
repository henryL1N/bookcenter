import { BaseEntity } from './../../shared';

export class ProfitReport implements BaseEntity {
    constructor(
        public id?: number,
        public year?: number,
        public month?: number,
        public profit?: number,
    ) {
    }
}
