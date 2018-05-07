import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { PurchaseComponent } from './purchase.component';
import { PurchaseDetailComponent } from './purchase-detail.component';
import { PurchasePopupComponent } from './purchase-dialog.component';
import { PurchaseDeletePopupComponent } from './purchase-delete-dialog.component';

export const purchaseRoute: Routes = [
    {
        path: 'purchase',
        component: PurchaseComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.purchase.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'purchase/:id',
        component: PurchaseDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.purchase.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const purchasePopupRoute: Routes = [
    {
        path: 'purchase-new',
        component: PurchasePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.purchase.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'purchase/:id/edit',
        component: PurchasePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.purchase.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'purchase/:id/delete',
        component: PurchaseDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.purchase.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
