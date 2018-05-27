import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BookCenterSharedModule } from '../../shared';
import {
    StockComponent,
    stockItemRoute,
    stockItemPopupRoute,
} from './';
import {
    StockItemService,
    StockItemPopupService,
    StockItemDetailComponent,
    StockItemDialogComponent,
    StockItemPopupComponent,
    StockItemDeletePopupComponent,
    StockItemDeleteDialogComponent,
    StockItemResolvePagingParams,
} from '../../entities/stock-item';

const ENTITY_STATES = [
    ...stockItemRoute,
    ...stockItemPopupRoute,
];

@NgModule({
    imports: [
        BookCenterSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        StockComponent,
        // StockItemDetailComponent,
        // StockItemDialogComponent,
        // StockItemDeleteDialogComponent,
        // StockItemPopupComponent,
        // StockItemDeletePopupComponent,
    ],
    entryComponents: [
        StockComponent,
        StockItemDialogComponent,
        StockItemPopupComponent,
        StockItemDeleteDialogComponent,
        StockItemDeletePopupComponent,
    ],
    providers: [
        StockItemService,
        StockItemPopupService,
        StockItemResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookCenterStockModule {}
