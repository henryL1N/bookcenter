import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {ProfitReport} from './profit-report.model';
import {ProfitReportService} from './profit-report.service';

@Component({
    selector: 'jhi-profit-report',
    templateUrl: './profit-report.component.html'
})
export class ProfitReportComponent implements OnInit, OnDestroy {

currentAccount: any;
    profitReports: ProfitReport[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;
    year: number;
    month: number;
    years: number[];
    months: number[];

    constructor(
        private profitReportService: ProfitReportService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe((data) => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    loadAll() {
        // this.profitReportService.query({
        //     page: this.page - 1,
        //     size: this.itemsPerPage,
        //     sort: this.sort()}).subscribe(
        //         (res: HttpResponse<ProfitReport[]>) => this.onSuccess(res.body, res.headers),
        //         (res: HttpErrorResponse) => this.onError(res.message)
        // );
        this.profitReportService.query({}).subscribe(
            (res: HttpResponse<ProfitReport[]>) => this.onSuccess(res.body, res.headers),
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }
    transition() {
        // this.router.navigate(['/book'], {queryParams:
        //     {
        //         page: this.page,
        //         size: this.itemsPerPage,
        //         sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        //     }
        // });
        this.loadAll();
    }

    clear() {
        // this.page = 0;
        // this.router.navigate(['/book', {
        //     page: this.page,
        //     sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
        // }]);
        this.loadAll();
    }
    ngOnInit() {
        this.loadAll();
        // this.principal.identity().then((account) => {
        //     this.currentAccount = account;
        // });
        // this.registerChangeInBooks();
    }

    ngOnDestroy() {
        // this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ProfitReport) {
        return item.id;
    }
    // registerChangeInBooks() {
    //     this.eventSubscriber = this.eventManager.subscribe('bookListModification', (response) => this.loadAll());
    // }

    sort() {
        // const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        // if (this.predicate !== 'id') {
        //     result.push('id');
        // }
        // return result;
    }

    private onSuccess(data, headers) {
        // this.links = this.parseLinks.parse(headers.get('link'));
        // this.totalItems = headers.get('X-Total-Count');
        // this.queryCount = this.totalItems;
        // this.page = pagingParams.page;
        this.profitReports = data;
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
