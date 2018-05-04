import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PublisherComponent } from './publisher.component';
import { PublisherDetailComponent } from './publisher-detail.component';
import { PublisherPopupComponent } from './publisher-dialog.component';
import { PublisherDeletePopupComponent } from './publisher-delete-dialog.component';

export const publisherRoute: Routes = [
    {
        path: 'publisher',
        component: PublisherComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.publisher.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'publisher/:id',
        component: PublisherDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.publisher.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const publisherPopupRoute: Routes = [
    {
        path: 'publisher-new',
        component: PublisherPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.publisher.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'publisher/:id/edit',
        component: PublisherPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.publisher.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'publisher/:id/delete',
        component: PublisherDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.publisher.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
