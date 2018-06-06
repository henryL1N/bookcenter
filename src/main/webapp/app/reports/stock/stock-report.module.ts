import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BookCenterSharedModule } from '../../shared';
import {
    StockReportComponent,
    WarehouseComponent,
    stockReportRoute,
    StockReportResolvePagingParams,
    StockReportService
} from './';

const ENTITY_STATES = [
    ...stockReportRoute,
];

@NgModule({
    imports: [
        BookCenterSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        StockReportComponent,
        WarehouseComponent,
    ],
    entryComponents: [
        StockReportComponent,
        WarehouseComponent,
    ],
    providers: [
        StockReportService,
        StockReportResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookCenterStockReportModule {}
