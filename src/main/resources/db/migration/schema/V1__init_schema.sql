CREATE TABLE vehicles (
                          id VARCHAR(36) NOT NULL,
                          brand VARCHAR(100) NOT NULL,
                          model VARCHAR(100) NOT NULL,
                          manufacture_year  INT NOT NULL,
                          color VARCHAR(50) NOT NULL,
                          price DECIMAL(15,2) NOT NULL,
                          status VARCHAR(20) NOT NULL,

                          CONSTRAINT pk_vehicles PRIMARY KEY (id),
                          CONSTRAINT chk_vehicle_price_positive CHECK (price >= 0)
);

CREATE TABLE sales (
                       id VARCHAR(36) NOT NULL,
                       vehicle_id VARCHAR(36) NOT NULL,
                       buyer_cpf VARCHAR(11) NOT NULL,
                       sale_date TIMESTAMP NOT NULL,
                       status VARCHAR(20) NOT NULL,

                       CONSTRAINT pk_sales PRIMARY KEY (id),
                       CONSTRAINT fk_sales_vehicle
                           FOREIGN KEY (vehicle_id) REFERENCES vehicles(id)
);

CREATE TABLE payments (
                          id VARCHAR(36) NOT NULL,
                          sale_id VARCHAR(36) NOT NULL,
                          status VARCHAR(20) NOT NULL,

                          CONSTRAINT pk_payments PRIMARY KEY (id),
                          CONSTRAINT fk_payments_sale
                              FOREIGN KEY (sale_id) REFERENCES sales(id)
);

CREATE INDEX idx_vehicle_status_price
    ON vehicles (status, price);

CREATE INDEX idx_sale_status
    ON sales (status);

CREATE INDEX idx_payment_status
    ON payments (status);