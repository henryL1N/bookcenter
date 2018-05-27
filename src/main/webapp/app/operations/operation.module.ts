import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { BookCenterPurchaseModule } from './purchase/purchase.module';
import {BookCenterSaleModule } from './sale/sale.module';
import { BookCenterStockModule } from './stock/stock.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        BookCenterPurchaseModule,
        BookCenterSaleModule,
        BookCenterStockModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookCenterOperationModule {}
