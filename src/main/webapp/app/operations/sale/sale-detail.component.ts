import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Sale } from './sale.model';
import { SaleService } from './sale.service';
import {OrderItem} from '../../entities/order-item/order-item.model';

@Component({
    selector: 'jhi-sale-detail',
    templateUrl: './sale-detail.component.html'
})
export class SaleDetailComponent implements OnInit, OnDestroy {

    sale: Sale;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private saleService: SaleService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInSales();
    }

    load(id) {
        this.saleService.find(id)
            .subscribe((saleResponse: HttpResponse<Sale>) => {
                this.sale = saleResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInSales() {
        this.eventSubscriber = this.eventManager.subscribe(
            'saleListModification',
            (response) => this.load(this.sale.id)
        );
    }

    trackOrderItemId(index: number, item: OrderItem) {
        return item.id;
    }
}
