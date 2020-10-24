package com.lpl.activiti;

import org.activiti.engine.*;
import org.activiti.engine.impl.db.DbSchemaCreate;
import org.junit.Test;

public class process {
    //使用配置文件
    @Test
    public void test2(){
        //1.创建一个流程引擎配置对象D:\project\workspace\activiti-spring\springactiviti\src\main\resources\spring\activiti.cfg.xml
//        String resource="activiti.cfg.xml";
        String resource=process.class.getClassLoader().getResource("activiti.cfg.xml").getPath();
        String beanName="datasource";
        ProcessEngineConfiguration configuration=  ProcessEngineConfiguration.createProcessEngineConfigurationFromResource(resource,beanName);

        //创建一个流程引擎对象，在创建流程引擎对象时会自动建表
        ProcessEngine engine= configuration.buildProcessEngine();
    }
//不使用配置文件创建表
    @Test
    public void test1(){
        //1.创建一个流程引擎配置对象
        ProcessEngineConfiguration configuration=  ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();
        //设置数据源
        configuration.setJdbcDriver("com.mysql.jdbc.Driver");
        configuration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti_demo");
        configuration.setJdbcUsername("root");
        configuration.setJdbcPassword("root");

        //设置自动建表
        configuration.setDatabaseSchema("true");
//        DbSchemaCreate
        //创建一个流程引擎对象，在创建流程引擎对象时会自动建表
        ProcessEngine engine= configuration.buildProcessEngine();
    }
    //执行 DbSchemaCreate 类的main方法默认配置文件创建表
    public static void main(String[] args) {
        ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault().setDatabaseSchemaUpdate("create").buildProcessEngine();
//        引擎API是与Activiti打交道的最常用方式。 我们从ProcessEngine开始，
        //        ProcessEngine和服务类都是线程安全的。 你可以在整个服务器中仅保持它们的一个引用就可以了。
//        会在第一次调用时 初始化并创建一个流程引擎，以后再调用就会返回相同的流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        所有服务都是无状态的。这意味着可以在多节点集群环境下运行Activiti，每个节点都指向同一个数据库， 不用担心哪个机器实际执行前端的调用。 无论在哪里执行服务都没有问题
        RuntimeService runtimeService = processEngine.getRuntimeService();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        TaskService taskService = processEngine.getTaskService();
        ManagementService managementService = processEngine.getManagementService();
        IdentityService identityService = processEngine.getIdentityService();
        HistoryService historyService = processEngine.getHistoryService();
        FormService formService = processEngine.getFormService();
    }
//    Activiti的表都以ACT_开头。 第二部分是表示表的用途的两个字母标识。 用途也和服务的API对应。
//
//    ACT_RE_*: 'RE'表示repository。 这个前缀的表包含了流程定义和流程静态资源 （图片，规则，等等）。
//
//    ACT_RU_*: 'RU'表示runtime。 这些运行时的表，包含流程实例，任务，变量，异步任务，等运行中的数据。 Activiti只在流程实例执行过程中保存这些数据， 在流程结束时就会删除这些记录。 这样运行时表可以一直很小速度很快。
//
//    ACT_ID_*: 'ID'表示identity。 这些表包含身份信息，比如用户，组等等。
//
//    ACT_HI_*: 'HI'表示history。 这些表包含历史数据，比如历史流程实例， 变量，任务等等。
//
//    ACT_GE_*: 通用数据， 用于不同场景下
}
