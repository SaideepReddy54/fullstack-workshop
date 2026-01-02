select e1.name as employee_name,e2.name as manager_name 
from employees e1 join employees e2 on e2.id=e1.manager_id;