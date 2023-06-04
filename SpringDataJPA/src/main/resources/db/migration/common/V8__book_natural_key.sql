create table book_natural
(
    title     varchar(255) not null,
    isbn      varchar(255),
    publisher varchar(255),
    primary key (title)
) engine = InnoDB;

create table author_composite
(
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    country varchar(255),
    primary key (first_name,last_name)
);