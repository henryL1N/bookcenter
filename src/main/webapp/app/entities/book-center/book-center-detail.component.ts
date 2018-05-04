import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { BookCenter } from './book-center.model';
import { BookCenterService } from './book-center.service';

@Component({
    selector: 'jhi-book-center-detail',
    templateUrl: './book-center-detail.component.html'
})
export class BookCenterDetailComponent implements OnInit, OnDestroy {

    bookCenter: BookCenter;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private bookCenterService: BookCenterService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInBookCenters();
    }

    load(id) {
        this.bookCenterService.find(id)
            .subscribe((bookCenterResponse: HttpResponse<BookCenter>) => {
                this.bookCenter = bookCenterResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInBookCenters() {
        this.eventSubscriber = this.eventManager.subscribe(
            'bookCenterListModification',
            (response) => this.load(this.bookCenter.id)
        );
    }
}
