import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { StockReportComponent } from './stock-report.component';
import {WarehouseComponent} from './warehouse.component';

@Injectable()
export class StockReportResolvePagingParams implements Resolve<any> {

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

export const stockReportRoute: Routes = [
    {
        path: 'stockReport',
        component: WarehouseComponent,
        resolve: {
            'pagingParams': StockReportResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.stockReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'stockReport/:warehouseId',
        component: StockReportComponent,
        data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.stockReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'stockReport/:warehouseId/:year',
        component: StockReportComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.stockReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'stockReport/:warehouseId/:year/:month',
        component: StockReportComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.stockReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];
