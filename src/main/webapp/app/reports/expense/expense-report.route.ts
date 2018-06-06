import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { ExpenseReportComponent } from './expense-report.component';

@Injectable()
export class ExpenseReportResolvePagingParams implements Resolve<any> {

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

export const expenseReportRoute: Routes = [
    {
        path: 'expenseReport',
        component: ExpenseReportComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.expenseReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'expenseReport/:year',
        component: ExpenseReportComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.expenseReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'expenseReport/:year/:month',
        component: ExpenseReportComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.expenseReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];
