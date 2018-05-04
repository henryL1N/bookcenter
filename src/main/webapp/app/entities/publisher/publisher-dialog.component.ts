import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Publisher } from './publisher.model';
import { PublisherPopupService } from './publisher-popup.service';
import { PublisherService } from './publisher.service';

@Component({
    selector: 'jhi-publisher-dialog',
    templateUrl: './publisher-dialog.component.html'
})
export class PublisherDialogComponent implements OnInit {

    publisher: Publisher;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private publisherService: PublisherService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.publisher.id !== undefined) {
            this.subscribeToSaveResponse(
                this.publisherService.update(this.publisher));
        } else {
            this.subscribeToSaveResponse(
                this.publisherService.create(this.publisher));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Publisher>>) {
        result.subscribe((res: HttpResponse<Publisher>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Publisher) {
        this.eventManager.broadcast({ name: 'publisherListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-publisher-popup',
    template: ''
})
export class PublisherPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private publisherPopupService: PublisherPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.publisherPopupService
                    .open(PublisherDialogComponent as Component, params['id']);
            } else {
                this.publisherPopupService
                    .open(PublisherDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
