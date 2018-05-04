/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { BookCenterTestModule } from '../../../test.module';
import { PublisherDetailComponent } from '../../../../../../main/webapp/app/entities/publisher/publisher-detail.component';
import { PublisherService } from '../../../../../../main/webapp/app/entities/publisher/publisher.service';
import { Publisher } from '../../../../../../main/webapp/app/entities/publisher/publisher.model';

describe('Component Tests', () => {

    describe('Publisher Management Detail Component', () => {
        let comp: PublisherDetailComponent;
        let fixture: ComponentFixture<PublisherDetailComponent>;
        let service: PublisherService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BookCenterTestModule],
                declarations: [PublisherDetailComponent],
                providers: [
                    PublisherService
                ]
            })
            .overrideTemplate(PublisherDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PublisherDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PublisherService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new Publisher(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.publisher).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
