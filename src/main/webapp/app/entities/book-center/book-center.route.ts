import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { BookCenterComponent } from './book-center.component';
import { BookCenterDetailComponent } from './book-center-detail.component';
import { BookCenterPopupComponent } from './book-center-dialog.component';
import { BookCenterDeletePopupComponent } from './book-center-delete-dialog.component';

export const bookCenterRoute: Routes = [
    {
        path: 'book-center',
        component: BookCenterComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.bookCenter.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'book-center/:id',
        component: BookCenterDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.bookCenter.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const bookCenterPopupRoute: Routes = [
    {
        path: 'book-center-new',
        component: BookCenterPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.bookCenter.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'book-center/:id/edit',
        component: BookCenterPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.bookCenter.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'book-center/:id/delete',
        component: BookCenterDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.bookCenter.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
