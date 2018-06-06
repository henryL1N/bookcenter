import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { BookCenterProfitReportModule } from './profit/profit-report.module';
import { BookCenterSalaryReportModule } from './salary/salary-report.module';
import { BookCenterSalesReportModule } from './sales/sales-report.module';
import { BookCenterStockReportModule } from './stock/stock-report.module';
// import { BookCenterExpenseReportModule } from './expense/stock-report.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        BookCenterProfitReportModule,
        BookCenterSalaryReportModule,
        BookCenterSalesReportModule,
        BookCenterStockReportModule,
        // BookCenterExpenseReportModule
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookCenterReportModule {}
