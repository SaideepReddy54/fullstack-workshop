select p.name as project_name,p.budget,count(p.id) as team_size,sum(a.hours_allocated) total_hours 
from projects p join assignments a
on p.id=a.project_id 
where p.budget>50000
group by a.project_id
order by total_hours desc;