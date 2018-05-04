import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { BookCenter } from './book-center.model';
import { BookCenterPopupService } from './book-center-popup.service';
import { BookCenterService } from './book-center.service';
import { Employee, EmployeeService } from '../employee';

@Component({
    selector: 'jhi-book-center-dialog',
    templateUrl: './book-center-dialog.component.html'
})
export class BookCenterDialogComponent implements OnInit {

    bookCenter: BookCenter;
    isSaving: boolean;

    generalmanagers: Employee[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private bookCenterService: BookCenterService,
        private employeeService: EmployeeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.employeeService
            .query({filter: 'bookcenter-is-null'})
            .subscribe((res: HttpResponse<Employee[]>) => {
                if (!this.bookCenter.generalManager || !this.bookCenter.generalManager.id) {
                    this.generalmanagers = res.body;
                } else {
                    this.employeeService
                        .find(this.bookCenter.generalManager.id)
                        .subscribe((subRes: HttpResponse<Employee>) => {
                            this.generalmanagers = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.bookCenter.id !== undefined) {
            this.subscribeToSaveResponse(
                this.bookCenterService.update(this.bookCenter));
        } else {
            this.subscribeToSaveResponse(
                this.bookCenterService.create(this.bookCenter));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<BookCenter>>) {
        result.subscribe((res: HttpResponse<BookCenter>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: BookCenter) {
        this.eventManager.broadcast({ name: 'bookCenterListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackEmployeeById(index: number, item: Employee) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-book-center-popup',
    template: ''
})
export class BookCenterPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private bookCenterPopupService: BookCenterPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.bookCenterPopupService
                    .open(BookCenterDialogComponent as Component, params['id']);
            } else {
                this.bookCenterPopupService
                    .open(BookCenterDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
