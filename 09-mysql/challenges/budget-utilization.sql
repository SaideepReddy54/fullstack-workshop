delimiter $$
create procedure GetBudgetUtilization()
begin 
	select 
		p.name as project_name,
        p.budget as project_budget,
        ifnull(
			sum(e.salary*a.hours_allocated/2080),0)
            as salary_cost,
		p.budget-ifnull(sum(e.salary*a.hours_allocated/2080),0)
			as remaining,
		round(
			ifnull(
				(sum(e.salary*a.hours_allocated/2080)/p.budget)*100,0
            ),2
        ) as utilization_pct
        from projects p
        left join assignments a on p.id=a.project_id
        left join employees e on a.employee_id=e.id
        group by p.id,p.name,p.budget;
	end $$
delimiter ;

call GetBudgetUtilization()
