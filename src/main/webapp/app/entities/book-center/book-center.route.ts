import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { BookCenterComponent } from './book-center.component';
import { BookCenterDetailComponent } from './book-center-detail.component';
import { BookCenterPopupComponent } from './book-center-dialog.component';
import { BookCenterDeletePopupComponent } from './book-center-delete-dialog.component';

@Injectable()
export class BookCenterResolvePagingParams implements Resolve<any> {

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

export const bookCenterRoute: Routes = [
    {
        path: 'book-center',
        component: BookCenterComponent,
        resolve: {
            'pagingParams': BookCenterResolvePagingParams
        },
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
