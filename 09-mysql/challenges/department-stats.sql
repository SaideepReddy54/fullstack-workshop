select department,count(*) as employee_count,round(avg(salary),2) as avg_salary,max(salary) as max_salary from employees 
group by department 
having count(*)>2; 