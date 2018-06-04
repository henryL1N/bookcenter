import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { ProfitReportComponent } from './profit-report.component';

@Injectable()
export class ProfitReportResolvePagingParams implements Resolve<any> {

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

export const profitReportRoute: Routes = [
    {
        path: 'profitReport',
        component: ProfitReportComponent,
        resolve: {
            'pagingParams': ProfitReportResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookCenterApp.profitReport.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];
