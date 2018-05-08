import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { PublisherComponent } from './publisher.component';
import { PublisherDetailComponent } from './publisher-detail.component';
import { PublisherPopupComponent } from './publisher-dialog.component';
import { PublisherDeletePopupComponent } from './publisher-delete-dialog.component';

@Injectable()
export class PublisherResolvePagingParams implements Resolve<any> {

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

export const publisherRoute: Routes = [
    {
        path: 'publisher',
        component: PublisherComponent,
        resolve: {
            'pagingParams': PublisherResolvePagingParams
        },
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
