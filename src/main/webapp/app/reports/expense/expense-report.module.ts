import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BookCenterSharedModule } from '../../shared';
import {
    ExpenseReportComponent,
    expenseReportRoute,
    ExpenseReportResolvePagingParams,
    ExpenseReportService
} from './';

const ENTITY_STATES = [
    ...expenseReportRoute,
];

@NgModule({
    imports: [
        BookCenterSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ExpenseReportComponent,
    ],
    entryComponents: [
        ExpenseReportComponent,
    ],
    providers: [
        ExpenseReportService,
        ExpenseReportResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookCenterExpenseReportModule {}
