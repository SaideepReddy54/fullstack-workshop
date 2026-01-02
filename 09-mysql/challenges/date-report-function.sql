delimiter $$
create function GetProjectStatus(project_id INT)
	returns varchar(20)
    deterministic
    begin
		declare s_date date;
        declare e_date date;
        
        
        select start_date,end_date
        into s_date,e_date
        from projects
        where id=project_id;
        if s_date is null then
			return 'unknown';
		elseif s_date>curdate() then
			return 'not started';
		elseif e_date<curdate() then
			return 'overdue';
		elseif e_date < curdate() then
			return 'completed';
		else 
			return 'in progress';
         end if;   
	end $$
   delimiter ;
   
SELECT 
    name,
    start_date,
    end_date,
    GetProjectStatus(id) AS status
FROM projects;
		