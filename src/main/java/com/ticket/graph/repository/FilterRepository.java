package com.ticket.graph.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.ticket.graph.entity.Filter;

public interface FilterRepository extends JpaRepository<Filter, Long>{

	@Query("select t from Filter t where t.priority= :priority and t.configurationItem= :configuration")
	List<Filter> findByPriorityAndConfiguration(@Param("priority") String priority, @Param("configuration") String configuration);
	
	@Query("select t from Filter t where t.taskType= :taskType and t.configurationItem= :configuration")
	List<Filter> findByTaskTypeAndConfiguration(@Param("taskType") String taskType, @Param("configuration") String configuration);

	@Query("select t from Filter t where t.number= :number")
	Filter findByNumber(@Param("number") String number);

}
