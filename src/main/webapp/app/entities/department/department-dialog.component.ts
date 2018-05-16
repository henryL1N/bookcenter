import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Department } from './department.model';
import { DepartmentPopupService } from './department-popup.service';
import { DepartmentService } from './department.service';
import { BookCenter, BookCenterService } from '../book-center';
import { Employee, EmployeeService } from '../employee';

@Component({
    selector: 'jhi-department-dialog',
    templateUrl: './department-dialog.component.html'
})
export class DepartmentDialogComponent implements OnInit {

    department: Department;
    isSaving: boolean;

    bookcenters: BookCenter[];

    managers: Employee[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private departmentService: DepartmentService,
        private bookCenterService: BookCenterService,
        private employeeService: EmployeeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.bookCenterService.query()
            .subscribe((res: HttpResponse<BookCenter[]>) => { this.bookcenters = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.employeeService
            .query({filter: 'department-is-null'})
            .subscribe((res: HttpResponse<Employee[]>) => {
                if (!this.department.managerId) {
                    this.managers = res.body;
                } else {
                    this.employeeService
                        .find(this.department.managerId)
                        .subscribe((subRes: HttpResponse<Employee>) => {
                            this.managers = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.department.id !== undefined) {
            this.subscribeToSaveResponse(
                this.departmentService.update(this.department));
        } else {
            this.subscribeToSaveResponse(
                this.departmentService.create(this.department));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Department>>) {
        result.subscribe((res: HttpResponse<Department>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Department) {
        this.eventManager.broadcast({ name: 'departmentListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackBookCenterById(index: number, item: BookCenter) {
        return item.id;
    }

    trackEmployeeById(index: number, item: Employee) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-department-popup',
    template: ''
})
export class DepartmentPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private departmentPopupService: DepartmentPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.departmentPopupService
                    .open(DepartmentDialogComponent as Component, params['id']);
            } else {
                this.departmentPopupService
                    .open(DepartmentDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
