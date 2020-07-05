package com.e_moradi.testproject.view.invoice_activity;

import com.e_moradi.testproject.R;

public class InvoiceHome {

    private String name;
    private int imageResourceId;
    public static final InvoiceHome[] invoiceHomes = {
            new InvoiceHome("ثبت فاکتور جدید ", R.drawable.ic_add_circle_black_24dp),
            new InvoiceHome("لیست فاکتورها ", R.drawable.ic_list_black_24dp),
            new InvoiceHome("تصاویر فاکتورها ", R.drawable.ic_view_carousel_black_24dp),
            new InvoiceHome("آموزش اپلیکشن ", R.drawable.ic_book_black_24dp),
            new InvoiceHome("اشتراک گذاری اپلیکیشن ", R.drawable.ic_share_black_24dp),
            new InvoiceHome("تنظیمات ", R.drawable.ic_settings_black_24dp)
    };

    private InvoiceHome(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

}
