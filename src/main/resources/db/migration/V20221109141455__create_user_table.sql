create table sp.user(
    id int primary key,
    login varchar not null,
    password varchar not null
);

insert into sp.user
values
        (1, 'testUser', '12345');