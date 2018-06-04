import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BookCenterSharedModule } from '../../shared';
import {
    SalaryReportComponent,
    salaryReportRoute,
    SalaryReportResolvePagingParams,
    SalaryReportService
} from './';

const ENTITY_STATES = [
    ...salaryReportRoute,
];

@NgModule({
    imports: [
        BookCenterSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SalaryReportComponent,
    ],
    entryComponents: [
        SalaryReportComponent,
    ],
    providers: [
        SalaryReportService,
        SalaryReportResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookCenterSalaryReportModule {}
