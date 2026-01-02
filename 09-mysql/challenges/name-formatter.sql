select concat( upper(substring_index(name, ' ', -1)),', ',substring_index(name, ' ', 1)) formatted_name,
concat(lower(substring_index(name,' ',1)),'.',lower(substring_index(name,' ',-1)),'@company.com') email,
concat(substr(substring_index(name,' ',1),1,1),substr(substring_index(name,' ',-1),1,1)) intials
from  employees;