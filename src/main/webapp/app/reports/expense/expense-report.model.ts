import { BaseEntity } from './../../shared';

export class ExpenseReport implements BaseEntity {
    constructor(
        public id?: number,
        public departmentId?: number,
        public departmentName?: string,
        public expense?: number,
    ) {
    }
}
