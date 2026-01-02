delimiter $$
create function GetTenureCategory(hire_date DATE)
returns varchar(20)
deterministic
begin
	declare years_worked int;
	set years_worked=timestampdiff(year,hire_date,CURDATE());
	if years_worked>5 then 
		return 'veteran';
	elseif years_worked between 2 and 5 then
		return 'experienced';
	else 
		return 'new hire';
	end if;
end $$
delimiter ;

select name,hire_date,GetTenureCategory(hire_date) as tenure_category from employees