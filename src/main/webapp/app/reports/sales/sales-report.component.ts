import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { ITEMS_PER_PAGE, Principal } from '../../shared';
import {SalesReport} from './sales-report.model';
import {SalesReportService} from './sales-report.service';

@Component({
    selector: 'jhi-sales-report',
    templateUrl: './sales-report.component.html'
})
export class SalesReportComponent implements OnInit, OnDestroy {

currentAccount: any;
    salesReports: SalesReport[];
    error: any;
    success: any;
    private subscription: Subscription;
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
    departmentId: number;
    year: number;
    month: number;

    constructor(
        private salesReportService: SalesReportService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private router: Router,
        private eventManager: JhiEventManager
    ) {
        // this.itemsPerPage = ITEMS_PER_PAGE;
        // this.routeData = this.activatedRoute.data.subscribe((data) => {
        //     this.page = data.pagingParams.page;
        //     this.previousPage = data.pagingParams.page;
        //     this.reverse = data.pagingParams.ascending;
        //     this.predicate = data.pagingParams.predicate;
        // });
    }

    loadAll() {
        this.salesReportService.query({}).subscribe(
            (res: HttpResponse<SalesReport[]>) => this.onSuccess(res.body, res.headers),
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
        this.loadAll();
    }

    clear() {
        this.loadAll();
    }
    ngOnInit() {
        this.subscription = this.activatedRoute.params.subscribe((params) => {
            this.load(params['departmentId'], params['year'], params['month']);
        });
        // this.loadAll();
    }

    load(departmentId, year, month) {
        if (null == year || null == month) {
            let today = new Date();
            year=today.getFullYear();
            month=today.getMonth();
        }
        this.departmentId=+departmentId;
        this.year=+year;
        this.month=+month;
        this.salesReportService.find(departmentId, year, month).subscribe(
            (res: HttpResponse<SalesReport[]>) => this.onSuccess(res.body, res.headers),
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnDestroy() {
    }

    trackId(index: number, item: SalesReport) {
        return item.id;
    }

    private onSuccess(data, headers) {
        this.salesReports = data;
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

    previousYear() {
        if(this.month==1) {
            return this.year-1;
        }else{
            return this.year;
        }
    }

    previouseMonth() {
        if(this.month==1) {
            return 12;
        }else{
            return this.month-1;
        }
    }

    nextYear() {
        if(this.month==12) {
            return this.year+1;
        }else{
            return this.year;
        }
    }

    nextMonth() {
        if(this.month==12) {
            return 1;
        }else{
            return this.month+1;
        }
    }
}
