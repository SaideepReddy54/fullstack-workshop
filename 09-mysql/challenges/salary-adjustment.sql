delimiter $$
create procedure AdjustDepartmentSalary(IN dept VARCHAR(50), IN percent DECIMAL(5,2), OUT affected_count INT)
	begin
    if percent < 0 then
		set affected_count=0;
	else
		update employees
        set salary=salary+(salary*percent/100)
        where department=dept;
        set affected_count=row_count();
	end if;
    end $$
delimiter ;
SET SQL_SAFE_UPDATES = 0;
call AdjustDepartmentSalary('Engineering',10,@count)
select @count as affectedrows from employees;