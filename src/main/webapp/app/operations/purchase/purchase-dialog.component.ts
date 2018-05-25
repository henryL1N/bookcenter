import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Purchase } from './purchase.model';
import { PurchasePopupService } from './purchase-popup.service';
import { PurchaseService } from './purchase.service';
import { Employee, EmployeeService } from '../../entities/employee';
import { Warehouse, WarehouseService } from '../../entities/warehouse';
import {Book, BookService} from '../../entities/book';
import {OrderItem} from '../../entities/order-item/order-item.model';
import {DatePipe} from '@angular/common';

@Component({
    selector: 'jhi-purchase-dialog',
    templateUrl: './purchase-dialog.component.html'
})
export class PurchaseDialogComponent implements OnInit {

    purchase: Purchase;
    isSaving: boolean;

    buyers: Employee[];

    warehouses: Warehouse[];

    books: Book[];

    constructor(
        private datePipe: DatePipe,
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private purchaseService: PurchaseService,
        private employeeService: EmployeeService,
        private warehouseService: WarehouseService,
        private bookService: BookService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.employeeService
            .query({filter: 'purchase-is-null'})
            .subscribe((res: HttpResponse<Employee[]>) => {
                if (!this.purchase.buyerId) {
                    this.buyers = res.body;
                } else {
                    this.employeeService
                        .find(this.purchase.buyerId)
                        .subscribe((subRes: HttpResponse<Employee>) => {
                            this.buyers = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.warehouseService.query()
            .subscribe((res: HttpResponse<Warehouse[]>) => { this.warehouses = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.bookService.query()
            .subscribe((res: HttpResponse<Book[]>) => { this.books = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        if (!this.purchase.date) {
            this.purchase.date = this.datePipe.transform(new Date(), 'yyyy-MM-ddTHH:mm:ss');
        }
        this.updateTotalAmount();
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.purchase.id !== undefined) {
            this.subscribeToSaveResponse(
                this.purchaseService.update(this.purchase));
        } else {
            this.subscribeToSaveResponse(
                this.purchaseService.create(this.purchase));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Purchase>>) {
        result.subscribe((res: HttpResponse<Purchase>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Purchase) {
        this.eventManager.broadcast({ name: 'purchaseListModification', content: 'OK'});
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

    trackWarehouseById(index: number, item: Warehouse) {
        return item.id;
    }

    trackBookById(index: number, item: Book) {
        return item.id;
    }

    trackOrderItemId(index: number, item: OrderItem) {
        return item.id;
    }

    trackOrderItemIndex(index: number, item: number) {
        return item;
    }

    addOrderItem() {
        if (!this.purchase.orderItems) {
            this.purchase.orderItems = [];
        }
        this.purchase.orderItems.push(new OrderItem);
    }

    removeOrderItem(index: number) {
        this.purchase.orderItems.splice(index, 1);
        this.updateTotalAmount();
    }

    updateTotalAmount() {
        this.purchase.totalAmount = 0;
        for (const orderItem of this.purchase.orderItems) {
            if (orderItem.price > 0 && orderItem.quantity > 0) {
                this.purchase.totalAmount += orderItem.price * orderItem.quantity;
            }
        }
    }
}

@Component({
    selector: 'jhi-purchase-popup',
    template: ''
})
export class PurchasePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private purchasePopupService: PurchasePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.purchasePopupService
                    .open(PurchaseDialogComponent as Component, params['id']);
            } else {
                this.purchasePopupService
                    .open(PurchaseDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
