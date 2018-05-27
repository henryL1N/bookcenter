import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Sale } from './sale.model';
import { SalePopupService } from './sale-popup.service';
import { SaleService } from './sale.service';
import { Employee, EmployeeService } from '../../entities/employee';
import { Warehouse, WarehouseService } from '../../entities/warehouse';
import {Book, BookService} from '../../entities/book';
import {OrderItem} from '../../entities/order-item/order-item.model';
import {DatePipe} from '@angular/common';
import {StockItemService} from '../../entities/stock-item/stock-item.service';
import {StockItem} from '../../entities/stock-item/stock-item.model';

@Component({
    selector: 'jhi-sale-dialog',
    templateUrl: './sale-dialog.component.html'
})
export class SaleDialogComponent implements OnInit {

    sale: Sale;
    isSaving: boolean;

    sellers: Employee[];

    warehouses: Warehouse[];

    stockItems: StockItem[];

    constructor(
        private datePipe: DatePipe,
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private saleService: SaleService,
        private employeeService: EmployeeService,
        private warehouseService: WarehouseService,
        private stockItemService: StockItemService,
        private bookService: BookService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.employeeService
            .query({filter: 'sale-is-null'})
            .subscribe((res: HttpResponse<Employee[]>) => {
                if (!this.sale.sellerId) {
                    this.sellers = res.body;
                } else {
                    this.employeeService
                        .find(this.sale.sellerId)
                        .subscribe((subRes: HttpResponse<Employee>) => {
                            this.sellers = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.warehouseService.query()
            .subscribe((res: HttpResponse<Warehouse[]>) => { this.warehouses = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        if (!this.sale.date) {
            this.sale.date = this.datePipe.transform(new Date(), 'yyyy-MM-ddTHH:mm:ss');
        }
        this.updateTotalAmount();
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.sale.id !== undefined) {
            this.subscribeToSaveResponse(
                this.saleService.update(this.sale));
        } else {
            this.subscribeToSaveResponse(
                this.saleService.create(this.sale));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Sale>>) {
        result.subscribe((res: HttpResponse<Sale>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Sale) {
        this.eventManager.broadcast({ name: 'saleListModification', content: 'OK'});
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

    trackStockItemById(index: number, item: StockItem) {
        return item.id;
    }

    trackOrderItemId(index: number, item: OrderItem) {
        return item.id;
    }

    trackOrderItemIndex(index: number, item: number) {
        return item;
    }

    addOrderItem() {
        if (!this.sale.orderItems) {
            this.sale.orderItems = [];
        }
        this.sale.orderItems.push(new OrderItem);
    }

    removeOrderItem(index: number) {
        this.sale.orderItems.splice(index, 1);
        this.updateTotalAmount();
    }

    updateTotalAmount() {
        this.sale.totalAmount = 0;
        for (const orderItem of this.sale.orderItems) {
            if (orderItem.price > 0 && orderItem.quantity > 0) {
                this.sale.totalAmount += orderItem.price * orderItem.quantity;
            }
        }
    }

    updateStockItems() {
        this.stockItemService.getAllStockItemsByWarehouseId(this.sale.warehouseId)
            .subscribe((res: HttpResponse<StockItem[]>) => { this.stockItems = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }
}

@Component({
    selector: 'jhi-sale-popup',
    template: ''
})
export class SalePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private salePopupService: SalePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.salePopupService
                    .open(SaleDialogComponent as Component, params['id']);
            } else {
                this.salePopupService
                    .open(SaleDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
