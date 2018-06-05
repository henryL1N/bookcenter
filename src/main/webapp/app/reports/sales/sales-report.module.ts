import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BookCenterSharedModule } from '../../shared';
import {
    SalesReportComponent,
    DepartmentComponent,
    salesReportRoute,
    SalesReportResolvePagingParams,
    SalesReportService
} from './';

const ENTITY_STATES = [
    ...salesReportRoute,
];

@NgModule({
    imports: [
        BookCenterSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SalesReportComponent,
        DepartmentComponent,
    ],
    entryComponents: [
        SalesReportComponent,
        DepartmentComponent,
    ],
    providers: [
        SalesReportService,
        SalesReportResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookCenterSalesReportModule {}
