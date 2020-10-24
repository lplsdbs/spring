package com.lpl.activiti;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.AbstractTransactionalSpringContextTests;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

@RunWith(SpringJUnit4ClassRunner.class)
//locations:参数值因配置文件地址来改变
@ContextConfiguration(locations={"classpath:spring/Spring-Context.xml"})
//public class springtest extends AbstractJUnit4SpringContextTests {
public class springtest extends AbstractTransactionalSpringContextTests {
    @Autowired
    TaskService taskService;
    @Autowired
    RuntimeService runtimeService;
    @Autowired
    RepositoryService repositoryService;
    @Autowired
    HistoryService historyService;
    //使用默认配置文件
    @Test
    @Transactional
//    @Rollback(false)
    public void test3(){
        //把挂起的流程解了
//        repositoryService.activateProcessDefinitionByKey("vacationRequest");
//         Map<String, Object> variables = new HashMap<String, Object>();
//        variables.put("employeeName", "Kermit");
//        variables.put("numberOfDays", new Integer(4));
//        variables.put("vacationMotivation", "I'm really tired!");
//        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequest", variables);
//        System.out.println(runtimeService.createProcessInstanceQuery().count());
//        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
//        for (Task task : tasks) {
//            System.out.println(task.getName());
//        }
//        Task task = tasks.get(0);
//        Map<String, Object> taskVariables = new HashMap<String, Object>();
//        taskVariables.put("vacationApproved", "false");
//        taskVariables.put("managerMotivation", "We have a tight deadline!");
//        taskService.complete(task.getId(), taskVariables);
//获得流程图片
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("leavel")
                .singleResult();
        String diagramResourceName = processDefinition.getDiagramResourceName();
        InputStream in = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), diagramResourceName);
//原文件存在，判断目标文件是否存在
        OutputStream out=null;
        File file = new File("D:/test/testIOO.png");
        try {
            if(!file.exists()){
                //目标文件不存在，创建目标文件
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            //将原文件内容读取到目标文件
            out = new FileOutputStream(file);
            IOUtils.copy(in,out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    发布
    @Test
    @Transactional
    public void publicprocess(){
//        repositoryService.createDeployment()
//                .addClasspathResource("test/finacni-report.bpmn20.xml")
//                .deploy();
        System.out.println(repositoryService.createProcessDefinitionQuery().count());
    }
    //启动实例
    @Test
    public void startProcessINstance(){
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("financialReport");
        System.out.println(runtimeService.createProcessInstanceQuery().count());
    }
    @Test
    //用用户和组去查询任务
    public void taskQuery(){
        List<Task> taskss = taskService.createTaskQuery().taskCandidateUser("kermit").list();
        System.out.println(taskss.size());
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();
        System.out.println("ew");
    }

    public void  hisQuery(){
        historyService.createHistoricProcessInstanceQuery()
                .finished();
    }

    //请假流程演示，基本流程
    //部署流程
    @Test
    public void deployFlow(){
//        File file=new File("D:\\project\\webworkspace\\actbpmn\\actbpmn.zip");
        InputStream in = this.getClass().getResourceAsStream("/test/actbpmn2.zip");
//        InputStream in = null;
//        try {test/actbpmn2.zip
//            in = new FileInputStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        ZipInputStream zipInputStream = new ZipInputStream(in);

        Deployment dm = repositoryService.createDeployment()
                .name("学生请假2")
                .addZipInputStream(zipInputStream)
                .deploy();
        System.out.println("id:"+dm.getId()+",name:"+dm.getName());
    }
    /**
     * 启动流程 并完成 提交
     */
    @Test
    public void startProcessAndComp(){
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("myProcess");
        System.out.println("id:"+pi.getId()+",流程实例ID:"+pi.getProcessInstanceId()+",流程定义ID:"+pi.getProcessDefinitionId());
        //通过流程实例ID获取任务对象
        Task task = taskService.createTaskQuery()
                .processInstanceId(pi.getProcessInstanceId())
                .singleResult();
        System.out.println("taskID:"+task.getId()+",name:"+task.getName());

        Map<String, Object> paramMap = new HashMap<String, Object>();
        //设置流程变量day=3
//        paramMap.put("day", 3);
        paramMap.put("day", 10);
        //提交任务的时候传入流程变量
        taskService.complete(task.getId(), paramMap);

        //查询任务
        task = taskService.createTaskQuery()
                .processInstanceId(pi.getProcessInstanceId())
                .singleResult();

        //如果任务对象为空,则流程执行结束
        if (task != null) {
            System.out.println("taskID:"+task.getId()+",name:"+task.getName());
        } else {
            System.out.println("任务执行完毕");
        }
    }
    /**
     * 提交任务(年纪主任)
     */
    @Test
    public void completeTask(){
        taskService.complete("5008");
    }
    //请假排他网关

}
