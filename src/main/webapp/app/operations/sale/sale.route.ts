import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { SaleComponent } from './sale.component';
import { SaleDetailComponent } from './sale-detail.component';
import { SalePopupComponent } from './sale-dialog.component';
import { SaleDeletePopupComponent } from './sale-delete-dialog.component';

@Injectable()
export class SaleResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const saleRoute: Routes = [
    {
        path: 'sale',
        component: SaleComponent,
        resolve: {
            'pagingParams': SaleResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.sale.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'sale/:id',
        component: SaleDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.sale.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const salePopupRoute: Routes = [
    {
        path: 'sale-new',
        component: SalePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.sale.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'sale/:id/edit',
        component: SalePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.sale.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'sale/:id/delete',
        component: SaleDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.sale.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
