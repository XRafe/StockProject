create table sp.stock(
    id serial primary key,
    name varchar not null
);

create table sp.product(
    id serial primary key,
    article varchar not null,
    name varchar not null,
    count int not null,
    price_last_buy bigint not null,
    price_last_sale bigint null,
    stock_id int not null
        references sp.stock(id)
);
