import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BookCenterSharedModule } from '../../shared';
import {
    BookCenterService,
    BookCenterPopupService,
    BookCenterComponent,
    BookCenterDetailComponent,
    BookCenterDialogComponent,
    BookCenterPopupComponent,
    BookCenterDeletePopupComponent,
    BookCenterDeleteDialogComponent,
    bookCenterRoute,
    bookCenterPopupRoute,
} from './';

const ENTITY_STATES = [
    ...bookCenterRoute,
    ...bookCenterPopupRoute,
];

@NgModule({
    imports: [
        BookCenterSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        BookCenterComponent,
        BookCenterDetailComponent,
        BookCenterDialogComponent,
        BookCenterDeleteDialogComponent,
        BookCenterPopupComponent,
        BookCenterDeletePopupComponent,
    ],
    entryComponents: [
        BookCenterComponent,
        BookCenterDialogComponent,
        BookCenterPopupComponent,
        BookCenterDeleteDialogComponent,
        BookCenterDeletePopupComponent,
    ],
    providers: [
        BookCenterService,
        BookCenterPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookCenterBookCenterModule {}
