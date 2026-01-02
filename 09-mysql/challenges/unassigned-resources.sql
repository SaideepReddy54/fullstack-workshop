select e.id,e.name,e.department 
from employees e left join assignments a 
on e.id=a.employee_id 
where a.employee_id is null;
