package org.activiti;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class MyUnitTest {

//	@Rule
//	public ActivitiRule activitiRule = new ActivitiRule();
     public 		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

	@Test
//	@Deployment(resources = {"org/activiti/test/my-process.bpmn20.xml"})
	//发布一个流程
	public void test() {

//		为了让Activiti引擎知道这个流程，我们必须先进行“发布”。 发布意味着引擎会把BPMN 2.0 xml解析成可以执行的东西， “发布包”中的所有流程定义都会添加到数据库中。 这样，当引擎重启时，它依然可以获得“已发布”的流程
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		RepositoryService repositoryService = processEngine.getRepositoryService();
		repositoryService.createDeployment()
				.addClasspathResource("org/activiti/test/VacationRequest.bpmn20.xml")
				.deploy();
		System.out.println(repositoryService.createProcessDefinitionQuery().count());
//		Log.info("Number of process definitions: " + repositoryService.createProcessDefinitionQuery().count());
//		ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
//		assertNotNull(processInstance);
//
//		Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
//		assertEquals("Activiti is awesome!", task.getName());
	}
//	把流程定义发布到Activiti引擎后，我们可以基于它发起新流程实例。 对每个流程定义，都可以有很多流程实例。 流程定义是“蓝图”，流程实例是它的一个运行的执行。
//
//	所有与流程运行状态相关的东西都可以通过RuntimeService获得。 有很多方法可以启动一个新流程实例。在下面的代码中，我们使用定义在流程定义xml 中的key来启动流程实例。 我们也可以在流程实例启动时添加一些流程变量，因为第一个用户任务的表达式需要这些变量。 流程变量经常会被用到，因为它们赋予来自同一个流程定义的不同流程实例的特别含义。 简单来说，流程变量是区分流程实例的关键。
	@Test
	//启动流程实例
	public void test2(){
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("employeeName", "Kermit");
		variables.put("numberOfDays", new Integer(4));
		variables.put("vacationMotivation", "I'm really tired!");

		RuntimeService runtimeService = processEngine.getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequest", variables);
		System.out.println(runtimeService.createProcessInstanceQuery().count());
// Verify that we started a new process instance
//		Log.info("Number of process instances: " + runtimeService.createProcessInstanceQuery().count());
	}
	@Test
//	通常，用户会有一个“任务列表”，展示了所有必须由整个用户处理的任务。 下面的代码展示了对应的查询可能是怎样的
   public void list(){
	   TaskService taskService = processEngine.getTaskService();
	   List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
	   for (Task task : tasks) {
		   System.out.println(task.getName());
//		   Log.info("Task available: " + task.getName());
	   }
//		为了让流程实例继续运行，我们需要完成整个任务。对Activiti来说，就是需要complete任务。 下面的代码展示了如何做这件事：
//		流程实例会进入到下一个环节。在这里例子中， 下一环节允许员工通过表单调整原始的请假申请。员工可以重新提交请假申请， 这会使流程重新进入到第一个任务。
		Task task = tasks.get(0);
		Map<String, Object> taskVariables = new HashMap<String, Object>();
		taskVariables.put("vacationApproved", "false");
		taskVariables.put("managerMotivation", "We have a tight deadline!");
		taskService.complete(task.getId(), taskVariables);
//		有两种方法可以从引擎中查询数据：查询API和原生查询。查询API提供了完全类型安全的API。 你可以为自己的查询条件添加很多条件 （所以条件都以AND组合）和精确的排序条件。下面的代码展示了一个例子：
//		List<Task> tasks2 = taskService.createTaskQuery()
//				.taskAssignee("kermit")
//				.processVariableValueEquals("orderId", "0815")
//				.orderByDueDate().asc()
//				.list();
   }

//	我们可以挂起一个流程定义。当挂起流程定时时， 就不能创建新流程了（会抛出一个异常）。 可以通过RepositoryService挂起一个流程：
  @Test
   public void stopprocess(){
	   ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	   RepositoryService repositoryService = processEngine.getRepositoryService();
//	  要想重新激活一个流程定义，可以调用repositoryService.activateProcessDefinitionXXX方法。
	  repositoryService.suspendProcessDefinitionByKey("vacationRequest");
	   RuntimeService runtimeService = processEngine.getRuntimeService();
	   try {
		   runtimeService.startProcessInstanceByKey("vacationRequest");
	   } catch (ActivitiException e) {
		   e.printStackTrace();
	   }
//	  也可以挂起一个流程实例。挂起时，流程不能继续执行（比如，完成任务会抛出异常）， 异步操作（比如定时器）也不会执行。 骨气流程实例可以调用 runtimeService.suspendProcessInstance方法。 激活流程实例可以调用runtimeService.activateProcessInstanceXXX方法。
   }

}
