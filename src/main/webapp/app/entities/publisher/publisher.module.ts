import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BookCenterSharedModule } from '../../shared';
import {
    PublisherService,
    PublisherPopupService,
    PublisherComponent,
    PublisherDetailComponent,
    PublisherDialogComponent,
    PublisherPopupComponent,
    PublisherDeletePopupComponent,
    PublisherDeleteDialogComponent,
    publisherRoute,
    publisherPopupRoute,
    PublisherResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...publisherRoute,
    ...publisherPopupRoute,
];

@NgModule({
    imports: [
        BookCenterSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        PublisherComponent,
        PublisherDetailComponent,
        PublisherDialogComponent,
        PublisherDeleteDialogComponent,
        PublisherPopupComponent,
        PublisherDeletePopupComponent,
    ],
    entryComponents: [
        PublisherComponent,
        PublisherDialogComponent,
        PublisherPopupComponent,
        PublisherDeleteDialogComponent,
        PublisherDeletePopupComponent,
    ],
    providers: [
        PublisherService,
        PublisherPopupService,
        PublisherResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookCenterPublisherModule {}
