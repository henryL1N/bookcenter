import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BookCenterSharedModule } from '../../shared';
import {
    ProfitReportComponent,
    profitReportRoute,
    ProfitReportResolvePagingParams,
    ProfitReportService
} from './';

const ENTITY_STATES = [
    ...profitReportRoute,
];

@NgModule({
    imports: [
        BookCenterSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ProfitReportComponent,
    ],
    entryComponents: [
        ProfitReportComponent,
    ],
    providers: [
        ProfitReportService,
        ProfitReportResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookCenterProfitReportModule {}
