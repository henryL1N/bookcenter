import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { BookCenterPurchaseModule } from './purchase/purchase.module';
// import { BookCenterSalesModule } from './sales/sales.module';
// import { BookCenterInventoryModule } from './inventory/inventory.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        BookCenterPurchaseModule,
        // BookCenterSalesModule,
        // BookCenterInventoryModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookCenterOperationModule {}
