import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { SalesReportComponent } from './sales-report.component';
import {DepartmentComponent} from './department.component';

@Injectable()
export class SalesReportResolvePagingParams implements Resolve<any> {

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

export const salesReportRoute: Routes = [
    {
        path: 'salesReport',
        component: DepartmentComponent,
        resolve: {
            'pagingParams': SalesReportResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.salesReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'salesReport/:departmentId',
        component: SalesReportComponent,
        data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.salesReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'salesReport/:departmentId/:year',
        component: SalesReportComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.salesReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'salesReport/:departmentId/:year/:month',
        component: SalesReportComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.salesReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];
