import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BookCenterSharedModule } from '../../shared';
import {
    PurchaseOrderService,
    PurchaseOrderPopupService,
} from '../../entities/purchase-order';
import {
    PurchaseComponent,
    PurchaseDetailComponent,
    PurchaseDialogComponent,
    PurchasePopupComponent,
    PurchaseDeletePopupComponent,
    PurchaseDeleteDialogComponent,
    purchaseRoute,
    purchasePopupRoute,
} from './';

const ENTITY_STATES = [
    ...purchaseRoute,
    ...purchasePopupRoute,
];

@NgModule({
    imports: [
        BookCenterSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        PurchaseComponent,
        PurchaseDetailComponent,
        PurchaseDialogComponent,
        PurchaseDeleteDialogComponent,
        PurchasePopupComponent,
        PurchaseDeletePopupComponent,
    ],
    entryComponents: [
        PurchaseComponent,
        PurchaseDialogComponent,
        PurchasePopupComponent,
        PurchaseDeleteDialogComponent,
        PurchaseDeletePopupComponent,
    ],
    providers: [
        PurchaseOrderService,
        PurchaseOrderPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookCenterPurchaseModule {}
