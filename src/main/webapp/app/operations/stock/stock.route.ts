import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { StockItemComponent } from './stock.component';
import { StockItemDetailComponent } from './stock-detail.component';
import { StockItemPopupComponent } from './stock-dialog.component';
import { StockItemDeletePopupComponent } from './stock-delete-dialog.component';

@Injectable()
export class StockItemResolvePagingParams implements Resolve<any> {

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

export const stockItemRoute: Routes = [
    {
        path: 'stock',
        component: StockItemComponent,
        resolve: {
            'pagingParams': StockItemResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.stockItem.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'stock-item/:id',
        component: StockItemDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.stockItem.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const stockItemPopupRoute: Routes = [
    {
        path: 'stock-new',
        component: StockItemPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.stockItem.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'stock/:id/edit',
        component: StockItemPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.stockItem.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'stock/:id/delete',
        component: StockItemDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.stockItem.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
