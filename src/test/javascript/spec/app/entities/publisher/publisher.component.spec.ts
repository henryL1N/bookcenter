/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { BookCenterTestModule } from '../../../test.module';
import { PublisherComponent } from '../../../../../../main/webapp/app/entities/publisher/publisher.component';
import { PublisherService } from '../../../../../../main/webapp/app/entities/publisher/publisher.service';
import { Publisher } from '../../../../../../main/webapp/app/entities/publisher/publisher.model';

describe('Component Tests', () => {

    describe('Publisher Management Component', () => {
        let comp: PublisherComponent;
        let fixture: ComponentFixture<PublisherComponent>;
        let service: PublisherService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [BookCenterTestModule],
                declarations: [PublisherComponent],
                providers: [
                    PublisherService
                ]
            })
            .overrideTemplate(PublisherComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(PublisherComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PublisherService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new Publisher(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.publishers[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
