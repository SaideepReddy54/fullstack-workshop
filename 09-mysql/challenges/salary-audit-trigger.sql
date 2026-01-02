delimiter $$
create trigger trg_salary_audit
after update
on employees
for each row
begin
	if old.salary<>new.salary then
    insert into salary_audit(
    employee_id,
    old_salary,
    new_salary,
    change_percent
    ) values (
    old.id,
    old.salary,
    new.salary,
    round(((new.salary-old.salary)/old.salary)*100,2)
    );
    end if;
end $$
delimiter ;

INSERT INTO employees (id, name, salary, department)
VALUES (101, 'Ganesh', 50000, 'IT');


UPDATE employees
SET salary = 60000
WHERE id = 101;

select * from salary_audit