CREATE TABLE products (
    prod_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    vend_id UUID NOT NULL,
    prod_name VARCHAR(100) NOT NULL,
    prod_price DECIMAL(10, 2) NOT NULL,
    prod_desc TEXT,
    FOREIGN KEY (vend_id) REFERENCES vendors(vend_id)
);