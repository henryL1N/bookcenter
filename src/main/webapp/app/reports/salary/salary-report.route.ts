import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { SalaryReportComponent } from './salary-report.component';

@Injectable()
export class SalaryReportResolvePagingParams implements Resolve<any> {

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

export const salaryReportRoute: Routes = [
    {
        path: 'salaryReport',
        component: SalaryReportComponent,
        resolve: {
            'pagingParams': SalaryReportResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.salaryReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];
