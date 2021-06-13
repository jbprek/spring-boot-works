create table employees
(
    id            varchar(20) primary key,
    hire_date     date        not null,
    first_name    varchar(50) not null,
    last_name     varchar(50) not null,
    department_id int not null,
    foreign key (department_id) references departments (id) on delete cascade on update cascade
);

create table departments
(
    id   int primary key,
    name varchar(20) not null
);