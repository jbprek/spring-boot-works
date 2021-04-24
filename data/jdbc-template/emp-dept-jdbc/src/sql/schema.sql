create table departments
(
    id   int primary key,
    name varchar(20) not null
);


create table employees
(
    id            varchar(20) primary key,
    hire_date     date        not null,
    first_name    varchar(50) not null,
    last_name     varchar(50) not null,
    department_id int,
    foreign key (department_id) references departments (id) on delete cascade on update cascade
);

create procedure sproc_dept_emp_rep1(IN in_dept_id int)
    reads sql data dynamic result sets 1
begin atomic
declare result cursor with return for
    select e.id, e.hire_date, e.last_name, e.first_name
    from departments d inner join employees e
                                  on d.id = e.department_id
    where d.id = in_dept_id;
-- you can have more more statements here ...
open result;
end;

