CREATE TABLE vendors (
    vend_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    vend_name VARCHAR(100) NOT NULL,
    vend_address VARCHAR(255),
    vend_city VARCHAR(100),
    vend_state VARCHAR(50),
    vend_zip VARCHAR(20),
    vend_country VARCHAR(50)
);