select department,name,salary,rank_in_dept 
from (select department,name,salary,row_number() over(partition by department order by salary desc) as rank_in_dept 
from employees)ranked_employees where rank_in_dept<=3 order by department,rank_in_dept;