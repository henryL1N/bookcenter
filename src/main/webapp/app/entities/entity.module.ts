import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { BookCenterBookModule } from './book/book.module';
import { BookCenterBookCenterModule } from './book-center/book-center.module';
import { BookCenterCategoryModule } from './category/category.module';
import { BookCenterDepartmentModule } from './department/department.module';
import { BookCenterEmployeeModule } from './employee/employee.module';
import { BookCenterPublisherModule } from './publisher/publisher.module';
import { BookCenterStockItemModule } from './stock-item/stock-item.module';
import { BookCenterWarehouseModule } from './warehouse/warehouse.module';
import { BookCenterPurchaseOrderModule } from './purchase-order/purchase-order.module';
import { BookCenterSalesOrderModule } from './sales-order/sales-order.module';
import { BookCenterOrderItemModule } from './order-item/order-item.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        BookCenterBookModule,
        BookCenterBookCenterModule,
        BookCenterCategoryModule,
        BookCenterDepartmentModule,
        BookCenterEmployeeModule,
        BookCenterPublisherModule,
        BookCenterStockItemModule,
        BookCenterWarehouseModule,
        BookCenterPurchaseOrderModule,
        BookCenterSalesOrderModule,
        BookCenterOrderItemModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookCenterEntityModule {}
